DECLARE
    TYPE PUNTOS IS VARRAY(4) OF INT;
    v_puntos_mult PUNTOS := PUNTOS(250, 300, 550, 700);
    
    CURSOR cur_card_client IS
        SELECT
            nro_tarjeta,
            nro_transaccion,
            fecha_transaccion,
            nombre_tptran_tarjeta,
            monto_transaccion
        FROM transaccion_tarjeta_cliente
            NATURAL JOIN tipo_transaccion_tarjeta
        WHERE EXTRACT(YEAR FROM fecha_transaccion) = EXTRACT(YEAR FROM SYSDATE) - 1
        ORDER BY fecha_transaccion, nro_transaccion;
        
    v_numrun cliente.numrun%type;
    v_dvrun cliente.dvrun%type;
    v_puntos detalle_puntos_tarjeta_catb.puntos_allthebest%type;
BEGIN
    EXECUTE IMMEDIATE 'TRUNCATE TABLE detalle_puntos_tarjeta_catb';

    FOR reg IN cur_card_client LOOP
        --LECTURAA
        SELECT numrun INTO v_numrun FROM tarjeta_cliente
            WHERE nro_tarjeta = reg.nro_tarjeta;
            
        SELECT dvrun INTO v_dvrun FROM cliente
            WHERE numrun = v_numrun;
        --PROCESOS
        v_puntos := TRUNC(reg.monto_transaccion / 100000) * v_puntos_mult(1);
        --
        INSERT INTO detalle_puntos_tarjeta_catb (
            numrun,
            dvrun,
            nro_tarjeta,
            nro_transaccion,
            fecha_transaccion,
            tipo_transaccion,
            monto_transaccion,
            puntos_allthebest
        ) VALUES (
            v_numrun,
            v_dvrun,
            reg.nro_tarjeta,
            reg.nro_transaccion,
            reg.fecha_transaccion,
            reg.nombre_tptran_tarjeta,
            reg.monto_transaccion,
            v_puntos
        );
    END LOOP;
END;

