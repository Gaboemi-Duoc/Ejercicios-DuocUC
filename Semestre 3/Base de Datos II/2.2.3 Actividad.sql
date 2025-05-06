-- Requerimiento 1
CREATE OR REPLACE VIEW BDY1102_P7_1
AS SELECT
    TO_CHAR(c.numrun, '09G999G999') || '-' || c.dvrun AS "RUN CLIENTE",
    c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno AS "NOMBRE CLIENTE",
    pro.nombre_prof_ofic AS "PROFESION U OFICIO",
    tcon.nombre_tipo_contrato AS "TIPO CONTRATO",
    TO_CHAR(SUM(pic.monto_total_ahorrado), '$99G999G999') AS "MONTO TOTAL AHORRADO",
    CASE
        WHEN SUM(pic.monto_total_ahorrado) >= 100000 AND SUM(pic.monto_total_ahorrado) <= 1000000 THEN 'BRONCE'
        WHEN SUM(pic.monto_total_ahorrado) > 1000000 AND SUM(pic.monto_total_ahorrado) <= 4000000 THEN 'PLATA'
        WHEN SUM(pic.monto_total_ahorrado) > 4000000 AND SUM(pic.monto_total_ahorrado) <= 8000000 THEN 'SILVER'
        WHEN SUM(pic.monto_total_ahorrado) > 8000000 AND SUM(pic.monto_total_ahorrado) <= 15000000 THEN 'GOLD'
        WHEN SUM(pic.monto_total_ahorrado) > 15000000 THEN 'PLATINUM'
        ELSE 'NO APLICA'
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

-- Requerimiento 2
SELECT
    *
FROM credito_cliente cc INNER JOIN credito cred
    ON(cc.cod_credito = cred.cod_credito);
    
-- Requerimiento 3
DROP SEQUENCE SEQ_ENCRIPT_RUN;
CREATE SEQUENCE SEQ_ENCRIPT_RUN
    START WITH 10
    INCREMENT BY 2
    MINVALUE 0
    MAXVALUE 999999
    NOCYCLE
    NOCACHE;
    
DROP SEQUENCE SEQ_ENCRIPT_DV;
CREATE SEQUENCE SEQ_ENCRIPT_DV
    START WITH 1
    INCREMENT BY 3
    MINVALUE 0
    MAXVALUE 999999
    NOCYCLE
    NOCACHE;
    
DROP SEQUENCE SEQ_ENCRIPT_PROD_INV;
CREATE SEQUENCE SEQ_ENCRIPT_PROD_INV
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 1000
    NOCYCLE
    NOCACHE;
    
DROP SEQUENCE SEQ_ENCRIPT_TOTAL_AHORRADO;
CREATE SEQUENCE SEQ_ENCRIPT_TOTAL_AHORRADO
    START WITH 100
    INCREMENT BY -1
    MINVALUE 0
    MAXVALUE 100
    NOCYCLE
    NOCACHE;

CREATE OR REPLACE VIEW ANNUM_TRIBUTARIO_SII AS
SELECT
    TO_CHAR(pic.fecha_solic_prod, 'YYYY') AS "ANNO_TRIBUTARIO",
    TO_CHAR(c.numrun, '09G999G999') ||'-'|| c.dvrun AS "RUN_CLIENTE",
    INITCAP(c.pnombre  ||' '|| SUBSTR(c.snombre,1,1) ||'. '|| c.appaterno ||' '|| c.apmaterno) AS "NOMBRE_CLIENTE",
    COUNT(*) AS "TOTAL_PROD_INV_AFECTOS_OMPTO",
    SUM(pic.monto_total_ahorrado) AS "MONTO_TOTAL_AHORRADO"
FROM cliente c INNER JOIN producto_inversion_cliente pic ON(c.nro_cliente = pic.nro_cliente)
WHERE TO_CHAR(pic.fecha_solic_prod, 'YYYY') = '2025'
GROUP BY
    TO_CHAR(pic.fecha_solic_prod, 'YYYY'),
    TO_CHAR(c.numrun, '09G999G999') ||'-'|| c.dvrun,
    INITCAP(c.pnombre  ||' '|| SUBSTR(c.snombre,1,1) ||'. '|| c.appaterno ||' '|| c.apmaterno);

SELECT
    ANNO_TRIBUTARIO,
    SEQ_ENCRIPT_RUN.NEXTVAL || SUBSTR(RUN_CLIENTE,0,12)  || SEQ_ENCRIPT_DV.NEXTVAL || SUBSTR(RUN_CLIENTE,-1) AS "RUN_CLIENTE",
    NOMBRE_CLIENTE,
    SEQ_ENCRIPT_PROD_INV.NEXTVAL * SEQ_ENCRIPT_PROD_INV.CURRVAL || TOTAL_PROD_INV_AFECTOS_OMPTO AS "TOTAL_PROD_INV_AFECTOS_OMPTO",
    MONTO_TOTAL_AHORRADO || SEQ_ENCRIPT_TOTAL_AHORRADO.NEXTVAL AS "MONTO_TOTAL_AHORRADO"
FROM annum_tributario_sii;
