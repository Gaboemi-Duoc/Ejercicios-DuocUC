DECLARE
    TYPE departmentsrec is RECORD (
        v_dep_id departments.department_id%type,
        v_dep_name departments.department_name%type,
        v_man_id departments.manager_id%type,
        v_loc_id departments.location_id%type
    );
    v_department departmentsrec;
BEGIN
    SELECT
        department_id,
        department_name,
        manager_id,
        location_id
    INTO v_department
    FROM departments
    WHERE department_id = 40;
    
    dbms_output.put_line('ID: ' || v_department.v_dep_id || ', Name: ' || v_department.v_dep_name || ', Manager ID: ' || v_department.v_man_id || ', Location ID: ' || v_department.v_loc_id );
END;


DECLARE
    TYPE tipo_varray is VARRAY(5) of INT;
    TYPE t_name_type is VARRAY(2) OF VARCHAR2(20) NOT NULL;
    TYPE PRODUCT_ARRAY is VARRAY(5) OF VARCHAR2(20);
    
    v_numeros tipo_varray;
    
    v_products PRODUCT_ARRAY := PRODUCT_ARRAY('Product A', 'Product B', 'Product C');
    
    v_names t_name_type := t_name_type('John', 'Cena');
    
BEGIN
    v_numeros := tipo_varray(10,20,30,40,50);
    v_products(3) := 'Product D';
    dbms_output.put_line(v_products(2));
END;

DECLARE
    TYPE TYPE_ID_DPTO IS VARRAY(40) OF INT;
    
    V_IDS TYPE_ID_DPTO := TYPE_ID_DPTO();
    
    V_CONTADOR INT:=0;
BEGIN
    FOR reg IN (SELECT department_id FROM departments) LOOP
        --v_contador := v_contador +1;
        v_ids.Extend;
        v_ids(v_ids.count):= reg.department_id;
        dbms_output.put_line(v_ids(v_ids.count));
    END LOOP;
END;