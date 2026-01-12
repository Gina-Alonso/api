CREATE TABLE medicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento VARCHAR(12) NOT NULL UNIQUE,
    especialidad VARCHAR(50) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(100),
    barrio VARCHAR(50) NOT NULL,
    codigo_postal VARCHAR(8) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);