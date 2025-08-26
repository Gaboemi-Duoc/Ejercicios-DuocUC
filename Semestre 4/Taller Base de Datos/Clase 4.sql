-- Cursor 1 - Pais
DECLARE
    CURSOR cur_region IS
        SELECT
            region_id,
            region_name
        FROM regions;
        
    CURSOR cur_country(p_region NUMBER) IS
        SELECT
            country_name,
            region_id
        FROM countries
        WHERE region_id = p_region;
BEGIN
    FOR reg IN cur_region LOOP
        dbms_output.put_line('');
        dbms_output.put_line(reg.region_name);
        dbms_output.put_line('-------------------------------');
        FOR reg2 IN cur_country(reg.region_id) LOOP
            dbms_output.put_line(reg2.country_name);
        END LOOP;
    END LOOP;
END;

-- Cursor 2 - Empleados y Jefes
DECLARE
    CURSOR cur_boss IS
        SELECT
            employee_id,
            first_name || ' ' || last_name employee_name,
            job_title
        FROM employees NATURAL JOIN jobs
        WHERE employee_id IN (SELECT manager_id FROM employees);
    CURSOR cur_collaborator(p_manager_id employees.manager_id%TYPE) IS
        SELECT
            employee_id,
            first_name || ' ' || last_name employee_name,
            job_title
        FROM employees NATURAL JOIN jobs
        WHERE manager_id = p_manager_id;
BEGIN
    FOR boss IN cur_boss LOOP
        dbms_output.put_line('');
        dbms_output.put_line(boss.employee_name || ' - Title: ' || boss.job_title);
        dbms_output.put_line('-------------------------------');
        FOR employee IN cur_collaborator(boss.employee_id) LOOP
            dbms_output.put_line(employee.employee_name || ' - Title: ' || employee.job_title);
        END LOOP;
    END LOOP;
END;

-- Cursor 3 - Departamentos
DECLARE
    CURSOR cur_department IS
        SELECT
            *
        FROM departments
        WHERE department_id IN (SELECT department_id FROM employees);
        
    CURSOR cur_employees(par_dep_id departments.department_id%TYPE) IS
        SELECT
            employee_id,
            first_name || ' ' || last_name employee_name
        FROM employees
        WHERE department_id = par_dep_id;
BEGIN
    FOR depart IN cur_department LOOP
        dbms_output.put_line('');
        dbms_output.put_line(depart.department_name);
        dbms_output.put_line('-------------------------------');
        FOR employee IN cur_employees(depart.department_id) LOOP
            dbms_output.put_line(employee.employee_name);
        END LOOP;
    END LOOP;
END;
