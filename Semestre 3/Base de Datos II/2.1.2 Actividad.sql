-- 2.1.2
-- CASO 1
SAVEPOINT C1;
SELECT
    TO_CHAR(SYSDATE, 'YYYYMM') AS "ANNO_MES",
    numrun_emp,
    INITCAP(pnombre_emp || ' ' || snombre_emp || ' ' || appaterno_emp || ' ' || apmaterno_emp) AS "NOMBRE_EMPLEADO",
    sueldo_base,
    COUNT(arriendo_camion.id_arriendo) AS "TOTAL_ARRIENDO_MENSUAL"
FROM empleado NATURAL JOIN camion NATURAL JOIN arriendo_camion
GROUP BY TO_CHAR(SYSDATE, 'YYYYMM'),
    numrun_emp,
    INITCAP(pnombre_emp || ' ' || snombre_emp || ' ' || appaterno_emp || ' ' || apmaterno_emp),
    sueldo_base
ORDER BY 2;

SELECT

COUNT()
FROM empleado NATURAL JOIN camion NATURAL JOIN arriendo_camion;

SELECT * FROM CAMION;

-- CASO 2

-- CASO 3
SAVEPOINT C3;
CREATE TABLE CLIENTES_SIN_ARRIENDO AS
    SELECT
        cli.*
    FROM cliente cli LEFT JOIN arriendo_camion acam
        ON(cli.numrun_cli = acam.numrun_cli)
    WHERE acam.id_arriendo IS NULL;

DELETE FROM CLIENTE
WHERE numrun_cli IN(SELECT numrun_cli FROM clientes_sin_arriendo);

SELECT * FROM CLIENTE;
SELECT * FROM CLIENTES_SIN_ARRIENDO;

--ROLLBACK TO C3;
--COMMIT;

-- CASO 4