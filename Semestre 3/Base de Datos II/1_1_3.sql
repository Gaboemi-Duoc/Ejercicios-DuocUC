SELECT
    NUMRUN_CLI || '-' || DVRUN_CLI AS "RUN CLIENTE",
    LOWER(pnombre_cli) || ' ' || INITCAP(snombre_cli) || ' ' || appaterno_cli || ' ' || apmaterno_cli AS "NOMBRE COMPLETO CLIENTE",
    TO_CHAR(fecha_nac_cli,'DD/MM/YYYY') AS "FECHA NACIMIENTO"
FROM CLIENTE
WHERE EXTRACT(DAY FROM fecha_nac_cli)=20+1 AND EXTRACT(MONTH FROM fecha_nac_cli)=8
/*WHERE '21/08' = TO_CHAR(fecha_nac_cli, 'DD/MM')*/
ORDER BY numrun_cli;