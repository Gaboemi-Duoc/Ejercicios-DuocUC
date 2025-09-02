DECLARE
    v_name countries.country_name%TYPE;
BEGIN
    BEGIN
    SELECT country_name INTO v_name
    FROM countries;
    dbms_output.put_line('!');
    EXCEPTION WHEN OTHERS THEN
        dbms_output.put_line('ERROR!');
    END;
    dbms_output.put_line('!?');
END;

-- Ejemplo 1
DECLARE
    v_emp_id employees.employee_id%TYPE;
    v_emp_name employees.first_name%TYPE;
BEGIN
    -- Intenta obtener el nombre del empleado con ID específico
    SELECT first_name INTO v_emp_name FROM employees WHERE employee_id = 9999; -- ID no existente para ilustrar la excepción
    DBMS_OUTPUT.PUT_LINE('Employee Name: ' || v_emp_name);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('No employee found with this ID.');
        WHEN TOO_MANY_ROWS THEN
            DBMS_OUTPUT.PUT_LINE('More than one employee found.');
END;

-- Ejemplo 2
    DECLARE
    v_emp_id employees.employee_id%TYPE := 1234;
    v_salary employees.salary%TYPE;
BEGIN
    -- Intenta obtener el salario del empleado con ID específico
    SELECT salary INTO v_salary FROM employees WHERE employee_id = v_emp_id;
    DBMS_OUTPUT.PUT_LINE('Salary: ' || v_salary);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('No employee found with this ID.');
        WHEN TOO_MANY_ROWS THEN
            DBMS_OUTPUT.PUT_LINE('More than one employee found.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
END;