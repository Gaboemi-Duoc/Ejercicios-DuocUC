-- Paquete completo para poblar las tablas MEDICO_SERVICIO_COMUNIDAD, PAGO_MOROSO e INFO_MEDICO_SII
CREATE OR REPLACE PACKAGE pkg_pobla_tablas_clinica AS
    -- Excepciones personalizadas
    e_error_procesamiento EXCEPTION;
    PRAGMA EXCEPTION_INIT(e_error_procesamiento, -20001);
    
    -- Procedimiento principal que orquesta el poblamiento
    PROCEDURE poblar_tablas_adicionales;
    
    -- Procedimientos individuales para cada tabla
    PROCEDURE poblar_medico_servicio_comunidad;
    PROCEDURE poblar_pago_moroso;
    PROCEDURE poblar_info_medico_sii;
    
    -- Funciones auxiliares
    FUNCTION calcular_dias_morosidad(p_fecha_venc DATE, p_fecha_pago DATE) RETURN NUMBER;
    FUNCTION calcular_multa_morosidad(p_dias_morosidad NUMBER, p_monto_original NUMBER) RETURN NUMBER;
    FUNCTION obtener_porcentaje_asignacion(p_atenciones NUMBER) RETURN NUMBER;
    FUNCTION calcular_bono_especial(p_sueldo_base NUMBER, p_atenciones NUMBER) RETURN NUMBER;
    
END pkg_pobla_tablas_clinica;
/

