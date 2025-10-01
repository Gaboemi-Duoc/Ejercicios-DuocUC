CREATE OR REPLACE FUNCTION calculate_total_empleados(p_id_dep IN NUMBER)
RETURN NUMBER IS
    v_total_empleados NUMBER;
BEGIN
    SELECT count(*)
    INTO v_total_empleados
    FROM employees
    WHERE department_id = p_id_dep;
    
    RETURN v_total_empleados;
END;
/

SELECT calculate_total_empleados(50)
FROM dual;

CREATE OR REPLACE FUNCTION get_dep_name_from_employee(p_id_employee IN NUMBER)
RETURN VARCHAR2 IS
    v_dep_name VARCHAR2(30);
BEGIN
    SELECT department_name
    INTO v_dep_name
    FROM departments NATURAL JOIN employees
    WHERE employee_id = p_id_employee;
    
    RETURN v_dep_name;
END;
/

SELECT get_dep_name_from_employee(150)
FROM dual;

CREATE OR REPLACE FUNCTION get_emp_title(p_id_employee IN NUMBER)
RETURN VARCHAR2 IS
    v_job_title VARCHAR2(35);
BEGIN
    SELECT job_title
    INTO v_job_title
    FROM jobs NATURAL JOIN employees
    WHERE employee_id = p_id_employee;
    
    RETURN v_job_title;
END;
/

SELECT get_emp_title(150)
FROM dual;