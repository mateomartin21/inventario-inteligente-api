# 📦 Inventario Inteligente API & Data Analysis

¡Bienvenido/a al repositorio de **Inventario Inteligente**! 
Este proyecto es una solución backend integral diseñada para optimizar el control de stock de una tienda, combinando el poder del desarrollo de software tradicional con la ciencia de datos para proporcionar visión financiera en tiempo real.

---

## 🚀 Visión del Proyecto
En el entorno de los negocios digitales, un simple registro de productos no es suficiente. Este sistema no solo expone una API RESTful para la gestión del inventario (CRUD), sino que integra una arquitectura basada en contenedores y un módulo de análisis en Python que transforma los datos crudos en entregables ejecutivos (Dashboards gráficos y reportes en Excel).

## 🛠️ Stack Tecnológico

**Backend & Frameworks:**
* Java 17
* Spring Boot 3 (Spring Web, Spring Data JPA)
* Hibernate

**Base de Datos & Infraestructura:**
* PostgreSQL
* Docker & Docker Compose

**Análisis de Datos & Reportes:**
* Python 3
* Pandas (Manipulación de datos y Excel)
* Matplotlib (Visualización de datos)
* Psycopg2 (Conector DB)

**Documentación:**
* Swagger / OpenAPI 3

---

## 📊 Características Principales

1. **API RESTful Blindada:** Endpoints robustos para la creación, lectura y eliminación de productos, con manejo de excepciones (ej. respuestas `404 Not Found` personalizadas).
2. **Lógica de Negocio Automática:** Endpoint específico que filtra y detecta productos cuyo stock actual ha caído por debajo de su umbral mínimo de seguridad.
3. **Persistencia Profesional:** Base de datos relacional PostgreSQL ejecutándose en un contenedor de Docker para garantizar la persistencia e integridad de los datos.
4. **Módulo Ejecutivo de Python:** Un script automatizado que extrae los datos de la base de datos y genera:
   * Un resumen financiero en consola.
   * Un dashboard con gráficas de barras (estado del stock) y de pastel (distribución de inversión).
   * Un reporte estructurado en Excel (`.xlsx`) con múltiples hojas.
5. **Documentación Interactiva:** Interfaz gráfica generada por Swagger para probar los endpoints sin necesidad de clientes externos.

---

## ⚙️ Instrucciones de Ejecución

### 1. Levantar la Infraestructura (Base de Datos)
Asegúrate de tener Docker Desktop ejecutándose. En la terminal, sitúate en la raíz del proyecto y ejecuta:
```bash
docker compose up -d
```
Esto descargará la imagen de PostgreSQL y levantará la base de datos inventario_db en el puerto 5432.

2. Ejecutar la API (Java)
Puedes ejecutar la aplicación directamente desde tu IDE (ej. VS Code, IntelliJ) ejecutando el archivo InventApiApplication.java o mediante la terminal:
```bash
./mvnw spring-boot:run
```
Nota: La API estará escuchando en el puerto 8080. Puedes acceder a la documentación interactiva en: http://localhost:8080/swagger-ui/index.html

3. Generar Reportes (Módulo Python)
Para ejecutar el análisis de datos, abre una nueva terminal, instala las dependencias necesarias y corre el script:
```bash
pip install pandas psycopg2-binary matplotlib openpyxl
cd scripts
python reporte_inventario_pro.py
```
Los entregables se guardarán automáticamente en la carpeta scripts/reportes_generados/
