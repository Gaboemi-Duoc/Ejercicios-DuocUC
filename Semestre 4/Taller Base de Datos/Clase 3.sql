DECLARE
    CURSOR cur_paises IS
        SELECT
          country_id,
          country_name nombre_pais,
          region_id
        FROM
          countries;
BEGIN
    -- Alternativa SIMPLE LOOP
    -- OPEN cur_paises;
    -- LOOP
    --     FETCH cur_paises INTO v_nombre_pais;
    --     EXIT WHEN cur_paises%NOTFOUND;
    --     dbms_output.put_line(upper(v_nombre_pais));
    -- END LOOP;
    -- CLOSE cur_paises;

    -- Alternativa FOR LOOP
    FOR reg IN cur_paises LOOP
        dbms_output.put_line(reg.nombre_pais);
    END LOOP;

END;

-- Actividad 1
DECLARE
    CURSOR c_employee_dep_minsal(p_dep_id NUMBER, p_min_sal NUMBER) IS
        SELECT
            first_name || ' ' || last_name nombre_empleado,
            department_id dep_id,
            salary salario
        FROM employees
        WHERE
            department_id = p_dep_id AND
            salary >= p_min_sal;
BEGIN
    FOR reg IN c_employee_dep_minsal(50, 5000) LOOP
        dbms_output.put_line(reg.nombre_empleado);
    END LOOP;
END;

-- Actividad 2
DECLARE
     CURSOR cur_employee_salaries IS
        SELECT
            first_name || ' ' || last_name nombre_empleado,
            salary salario
        FROM
            employees;
BEGIN
    FOR reg IN cur_employee_salaries LOOP
        dbms_output.put_line(reg.nombre_empleado || ' - Salario: ' || reg.salario);
    END LOOP;
END;

-- Actividad 3
DECLARE
    CURSOR cur_countries IS
        SELECT country_name, region_name
        FROM countries NATURAL JOIN regions;
BEGIN
    FOR reg IN cur_countries LOOP
        dbms_output.put_line('NAME COUNTRY: ' || reg.country_name || ' - REGION: ' || reg.region_name); 
    END LOOP;
END;



-- Saved Procedure
CREATE OR REPLACE PROCEDURE sp_paises(p_area1 NUMBER, p_area2 NUMBER) IS
    CURSOR cur_paises(p_region NUMBER) IS
        SELECT
            country_id,
            country_name nombre_pais,
            region_id
        FROM
            countries
        WHERE region_id = p_region;
BEGIN
    FOR reg in cur_paises(p_area1) LOOP
        dbms_output.put_line(upper(reg.nombre_pais));
    END LOOP;
    dbms_output.put_line('');
    FOR reg in cur_paises(p_area2) LOOP
        dbms_output.put_line(upper(reg.nombre_pais));
    END LOOP;
END;
-- Execution
EXEC sp_paises(10,20);