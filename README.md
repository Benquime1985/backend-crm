# 👓 CRM Óptica - Sistema de Gestión de Inventario y Clientes

Este es un sistema **Full Stack** diseñado para digitalizar la operación de una óptica, eliminando el uso de papel y centralizando el historial clínico de los pacientes y el control de inventario.

## 🚀 Tecnologías Utilizadas

* **Backend:** Java 21, Spring Boot 4.0, Spring Security (JWT).
* **Frontend:** Angular 20 (Signals & Zoneless).
* **Base de Datos:** PostgreSQL 18.
* **Gestión de Dependencias:** Maven.

## 🛠️ Configuración e Instalación

### Requisitos Previos
* JDK 21 o superior.
* Node.js (v22+).
* PostgreSQL 18.

### Pasos para ejecutar el Backend
1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/Benquime1985/backend-crm.git
    ```
2.  **Configura las Variables de Entorno:**
    Crea un archivo `.env` en la raíz del proyecto con el siguiente formato:
    ```text
    DB_URL=jdbc:postgresql://localhost:5432/tu_base_de_datos
    DB_USER=tu_usuario
    DB_PASSWORD=tu_contraseña
    ```
3.  **Compila y corre el proyecto:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## 📋 Módulos Principales
* **Gestión de Pacientes:** Historial clínico detallado y graduaciones.
* **Inventario:** Control de armazones, micas y accesorios.
* **Ventas:** Generación de notas de remisión y seguimiento de saldos.

## 🔐 Seguridad
El proyecto utiliza **variables de entorno** para proteger credenciales sensibles y **Spring Security** para el control de acceso de usuarios.