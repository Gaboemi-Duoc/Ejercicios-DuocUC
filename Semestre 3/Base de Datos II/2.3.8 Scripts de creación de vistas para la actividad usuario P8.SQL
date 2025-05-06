
--CASO  1
CREATE OR REPLACE  VIEW V_CLIENTES_CON_MAS_PROD_INV AS
SELECT EXTRACT(YEAR FROM SYSDATE) "AÑO TRIBUTARIO",
       TO_CHAR(c.numrun,'09G999G999') || '-' || UPPER(c.dvrun) "RUN CLIENTE",
       INITCAP(c.pnombre || ' ' || SUBSTR(c.snombre,1,1) || '. ' || c.appaterno || ' ' || c.apmaterno) "NOMBRE CLIENTE",
       COUNT(pic.nro_cliente) "TOTAL PROD. INV AFECTOS IMPTO",
       LPAD(TO_CHAR(SUM(pic.monto_total_ahorrado),'$999G999G999'),21, ' ') "MONTO TOTAL AHORRADO"
FROM cliente c JOIN producto_inversion_cliente pic
ON c.nro_cliente=pic.nro_cliente
WHERE pic.cod_prod_inv IN(30,35,40,45,50,55)
AND  COUNT( c.nro_cliente)   IN (SELECT MAX(COUNT(*))
                 FROM producto_inversion_cliente
                 GROUP BY nro_cliente)
GROUP BY numrun,c.dvrun,c.pnombre,c.snombre,c.appaterno,c.apmaterno
ORDER BY c.appaterno
WITH READ ONLY;


--CASO 2   ESTARÁN DE CUMPLEAÑOS EL SIGUIENTE MES
CREATE OR REPLACE  VIEW V_CLIENTES_CUMPLES_MES   AS
SELECT TO_CHAR(c.numrun,'09G999G999') || '-' || UPPER(c.dvrun) "RUN CLIENTE",
       INITCAP(c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno) "NOMBRE CLIENTE",
       TO_CHAR(c.fecha_nacimiento,'dd " de " MONTH')  "DIA DE CUMPLEAÑOS"
FROM cliente c
WHERE TO_CHAR(c.fecha_nacimiento,'MM')=TO_CHAR(SYSDATE,'MM')+1
ORDER BY c.appaterno
with read only;

--CASO  3
CREATE OR REPLACE VIEW V_CRED_MAYOR_PROM  AS
SELECT TO_CHAR(crc.fecha_otorga_cred,'MMYYYY') "MES TRANSACCIÓN",
       c.nombre_credito "TIPO CREDITO",
       SUM(crc.monto_credito) "MONTO SOLICITADO CREDITO",
       SUM(CASE WHEN crc.monto_credito BETWEEN 100000 AND 1000000 THEN ROUND(crc.monto_credito*0.01)
            WHEN crc.monto_credito BETWEEN 1000001 AND 2000000 THEN ROUND(crc.monto_credito*0.02)
            WHEN crc.monto_credito BETWEEN 2000001 AND 4000000 THEN ROUND(crc.monto_credito*0.03)
            WHEN crc.monto_credito BETWEEN 4000001 AND 6000000 THEN ROUND(crc.monto_credito*0.04)
       ELSE ROUND(crc.monto_credito*0.07) END) "APORTE A LA SBIF"
FROM credito_cliente crc JOIN credito c
ON crc.cod_credito=c.cod_credito
AND crc.monto_credito > (SELECT ROUND(AVG(monto_credito))
                          FROM credito_cliente)
GROUP BY TO_CHAR(crc.fecha_otorga_cred,'MMYYYY'), c.nombre_credito
ORDER BY TO_CHAR(crc.fecha_otorga_cred,'MMYYYY'), c.nombre_credito
WITH READ ONLY;


--CASO  4
--Sentencia  Clientes_Prod_Inv_SII
SELECT TO_CHAR(c.numrun,'09G999G999') || '-' || UPPER(c.dvrun) "RUN CLIENTE",
       INITCAP(c.pnombre || ' ' || c.snombre || ' ' || c.appaterno || ' ' || c.apmaterno) "NOMBRE CLIENTE",
       pi.nombre_prod_inv "PRODUCTO DE INVERSION",
       pic.monto_total_ahorrado "MONTO TOTAL AHORRADO"
FROM cliente c JOIN producto_inversion_cliente pic
ON c.nro_cliente = pic.nro_cliente
JOIN producto_inversion pi
ON pic.cod_prod_inv=pi.cod_prod_inv
AND EXTRACT(YEAR FROM pic.fecha_solic_prod)=EXTRACT(YEAR FROM SYSDATE)
AND c.cod_tipo_contrato = 1
ORDER BY c.appaterno;

CREATE INDEX IDX_TESTING ON producto_inversion_cliente(EXTRACT(YEAR FROM fecha_solic_prod));
DROP INDEX IDX_TESTING;
CREATE INDEX IDX_TESTING2 ON CLIENTE(NRO_CLIENTE, COD_TIPO_CONTRATO);
DROP INDEX IDX_TESTING2;
