/* En otra coneccion 
CREATE TABLE AUDITORIA (
 id NUMBER PRIMARY KEY,
 pk_tabla NUMBER,
 valor_antiguo NUMBER,
 valor_nuevo NUMBER,
 tabla varchar2(30),
 columna_afectada varchar2(30),
 fecha DATE
);
*/

 
CREATE OR REPLACE TRIGGER trg_salario_empleado
AFTER
UPDATE OF salary ON employees
FOR EACH ROW
 
BEGIN
    INSERT INTO AUDITORIA
    VALUES(
        seq_auditoria.NEXTVAL,
        :OLD.EMPLOYEE_ID,
        :OLD.SALARY,
        :NEW.SALARY,
        'EMPLOYEES',
        'SALARY',
        SYSDATE
    );
END;
/

-- Trigger para asegurar que un país tenga al menos una ciudad asociada en la tabla LOCATIONS al insertarse un nuevo país.
CREATE OR REPLACE TRIGGER trg_check_country_location
BEFORE INSERT ON COUNTRIES
FOR EACH ROW
DECLARE location_count INT;

BEGIN
    -- Verificar si el país tiene al menos una ciudad asociada
    SELECT COUNT(*) INTO location_count
    FROM LOCATIONS
    WHERE COUNTRY_ID = :NEW.COUNTRY_ID;
    IF location_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'El país debe tener al menos una ciudad asociada.');
    END IF;
END;
/
