-- PRUEBA FUNCION calcular_dias_morosidad (funcionando)
SET SERVEROUTPUT ON;
DECLARE
    v_dias NUMBER;
BEGIN
    v_dias := pkg_pobla_tablas_clinica.calcular_dias_morosidad(
                  TO_DATE('2025-09-10','YYYY-MM-DD'),
                  TO_DATE('2025-09-25','YYYY-MM-DD')
              );
    DBMS_OUTPUT.PUT_LINE('Días de morosidad: ' || v_dias);
END;
/

-- PRUEBA FUNCION calcular_multa_morosidad (funciona)
SET SERVEROUTPUT ON;
DECLARE
    v_multa NUMBER;
BEGIN
    v_multa := pkg_pobla_tablas_clinica.calcular_multa_morosidad(45, 100000);
    DBMS_OUTPUT.PUT_LINE('Multa calculada: ' || v_multa);
END;
/

-- PRUEBA FUNCION obtener_porcentaje_asignacion (funciona)
SET SERVEROUTPUT ON;
DECLARE
    v_porcentaje NUMBER;
BEGIN
    v_porcentaje := pkg_pobla_tablas_clinica.obtener_porcentaje_asignacion(12);
    DBMS_OUTPUT.PUT_LINE('Porcentaje asignación: ' || v_porcentaje);
END;
/

--PRUEBA FUNCION calcular_bono_especial (funciona)
SET SERVEROUTPUT ON;
DECLARE
    v_bono NUMBER;
BEGIN
    v_bono := pkg_pobla_tablas_clinica.calcular_bono_especial(1200000, 15);
    DBMS_OUTPUT.PUT_LINE('Bono especial anual: ' || v_bono);
END;
/

-- PRUEBA FUNCION poblar_medico_servicio_comunidad(funcionan)
SET SERVEROUTPUT ON;
BEGIN
    pkg_pobla_tablas_clinica.poblar_medico_servicio_comunidad(5);
END;
/
SELECT * FROM medico_servicio_comunidad;

-- PRUEBA FUNCION poblar_pago_moroso(funciona)
SET SERVEROUTPUT ON;
BEGIN
    pkg_pobla_tablas_clinica.poblar_pago_moroso;
END;
/
SELECT * FROM pago_moroso;

-- PRUEBA FUNCION poblar_info_medico_sii(si funciona)
SET SERVEROUTPUT ON;
BEGIN
    pkg_pobla_tablas_clinica.poblar_info_medico_sii(2024);
END;
/
SELECT * FROM info_medico_sii;

-- PRUEBA FUNCION poblar_tablas_adicionales(funciona)
SET SERVEROUTPUT ON;
BEGIN
    pkg_pobla_tablas_clinica.poblar_tablas_adicionales;
END;
/

-- PRUEBA TRIGGER trg_valida_correo_medico (funciona)
-- esta consulta es en el caso correcto
INSERT INTO medico_servicio_comunidad (unidad, run_medico, nombre_medico, total_aten_medicas, destinacion)
VALUES ('U. Urgencias', '12345678-9', 'Juan Soto', 10, 'Servicio de Urgencias');
/
SELECT correo_institucional FROM medico_servicio_comunidad
WHERE run_medico = '12345678-9';

-- esta consulta es en el caso incorrecto
INSERT INTO medico_servicio_comunidad (unidad, run_medico, nombre_medico, correo_institucional, total_aten_medicas, destinacion)
VALUES ('U. Urgencias', '87654321-0', 'Pedro Muñoz', 'pedro@gmail.com', 12, 'Servicio General');
/

-- PRUEBA TRIGGER trg_calc_multa_pago_moroso(funciona)
INSERT INTO pago_moroso (pac_run, pac_dv_run, pac_nombre, ate_id, fecha_venc_pago, fecha_pago, especialidad_atencion, monto_multa)
VALUES (11111111, '1', 'Carlos Pérez', 10, TO_DATE('2023-09-01','YYYY-MM-DD'), TO_DATE('2023-09-25','YYYY-MM-DD'), 'Cardiología', NULL);
/
SELECT dias_morosidad, monto_multa FROM pago_moroso
WHERE pac_run = 5000746;

-- PRUEBA TRIGGER trg_hist_sueldo_med
SELECT med_run, sueldo_base
FROM medico
WHERE med_run = 3126425;

UPDATE medico
   SET sueldo_base = sueldo_base + 100000
 WHERE med_run = 3126425;

COMMIT;

SELECT *
FROM historico_sueldo_medico
WHERE med_run = 3126425
ORDER BY fecha_cambio DESC;










