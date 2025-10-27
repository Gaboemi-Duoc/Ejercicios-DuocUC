-- SPEC
CREATE OR REPLACE PACKAGE pkg_pobla_tablas_clinica AS
    -- Excepciones personalizadas
    e_error_procesamiento EXCEPTION;
    PRAGMA EXCEPTION_INIT(e_error_procesamiento, -20001);

    -- Procedimiento principal que orquesta el poblamiento
    PROCEDURE poblar_tablas_adicionales;

    -- Procedimientos individuales para cada tabla
    PROCEDURE poblar_medico_servicio_comunidad(p_min_atenciones NUMBER := 5);
    PROCEDURE poblar_pago_moroso;
    PROCEDURE poblar_info_medico_sii(p_anno NUMBER := NULL);

    -- Funciones auxiliares
    FUNCTION calcular_dias_morosidad(p_fecha_venc DATE, p_fecha_pago DATE) RETURN NUMBER;
    FUNCTION calcular_multa_morosidad(p_dias_morosidad NUMBER, p_monto_original NUMBER) RETURN NUMBER;
    FUNCTION obtener_porcentaje_asignacion(p_atenciones NUMBER) RETURN NUMBER;
    FUNCTION calcular_bono_especial(p_sueldo_base NUMBER, p_atenciones NUMBER) RETURN NUMBER;