CREATE OR REPLACE PACKAGE BODY pkg_pobla_tablas_clinica AS

    -- Función para calcular días de morosidad
    FUNCTION calcular_dias_morosidad(p_fecha_venc DATE, p_fecha_pago DATE) 
    RETURN NUMBER IS
        v_dias NUMBER := 0;
    BEGIN
        IF p_fecha_pago IS NULL THEN
            v_dias := TRUNC(SYSDATE) - p_fecha_venc;
        ELSE
            v_dias := p_fecha_pago - p_fecha_venc;
        END IF;
        
        RETURN GREATEST(v_dias, 0);
    EXCEPTION
        WHEN OTHERS THEN
            RETURN 0;
    END calcular_dias_morosidad;

    -- Función para calcular multa por morosidad
    FUNCTION calcular_multa_morosidad(p_dias_morosidad NUMBER, p_monto_original NUMBER) 
    RETURN NUMBER IS
        v_porcentaje_multa NUMBER;
        v_multa NUMBER;
    BEGIN
        IF p_dias_morosidad <= 0 THEN
            RETURN 0;
        ELSIF p_dias_morosidad BETWEEN 1 AND 30 THEN
            v_porcentaje_multa := 0.05; -- 5%
        ELSIF p_dias_morosidad BETWEEN 31 AND 60 THEN
            v_porcentaje_multa := 0.10; -- 10%
        ELSE
            v_porcentaje_multa := 0.15; -- 15%
        END IF;
        
        v_multa := p_monto_original * v_porcentaje_multa;
        RETURN ROUND(v_multa, 0);
    EXCEPTION
        WHEN OTHERS THEN
            RETURN 0;
    END calcular_multa_morosidad;

    -- Función para obtener porcentaje de asignación según tramo de atenciones
    FUNCTION obtener_porcentaje_asignacion(p_atenciones NUMBER) 
    RETURN NUMBER IS
        v_porcentaje NUMBER := 0;
    BEGIN
        SELECT porc_asig 
        INTO v_porcentaje
        FROM tramo_asig_atmed 
        WHERE p_atenciones BETWEEN tramo_inf_atm AND tramo_sup_atm
        AND ROWNUM = 1;
        
        RETURN v_porcentaje;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
        WHEN OTHERS THEN
            RETURN 0;
    END obtener_porcentaje_asignacion;

    -- Función para calcular bono especial según atenciones realizadas
    FUNCTION calcular_bono_especial(p_sueldo_base NUMBER, p_atenciones NUMBER) 
    RETURN NUMBER IS
        v_porcentaje_bono NUMBER;
        v_bono NUMBER;
    BEGIN
        v_porcentaje_bono := obtener_porcentaje_asignacion(p_atenciones);
        v_bono := (p_sueldo_base * 12) * (v_porcentaje_bono / 100);
        
        RETURN ROUND(v_bono, 0);
    EXCEPTION
        WHEN OTHERS THEN
            RETURN 0;
    END calcular_bono_especial;

    -- Procedimiento para poblar MEDICO_SERVICIO_COMUNIDAD
    PROCEDURE poblar_medico_servicio_comunidad IS
        -- Cursor con parámetro para médicos con más de X atenciones
        CURSOR c_medicos_servicio(p_min_atenciones NUMBER) IS
            SELECT m.med_run, 
                   m.dv_run,
                   m.pnombre || ' ' || m.snombre || ' ' || m.apaterno || ' ' || m.amaterno as nombre_completo,
                   u.nombre as unidad_trabajo,
                   COUNT(a.ate_id) as total_atenciones
            FROM medico m
            JOIN unidad u ON m.uni_id = u.uni_id
            LEFT JOIN atencion a ON m.med_run = a.med_run
            WHERE EXTRACT(YEAR FROM a.fecha_atencion) = EXTRACT(YEAR FROM SYSDATE) - 1
            GROUP BY m.med_run, m.dv_run, m.pnombre, m.snombre, m.apaterno, m.amaterno, u.nombre
            HAVING COUNT(a.ate_id) >= p_min_atenciones;
        
        v_correo_institucional VARCHAR2(25);
        v_destinacion VARCHAR2(50);
        v_contador NUMBER := 0;
        
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Iniciando poblamiento de MEDICO_SERVICIO_COMUNIDAD...');
        
        -- Recorrer cursor con parámetro (mínimo 5 atenciones)
        FOR reg_medico IN c_medicos_servicio(5) LOOP
            -- Generar correo institucional
            v_correo_institucional := LOWER(SUBSTR(reg_medico.nombre_completo, 1, 1)) || 
                                     LOWER(reg_medico.apaterno) || 
                                     '@clinica.cl';
            
            -- Asignar destinación según la unidad
            IF reg_medico.unidad_trabajo LIKE '%URGENCIA%' THEN
                v_destinacion := 'Servicio de Urgencias';
            ELSIF reg_medico.unidad_trabajo LIKE '%CIRUGÍA%' THEN
                v_destinacion := 'Servicio Quirúrgico';
            ELSIF reg_medico.unidad_trabajo LIKE '%PSIQUIATRÍA%' THEN
                v_destinacion := 'Servicio de Salud Mental';
            ELSE
                v_destinacion := 'Servicio General';
            END IF;
            
            -- Insertar registro
            INSERT INTO medico_servicio_comunidad (
                unidad, run_medico, nombre_medico, correo_institucional, 
                total_aten_medicas, destinacion
            ) VALUES (
                reg_medico.unidad_trabajo,
                reg_medico.med_run || '-' || reg_medico.dv_run,
                reg_medico.nombre_completo,
                v_correo_institucional,
                reg_medico.total_atenciones,
                v_destinacion
            );
            
            v_contador := v_contador + 1;
        END LOOP;
        
        DBMS_OUTPUT.PUT_LINE('Registros insertados en MEDICO_SERVICIO_COMUNIDAD: ' || v_contador);
        COMMIT;
        
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error en poblar_medico_servicio_comunidad: ' || SQLERRM);
            ROLLBACK;
            RAISE e_error_procesamiento;
    END poblar_medico_servicio_comunidad;

    -- Procedimiento para poblar PAGO_MOROSO
    PROCEDURE poblar_pago_moroso IS
        -- Cursor para atenciones con pagos morosos
        CURSOR c_atenciones_morosas IS
            SELECT a.ate_id,
                   p.pac_run,
                   p.dv_run,
                   p.pnombre || ' ' || p.snombre || ' ' || p.apaterno || ' ' || p.amaterno as nombre_paciente,
                   a.fecha_atencion,
                   pa.fecha_venc_pago,
                   pa.fecha_pago,
                   pa.monto_atencion,
                   e.nombre as especialidad
            FROM atencion a
            JOIN paciente p ON a.pac_run = p.pac_run
            JOIN pago_atencion pa ON a.ate_id = pa.ate_id
            JOIN especialidad e ON a.esp_id = e.esp_id
            WHERE (pa.fecha_pago IS NULL OR pa.fecha_pago > pa.fecha_venc_pago)
            AND pa.fecha_venc_pago < SYSDATE;
        
        v_dias_morosidad NUMBER;
        v_monto_multa NUMBER;
        v_contador NUMBER := 0;
        
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Iniciando poblamiento de PAGO_MOROSO...');
        
        -- Recorrer cursor sin parámetros
        FOR reg_moroso IN c_atenciones_morosas LOOP
            -- Calcular días de morosidad usando función
            v_dias_morosidad := calcular_dias_morosidad(
                reg_moroso.fecha_venc_pago, 
                reg_moroso.fecha_pago
            );
            
            -- Calcular multa usando función
            v_monto_multa := calcular_multa_morosidad(
                v_dias_morosidad, 
                reg_moroso.monto_atencion
            );
            
            -- Insertar registro solo si hay morosidad
            IF v_dias_morosidad > 0 THEN
                INSERT INTO pago_moroso (
                    pac_run, pac_dv_run, pac_nombre, ate_id,
                    fecha_venc_pago, fecha_pago, dias_morosidad,
                    especialidad_atencion, monto_multa
                ) VALUES (
                    reg_moroso.pac_run,
                    reg_moroso.dv_run,
                    reg_moroso.nombre_paciente,
                    reg_moroso.ate_id,
                    reg_moroso.fecha_venc_pago,
                    reg_moroso.fecha_pago,
                    v_dias_morosidad,
                    reg_moroso.especialidad,
                    v_monto_multa
                );
                
                v_contador := v_contador + 1;
            END IF;
        END LOOP;
        
        DBMS_OUTPUT.PUT_LINE('Registros insertados en PAGO_MOROSO: ' || v_contador);
        COMMIT;
        
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error en poblar_pago_moroso: ' || SQLERRM);
            ROLLBACK;
            RAISE e_error_procesamiento;
    END poblar_pago_moroso;

    -- Procedimiento para poblar INFO_MEDICO_SII
    PROCEDURE poblar_info_medico_sii IS
        -- Cursor con parámetro para año tributario
        CURSOR c_medicos_tributarios(p_anno NUMBER) IS
            SELECT m.med_run,
                   m.dv_run,
                   m.pnombre || ' ' || m.snombre || ' ' || m.apaterno || ' ' || m.amaterno as nombre_completo,
                   c.nombre as cargo,
                   m.sueldo_base,
                   COUNT(a.ate_id) as total_atenciones
            FROM medico m
            JOIN cargo c ON m.car_id = c.car_id
            LEFT JOIN atencion a ON m.med_run = a.med_run 
                AND EXTRACT(YEAR FROM a.fecha_atencion) = p_anno
            GROUP BY m.med_run, m.dv_run, m.pnombre, m.snombre, m.apaterno, m.amaterno, c.nombre, m.sueldo_base;
        
        v_anno_tributario NUMBER := EXTRACT(YEAR FROM SYSDATE) - 1;
        v_meses_trabajados NUMBER;
        v_sueldo_base_anual NUMBER;
        v_bonif_especial NUMBER;
        v_sueldo_bruto_anual NUMBER;
        v_renta_imponible NUMBER;
        v_contador NUMBER := 0;
        
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Iniciando poblamiento de INFO_MEDICO_SII para el año: ' || v_anno_tributario);
        
        -- Recorrer cursor con parámetro de año
        FOR reg_medico IN c_medicos_tributarios(v_anno_tributario) LOOP
            -- Calcular valores tributarios
            v_meses_trabajados := 12; -- Asumimos 12 meses trabajados
            v_sueldo_base_anual := reg_medico.sueldo_base * 12;
            
            -- Calcular bono especial usando función
            v_bonif_especial := calcular_bono_especial(
                reg_medico.sueldo_base, 
                reg_medico.total_atenciones
            );
            
            v_sueldo_bruto_anual := v_sueldo_base_anual + v_bonif_especial;
            
            -- Calcular renta imponible (suponemos 80% del bruto)
            v_renta_imponible := v_sueldo_bruto_anual * 0.8;
            
            -- Insertar registro
            INSERT INTO info_medico_sii (
                anno_tributario, numrun, dv_run, nombre_completo, cargo,
                meses_trabajados, sueldo_base_mensual, sueldo_base_anual,
                bonif_especial, sueldo_bruto_anual, renta_imponible_anual
            ) VALUES (
                v_anno_tributario,
                reg_medico.med_run,
                reg_medico.dv_run,
                reg_medico.nombre_completo,
                reg_medico.cargo,
                v_meses_trabajados,
                reg_medico.sueldo_base,
                v_sueldo_base_anual,
                v_bonif_especial,
                v_sueldo_bruto_anual,
                v_renta_imponible
            );
            
            v_contador := v_contador + 1;
        END LOOP;
        
        DBMS_OUTPUT.PUT_LINE('Registros insertados en INFO_MEDICO_SII: ' || v_contador);
        COMMIT;
        
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error en poblar_info_medico_sii: ' || SQLERRM);
            ROLLBACK;
            RAISE e_error_procesamiento;
    END poblar_info_medico_sii;

    -- Procedimiento principal que orquesta todo el proceso
    PROCEDURE poblar_tablas_adicionales IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE('=== INICIANDO POBLAMIENTO DE TABLAS ADICIONALES ===');
        
        -- Poblar MEDICO_SERVICIO_COMUNIDAD
        poblar_medico_servicio_comunidad;
        
        -- Poblar PAGO_MOROSO
        poblar_pago_moroso;
        
        -- Poblar INFO_MEDICO_SII
        poblar_info_medico_sii;
        
        DBMS_OUTPUT.PUT_LINE('=== POBLAMIENTO COMPLETADO EXITOSAMENTE ===');
        
    EXCEPTION
        WHEN e_error_procesamiento THEN
            DBMS_OUTPUT.PUT_LINE('Error durante el proceso de poblamiento.');
            ROLLBACK;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error inesperado: ' || SQLERRM);
            ROLLBACK;
    END poblar_tablas_adicionales;

END pkg_pobla_tablas_clinica;
/

-- Bloque anónimo para ejecutar el poblamiento
DECLARE
BEGIN
    DBMS_OUTPUT.PUT_LINE('Ejecutando proceso de poblamiento...');
    pkg_pobla_tablas_clinica.poblar_tablas_adicionales;
    DBMS_OUTPUT.PUT_LINE('Proceso finalizado.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en la ejecución: ' || SQLERRM);
END;
/
