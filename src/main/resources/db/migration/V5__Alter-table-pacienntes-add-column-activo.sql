ALTER TABLE pacientes ADD activo TINYINT;
UPDATE pacientes SET activo = 1;
ALTER TABLE pacientes MODIFY activo TINYINT NOT NULL;