END pkg_pobla_tablas_clinica;
/
-- BODY
CREATE OR REPLACE PACKAGE BODY pkg_pobla_tablas_clinica AS

    -- Función para calcular días de morosidad
    FUNCTION calcular_dias_morosidad(p_fecha_venc DATE, p_fecha_pago DATE)
    RETURN NUMBER IS
        v_dias NUMBER := 0;
    BEGIN
        IF p_fecha_venc IS NULL THEN
            RETURN 0;
        END IF;

        IF p_fecha_pago IS NULL THEN
            v_dias := TRUNC(SYSDATE) - TRUNC(p_fecha_venc);
        ELSE
            v_dias := TRUNC(p_fecha_pago) - TRUNC(p_fecha_venc);
        END IF;

        RETURN GREATEST(v_dias, 0);
    EXCEPTION
        WHEN OTHERS THEN
            RETURN 0;
    END calcular_dias_morosidad;

    -- Función para calcular multa por morosidad
    FUNCTION calcular_multa_morosidad(p_dias_morosidad NUMBER, p_monto_original NUMBER)
    RETURN NUMBER IS
        v_porcentaje_multa NUMBER := 0;
        v_multa NUMBER := 0;
    BEGIN
        IF p_monto_original IS NULL OR p_monto_original <= 0 THEN
            RETURN 0;
        END IF;

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

        RETURN NVL(v_porcentaje, 0);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
        WHEN OTHERS THEN
            RETURN 0;
    END obtener_porcentaje_asignacion;

    -- Función para calcular bono especial según atenciones realizadas
    FUNCTION calcular_bono_especial(p_sueldo_base NUMBER, p_atenciones NUMBER)
    RETURN NUMBER IS
        v_porcentaje_bono NUMBER := 0;
        v_bono NUMBER := 0;
    BEGIN
        v_porcentaje_bono := obtener_porcentaje_asignacion(NVL(p_atenciones,0));
        v_bono := NVL(p_sueldo_base,0) * 12 * (v_porcentaje_bono / 100);

        RETURN ROUND(v_bono, 0);
    EXCEPTION
        WHEN OTHERS THEN
            RETURN 0;
    END calcular_bono_especial;


    ---------------------------------------------------------------------
    -- poblar_medico_servicio_comunidad
    -- ahora: cursor con LEFT JOIN correcto (condición de año en ON),
    --         MERGE para evitar duplicados,
    --         generación segura de correo y truncado a 25 chars.
    ---------------------------------------------------------------------
    PROCEDURE poblar_medico_servicio_comunidad(p_min_atenciones NUMBER := 5) IS
        CURSOR c_medicos_servicio(p_min_atenciones NUMBER, p_anno NUMBER) IS
            SELECT m.med_run,
                   m.dv_run,
                   m.pnombre,
                   m.snombre,
                   m.apaterno,
                   m.amaterno,
                   u.nombre as unidad_trabajo,
                   COUNT(a.ate_id) as total_atenciones
            FROM medico m
            JOIN unidad u ON m.uni_id = u.uni_id
            LEFT JOIN atencion a
              ON m.med_run = a.med_run
              AND EXTRACT(YEAR FROM a.fecha_atencion) = p_anno
            GROUP BY m.med_run, m.dv_run, m.pnombre, m.snombre, m.apaterno, m.amaterno, u.nombre
            HAVING COUNT(a.ate_id) >= p_min_atenciones;

        v_correo_institucional VARCHAR2(25);
        v_destinacion VARCHAR2(50);
        v_contador NUMBER := 0;
        v_anno NUMBER := EXTRACT(YEAR FROM SYSDATE) - 1;
        v_nombre_completo VARCHAR2(200);
        v_run_medico VARCHAR2(30);
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Iniciando poblamiento de MEDICO_SERVICIO_COMUNIDAD...');

        FOR r IN c_medicos_servicio(p_min_atenciones, v_anno) LOOP
            -- nombre completo
            v_nombre_completo := RTRIM(LTRIM(NVL(r.pnombre,'') || ' ' || NVL(r.snombre,'') || ' ' || NVL(r.apaterno,'') || ' ' || NVL(r.amaterno,'')));
            v_run_medico := TO_CHAR(r.med_run) || '-' || NVL(r.dv_run,' ');

            -- Generar correo institucional
            v_correo_institucional := LOWER();
            v_correo_institucional := v_correo_institucional || '@clinica.cl';

            -- Asignar destinación según la unidad (coincidencias tolerantes)
            IF UPPER(r.unidad_trabajo) LIKE '%URGEN%' THEN
                v_destinacion := 'Servicio de Urgencias';
            ELSIF UPPER(r.unidad_trabajo) LIKE '%CIRUG%' THEN
                v_destinacion := 'Servicio Quirúrgico';
            ELSIF UPPER(r.unidad_trabajo) LIKE '%PSIQUIAT%' OR UPPER(r.unidad_trabajo) LIKE '%SALUD MENTAL%' THEN
                v_destinacion := 'Servicio de Salud Mental';
            ELSE
                v_destinacion := 'Servicio General';
            END IF;

            -- MERGE para evitar duplicados (idempotente)
            MERGE INTO medico_servicio_comunidad t
            USING (SELECT r.unidad_trabajo AS unidad,
                          v_run_medico AS run_medico,
                          v_nombre_completo AS nombre_medico,
                          v_correo_institucional AS correo_institucional,
                          r.total_atenciones AS total_aten_medicas,
                          v_destinacion AS destinacion
                   FROM DUAL) src
            ON (t.run_medico = src.run_medico)
            WHEN MATCHED THEN
              UPDATE SET
                t.unidad = src.unidad,
                t.nombre_medico = src.nombre_medico,
                t.correo_institucional = src.correo_institucional,
                t.total_aten_medicas = src.total_aten_medicas,
                t.destinacion = src.destinacion
            WHEN NOT MATCHED THEN
              INSERT (unidad, run_medico, nombre_medico, correo_institucional, total_aten_medicas, destinacion)
              VALUES (src.unidad, src.run_medico, src.nombre_medico, src.correo_institucional, src.total_aten_medicas, src.destinacion);

            v_contador := v_contador + 1;
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('Registros procesados en MEDICO_SERVICIO_COMUNIDAD: ' || v_contador);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error en poblar_medico_servicio_comunidad: ' || SQLERRM);
            ROLLBACK;
            RAISE e_error_procesamiento;
    END poblar_medico_servicio_comunidad;


    ---------------------------------------------------------------------
    -- poblar_pago_moroso
    -- cursor que detecta morosidad; uso de MERGE para evitar duplicados
    ---------------------------------------------------------------------
    PROCEDURE poblar_pago_moroso IS
        CURSOR c_atenciones_morosas IS
            SELECT a.ate_id,
                   p.pac_run,
                   p.dv_run,
                   NVL(p.pnombre,'') || ' ' || NVL(p.snombre,'') || ' ' || NVL(p.apaterno,'') || ' ' || NVL(p.amaterno,'') as nombre_paciente,
                   a.fecha_atencion,
                   pa.fecha_venc_pago,
                   pa.fecha_pago,
                   pa.monto_atencion,
                   e.nombre as especialidad
            FROM atencion a
            JOIN paciente p ON a.pac_run = p.pac_run
            JOIN pago_atencion pa ON a.ate_id = pa.ate_id
            LEFT JOIN especialidad e ON a.esp_id = e.esp_id
            WHERE pa.fecha_venc_pago IS NOT NULL
              AND pa.fecha_venc_pago < TRUNC(SYSDATE)
              AND (pa.fecha_pago IS NULL OR pa.fecha_pago > pa.fecha_venc_pago);

        v_dias_morosidad NUMBER := 0;
        v_monto_multa NUMBER := 0;
        v_contador NUMBER := 0;
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Iniciando poblamiento de PAGO_MOROSO...');

        FOR r IN c_atenciones_morosas LOOP
            v_dias_morosidad := calcular_dias_morosidad(r.fecha_venc_pago, r.fecha_pago);
            v_monto_multa := calcular_multa_morosidad(v_dias_morosidad, NVL(r.monto_atencion,0));

            IF v_dias_morosidad > 0 THEN
                MERGE INTO pago_moroso t
                USING (SELECT r.pac_run AS pac_run,
                              r.dv_run AS pac_dv_run,
                              r.nombre_paciente AS pac_nombre,
                              r.ate_id AS ate_id,
                              r.fecha_venc_pago AS fecha_venc_pago,
                              r.fecha_pago AS fecha_pago,
                              v_dias_morosidad AS dias_morosidad,
                              r.especialidad AS especialidad_atencion,
                              v_monto_multa AS monto_multa
                       FROM DUAL) src
                ON (t.pac_run = src.pac_run AND t.ate_id = src.ate_id)
                WHEN MATCHED THEN
                  UPDATE SET
                    t.fecha_venc_pago = src.fecha_venc_pago,
                    t.fecha_pago = src.fecha_pago,
                    t.dias_morosidad = src.dias_morosidad,
                    t.especialidad_atencion = src.especialidad_atencion,
                    t.monto_multa = src.monto_multa
                WHEN NOT MATCHED THEN
                  INSERT (pac_run, pac_dv_run, pac_nombre, ate_id, fecha_venc_pago, fecha_pago, dias_morosidad, especialidad_atencion, monto_multa)
                  VALUES (src.pac_run, src.pac_dv_run, src.pac_nombre, src.ate_id, src.fecha_venc_pago, src.fecha_pago, src.dias_morosidad, src.especialidad_atencion, src.monto_multa);

                v_contador := v_contador + 1;
            END IF;
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('Registros procesados en PAGO_MOROSO: ' || v_contador);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error en poblar_pago_moroso: ' || SQLERRM);
            ROLLBACK;
            RAISE e_error_procesamiento;
    END poblar_pago_moroso;


    ---------------------------------------------------------------------
    -- poblar_info_medico_sii
    -- permite pasar año; MERGE para evitar duplicados
    ---------------------------------------------------------------------
    PROCEDURE poblar_info_medico_sii(p_anno NUMBER := NULL) IS
        CURSOR c_medicos_tributarios(p_anno NUMBER) IS
            SELECT m.med_run,
                   m.dv_run,
                   m.pnombre,
                   m.snombre,
                   m.apaterno,
                   m.amaterno,
                   c.nombre as cargo,
                   m.sueldo_base,
                   COUNT(a.ate_id) as total_atenciones
            FROM medico m
            JOIN cargo c ON m.car_id = c.car_id
            LEFT JOIN atencion a
              ON m.med_run = a.med_run
              AND (p_anno IS NULL OR EXTRACT(YEAR FROM a.fecha_atencion) = p_anno)
            GROUP BY m.med_run, m.dv_run, m.pnombre, m.snombre, m.apaterno, m.amaterno, c.nombre, m.sueldo_base;

        v_anno_tributario NUMBER := NVL(p_anno, EXTRACT(YEAR FROM SYSDATE) - 1);
        v_meses_trabajados NUMBER := 12;
        v_sueldo_base_anual NUMBER := 0;
        v_bonif_especial NUMBER := 0;
        v_sueldo_bruto_anual NUMBER := 0;
        v_renta_imponible NUMBER := 0;
        v_contador NUMBER := 0;
        v_nombre_completo VARCHAR2(200);
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Iniciando poblamiento de INFO_MEDICO_SII para el año: ' || v_anno_tributario);

        FOR r IN c_medicos_tributarios(v_anno_tributario) LOOP
            v_nombre_completo := RTRIM(LTRIM(NVL(r.pnombre,'') || ' ' || NVL(r.snombre,'') || ' ' || NVL(r.apaterno,'') || ' ' || NVL(r.amaterno,'')));
            v_sueldo_base_anual := NVL(r.sueldo_base,0) * 12;
            v_bonif_especial := calcular_bono_especial(NVL(r.sueldo_base,0), NVL(r.total_atenciones,0));
            v_sueldo_bruto_anual := v_sueldo_base_anual + v_bonif_especial;
            v_renta_imponible := v_sueldo_bruto_anual * 0.8; -- regla de negocio asumida

            MERGE INTO info_medico_sii t
            USING (SELECT v_anno_tributario AS anno_tributario,
                          r.med_run AS numrun,
                          r.dv_run AS dv_run,
                          v_nombre_completo AS nombre_completo,
                          r.cargo AS cargo,
                          v_meses_trabajados AS meses_trabajados,
                          NVL(r.sueldo_base,0) AS sueldo_base_mensual,
                          v_sueldo_base_anual AS sueldo_base_anual,
                          v_bonif_especial AS bonif_especial,
                          v_sueldo_bruto_anual AS sueldo_bruto_anual,
                          v_renta_imponible AS renta_imponible_anual
                   FROM DUAL) src
            ON (t.anno_tributario = src.anno_tributario AND t.numrun = src.numrun)
            WHEN MATCHED THEN
              UPDATE SET
                t.dv_run = src.dv_run,
                t.nombre_completo = src.nombre_completo,
                t.cargo = src.cargo,
                t.meses_trabajados = src.meses_trabajados,
                t.sueldo_base_mensual = src.sueldo_base_mensual,
                t.sueldo_base_anual = src.sueldo_base_anual,
                t.bonif_especial = src.bonif_especial,
                t.sueldo_bruto_anual = src.sueldo_bruto_anual,
                t.renta_imponible_anual = src.renta_imponible_anual
            WHEN NOT MATCHED THEN
              INSERT (anno_tributario, numrun, dv_run, nombre_completo, cargo, meses_trabajados, sueldo_base_mensual, sueldo_base_anual, bonif_especial, sueldo_bruto_anual, renta_imponible_anual)
              VALUES (src.anno_tributario, src.numrun, src.dv_run, src.nombre_completo, src.cargo, src.meses_trabajados, src.sueldo_base_mensual, src.sueldo_base_anual, src.bonif_especial, src.sueldo_bruto_anual, src.renta_imponible_anual);

            v_contador := v_contador + 1;
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('Registros procesados en INFO_MEDICO_SII: ' || v_contador);
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

        -- Poblar MEDICO_SERVICIO_COMUNIDAD (mínimo 5 atenciones por defecto)
        poblar_medico_servicio_comunidad(5);

        -- Poblar PAGO_MOROSO
        poblar_pago_moroso;

        -- Poblar INFO_MEDICO_SII (año por defecto = año anterior)
        poblar_info_medico_sii(NULL);

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
