CREATE TABLE AUDITORIA (
 id NUMBER PRIMARY KEY,
 id_ELEMENTO NUMBER,
 tabla VARCHAR2(30),
 evento VARCHAR2(30),
 user_conectado VARCHAR2(30),
 fecha_evento DATE
);
 
 
create sequence seq_auditoria START WITH 1 INCREMENT BY 1 order;
 
create or replace trigger trg_audita_empleados
after
insert or update or delete on employees
declare
v_evento varchar2(30);
begin
 
 
IF inserting THEN
    v_evento := 'Inserción';
ELSIF updating THEN
    v_evento := 'Actualización';
ELSIF deleting THEN
    v_evento := 'Eliminación';
END IF;
 
 
insert into auditoria
values(seq_auditoria.nextval,null,'employees',v_evento, user, sysdate);
 
 
end;