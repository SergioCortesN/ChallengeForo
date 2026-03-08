CREATE DATABASE forohub;
USE forohub;

-- Tabla Perfil
CREATE TABLE perfil (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla Usuario
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correoElectronico VARCHAR(150) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

-- Relación muchos a muchos Usuario - Perfil
CREATE TABLE usuario_perfil (
    usuario_id INT,
    perfil_id INT,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

-- Tabla Curso
CREATE TABLE curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    categoria VARCHAR(100)
);

-- Tabla Topico
CREATE TABLE topico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50),
    autor INT,
    curso INT,
    respuestas INT DEFAULT 0,

    FOREIGN KEY (autor) REFERENCES usuario(id),
    FOREIGN KEY (curso) REFERENCES curso(id)
);

-- Tabla Respuesta
CREATE TABLE respuesta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico INT,
    fechaCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    autor INT,
    solucion BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (topico) REFERENCES topico(id),
    FOREIGN KEY (autor) REFERENCES usuario(id)
);