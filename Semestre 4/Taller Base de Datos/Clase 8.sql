CREATE OR REPLACE PROCEDURE sp_actualiza_salario(p_id_empleado NUMBER) IS
    BEGIN
        UPDATE employees
        set salary = salary* 1.1
        WHERE employee_id = p_id_empleado;
END;
EXECUTE sp_actualiza_salario(120);

CREATE OR REPLACE FUNCTION fn_calcula_iva(p_monto NUMBER) 
RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        v_total := 100 * 0.19;
    RETURN v_total;
END;


CREATE OR REPLACE PACKAGE pkg_util IS
    PROCEDURE sp_actualiza_salario(p_id_empleado NUMBER);
    function fn_calcula_iva(p_monto NUMBER)return NUMBER;
END;

CREATE OR REPLACE PACKAGE BODY pkg_util IS
    PROCEDURE sp_actualiza_salario(p_id_empleado NUMBER) IS
        BEGIN
            UPDATE employees
            set salary = salary* 1.1
            WHERE employee_id = p_id_empleado;
    END;
    FUNCTION fn_calcula_iva(p_monto NUMBER) 
    RETURN NUMBER IS
            v_total NUMBER;
        BEGIN
            v_total := 100 * 0.19;
        RETURN v_total;
    END;
END;

-- Pruebas
SELECT 
    pkg_util.fn_calcula_iva(salary)
FROM employees;
EXECUTE pkg_util.sp_actualiza_salario(100);