CREATE OR REPLACE PACKAGE pkg_reportes_empleados IS
    PROCEDURE sp_reporte_salario(p_sal_min NUMBER);
END;
/

CREATE OR REPLACE PACKAGE BODY pkg_reportes_empleados IS
    PROCEDURE sp_reporte_salario(p_sal_min NUMBER) IS
        CURSOR cur_emp(p_sal_min_cur NUMBER) IS
            SELECT
                e.first_name,
                e.last_name,
                j.job_title,
                e.salary
            FROM employees e JOIN jobs j
            ON e.job_id = j.job_id
            WHERE e.salary >= p_sal_min_cur
            ORDER BY 1;
    BEGIN
        FOR reg IN cur_emp(p_sal_min) LOOP
            dbms_output.put_line('Nombre ' || 'reg.first_name');
        END LOOP;
    END;
END;
/