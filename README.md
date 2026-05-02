# 📦 Inventario Inteligente API & Data Analysis
[![Docker Hub](https://img.shields.io/badge/Docker_Image-v3-blue?logo=docker)](https://hub.docker.com/r/mateomartin21/inventario-api)

¡Bienvenido/a al repositorio de **Inventario Inteligente**!
Este proyecto es una solución backend integral diseñada para optimizar el control de stock de una tienda, combinando el poder del desarrollo de software tradicional con la ciencia de datos para proporcionar visión financiera en tiempo real.

---

## 🚀 Visión del Proyecto

En el entorno de los negocios digitales, un simple registro de productos no es suficiente. Este sistema no solo expone una API RESTful para la gestión del inventario (CRUD), sino que integra una arquitectura basada en contenedores y un módulo de análisis en Python que transforma los datos crudos en entregables ejecutivos (dashboards gráficos y reportes en Excel).

---

## 🛠️ Stack Tecnológico

**Backend & Frameworks:**
* Java 17
* Spring Boot 3.3 (Spring Web, Spring Data JPA, **Validation**)
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

**Documentación & Testing:**
* Swagger / OpenAPI 3 (SpringDoc)
* JUnit 5 + Mockito

---

## 📊 Características Principales

1. **API RESTful Blindada y CRUD Completo:** Endpoints robustos para la creación, lectura, actualización y eliminación de productos. Implementa **Jakarta Validation** para proteger la integridad de los datos y un **`@ControllerAdvice`** para el manejo global de excepciones (respuestas limpias `404 Not Found` y `400 Bad Request`).

2. **Lógica de Negocio Optimizada (JPQL):** Endpoint específico que detecta productos cuyo stock ha caído por debajo de su umbral mínimo, procesado directamente en PostgreSQL sin cargar datos innecesarios a memoria.

3. **Búsqueda por Categoría:** Filtrado de productos mediante `GET /api/productos/buscar?categoria=` implementado con Spring Data JPA.

4. **Persistencia y Seguridad Profesional:** Base de datos relacional y API ejecutándose en contenedores Docker. Las credenciales están protegidas mediante **variables de entorno (`.env`)**, cumpliendo con los estándares de seguridad de la industria.

5. **Imagen Pública en Docker Hub:** El artefacto del backend está compilado mediante un *Multi-stage build* y publicado para su descarga y ejecución global sin necesidad de compilar el proyecto.

6. **Módulo Ejecutivo de Python:** Un script automatizado que extrae los datos directamente de la base de datos y genera:
   * Resumen financiero en consola (inversión total y productos en riesgo).
   * Dashboard visual con gráfica de barras (estado del stock) y gráfica de pastel (distribución de inversión por categoría).
   * Reporte Excel (`.xlsx`) con 3 hojas: inventario completo, alertas y resumen por categoría.

7. **Tests Unitarios con Mockito:** Tests del `ProductoService` que verifican la lógica de alertas de forma aislada, sin necesidad de base de datos activa.

---

## 🔗 Endpoints Disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/productos` | Listar todos los productos |
| `GET` | `/api/productos/{id}` | Obtener producto por ID |
| `GET` | `/api/productos/buscar?categoria=` | Filtrar por categoría |
| `GET` | `/api/productos/alertas` | Productos bajo stock mínimo |
| `POST` | `/api/productos` | Crear nuevo producto |
| `PUT` | `/api/productos/{id}` | Actualizar producto existente |
| `DELETE` | `/api/productos/{id}` | Eliminar producto |

---

## ⚙️ Instrucciones de Ejecución

### 1. Configuración de Seguridad

Crea un archivo `.env` en la raíz del proyecto basándote en el archivo `.env.example`:

```env
DB_NAME=inventario_db
DB_USER=tu_usuario
DB_PASSWORD=tu_password
```

### 2. Levantar la Infraestructura y la API (Docker)

Asegúrate de tener Docker Desktop ejecutándose. En la terminal, sitúate en la raíz del proyecto y ejecuta:

```bash
docker compose --env-file .env up -d
```

Docker descargará automáticamente la imagen `mateomartin21/inventario-api:v3` y levantará la base de datos PostgreSQL. La API estará escuchando en el puerto `8080`.

Documentación interactiva en: `http://localhost:8080/swagger-ui/index.html`

### 3. Generar Reportes (Módulo Python)

Abre una nueva terminal, instala las dependencias y ejecuta el script:

```bash
pip install pandas psycopg2-binary matplotlib openpyxl python-dotenv
cd scripts
python reporte_inventario.py
```

Los reportes gráficos y en Excel se guardarán en `scripts/reportes_generados/`

---

📸 Evidencia Visual
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/07d7f195-b09a-487f-863a-4ff4e3c56ad4" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/5e9c6d9e-08e1-42d1-8fb4-2c1296bdd8c3" />

---

## 🏗️ Arquitectura del Proyecto

```
src/
├── controller/     # Endpoints REST
├── service/        # Lógica de negocio
├── repository/     # Acceso a datos (JPA + JPQL)
├── model/          # Entidad Producto
├── exception/      # ProductoNotFoundException + GlobalExceptionHandler
└── config/         # SwaggerConfig

scripts/
└── reporte_inventario.py   # Módulo de análisis y reportes
```
