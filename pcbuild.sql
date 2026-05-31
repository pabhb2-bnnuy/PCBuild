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
    password VARCHAR(250),
    nombre VARCHAR(40),
    rol VARCHAR(20)
);

CREATE TABLE configuracion (
    idconfiguracion INT PRIMARY KEY AUTO_INCREMENT,
    fechacreacion VARCHAR (50),
    nombre VARCHAR (100) UNIQUE,
    idusuario INT NOT NULL,
    FOREIGN KEY (idusuario) REFERENCES usuario(idusuario)
);

CREATE TABLE producto_configuracion(
    idproducto int,
    idconfiguracion int,
    PRIMARY KEY (idproducto, idconfiguracion),
    FOREIGN KEY (idproducto) REFERENCES producto(idproducto) ON DELETE CASCADE,
    FOREIGN KEY (idconfiguracion) REFERENCES configuracion(idconfiguracion) ON DELETE CASCADE
);


-- Usuarios de prueba..
INSERT INTO usuario VALUES (1,'a@a.com','$2a$10$5QVXo79qMRwlNHMt/viXrOD50I/YwZLDblQAo36kDgIKHRL/ZNkR.','a','USER');
INSERT INTO usuario VALUES (2,'test@test.com','$2a$10$5QVXo79qMRwlNHMt/viXrOD50I/YwZLDblQAo36kDgIKHRL/ZNkR.','test','USER');


-- Placa base AMD
INSERT INTO producto (categoria, precio, nombre, modelo, stock, marca) VALUES
('Placa base', 120, 'ASUS Prime B550-Plus AMD', 'B550-PLUS', 30, 'ASUS'),
('Placa base', 150, 'MSI B550-A Pro AMD', 'B550-A PRO', 25, 'MSI'),
('Placa base', 180, 'Gigabyte B550 Aorus Elite AMD', 'B550 AORUS ELITE', 20, 'Gigabyte'),
('Placa base', 200, 'ASUS TUF Gaming B550-Plus AMD', 'TUF B550 PLUS', 22, 'ASUS'),
('Placa base', 220, 'MSI MAG B550 Tomahawk AMD', 'B550 TOMAHAWK', 18, 'MSI'),
('Placa base', 250, 'ASUS ROG Strix B550-F Gaming AMD', 'ROG STRIX B550-F', 15, 'ASUS'),
('Placa base', 140, 'ASRock B550 Pro4 AMD', 'B550 PRO4', 28, 'ASRock'),
('Placa base', 170, 'Gigabyte B450 Aorus Elite AMD', 'B450 AORUS ELITE', 24, 'Gigabyte'),
('Placa base', 280, 'ASUS ROG Strix X570-E Gaming AMD', 'ROG STRIX X570-E', 12, 'ASUS'),
('Placa base', 230, 'MSI MPG B550 Gaming Plus AMD', 'MPG B550 GAMING PLUS', 19, 'MSI');

-- Placa base INTEL
INSERT INTO producto (categoria, precio, nombre, modelo, stock, marca) VALUES
('Placa base', 130, 'ASUS Prime Z690-A INTEL', 'Z690-A', 30, 'ASUS'),
('Placa base', 160, 'MSI Z690-A Pro INTEL', 'Z690-A PRO', 25, 'MSI'),
('Placa base', 190, 'Gigabyte Z690 Aorus Elite INTEL', 'Z690 AORUS ELITE', 20, 'Gigabyte'),
('Placa base', 210, 'ASUS TUF Gaming Z690-Plus INTEL', 'TUF Z690 PLUS', 22, 'ASUS'),
('Placa base', 230, 'MSI MAG Z690 Tomahawk INTEL', 'Z690 TOMAHAWK', 18, 'MSI'),
('Placa base', 260, 'ASUS ROG Strix Z690-F Gaming INTEL', 'ROG STRIX Z690-F', 15, 'ASUS'),
('Placa base', 150, 'ASRock Z690 Pro4 INTEL', 'Z690 PRO4', 28, 'ASRock'),
('Placa base', 180, 'Gigabyte Z790 Aorus Elite INTEL', 'Z790 AORUS ELITE', 24, 'Gigabyte'),
('Placa base', 290, 'ASUS ROG Strix Z790-E Gaming INTEL', 'ROG STRIX Z790-E', 12, 'ASUS'),
('Placa base', 240, 'MSI MPG Z790 Gaming Plus INTEL', 'MPG Z790 GAMING PLUS', 19, 'MSI');


