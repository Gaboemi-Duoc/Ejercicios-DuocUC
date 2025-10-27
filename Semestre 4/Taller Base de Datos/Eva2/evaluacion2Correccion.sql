CREATE OR REPLACE PACKAGE BODY pkg_pobla_tablas_clinica AS
    FUNCTION priv_calcular_dias_morosidad(p_fecha_venc DATE, p_fecha_pago DATE)
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
    END priv_calcular_dias_morosidad;

    -- Función privada para calcular multa por morosidad
    FUNCTION priv_calcular_multa_morosidad(p_dias_morosidad NUMBER, p_monto_original NUMBER)
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
            v_porcentaje_multa := 0.05;
        ELSIF p_dias_morosidad BETWEEN 31 AND 60 THEN
            v_porcentaje_multa := 0.10;
        ELSE
            v_porcentaje_multa := 0.15;
        END IF;

        v_multa := p_monto_original * v_porcentaje_multa;
        RETURN ROUND(v_multa, 0);
    END priv_calcular_multa_morosidad;

    -- Función privada para obtener porcentaje de asignación
    FUNCTION priv_obtener_porcentaje_asignacion(p_atenciones NUMBER)
    RETURN NUMBER IS
        v_porcentaje NUMBER := 0;
    BEGIN
        SELECT porc_asig
        INTO v_porcentaje
        FROM tramo_asig_atmed
        WHERE p_atenciones BETWEEN tramo_inf_atm AND tramo_sup_atm
        AND ROWNUM = 1;

        RETURN NVL(v_porcentaje, 0);
    END priv_obtener_porcentaje_asignacion;

    -- Función privada para calcular bono especial
    FUNCTION priv_calcular_bono_especial(p_sueldo_base NUMBER, p_atenciones NUMBER)
    RETURN NUMBER IS
        v_porcentaje_bono NUMBER := 0;
        v_bono NUMBER := 0;
    BEGIN
        v_porcentaje_bono := priv_obtener_porcentaje_asignacion(NVL(p_atenciones,0));
        v_bono := NVL(p_sueldo_base,0) * 12 * (v_porcentaje_bono / 100);
        RETURN ROUND(v_bono, 0);
    END priv_calcular_bono_especial;

    -- Función privada sin parámetros
    FUNCTION priv_obtener_anno_anterior
    RETURN NUMBER IS
    BEGIN
        RETURN EXTRACT(YEAR FROM SYSDATE) - 1;
    END priv_obtener_anno_anterior;

    -- Función privada para generar correo institucional
    FUNCTION priv_generar_correo(p_pnombre VARCHAR2, p_apaterno VARCHAR2)
    RETURN VARCHAR2 IS
        v_correo VARCHAR2(25);
    BEGIN
        v_correo := LOWER(SUBSTR(p_pnombre, 1, 1) || p_apaterno) || '@clinica.cl';
        RETURN SUBSTR(v_correo, 1, 25);
    END priv_generar_correo;

    -- Función privada para construir nombre completo
    FUNCTION priv_construir_nombre_completo(p_pnombre VARCHAR2, p_snombre VARCHAR2, p_apaterno VARCHAR2, p_amaterno VARCHAR2)
    RETURN VARCHAR2 IS
    BEGIN
        RETURN RTRIM(LTRIM(NVL(p_pnombre,'') || ' ' || NVL(p_snombre,'') || ' ' || NVL(p_apaterno,'') || ' ' || NVL(p_amaterno,'')));
    END priv_construir_nombre_completo;

    -- Función privada para formatear RUN
    FUNCTION priv_formatear_run(p_run NUMBER, p_dv VARCHAR2)
    RETURN VARCHAR2 IS
    BEGIN
        RETURN TO_CHAR(p_run) || '-' || NVL(p_dv,' ');
    END priv_formatear_run;

    -- Función privada para determinar destinación
    FUNCTION priv_determinar_destinacion(p_unidad_trabajo VARCHAR2)
    RETURN VARCHAR2 IS
    BEGIN
        IF UPPER(p_unidad_trabajo) LIKE '%URGEN%' THEN
            RETURN 'Servicio de Urgencias';
        ELSIF UPPER(p_unidad_trabajo) LIKE '%CIRUG%' THEN
            RETURN 'Servicio Quirúrgico';
        ELSIF UPPER(p_unidad_trabajo) LIKE '%PSIQUIAT%' OR UPPER(p_unidad_trabajo) LIKE '%SALUD MENTAL%' THEN
            RETURN 'Servicio de Salud Mental';
        ELSE
            RETURN 'Servicio General';
        END IF;
    END priv_determinar_destinacion;

    -- PROCEDIMIENTOS PRIVADOS

    -- Procedimiento privado sin parámetros para limpiar tabla médico servicio comunidad
    PROCEDURE priv_limpiar_medico_servicio IS
    BEGIN
        DELETE FROM medico_servicio_comunidad;
    END priv_limpiar_medico_servicio;

    -- Procedimiento privado sin parámetros para limpiar tabla pago moroso
    PROCEDURE priv_limpiar_pago_moroso IS
    BEGIN
        DELETE FROM pago_moroso;
    END priv_limpiar_pago_moroso;

    -- Procedimiento privado sin parámetros para limpiar tabla info médico SII
    PROCEDURE priv_limpiar_info_medico_sii IS
    BEGIN
        DELETE FROM info_medico_sii;
    END priv_limpiar_info_medico_sii;

    -- PROCEDIMIENTOS PÚBLICOS

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
        v_anno NUMBER := priv_obtener_anno_anterior;
        v_nombre_completo VARCHAR2(200);
        v_run_medico VARCHAR2(30);
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Iniciando poblamiento de MEDICO_SERVICIO_COMUNIDAD...');
        priv_limpiar_medico_servicio;

        FOR r IN c_medicos_servicio(p_min_atenciones, v_anno) LOOP
            v_nombre_completo := priv_construir_nombre_completo(r.pnombre, r.snombre, r.apaterno, r.amaterno);
            v_run_medico := priv_formatear_run(r.med_run, r.dv_run);
            v_correo_institucional := priv_generar_correo(r.pnombre, r.apaterno);
            v_destinacion := priv_determinar_destinacion(r.unidad_trabajo);

            INSERT INTO medico_servicio_comunidad 
                (unidad, run_medico, nombre_medico, correo_institucional, total_aten_medicas, destinacion)
            VALUES 
                (r.unidad_trabajo, v_run_medico, v_nombre_completo, v_correo_institucional, r.total_atenciones, v_destinacion);

            v_contador := v_contador + 1;
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('Registros procesados en MEDICO_SERVICIO_COMUNIDAD: ' || v_contador);
        COMMIT;
    END poblar_medico_servicio_comunidad;

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
        priv_limpiar_pago_moroso;

        FOR r IN c_atenciones_morosas LOOP
            v_dias_morosidad := priv_calcular_dias_morosidad(r.fecha_venc_pago, r.fecha_pago);
            v_monto_multa := priv_calcular_multa_morosidad(v_dias_morosidad, NVL(r.monto_atencion,0));

            IF v_dias_morosidad > 0 THEN
                INSERT INTO pago_moroso 
                    (pac_run, pac_dv_run, pac_nombre, ate_id, fecha_venc_pago, fecha_pago, dias_morosidad, especialidad_atencion, monto_multa)
                VALUES 
                    (r.pac_run, r.dv_run, r.nombre_paciente, r.ate_id, r.fecha_venc_pago, r.fecha_pago, v_dias_morosidad, r.especialidad, v_monto_multa);

                v_contador := v_contador + 1;
            END IF;
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('Registros procesados en PAGO_MOROSO: ' || v_contador);
        COMMIT;
    END poblar_pago_moroso;

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

        v_anno_tributario NUMBER := NVL(p_anno, priv_obtener_anno_anterior);
        v_meses_trabajados NUMBER := 12;
        v_sueldo_base_anual NUMBER := 0;
        v_bonif_especial NUMBER := 0;
        v_sueldo_bruto_anual NUMBER := 0;
        v_renta_imponible NUMBER := 0;
        v_contador NUMBER := 0;
        v_nombre_completo VARCHAR2(200);
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Iniciando poblamiento de INFO_MEDICO_SII para el año: ' || v_anno_tributario);
        priv_limpiar_info_medico_sii;

        FOR r IN c_medicos_tributarios(v_anno_tributario) LOOP
            v_nombre_completo := priv_construir_nombre_completo(r.pnombre, r.snombre, r.apaterno, r.amaterno);
            v_sueldo_base_anual := NVL(r.sueldo_base,0) * 12;
            v_bonif_especial := priv_calcular_bono_especial(NVL(r.sueldo_base,0), NVL(r.total_atenciones,0));
            v_sueldo_bruto_anual := v_sueldo_base_anual + v_bonif_especial;
            v_renta_imponible := v_sueldo_bruto_anual * 0.8;

            INSERT INTO info_medico_sii 
                (anno_tributario, numrun, dv_run, nombre_completo, cargo, meses_trabajados, sueldo_base_mensual, sueldo_base_anual, bonif_especial, sueldo_bruto_anual, renta_imponible_anual)
            VALUES 
                (v_anno_tributario, r.med_run, r.dv_run, v_nombre_completo, r.cargo, v_meses_trabajados, NVL(r.sueldo_base,0), v_sueldo_base_anual, v_bonif_especial, v_sueldo_bruto_anual, v_renta_imponible);

            v_contador := v_contador + 1;
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('Registros procesados en INFO_MEDICO_SII: ' || v_contador);
        COMMIT;
    END poblar_info_medico_sii;

    -- Procedimiento principal que orquesta todo el proceso
    PROCEDURE poblar_tablas_adicionales IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE('=== INICIANDO POBLAMIENTO DE TABLAS ADICIONALES ===');

        poblar_medico_servicio_comunidad(5);
        poblar_pago_moroso;
        poblar_info_medico_sii(2024);

        DBMS_OUTPUT.PUT_LINE('=== POBLAMIENTO COMPLETADO EXITOSAMENTE ===');
    END poblar_tablas_adicionales;

END pkg_pobla_tablas_clinica;
/

-- Bloque anónimo para ejecutar el poblamiento
DECLARE
BEGIN
    DBMS_OUTPUT.PUT_LINE('Ejecutando proceso de poblamiento...');
    pkg_pobla_tablas_clinica.poblar_tablas_adicionales;
    DBMS_OUTPUT.PUT_LINE('Proceso finalizado.');
END;
/