# PCBuild

## Presentación en canva del proyecto 

https://canva.link/3nj0t1b5ke52svf

---

<p align="center">

[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)](https://nginx.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

</p>

<p align="left">
  Preparate para buildear tu pc soñado con un par de pasos para desplegar localmente esta web/app.

  Incluye un servicio (como container en docker) con certbot para futura escalación a SSL/HTTPS.
</p>

---

#  Descripción

Se incluye una configuración lista para ejecutar una aplicación Spring Boot mediante contenedores Docker, utilizando Nginx como reverse proxy.

Estructurado para iniciar facilmente con Docker Compose.

Completamente configurable mediante los archivos como .env, nginx.conf, docker-compose.yml...

---


#  Requisitos

Antes de comenzar asegúrate de tener Docker instalado.

Puedes comprobarlo ejecutando:

```bash
docker --version
```

---

# Instalación

## 1. Descargar la release

Descarga el archivo `.zip` desde la sección de Releases del repositorio.



## 2. Descomprimir el proyecto

```bash
unzip release_v1.zip
```



## 3. Acceder a la carpeta del proyecto

```bash
cd release
```



## 4. Arrancamos docker, los contenedores

```bash
docker compose up -d --build
```

Docker descargará automáticamente las imágenes necesarias y construirá los servicios del proyecto.

---

# Acceso

Una vez iniciado correctamente, la aplicación estará disponible localmente en:

```txt
http://localhost
```

---

# Estructura del proyecto
(En base al release)

```txt
release/
├── compiled/
│   └── pcbuild.jar
├── docker-compose.yml
├── .env
├── Dockerfile
├── pcbuild.sql
└── nginx.conf
```

---

# Desarrollador
[![Pablo Hermosilla](https://img.shields.io/badge/Pablo%20Hermosilla-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/pabhb2-bnnuy)
