-- Requerimiento 1
CREATE OR REPLACE VIEW BDY1102_P7_1
AS SELECT
    TO_CHAR(c.numrun, '09G999G999') || '-' || c.dvrun AS "RUN CLIENTE",
    c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno AS "NOMBRE CLIENTE",
    pro.nombre_prof_ofic AS "PROFESION U OFICIO",
    tcon.nombre_tipo_contrato AS "TIPO CONTRATO",
    SUM(pic.monto_total_ahorrado) AS "MONTO TOTAL AHORRADO",
    CASE
        WHEN SUM(pic.monto_total_ahorrado) >= 100000 AND SUM(pic.monto_total_ahorrado) <= 1000000 THEN 'BRONCE'
        WHEN SUM(pic.monto_total_ahorrado) > 1000000 AND SUM(pic.monto_total_ahorrado) <= 4000000 THEN 'PLATA'
        WHEN SUM(pic.monto_total_ahorrado) > 4000000 AND SUM(pic.monto_total_ahorrado) <= 8000000 THEN 'SILVER'
        WHEN SUM(pic.monto_total_ahorrado) > 8000000 AND SUM(pic.monto_total_ahorrado) <= 15000000 THEN 'GOLD'
        WHEN SUM(pic.monto_total_ahorrado) > 15000000 THEN 'PLATINUM'
    END AS "CATEGORIA CLIENTE"
FROM producto_inversion_cliente pic INNER JOIN cliente c ON(pic.nro_cliente = c.nro_cliente)
    INNER JOIN profesion_oficio pro ON(pro.cod_prof_ofic = c.cod_prof_ofic)
    INNER JOIN tipo_contrato tcon ON(tcon.cod_tipo_contrato = c.cod_tipo_contrato)
GROUP BY
    TO_CHAR(c.numrun, '09G999G999') || '-' || c.dvrun,
    c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno, c.appaterno,
    pro.nombre_prof_ofic,
    tcon.nombre_tipo_contrato
ORDER BY c.appaterno ASC, 5 DESC;

SELECT * FROM BDY1102_P7_1;