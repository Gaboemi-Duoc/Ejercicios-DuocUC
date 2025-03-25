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