DROP TABLE IF EXISTS empleado;
DROP TABLE IF EXISTS comuna;

CREATE TABLE comuna (
    id_comuna INT(3) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(25) NOT NULL,
    CONSTRAINT un_comuna UNIQUE (nombre),   -- Esto mantiene la restricción UNIQUE en el nombre
    PRIMARY KEY(id_comuna)
);


CREATE TABLE empleado (
    numrut_emp INT(10) NOT NULL,              -- Usamos INT para el número de RUT
    dvrut_emp VARCHAR(1) NOT NULL,
    appaterno_emp VARCHAR(15) NOT NULL,
    apmaterno_emp VARCHAR(15) NOT NULL,
    nombre_emp VARCHAR(25) NOT NULL,
    direccion_emp VARCHAR(60) NOT NULL,
    fonofijo_emp VARCHAR(15) NOT NULL,
    celular_emp VARCHAR(15),
    fecnac_emp DATE,
    sueldo_emp INT(7) NOT NULL,               -- Sueldo, como un número entero
    id_comuna INT(3) NOT NULL,
    CONSTRAINT pk_empleado PRIMARY KEY (numrut_emp),
    CONSTRAINT un_fono_empleado UNIQUE (fonofijo_emp),   -- Único para fonofijo
    CONSTRAINT nn_apellido_empleado CHECK (appaterno_emp IS NOT NULL), -- Condición en MySQL para verificar que no sea NULL
    CONSTRAINT fk_empleado_comuna FOREIGN KEY (id_comuna) REFERENCES comuna(id_comuna)
);


-- Insertar datos en comuna, el valor de id_comuna se generará automáticamente
INSERT INTO comuna (nombre) VALUES 
    ('Melipilla'),
    ('Santiago'),
    ('Viña del Mar'),
    ('Ñuñoa'),
    ('Vitacura'),
    ('La Reina'),
    ('La Florida'),
    ('Maipú'),
    ('Lo Barnechea'),
    ('Macul');


INSERT INTO empleado (numrut_emp, dvrut_emp, appaterno_emp, apmaterno_emp, nombre_emp, direccion_emp, fonofijo_emp, celular_emp, fecnac_emp, sueldo_emp, id_comuna) VALUES
(11649964, '0', 'GALVEZ', 'CASTRO', 'MARTA', 'CLOVIS MONTERO 0260 D/202', '23417556', '25273328', '2012-12-19', 1515239, 2),
(12113369, '4', 'ROMERO', 'DIAZ', 'NANCY', 'TENIENTE RAMON JIMENEZ 4753', '25631567', '22623877', '1968-09-06', 2710153, 1),
(12456905, '1', 'CANALES', 'BASTIAS', 'JORGE', 'GENERAL CONCHA PEDREGAL #885', '27779827', '27413395', '1957-12-21', 2945675, 3),
(12466553, '2', 'VIDAL', 'PEREZ', 'TERESA', 'FCO. DE CAMARGO 14515 D/14', '28583700', '28122603', '1969-11-01', 1202614, 4),
(11745244, '3', 'VENEGAS', 'SOTO', 'KARINA', 'ARICA 3850', '27790734', '27494190', '1963-06-23', 1439042, 5),
(11999100, '4', 'CONTRERAS', 'CASTILLO', 'CLAUDIO', 'ISABEL RIQUELME 6075', '27764142', '25232677', '1966-03-15', 364163, 2),
(12888868, '5', 'PAEZ', 'MACMILLAN', 'JOSE', 'FERNANDEZ CONCHA 500', '22399493', '27735417', '1964-07-25', 1896155, 1),
(12811094, '6', 'MOLINA', 'GONZALEZ', 'PAULA', 'PJE.TIMBAL 1095 V/POMAIRE', '25313830', NULL, '1978-08-26', 1757577, 1),
(14255602, '7', 'MUÑOZ', 'ROJAS', 'CARLOTA', 'TERCEIRA 7426 V/LIBERTAD', '26490093', '27414886', '1982-09-27', 2658577, 2),
(11630572, '8', 'ARAVENA', 'HERBAGE', 'GUSTAVO', 'FERNANDO DE ARAGON 8420', '25588481', '26256661', '1966-03-15', 1957095, 4),
(11636534, '9', 'ADASME', 'ZUÑIGA', 'LUIS', 'LITTLE ROCK 117 V/PDTE.KENNEDY', '26483081', '26213403', '1973-10-29', 1614934, 3),
(12272880, 'K', 'LAPAZ', 'SEPULVEDA', 'MARCO', 'GUARDIA MARINA. RIQUELME 561', '26038967', '22814034', '1989-10-01', 1352596, 2),
(11846972, '5', 'OGAZ', 'VARAS', 'MARCO', 'OVALLE Nº5798 V/ OHIGGINS', '27763209', '27377830', '1959-12-31', 353590, 1),
(14283083, '6', 'MONDACA', 'COLLAO', 'AUGUSTO', 'NUEVA COLON Nº1152', '27357104', '25376873', '1989-10-01', 1144245, 1),
(14541837, '7', 'ALVAREZ', 'RIVERA', 'MARCO', 'HONDURAS B/8908 D/102 L.BRISAS', '22875902', '25292497', '1977-01-02', 1541418, 3),
(12482036, '8', 'OLAVE', 'CASTILLO', 'ADRIAN', 'ELISA CORREA 188', '22888897', '28441077', '1956-01-03', 1068086, 5),
(12468081, '9', 'SANCHEZ', 'GONZALEZ', 'PAOLA', 'AV.OSSA 01240 V/MI VIÑITA', '25273328', '25581593', '1987-10-04', 1330355, 5),
(12260812, '0', 'RIOS', 'ZUÑIGA', 'RAFAEL', 'LOS CASTAÑOS 1427 VILLA C.C.U.', '26410462', '28501857', '1991-11-05', 367056, 5),
(12899759, '1', 'CACERES', 'JIMENEZ', 'ERIKA', 'PJE.NAVARINO 15758 V/P.DE OÑA', '28593881', '22650316', '1974-01-06', 2281415, 2),
(12868553, '2', 'CHACON', 'AMAYA', 'PATRICIA', 'LO ERRAZURIZ 530 V/EL SENDERO', '25577963', NULL, '1985-11-07', 1723055, 3),
(12648200, '3', 'NARVAEZ', 'MUÑOZ', 'LUIS', 'AMBRIOSO OHIGGINS 2010', '27742268', '25317272', '1993-10-08', 1966613, 4),
(11670042, '5', 'GONGORA', 'DEVIA', 'VALESKA', 'PASAJE VENUS 2765', '23244270', '26224817', '1975-01-10', 1635086, 5),
(12642309, 'K', 'NAVARRO', 'SANTIBAÑEZ', 'JUAN', 'SANTA ELENA 300 V/LOS ALAMOS', '27441530', '25342599', '1986-10-11', 1659230, 5);



CREATE TABLE alumno (
    id_alumno INT(8) NOT NULL AUTO_INCREMENT,  -- Añadimos la columna id_alumno como AUTO_INCREMENT
    run INT(8),
    dv VARCHAR(1),
    nombre VARCHAR(20),
    apellido VARCHAR(20),
    fecha_nacimiento DATE,
    PRIMARY KEY (id_alumno)  -- Definimos id_alumno como la clave primaria
);
