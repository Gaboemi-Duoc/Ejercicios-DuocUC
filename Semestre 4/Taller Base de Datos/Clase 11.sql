
CREATE OR REPLACE PACKAGE emp_management IS
PROCEDURE update_employee(p_emp_id IN NUMBER, p_new_name IN VARCHAR2);
FUNCTION get_emp_count(p_dep_name IN VARCHAR2) RETURN NUMBER;
END emp_management;


CREATE OR REPLACE PACKAGE BODY emp_management IS
	PROCEDURE update_employee(
		p_emp_id IN NUMBER,
		p_new_name IN VARCHAR2
		) IS
		BEGIN
			UPDATE EMPLOYEES
			SET FIRST_NAME = p_new_name
			WHERE EMPLOYEE_ID = p_emp_id;
		END update_employee;
	FUNCTION get_emp_count(p_dep_name IN VARCHAR2) RETURN NUMBER IS
		V_CANT_NUM NUMBER;
		BEGIN
			SELECT COUNT(*) INTO V_CANT_NUM 
			FROM EMPLOYEES EMP JOIN DEPARTMENTS DEP
			ON(EMP.DEPARTMENT_ID = DEP.DEPARTMENT_ID)
			WHERE DEP.DEPARTMENT_NAME = p_dep_name;
			RETURN V_CANT_NUM;
	END get_emp_count;
END emp_management;


CREATE OR REPLACE PACKAGE emp_salary_manager IS
FUNCTION get_avg_salary_dep(p_dep_id IN NUMBER) RETURN NUMBER;
PROCEDURE update_salary(p_empno IN NUMBER, p_new_salary IN NUMBER);
END emp_salary_manager;
/

CREATE OR REPLACE PACKAGE BODY emp_salary_manager IS
-- Procedimiento Público
    PROCEDURE update_salary(p_empno IN NUMBER, p_new_salary IN NUMBER) IS
        BEGIN
            UPDATE employees
            SET salary = p_new_salary
            WHERE employee_id = p_empno;
            IF SQL%ROWCOUNT = 0 THEN
                DBMS_OUTPUT.PUT_LINE('No employee found with ID: ' || p_empno);
            ELSE
                DBMS_OUTPUT.PUT_LINE('Salary updated for employee ID: ' ||
                p_empno);
            END IF;
    END update_salary;
	-- Función Pública
	FUNCTION get_avg_salary_dep(p_dep_id IN NUMBER) RETURN NUMBER IS
        v_total NUMBER;
        v_count NUMBER;
        v_average NUMBER;
        BEGIN
            SELECT SUM(employees.salary) INTO v_total FROM employees WHERE department_id = p_dep_id;
            SELECT COUNT(*) INTO v_count FROM employees WHERE department_id = p_dep_id;
            v_average := round(v_total / v_count);
            RETURN v_average;
	END get_avg_salary_dep;
END emp_salary_manager;
/

BEGIN
    emp_salary_manager.update_salary(100, 500000);
    DBMS_OUTPUT.PUT_LINE(emp_salary_manager.get_avg_salary_dep(100));
END;

