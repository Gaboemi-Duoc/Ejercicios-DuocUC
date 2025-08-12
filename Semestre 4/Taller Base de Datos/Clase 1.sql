DECLARE
    TYPE tipo_persona is RECORD (
        v_nombre VARCHAR2(30),
        v_apellido VARCHAR2(30),
        v_fecha_nacimiento DATE
    );
    
    reg_persona tipo_persona;
BEGIN
    reg_persona.v_nombre := 'Cesar';
    reg_persona.v_apellido := 'Burgos';
    reg_persona.v_fecha_nacimiento := '01/01/2000';
    
    dbms_output.put_line(reg_persona.v_nombre);
    dbms_output.put_line(reg_persona.v_apellido);
    dbms_output.put_line(reg_persona.v_fecha_nacimiento);

END;
