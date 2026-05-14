DROP DATABASE IF EXISTS pcbuild;

CREATE DATABASE pcbuild;

USE pcbuild;

CREATE TABLE producto(
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    categoria VARCHAR(50),
    precio INT,
    nombre VARCHAR(50) NOT NULL,
    modelo VARCHAR(50),
    stock INT,
    marca VARCHAR(50)
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(30),
    contrasenya VARCHAR(50),
    nombre VARCHAR(40)
);

CREATE TABLE configuracion (
    id_configuracion int PRIMARY KEY AUTO_INCREMENT,
    fecha_creacion DATE,
    nombre VARCHAR (30),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);


CREATE TABLE producto_configuracion(
    id_producto int,
    id_configuracion int,
    PRIMARY KEY (id_producto, id_configuracion),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_configuracion) REFERENCES configuracion(id_configuracion)
);