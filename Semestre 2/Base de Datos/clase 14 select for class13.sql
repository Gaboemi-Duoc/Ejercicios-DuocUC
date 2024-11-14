SELECT
    numrut || '-' || dvrut AS "RUT",
    pnombre || ' ' || appaterno AS "NOMBRE",
    sueldo_base AS "SUELDO BASE",
    sueldo_base * (10/100) AS "BONO 10%",
    sueldo_base + (sueldo_base * (10/100)) AS "SUELDO TOTAL",
    (SELECT SUM (boleta.monto_total) FROM boleta WHERE vendedor.id_empleado = boleta.id_empleado) AS "TOTAL BOLETAS",
    (SELECT SUM (comision_boleta.monto_comision) FROM comision_boleta WHERE vendedor.id_empleado = comision_boleta.id_empleado) AS "TOTAL COMISIONES"
FROM
    vendedor
ORDER BY vendedor.id_empleado; -- Default is ASC, so 1, 2 ,3; DESC is the other one
    