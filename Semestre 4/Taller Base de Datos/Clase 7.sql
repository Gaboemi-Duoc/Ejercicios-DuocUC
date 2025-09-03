-- 3.2 Actividad
DECLARE
    v_dep_name deparatments.department_name%type;
BEGIN
    -- Intenta obtener el nombre del empleado con ID específico
    SELECT department_name INTO v_dep_name FROM departments WHERE department_id = 9999; -- ID no existente para ilustrar la excepción
    DBMS_OUTPUT.PUT_LINE('Department Name: ' || v_dep_name);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('No department found with this ID.');
        WHEN TOO_MANY_ROWS THEN
            DBMS_OUTPUT.PUT_LINE('More than one department found.');
END;

-- Raise Ejemplo
DECLARE
    v_emp_id number := 100;
    v_new_salary number := 7000;
BEGIN
    UPDATE employees SET SALARY = v_new_salary WHERE EMPLOYEE_ID = v_emp_id;
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Employee ID not found.');
    END IF;
        DBMS_OUTPUT.PUT_LINE('Salary updated successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error updating salary: ' || SQLERRM);
END;

-- Raise Ejemplo 2
DECLARE
    ex_invalid_discount EXCEPTION;
    v_discount NUMBER := 150;
    v_salary number := -1000;
BEGIN
    IF v_salary < 0 THEN
        RAISE ex_invalid_discount;
    END IF;
    IF v_discount > 100 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Discount cannot exceed 100%.');
    END IF;
    DBMS_OUTPUT.PUT_LINE('OK');
    EXCEPTION
        WHEN ex_invalid_discount THEN
            DBMS_OUTPUT.PUT_LINE('Error: Invalid discount value.');
        WHEN others THEN
            DBMS_OUTPUT.PUT_LINE('Error: others.');
END;