
-- 3.42
-- Caso 1
TRUNCATE TABLE PROY_MOVILIZACION;

var b_ANNO_PROCESO number(4);
exec b_ANNO_PROCESO := 2020;

var b_com_mp_mov_ad number(5);
exec b_com_mp_mov_ad := 20000;
var b_com_cc_mov_ad number(5);
exec b_com_cc_mov_ad := 25000;
var b_com_tal_mov_ad number(5);
exec b_com_tal_mov_ad := 30000;
var b_com_em_mov_ad number(5);
exec b_com_em_mov_ad := 35000;
var b_com_bu_mov_ad number(5);
exec b_com_bu_mov_ad := 40000;

DECLARE
    v_movil_porcentaje number(3,2);
    v_movil_normal number(8);
    v_movil_adicional number(5);

BEGIN
    FOR EMP IN (SELECT * FROM EMPLEADO) LOOP
        dbms_output.put_line('ANNO PROCESO: ' || :b_ANNO_PROCESO || ' ID: ' || EMP.ID_EMP || ' RUN: ' || EMP.numrun_emp || ' NOMBRE: ' || EMP.PNOMBRE_EMP);
    END LOOP;
END;

select * from proy_movilizacion;
SELECT * FROM EMPLEADO EMP;