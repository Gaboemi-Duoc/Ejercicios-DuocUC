-- 1.2.2
-- CASO 1

-- MONTO es 30200 segun ejercicio
SELECT
    carreraid as "IDENTIFICACION DE LA CARRERA",
    COUNT(*) AS "TOTAL DE ALUMNOS MATRICULADOS",
    'Le corresponden' || TO_CHAR( COUNT(*) * &MONTO,'$999G999' ) || ' del presupuesto total asignado para publicidad' as "MONTO POR PUBLICIDAD"
FROM alumno
GROUP BY carreraid
ORDER BY COUNT(*) DESC, carreraid ASC;

-- CASO 2

SELECT
    carreraid as "CARRERA",
    COUNT(*) as "TOTAL DE ALUMNOS"
FROM ALUMNO
GROUP BY carreraid
HAVING COUNT(*) > 4
ORDER BY carreraid; -- ASC BY DEFAULT

-- CASO 3

SELECT
    TO_CHAR(run_jefe, '99g999g999') AS "RUN JEFE SIN DV",
    COUNT(*) AS "TOTAL DE EMPLEADOS A SU CARGO",
    TO_CHAR( MAX(salario), '9G999G999' ) AS "SALARIO MAXIMO",
    TO_CHAR(COUNT(*)*10, '990')||'% del Salario Máximo' AS "PORCENTAJE DE BONIFICACION",
    TO_CHAR( MAX(salario) * ( COUNT(*)/10 ) , '$9G999G999' ) AS "BONIFICACION"
FROM empleado
WHERE run_jefe is not NULL
GROUP BY run_jefe
ORDER BY run_jefe; -- ASC BY DEFAULT

-- CASO 4

SELECT
    id_escolaridad AS "ESCOLARIDAD",
    CASE
        WHEN id_escolaridad = 10 THEN 'BÁSICA'
        WHEN id_escolaridad = 20 THEN 'MEDIA CIENTÍFICA HUMANISTA'
        WHEN id_escolaridad = 30 THEN 'MEDIA TÉCNICO PROFESIONAL'
        WHEN id_escolaridad = 40 THEN 'SUPERIOR CENTRO DE FORMACIÓN TÉCNICA'
        WHEN id_escolaridad = 50 THEN 'SUPERIOR INSTITUTO PROFESIONAL'
        WHEN id_escolaridad = 60 THEN 'SUPERIOR UNIVERSIDAD'
    END AS "DESCRIPCIÓN ESCOLARIDAD",
    COUNT(*) AS "TOTAL DE EMPLEADOS",
    TO_CHAR(MAX(salario),'$99G999G999') AS "SALARIO MAXIMO",
    TO_CHAR(MIN(salario),'$99G999G999') AS "SALARIO MINIMO",
    TO_CHAR(SUM(salario),'$99G999G999') AS "SALARIO TOTAL",
    TO_CHAR(ROUND(AVG(salario)),'$99G999G999') AS "SALARIO PROMEDIO",
    TO_CHAR(MEDIAN(salario),'$99G999G999') AS "SALARIO MEDIANO"
FROM EMPLEADO
GROUP BY id_escolaridad
ORDER BY 3 DESC; -- ORDENAR POR LA COLUMNA 3

-- CASO 5
-- CURRENT YEAR = 2025
SELECT
    tituloid AS "CODIGO DEL LIBRO",
    COUNT(*) AS "TOTAL DE VECES SOLICITADO",
    CASE
        WHEN COUNT(*) > 5 THEN 'Se requiere comprar 4 nuevos ejemplares'
        WHEN COUNT(*) BETWEEN 4 AND 5 THEN 'Se requiere comprar 2 nuevos ejemplares'
        WHEN COUNT(*) BETWEEN 2 AND 3 THEN 'Se requiere comprar 1 nuevos ejemplares'
        WHEN COUNT(*) = 1 THEN 'No se requiere nuevos ejemplares'
    END AS "SUGERENCIA"
FROM prestamo
WHERE '24' = TO_CHAR(fecha_ini_prestamo, 'YY')
GROUP BY tituloid
ORDER BY 2 DESC;