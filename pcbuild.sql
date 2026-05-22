DROP DATABASE IF EXISTS pcbuild;

CREATE DATABASE pcbuild;

USE pcbuild;

CREATE TABLE producto(
    idproducto INT PRIMARY KEY AUTO_INCREMENT,
    categoria VARCHAR(50),
    precio INT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    modelo VARCHAR(50),
    stock INT,
    marca VARCHAR(50)
);

CREATE TABLE usuario (
    idusuario INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(250) UNIQUE,
    nombre VARCHAR(40)
);

CREATE TABLE configuracion (
    idconfiguracion int PRIMARY KEY AUTO_INCREMENT,
    fecha_creacion VARCHAR (50),
    nombre VARCHAR (100) UNIQUE,
    idusuario INT NOT NULL,
    FOREIGN KEY (idusuario) REFERENCES usuario(idusuario) ON DELETE CASCADE
);


CREATE TABLE producto_configuracion(
    idproducto int,
    idconfiguracion int,
    PRIMARY KEY (idproducto, idconfiguracion),
    FOREIGN KEY (idproducto) REFERENCES producto(idproducto) ON DELETE CASCADE,
    FOREIGN KEY (idconfiguracion) REFERENCES configuracion(idconfiguracion) ON DELETE CASCADE
);