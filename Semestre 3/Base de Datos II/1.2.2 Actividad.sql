-- 1.2.2
-- CASO 1

-- MONTO es 30200 segun ejercicio
SELECT
    carreraid as "IDENTIFICACION DE LA CARRERA",
    COUNT(*) AS "TOTAL DE ALUMNOS MATRICULADOS",
    'Le corresponden ' || TO_CHAR( COUNT(*) * &MONTO,'$999G999' ) || ' del presupuesto total asignado para publicidad' as "MONTO POR PUBLICIDAD"
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
ORDER BY carreraid;

-- CASO 3

SELECT
    run_jefe AS "RUN JEFE SIN DV",
    COUNT(*) AS "TOTAL DE EMPLEADOS A SU CARGO",
    TO_CHAR( MAX(salario), '9G999G999' ) AS "SALARIO MAXIMO"
WHERE run_jefe is not NULL
GROUP BY run_jefe;