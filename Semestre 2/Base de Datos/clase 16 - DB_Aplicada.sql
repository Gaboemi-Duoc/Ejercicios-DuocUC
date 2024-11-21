-- 3.4.2
-- CASO 1

-- INFORME 1:
SELECT DISTINCT
    fecha_entrega_propiedad
FROM
    propiedad
WHERE
    fecha_entrega_propiedad LIKE '%/24' -- fecha_entrega_propiedad > '31-12-2023'
ORDER BY fecha_entrega_propiedad ASC;

-- INFORME 2:
SELECT DISTINCT
    fecini_arriendo
FROM
    propiedad_arrendada
WHERE
    fecini_arriendo LIKE '%/24'
ORDER BY fecini_arriendo ASC;

-- CASO 2
SELECT
    numrut_cli || '-' || dvrut_cli AS "RUN CLIENTE",
    nombre_cli || ' ' || appaterno_cli || ' ' || apmaterno_cli AS "NOMBRE CLIENTE",
    renta_cli AS "RENTA",
    fonofijo_cli AS "FONO FIJO",
    celular_cli AS "FONO MOVIL"
FROM
    cliente
WHERE
    ( renta_cli >= 800000 AND id_estcivil IN(3,4) ) OR id_estcivil = 1
ORDER BY appaterno_cli, nombre_cli ASC;

-- CASO 3

-- SIMULACION 1:
SELECT
    nombre_emp || ' ' || appaterno_emp || ' ' || apmaterno_emp AS "NOMBRE EMPLEADO",
    sueldo_emp AS "SALARIO ACTUAL",
    sueldo_emp * &porcentaje_aumento AS "AUMENTO",
    sueldo_emp + sueldo_emp * &&porcentaje_aumento AS "SALARIO REAJUSTADO"
FROM
    empleado
