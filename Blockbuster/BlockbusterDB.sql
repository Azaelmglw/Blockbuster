dropdb Blockbuster
createdb Blockbuster
psql Blockbuster

--Creación de Tablas:
CREATE TABLE Películas(
    PelículaID                  serial          NOT NULL    PRIMARY KEY,
    Nombre_Película             varchar(30)     NOT NULL,
    Formato_Película            varchar(10)     NOT NULL    DEFAULT 'DVD',
    Duración_Película           time            NOT NULL,
    Descripción_Película        text            NOT NULL
);

CREATE TABLE Clientes(
    ClienteID                   serial          NOT NULL    PRIMARY KEY,
    Nombre_Cliente              varchar(50)     NOT NULL,
    Teléfono_Cliente            char(13)        NOT NULL,
    Email_Cliente               varchar(30)     NOT NULL,
    Dirección_Cliente           varchar(40)     NOT NULL
);
-- Finaliza creación de tablas

-- Inserción de Datos.
INSERT INTO Películas(Nombre_Película, Formato_Película, Duración_Película, Descripción_Película)
VALUES
('Inception',                       'DVD',      '02:30:45', 'Texto texto texto texto texto texto texto texto texto'),
('Kingdom of Heaven',               'DVD',      '03:42:17', 'Texto texto texto texto texto texto texto texto texto'),
('Braveheart',                      'Blu-ray',  '03:42:17', 'Texto texto texto texto texto texto texto texto texto'),
('No se aceptan devoluciones',      'Blu-ray',  '02:26:52', 'Texto texto texto texto texto texto texto texto texto'),
('The Others',                      'DVD',      '01:55:43', 'Texto texto texto texto texto texto texto texto texto')
;

INSERT INTO Clientes(Nombre_Cliente, Teléfono_Cliente, Email_Cliente, Dirección_Cliente)
VALUES
('Boris Mandujano Contreras',       '772-105-54-55', 'bomaco@gmail.com',    'Roble #117 Col. Arboledas'),
('Fenton Juarez Carvajal',          '775-725-14-28', 'fejuca@gmail.com',    'Pedregales #331 Col. Acevedo'),
('Cleyton Molina García',           '442-809-75-14', 'clemoga@gmail.com',   'Pinal de Amoles #721 Col. Proletariado'),
('Anastacio Navajas Fernandez',     '442-225-82-17', 'anafe@gmail.com',     'Av. Candiles #315 Col. Valle Real'),
('Aurora Guzman Ríos',              '775-473-20-07', 'augurio@gmail.com',   'Piramides #31 Col. Tutankamon')
;
-- Finaliza inserción de datos.