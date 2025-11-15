-- Eliminar la base de datos anterior si existe
DROP DATABASE IF EXISTS gestion_cursos;

-- Crear nueva base de datos
CREATE DATABASE gestion_cursos CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- Usar la base de datos
USE gestion_cursos;

-- Tabla de usuarios
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    rol ENUM('Administrador', 'Alumno') NOT NULL
);

-- Tabla de cursos
CREATE TABLE curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    cupo INT NOT NULL
);

-- Tabla de inscripciones
CREATE TABLE inscripcion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_curso INT NOT NULL,
    fecha_inscripcion DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES curso(id) ON DELETE CASCADE
);

-- Insertar usuario administrador por defecto
INSERT INTO usuario (nombre, apellido, email, password, rol)
VALUES ('Admin', 'Principal', 'admin@admin.com', 'admin', 'Administrador');

-- Insertar algunos cursos de prueba
INSERT INTO curso (nombre, descripcion, cupo)
VALUES
('Taller de Java', 'Curso básico de programación en Java', 25),
('Diseño UX/UI', 'Introducción al diseño de interfaces', 20),
('Excel Avanzado', 'Funciones y análisis de datos', 30);

-- Insertar un alumno de prueba
INSERT INTO usuario (nombre, apellido, email, password, rol)
VALUES ('Juan', 'Pérez', 'juan@example.com', '1234', 'Alumno');
