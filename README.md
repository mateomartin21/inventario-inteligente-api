# 📦 Inventario Inteligente API & Data Analysis

[![Docker Hub](https://img.shields.io/badge/Docker_Image-v1-blue?logo=docker)](https://hub.docker.com/r/mateomartin21/inventario-api)

¡Bienvenido/a al repositorio de **Inventario Inteligente**! 
Este proyecto es una solución backend integral diseñada para optimizar el control de stock de una tienda, combinando el poder del desarrollo de software tradicional con la ciencia de datos para proporcionar visión financiera en tiempo real.

---

## 🚀 Visión del Proyecto
En el entorno de los negocios digitales, un simple registro de productos no es suficiente. Este sistema no solo expone una API RESTful para la gestión del inventario (CRUD), sino que integra una arquitectura basada en contenedores y un módulo de análisis en Python que transforma los datos crudos en entregables ejecutivos (Dashboards gráficos y reportes en Excel).

## 🛠️ Stack Tecnológico

**Backend & Frameworks:**
* Java 17
* Spring Boot 3 (Spring Web, Spring Data JPA, **Validation**)
* Hibernate

**Base de Datos & Infraestructura:**
* PostgreSQL
* Docker & Docker Hub (Imagen Pública)
* Docker Compose

**Análisis de Datos & Reportes:**
* Python 3
* Pandas (Manipulación de datos y Excel)
* Matplotlib (Visualización de datos)
* Psycopg2 y Python-dotenv (Conector DB y Seguridad)

**Documentación:**
* Swagger / OpenAPI 3

---

## 📊 Características Principales

1. **API RESTful Blindada y CRUD Completo:** Endpoints robustos para la creación, lectura, actualización y eliminación de productos. Implementa **Jakarta Validation** para proteger la integridad de los datos y un **@ControllerAdvice** para el manejo global de excepciones (ej. respuestas limpias `404 Not Found` y `400 Bad Request`).
2. **Lógica de Negocio Optimizada (JPQL):** Endpoint específico que detecta productos cuyo stock ha caído por debajo de su umbral mínimo procesado directamente en PostgreSQL.
3. **Persistencia y Seguridad Profesional:** Base de datos relacional y API ejecutándose en contenedores Docker. Las credenciales están protegidas mediante **variables de entorno (`.env`)**.
4. **Imagen Pública en Docker Hub:** El artefacto del backend está compilado mediante un *Multi-stage build* y publicado para su descarga global.
5. **Módulo Ejecutivo de Python:** Un script automatizado que extrae los datos y genera un resumen en consola, un dashboard visual y un reporte en Excel (`.xlsx`).

---

## ⚙️ Instrucciones de Ejecución

### 1. Configuración de Seguridad
Crea un archivo `.env` en la raíz del proyecto basándote en el archivo `.env.example` para configurar tus credenciales locales:
```env
DB_NAME=inventario_db
DB_USER=tu_usuario
DB_PASSWORD=tu_password
```
2. Levantar la Infraestructura y la API (Docker)
Asegúrate de tener Docker Desktop ejecutándose. En la terminal, sitúate en la raíz del proyecto y ejecuta este comando para construir y levantar todo el ecosistema (Base de Datos + API en Java):
```bash
docker compose up --build -d
```
Nota: Docker descargará automáticamente la imagen mateomartin21/inventario-api:v1 y levantará la base de datos PostgreSQL. La API estará escuchando en el puerto 8080. Documentación interactiva en: http://localhost:8080/swagger-ui/index.html

3. Generar Reportes (Módulo Python)
Para ejecutar el análisis de datos, abre una nueva terminal, instala las dependencias necesarias y corre el script:
```bash
pip install pandas psycopg2-binary matplotlib openpyxl python-dotenv
cd scripts
python reporte_inventario.py
```
Los entregables gráficos y en Excel se guardarán automáticamente en la carpeta scripts/reportes_generados/
