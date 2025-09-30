-- 2.1.2
-- Act 1
CREATE OR REPLACE PROCEDURE list_departments IS
BEGIN
    --SELECT * FROM departments;
    FOR department IN (SELECT department_id, department_name FROM departments) LOOP
        dbms_output.put_line('Dep ID: ' || department.department_id || ' | Dep Name: ' || department.department_name);
    END LOOP;
END;
/

BEGIN
    list_departments;
END;
/

-- Act 2
CREATE OR REPLACE PROCEDURE update_store_location(
    p_store_id IN NUMBER,
    p_new_location IN NUMBER
) IS
BEGIN
    UPDATE locations
    SET street_address = p_new_location
    WHERE location_id = p_store_id;
END;
/