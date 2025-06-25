-- 2.5.6
-- CASO 1

TRUNCATE TABLE PAGO_MOROSO;

SELECT * FROM PACIENTE;
SELECT * FROM porc_descto_3ra_edad;
SELECT * FROM especialidad;

DECLARE
    CURSOR CUR_PAGO_MOROSO IS 
        SELECT
            pac.pac_run,
            pac.dv_run,
            pac.pnombre || ' ' || pac.snombre || ' ' || pac.apaterno || ' ' || pac.amaterno,
            at.ate_id,
            pagate.fecha_venc_pago,
            pagate.fecha_pago,
            esp.nombre
        FROM atencion at 
            INNER JOIN paciente pac ON(at.pac_run = pac.pac_run)
            INNER JOIN pago_atencion pagate ON (at.ate_id = pagate.ate_id)
            INNER JOIN especialidad esp ON (at.esp_id = esp.esp_id)
        WHERE
            pagate.fecha_pago - pagate.fecha_venc_pago > 0
        ORDER BY pagate.fecha_venc_pago ASC, pac.apaterno ASC;
    V_PAC_RUN NUMBER(8);
    V_PAC_DV_RUN VARCHAR2(1);
    V_PAC_NOMBRE VARCHAR2(50);
    V_ATE_ID NUMBER(3);
    V_FECHA_VEN_PAG DATE;
    V_FECHA_PAG DATE;
    V_DIAS_MOROSIDAD NUMBER(3);
    V_ESP_ATENCION VARCHAR2(30);
    V_MONTO_MULTA NUMBER(6);
BEGIN
    OPEN CUR_PAGO_MOROSO;
    LOOP
        FETCH CUR_PAGO_MOROSO INTO V_PAC_RUN, V_PAC_DV_RUN, V_PAC_NOMBRE, V_ATE_ID, V_FECHA_VEN_PAG, V_FECHA_PAG, V_ESP_ATENCION;
        EXIT WHEN CUR_PAGO_MOROSO%NOTFOUND;
        V_DIAS_MOROSIDAD := V_FECHA_PAG - V_FECHA_VEN_PAG;
        CASE V_ESP_ATENCION
            WHEN 'Cirug�a General' THEN dbms_output.put_line('!!!');
            ELSE dbms_output.put_line('');
        END CASE;
    END LOOP;
    CLOSE CUR_PAGO_MOROSO;
END;