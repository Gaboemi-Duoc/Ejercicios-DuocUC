-- 1.3.2
-- CASO 1
SELECT
    TO_CHAR( c.numrun, '99G999G999' ) || '-' || UPPER( c.dvrun ) AS "RUN CLIENTE",
    c.pnombre || ' ' ||  c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno as "NOMBRE CLIENTE",
    p.nombre_prof_ofic AS "PROFESION/OFICIO",
    TO_CHAR( c.fecha_nacimiento, 'dd "de" Month' )
FROM cliente c NATURAL JOIN profesion_oficio p
WHERE 9 = EXTRACT(MONTH FROM c.fecha_nacimiento) -- 9 MONTH FOR THE CASE OTHERWISE EXTRACT(MONTH FROM SYSDATE)
ORDER BY 4, c.appaterno; -- ASC

-- CASO 2
SELECT
    TO_CHAR( c.numrun, '99G999G999' ) || '-' || UPPER( c.dvrun ) AS "RUN CLIENTE",
    c.pnombre || ' ' ||  c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno as "NOMBRE CLIENTE",
    TO_CHAR(SUM(cred.monto_solicitado), '$99G999G999') AS "MONTO SOLICITADO",
    TO_CHAR(SUM(cred.monto_solicitado) / 100000 * 1200, '$99G999G999') AS "TOTAL PESO SUMA"
FROM cliente c NATURAL JOIN credito_cliente cred
--ON(c.nro_cliente = cred.nro_cliente)
WHERE TO_CHAR(cred.fecha_otorga_cred, 'YY') = TO_CHAR(SYSDATE, 'YY')-1 -- THE YEAR IS 2025 FOR THE CASE
GROUP BY TO_CHAR( c.numrun, '99G999G999' ) || '-' || UPPER( c.dvrun ),
    c.pnombre || ' ' ||  c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno, appaterno
ORDER BY 4, appaterno;

-- CASO 3
SELECT
    TO_CHAR(cc.fecha_solic_cred, 'MMYYYY') AS "MES TRANSACCION",
    cred.nombre_credito AS "TIPO CREDITO",
    SUM(cc.monto_credito) AS "MONTO SOLICITADO CREDITO",
    CASE
        WHEN SUM(cc.monto_credito) >= 100000 AND SUM(cc.monto_credito) <= 1000000 THEN SUM(cc.monto_credito) * 0.01
        WHEN SUM(cc.monto_credito) > 1000000 AND SUM(cc.monto_credito) <= 2000000 THEN SUM(cc.monto_credito) * 0.02
        WHEN SUM(cc.monto_credito) > 2000000 AND SUM(cc.monto_credito) <= 4000000 THEN SUM(cc.monto_credito) * 0.03
        WHEN SUM(cc.monto_credito) > 4000000 AND SUM(cc.monto_credito) <= 6000000 THEN SUM(cc.monto_credito) * 0.04
        WHEN SUM(cc.monto_credito) > 6000000 THEN SUM(cc.monto_credito) * 0.07
    END AS "APORTE AL SBIF"
FROM CREDITO_CLIENTE cc NATURAL JOIN CREDITO cred
WHERE EXTRACT(YEAR FROM cc.fecha_solic_cred) = EXTRACT(YEAR FROM SYSDATE) - 1
GROUP BY TO_CHAR(cc.fecha_solic_cred, 'MMYYYY'), cred.nombre_credito
ORDER BY 1,3;

-- CASO 4
SELECT
    TO_CHAR( c.numrun, '99G999G999' ) || '-' || UPPER( c.dvrun ) AS "RUN CLIENTE",
    c.pnombre || ' ' ||  c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno AS "NOMBRE CLIENTE",
    TO_CHAR(SUM(inv.monto_total_ahorrado), '$99G999G999') AS "MONTO TOTAL AHORRADO",
    CASE
        WHEN SUM(inv.monto_total_ahorrado) >= 100000 AND SUM(inv.monto_total_ahorrado) <= 1000000 THEN 'BRONCE'
        WHEN SUM(inv.monto_total_ahorrado) > 1000000 AND SUM(inv.monto_total_ahorrado) <= 4000000 THEN 'PLATA'
        WHEN SUM(inv.monto_total_ahorrado) > 4000000 AND SUM(inv.monto_total_ahorrado) <= 8000000 THEN 'SILVER'
        WHEN SUM(inv.monto_total_ahorrado) > 8000000 AND SUM(inv.monto_total_ahorrado) <= 15000000 THEN 'GOLD'
        WHEN SUM(inv.monto_total_ahorrado) > 15000000 THEN 'PLATINUM'
    END AS "CATEGORIA CLIENTE"
FROM cliente c NATURAL JOIN producto_inversion_cliente inv
GROUP BY TO_CHAR( c.numrun, '99G999G999' ) || '-' || UPPER( c.dvrun ),
    c.pnombre || ' ' ||  c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno, c.appaterno
HAVING SUM(inv.monto_total_ahorrado) >= 100000
ORDER BY c.appaterno, SUM(inv.monto_total_ahorrado);

-- CASO 5

-- CASO 6
-- INFORME 1
SELECT
    TO_CHAR( c.numrun, '99G999G999' ) || '-' || UPPER( c.dvrun ) AS "RUN CLIENTE",
    INITCAP(c.pnombre || ' ' ||  c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno) AS "NOMBRE CLIENTE",
    COUNT(*) AS "TOTAL CREDITOS SOLICITADOS",
    TO_CHAR(SUM(cred.monto_solicitado), '$99G999G999') AS "MONTO TOTAL CREDITOS"
FROM cliente c NATURAL JOIN credito_cliente cred
GROUP BY TO_CHAR( c.numrun, '99G999G999' ) || '-' || UPPER( c.dvrun ),
    INITCAP(c.pnombre || ' ' ||  c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno), c.appaterno
ORDER BY c.appaterno;

-- INFORME 2
