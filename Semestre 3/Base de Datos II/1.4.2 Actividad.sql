-- 1.4.2
-- CASO 1
SELECT
    TO_CHAR(c.numrun,'09G999G999') || '-' || c.dvrun AS "RUN CLIENTE",
    INITCAP(c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno) AS "NOMBRE COMPLETO",
    TO_CHAR(c.fecha_nacimiento, 'DD "de" Month') AS "DIA DE COMPLEAÑOS",
    suc.direccion || '/' || UPPER(reg.nombre_region) AS "DIRECCION SUCURSAL/REGION"
FROM cliente c INNER JOIN REGION reg
    ON(c.cod_region = reg.cod_region)
    INNER JOIN sucursal_retail suc
    ON(c.cod_comuna = suc.cod_comuna AND c.cod_provincia = suc.cod_provincia AND c.cod_region = suc.cod_region)
WHERE EXTRACT(MONTH FROM SYSDATE) = EXTRACT(MONTH FROM c.fecha_nacimiento) AND reg.nombre_region = &region_nombre
ORDER BY 3;

--CASO 2
SELECT
    TO_CHAR(c.numrun,'09G999G999') || '-' || c.dvrun AS "RUN CLIENTE",
    c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno AS "NOMBRE CLIENTE",
    TO_CHAR(SUM(trans.monto_transaccion),'$999G999G999') AS "MONTO COMPRAS/AVANCES/S.AVANCES",
    TO_CHAR(ROUND(SUM(trans.monto_transaccion) / 10000) * 250, '999G999G999') AS "TOTAL PUNTOS ACOMULADOS"
FROM cliente c INNER JOIN tarjeta_cliente tar
    ON(c.numrun = tar.numrun)
    INNER JOIN transaccion_tarjeta_cliente trans
    ON(tar.nro_tarjeta = trans.nro_tarjeta)
WHERE EXTRACT(YEAR FROM trans.fecha_transaccion) = EXTRACT(YEAR FROM SYSDATE)-1
GROUP BY
    TO_CHAR(c.numrun,'09G999G999') || '-' || c.dvrun,
    c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno, c.appaterno
ORDER BY SUM(trans.monto_transaccion), c.appaterno;

--CASO 3
SELECT
    TO_CHAR(trans.fecha_transaccion, 'MMYYYY') AS "FECHA TRANSACCION",
    tip.nombre_tptran_tarjeta AS "TIPO TRANSACCIÓN",
    LPAD(TO_CHAR(SUM(trans.monto_total_transaccion), '$99G999G999'),26) AS "MONTO AVANCES/SUPER AVANCES",
    LPAD(TO_CHAR(SUM(trans.monto_total_transaccion*(sbif.porc_aporte_sbif/100)), '$99G999'), 14) AS "APORTE A LA SBIF"
FROM transaccion_tarjeta_cliente trans NATURAL JOIN tipo_transaccion_tarjeta tip
    JOIN aporte_sbif sbif ON(trans.monto_total_transaccion BETWEEN sbif.monto_inf_av_sav AND sbif.monto_sup_av_sav )
WHERE EXTRACT(YEAR FROM trans.fecha_transaccion) = EXTRACT(YEAR FROM SYSDATE) AND
    cod_tptran_tarjeta IN (102, 103)
GROUP BY
    TO_CHAR(trans.fecha_transaccion, 'MMYYYY'),
    tip.nombre_tptran_tarjeta
ORDER BY 1,2;

--CASO 4
SELECT
    TO_CHAR(c.numrun,'09G999G999') || '-' || c.dvrun AS "RUN CLIENTE",
    c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno AS "NOMBRE CLIENTE",
    LPAD(TO_CHAR(NVL(SUM(trans.monto_transaccion),0),'$999G999G999'),24) AS "COMPRAS/AVANCES/S.AVANCES"
FROM cliente c LEFT JOIN tarjeta_cliente tar
    ON(c.numrun = tar.numrun)
    LEFT JOIN transaccion_tarjeta_cliente trans
    ON(tar.nro_tarjeta = trans.nro_tarjeta)
GROUP BY
    TO_CHAR(c.numrun,'09G999G999') || '-' || c.dvrun,
    c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno, c.appaterno
ORDER BY c.appaterno, 3 DESC;