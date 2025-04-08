-- 1.5.2
-- CASO 1
SELECT
    tsa.descripcion || ', ' || sa.descripcion AS "SISTEMA SALUD",
    (SELECT COUNT(*)
        FROM atencion NATURAL JOIN paciente 
        WHERE
            sa.sal_id = paciente.sal_id AND
            TO_CHAR(ADD_MONTHS(SYSDATE,-1), 'MMYYYY') =  TO_CHAR(atencion.fecha_atencion, 'MMYYYY'))
    AS "TOTAL ATENCIONES"
FROM salud sa INNER JOIN tipo_salud tsa
ON(sa.tipo_sal_id = tsa.tipo_sal_id)
WHERE
    sa.tipo_sal_id IN ('F','I') AND
    (SELECT COUNT(*)
        FROM atencion NATURAL JOIN paciente 
        WHERE
            sa.sal_id = paciente.sal_id AND
            TO_CHAR(ADD_MONTHS(SYSDATE,-1), 'MMYYYY') =  TO_CHAR(atencion.fecha_atencion, 'MMYYYY')
    ) > (SELECT AVG(*)
        FROM atencion NATURAL JOIN paciente
        INNER JOIN tipo_salud tsa
        ON(sa.tipo_sal_id = tsa.tipo_sal_id)
        WHERE
            sa.sal_id = paciente.sal_id
;

SELECT
    tsa.descripcion || ', ' || sa.descripcion AS "SISTEMA SALUD"
FROM atencion ate NATURAL JOIN paciente pa
    INNER JOIN salud sa ON(sa.sal_id = pa.sal_id)
    INNER JOIN tipo_salud tsa ON(tsa.tipo_sal_id = sa.tipo_sal_id)
GROUP BY
    tsa.descripcion || ', ' || sa.descripcion;

SELECT * FROM SALUD;