DECLARE
    CURSOR cur_paises IS

    SELECT
      country_id,
      country_name nombre_pais,
      region_id
    FROM
      countries;
BEGIN
    -- Alternativa SIMPLE LOOP
    -- OPEN cur_paises;
    -- LOOP
    --     FETCH cur_paises INTO v_nombre_pais;
    --     EXIT WHEN cur_paises%NOTFOUND;
    --     dbms_output.put_line(upper(v_nombre_pais));
    -- END LOOP;
    -- CLOSE cur_paises;

    -- Alternativa FOR LOOP
    FOR reg IN cur_paises LOOP
        dbms_output.put_line(reg.nombre_pais);
    END LOOP;


END;