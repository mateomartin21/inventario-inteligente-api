# 📦 Inventario Inteligente API & Data Analysis

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
* Docker & Docker Compose

**Análisis de Datos & Reportes:**
* Python 3
* Pandas (Manipulación de datos y Excel)
* Matplotlib (Visualización de datos)
* Psycopg2 y Python-dotenv (Conector DB y Seguridad)

**Documentación:**
* Swagger / OpenAPI 3

---

## 📊 Características Principales

1. **API RESTful Blindada y CRUD Completo:** Endpoints robustos para la creación, lectura, **actualización** y eliminación de productos. Implementa **Jakarta Validation** para proteger la integridad de los datos (evita precios negativos o vacíos) y manejo de excepciones (ej. `404 Not Found`).
2. **Lógica de Negocio Optimizada (JPQL):** Endpoint específico que detecta productos cuyo stock ha caído por debajo de su umbral mínimo. Esta lógica se procesa directamente en la base de datos para garantizar un alto rendimiento.
3. **Persistencia y Seguridad Profesional:** Base de datos relacional PostgreSQL ejecutándose en un contenedor Docker. Las credenciales están protegidas mediante **variables de entorno (`.env`)**, cumpliendo con los estándares de seguridad de la industria.
4. **Módulo Ejecutivo de Python:** Un script automatizado que extrae los datos de la base de datos y genera:
   * Un resumen financiero en consola.
   * Un dashboard con gráficas de barras (estado del stock) y de pastel (distribución de inversión).
   * Un reporte estructurado en Excel (`.xlsx`) con múltiples hojas.
5. **Documentación Interactiva:** Interfaz gráfica generada por Swagger para probar los endpoints sin necesidad de clientes externos, incluyendo filtros de búsqueda por categoría.

---

## ⚙️ Instrucciones de Ejecución

### 1. Configuración de Seguridad
Antes de iniciar, crea un archivo `.env` en la raíz del proyecto basándote en el archivo de ejemplo (`.env.example`) para configurar tus credenciales locales:
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
Nota: La API estará escuchando en el puerto 8080. Puedes acceder a la documentación interactiva en: http://localhost:8080/swagger-ui/index.html

3. Generar Reportes (Módulo Python)
Para ejecutar el análisis de datos, abre una nueva terminal, instala las dependencias necesarias y corre el script:
```bash
pip install pandas psycopg2-binary matplotlib openpyxl python-dotenv
cd scripts
python reporte_inventario.py
```
Los entregables gráficos y en Excel se guardarán automáticamente en la carpeta scripts/reportes_generados/
