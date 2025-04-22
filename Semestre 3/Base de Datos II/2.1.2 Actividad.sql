-- 2.1.2
-- CASO 1
SELECT
    TO_CHAR(SYSDATE, 'YYYYMM') AS "ANNO_MES",
    numrun_emp,
    INITCAP(pnombre_emp || ' ' || snombre_emp || ' ' || appaterno_emp || ' ' || apmaterno_emp) AS "NOMBRE_EMPLEADO",
    sueldo_base
FROM empleado
ORDER BY 2;

SELECT * FROM arriendo_camion
ORDER BY 3;