-- Procesadores AMD
INSERT INTO producto (categoria, precio, nombre, modelo, stock, marca) VALUES
('Procesador', 120, 'AMD Ryzen 5 5600X', 'R5 5600X', 40, 'AMD'),
('Procesador', 180, 'AMD Ryzen 7 5800X', 'R7 5800X', 35, 'AMD'),
('Procesador', 300, 'AMD Ryzen 9 5900X', 'R9 5900X', 25, 'AMD'),
('Procesador', 400, 'AMD Ryzen 9 5950X', 'R9 5950X', 20, 'AMD'),
('Procesador', 110, 'AMD Ryzen 5 5600', 'R5 5600', 45, 'AMD'),
('Procesador', 200, 'AMD Ryzen 7 5700X', 'R7 5700X', 32, 'AMD'),

-- Procesadores Intel
('Procesador', 150, 'Intel Core i5-12400', 'i5-12400', 38, 'Intel'),
('Procesador', 220, 'Intel Core i5-12600K', 'i5-12600K', 30, 'Intel'),
('Procesador', 300, 'Intel Core i7-12700K', 'i7-12700K', 25, 'Intel'),
('Procesador', 450, 'Intel Core i9-12900K', 'i9-12900K', 18, 'Intel');


-- RAM
INSERT INTO producto (categoria, precio, nombre, modelo, stock, marca) VALUES
('RAM', 50, 'Corsair Vengeance LPX 8GB', 'CMK8GX4M1E3200C16', 60, 'Corsair'),
('RAM', 90, 'Corsair Vengeance LPX 16GB', 'CMK16GX4M2E3200C16', 55, 'Corsair'),
('RAM', 120, 'Corsair Vengeance RGB 16GB', 'CMWR16GX4M2C3600C18', 45, 'Corsair'),
('RAM', 55, 'G.Skill Aegis 8GB', 'F4-3200C16D-8GAGS', 65, 'G.Skill'),
('RAM', 95, 'G.Skill Ripjaws V 16GB', 'F4-3600C18D-16GVKB', 50, 'G.Skill'),
('RAM', 130, 'G.Skill Trident Z RGB 16GB', 'F4-3600C16D-16GTZR', 40, 'G.Skill'),
('RAM', 60, 'Crucial Ballistix 8GB', 'BL8G32C16U41', 58, 'Crucial'),
('RAM', 100, 'Crucial Ballistix 16GB', 'BL16G32C16U4B', 48, 'Crucial'),
('RAM', 70, 'Kingston Fury Beast 8GB', 'KF432C16BB/8', 62, 'Kingston'),
('RAM', 110, 'Kingston Fury Beast 16GB', 'KF436C18BBK2/16', 52, 'Kingston');


-- Fuentes de alimentación
INSERT INTO producto (categoria, precio, nombre, modelo, stock, marca) VALUES
('Alimentacion', 60, 'Corsair CV650 650W', 'CP-9020215-EU', 40, 'Corsair'),
('Alimentacion', 80, 'Corsair RM650 650W 80+ Gold', 'CP-9020185-EU', 35, 'Corsair'),
('Alimentacion', 100, 'Corsair RM750 750W 80+ Gold', 'CP-9020186-EU', 30, 'Corsair'),
('Alimentacion', 70, 'EVGA 600 W1 600W', '100-W1-0600-LR', 45, 'EVGA'),
('Alimentacion', 90, 'EVGA 650 GD 650W 80+ Gold', '220-GD-0650-V1', 38, 'EVGA'),
('Alimentacion', 110, 'EVGA 750 GD 750W 80+ Gold', '220-GD-0750-V1', 32, 'EVGA'),
('Alimentacion', 65, 'Seasonic S12III 500W', 'SS-500BB2-EU', 42, 'Seasonic'),
('Alimentacion', 85, 'Seasonic FOCUS GX-650 650W', 'FX-650-GX', 36, 'Seasonic'),
('Alimentacion', 105, 'Seasonic FOCUS GX-750 750W', 'FX-750-GX', 30, 'Seasonic'),
('Alimentacion', 150, 'be quiet! Straight Power 11 750W', 'BP7L750', 25, 'be quiet!');


