CREATE TABLE tipo_sala (
    id_tipo         NUMERIC(2)
        GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_tipo     VARCHAR2(25) NOT NULL
);

CREATE TABLE sala (
    id_sala         NUMERIC(3)
        GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    count_pc        NUMERIC(2) NOT NULL,
    capacidad       NUMERIC(2) NOT NULL,
    tipo_sala       NUMERIC(2) NOT NULL,
    CONSTRAINT fk_tipo_sala FOREIGN KEY (tipo_sala) REFERENCES tipo_sala(id_tipo)
);

INSERT INTO tipo_sala (
    nombre_tipo
) VALUES (
    'Laboratorio'
);

INSERT INTO sala (
    id_sala,
    count_pc,
    capacidad,
    tipo_sala
) VALUES (
    304,
    26,
    26,
    (SELECT id_tipo FROM tipo_sala WHERE nombre_tipo = 'Laboratorio')
);