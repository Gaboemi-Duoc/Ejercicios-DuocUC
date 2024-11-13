CREATE TABLE vendedor (
    id_empleado     NUMBER(5)
        GENERATED ALWAYS AS IDENTITY START WITH 1,
    numrut          NUMBER(10) NOT NULL,
    dvrut           VARCHAR2(1 BYTE) NOT NULL,
    pnombre         VARCHAR2(20) NOT NULL,
    snombre         VARCHAR2(20),
    appaterno       VARCHAR2(20) NOT NULL,
    apmaterno       VARCHAR2(20) NOT NULL,
    fecha_contrato  DATE NOT NULL,
    sueldo_base     NUMBER(7) NOT NULL CONSTRAINT ck_sueldo_base CHECK (sueldo_base > 440000),
    CONSTRAINT pk_vendedor PRIMARY KEY(id_empleado)
);

CREATE TABLE boleta (
    nro_boleta      NUMBER(8)
        GENERATED ALWAYS AS IDENTITY START WITH 1,
    id_empleado     NUMBER(5) NOT NULL,
    fecha_boleta    DATE NOT NULL,
    monto_total     NUMBER(8) NOT NULL CONSTRAINT ck_monto_total CHECK (monto_total > 0),
    CONSTRAINT fk_boleta_vendedor FOREIGN KEY (id_empleado) REFERENCES vendedor (id_empleado),
    PRIMARY KEY (nro_boleta)
);

CREATE TABLE comision_boleta (
    nro_boleta      NUMBER(8) NOT NULL,
    id_empleado     NUMBER(5) NOT NULL,
    monto_comision  NUMBER(8) NOT NULL,
    PRIMARY KEY (nro_boleta, id_empleado),
    CONSTRAINT fk_comision_boleta FOREIGN KEY (nro_boleta) REFERENCES boleta (nro_boleta),
    CONSTRAINT fk_comision_empleado FOREIGN KEY (id_empleado) REFERENCES vendedor (id_empleado)
);

INSERT INTO vendedor (
    numrut,
    dvrut,
    pnombre,
    snombre,
    appaterno,
    apmaterno,
    fecha_contrato,
    sueldo_base
) VALUES (
    16255478,
    'K',
    'PABLO',
    NULL,
    'PÉREZ',
    'SOTO',
    DATE '2020-03-01',
    460000
);

INSERT INTO vendedor (
    numrut,
    dvrut,
    pnombre,
    snombre,
    appaterno,
    apmaterno,
    fecha_contrato,
    sueldo_base
) VALUES (
    20456789,
    '1',
    'CARLOS',
    'ANTONIO',
    'CASTRO',
    'AGUILERA',
    DATE '2020-02-01',
    460000
);

INSERT INTO vendedor (
    numrut,
    dvrut,
    pnombre,
    snombre,
    appaterno,
    apmaterno,
    fecha_contrato,
    sueldo_base
) VALUES (
    23471369,
    '6',
    'MIGUEL',
    'ALEJANDRO',
    'AGUILAR',
    'SOTO',
    DATE '2022-04-15',
    480000
);

INSERT INTO vendedor (
    numrut,
    dvrut,
    pnombre,
    snombre,
    appaterno,
    apmaterno,
    fecha_contrato,
    sueldo_base
) VALUES (
    20697891,
    '0',
    'ANTONIO',
    NULL,
    'PÉREZ',
    'TOLEDO',
    DATE '2021-05-02',
    500000
);

INSERT INTO vendedor (
    numrut,
    dvrut,
    pnombre,
    snombre,
    appaterno,
    apmaterno,
    fecha_contrato,
    sueldo_base
) VALUES (
    20123321,
    'K',
    'JORGE',
    NULL,
    'ÁLVAREZ',
    'RODRÍGUEZ',
    DATE '2022-07-01',
    750000
);

INSERT INTO boleta (
    id_empleado,
    fecha_boleta,
    monto_total
) VALUES (
    (SELECT id_empleado FROM vendedor WHERE id_empleado = 3),
    DATE '2023-08-02',
    75990
);

INSERT INTO boleta (
    id_empleado,
    fecha_boleta,
    monto_total
) VALUES (
    (SELECT id_empleado FROM vendedor WHERE id_empleado = 1),
    DATE '2023-09-03',
    3600
);

INSERT INTO boleta (
    id_empleado,
    fecha_boleta,
    monto_total
) VALUES (
    (SELECT id_empleado FROM vendedor WHERE id_empleado = 1),
    DATE '2023-12-15',
    1590
);

INSERT INTO boleta (
    id_empleado,
    fecha_boleta,
    monto_total
) VALUES (
    (SELECT id_empleado FROM vendedor WHERE id_empleado = 2),
    DATE '2024-02-01',
    850
);

INSERT INTO boleta (
    id_empleado,
    fecha_boleta,
    monto_total
) VALUES (
    (SELECT id_empleado FROM vendedor WHERE id_empleado = 3),
    DATE '2023-08-02',
    7990
);

INSERT INTO comision_boleta (
    nro_boleta,
    id_empleado,
    monto_comision
) VALUES (
    (SELECT nro_boleta FROM boleta WHERE nro_boleta = 1),
    (SELECT vendedor.id_empleado FROM vendedor WHERE vendedor.id_empleado = 3),
    9750
);

INSERT INTO comision_boleta (
    nro_boleta,
    id_empleado,
    monto_comision
) VALUES (
    (SELECT nro_boleta FROM boleta WHERE nro_boleta = 2),
    (SELECT vendedor.id_empleado FROM vendedor WHERE vendedor.id_empleado = 1),
    3600
);

INSERT INTO comision_boleta (
    nro_boleta,
    id_empleado,
    monto_comision
) VALUES (
    (SELECT nro_boleta FROM boleta WHERE nro_boleta = 3),
    (SELECT vendedor.id_empleado FROM vendedor WHERE vendedor.id_empleado = 1),
    15000
);

INSERT INTO comision_boleta (
    nro_boleta,
    id_empleado,
    monto_comision
) VALUES (
    (SELECT nro_boleta FROM boleta WHERE nro_boleta = 4),
    (SELECT vendedor.id_empleado FROM vendedor WHERE vendedor.id_empleado = 2),
    970
);

INSERT INTO comision_boleta (
    nro_boleta,
    id_empleado,
    monto_comision
) VALUES (
    (SELECT nro_boleta FROM boleta WHERE nro_boleta = 5),
    (SELECT vendedor.id_empleado FROM vendedor WHERE vendedor.id_empleado = 3),
    5876
);