-- Tarjetas graficas
INSERT INTO producto (categoria, precio, nombre, modelo, stock, marca) VALUES
('Grafica', 300, 'NVIDIA GTX 1660 Super', 'GTX1660S-OC', 35, 'NVIDIA'),
('Grafica', 400, 'NVIDIA RTX 3060', 'RTX3060-12G', 30, 'NVIDIA'),
('Grafica', 550, 'NVIDIA RTX 3060 Ti', 'RTX3060TI-8G', 25, 'NVIDIA'),
('Grafica', 700, 'NVIDIA RTX 3070', 'RTX3070-8G', 20, 'NVIDIA'),
('Grafica', 900, 'NVIDIA RTX 3080', 'RTX3080-10G', 15, 'NVIDIA'),
('Grafica', 280, 'AMD RX 6600', 'RX6600-8G', 38, 'AMD'),
('Grafica', 380, 'AMD RX 6600 XT', 'RX6600XT-8G', 32, 'AMD'),
('Grafica', 500, 'AMD RX 6700 XT', 'RX6700XT-12G', 28, 'AMD'),
('Grafica', 650, 'AMD RX 6800', 'RX6800-16G', 22, 'AMD'),
('Grafica', 850, 'AMD RX 6800 XT', 'RX6800XT-16G', 18, 'AMD');


-- Alcenamiento...
INSERT INTO producto (categoria, precio, nombre, modelo, stock, marca) VALUES
('Almacenamiento', 40, 'Kingston A400 240GB SSD', 'SA400S37/240G', 70, 'Kingston'),
('Almacenamiento', 60, 'Kingston A400 480GB SSD', 'SA400S37/480G', 65, 'Kingston'),
('Almacenamiento', 80, 'Kingston A400 960GB SSD', 'SA400S37/960G', 60, 'Kingston'),
('Almacenamiento', 50, 'Crucial MX500 500GB SSD', 'CT500MX500SSD1', 58, 'Crucial'),
('Almacenamiento', 90, 'Crucial MX500 1TB SSD', 'CT1000MX500SSD1', 52, 'Crucial'),
('Almacenamiento', 120, 'Samsung 970 EVO Plus 1TB NVMe', 'MZ-V7S1T0B/AM', 45, 'Samsung'),
('Almacenamiento', 200, 'Samsung 980 PRO 1TB NVMe', 'MZ-V8P1T0B/AM', 40, 'Samsung'),
('Almacenamiento', 300, 'Samsung 980 PRO 2TB NVMe', 'MZ-V8P2T0B/AM', 35, 'Samsung'),
('Almacenamiento', 35, 'WD Blue 1TB HDD', 'WD10EZEX', 75, 'WD'),
('Almacenamiento', 60, 'WD Blue 2TB HDD', 'WD20EZEX', 68, 'WD');


-- Chasis de PC
INSERT INTO producto (categoria, precio, nombre, modelo, stock, marca) VALUES
('Gabinete', 50, 'Corsair 4000D Airflow', 'CC-9011200-WW', 40, 'Corsair'),
('Gabinete', 70, 'Corsair 5000D Airflow', 'CC-9011210-WW', 35, 'Corsair'),
('Gabinete', 45, 'NZXT H510', 'CA-H510B-B1', 42, 'NZXT'),
('Gabinete', 60, 'NZXT H510 Elite', 'CA-H510E-B1', 38, 'NZXT'),
('Gabinete', 55, 'Fractal Design Meshify C', 'FD-C-MES1A-06', 36, 'Fractal Design'),
('Gabinete', 80, 'Fractal Design Meshify S36', 'FD-C-MES3A-01', 30, 'Fractal Design'),
('Gabinete', 40, 'Cooler Master MasterBox Q300L', 'MCB-Q300L-KANN0', 45, 'Cooler Master'),
('Gabinete', 65, 'Cooler Master MasterBox MB511', 'MCB-MB511-KGNN0-S1', 32, 'Cooler Master'),
('Gabinete', 75, 'Lian Li Lancool 215', 'Lancool-215', 28, 'Lian Li'),
('Gabinete', 90, 'Lian Li O11 Dynamic', 'O11D-EUR', 25, 'Lian Li');