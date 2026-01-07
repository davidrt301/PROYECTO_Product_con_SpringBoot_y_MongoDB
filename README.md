# API Catálogo de Productos

Este proyecto es una API RESTful desarrollada con **Spring Boot** y **MongoDB** para la gestión eficiente de un catálogo de productos. Permite realizar operaciones CRUD completas, así como búsquedas avanzadas por especificaciones técnicas dinámicas, rangos de precios y etiquetas.

## Tecnologías Utilizadas

*   **Java** (JDK 17+)
*   **Spring Boot 3** (Web, Data MongoDB, Validation)
*   **MongoDB** (Base de datos NoSQL)
*   **OpenAPI / Swagger** (Documentación interactiva)
*   **Maven** (Gestión de dependencias)

## Requisitos Previos

1.  Tener instalado **Java JDK 17** o superior.
2.  Tener una instancia de **MongoDB** en ejecución (por defecto en `localhost:27017`).
3.  (Opcional) Maven instalado, aunque el proyecto incluye el Wrapper (`mvnw`).

## Instalación y Ejecución

1.  **Clonar el repositorio** o descargar el código fuente.
2.  **Navegar a la carpeta del proyecto** en tu terminal.
3.  **Ejecutar la aplicación** usando Maven Wrapper:

    **En Windows:**
    ```bash
    ./mvnw.cmd spring-boot:run
    ```

    **En Linux/Mac:**
    ```bash
    ./mvnw spring-boot:run
    ```

La aplicación iniciará en el puerto **8080**.

## Documentación de la API (Swagger UI)

El proyecto incluye documentación automática con Swagger. Una vez iniciada la aplicación, visita:

**http://localhost:8080/swagger-ui.html**

Desde allí podrás probar todos los endpoints directamente.

## Endpoints Principales

### Productos

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/products` | Obtiene todos los productos. |
| `GET` | `/products/{id}` | Obtiene un producto por su ID. |
| `POST` | `/products` | Crea un nuevo producto. |
| `PUT` | `/products/update/{id}` | Actualiza un producto existente. |
| `DELETE` | `/products/{id}` | Elimina un producto. |

### Búsquedas y Filtros

| Método | Endpoint | Parámetros | Descripción |
| :--- | :--- | :--- | :--- |
| `GET` | `/products/search` | `?palabra=...` | Busca por palabra clave en nombre o descripción. |
| `GET` | `/products/tag` | `?tag=...` | Busca productos por etiqueta. |
| `GET` | `/products/specification` | `?llave=...&valor=...` | Filtra por especificación técnica (ej: RAM, 16GB). |
| `GET` | `/products/price` | `?mallorigal=...&menorigual=...` | Filtra por rango de precio (Mínimo y Máximo). |

> **Nota sobre precios:** Los parámetros de consulta para el rango de precios son `mallorigal` (mayor o igual) y `menorigual` (menor o igual).

## Estructura del JSON (Ejemplo)

```json
{
  "name": "Laptop Gamer",
  "price": 1500.00,
  "general_description": "Laptop de alto rendimiento",
  "specifications": {
    "RAM": "16GB",
    "Processor": "Intel i7"
  },
  "tags": ["tecnologia", "computacion", "gamer"]
}
```

## ⚠️ Manejo de Errores

La API cuenta con un manejo global de excepciones (`GlobalExceptionHandler`) que devuelve respuestas JSON estructuradas para errores de validación (400), recursos no encontrados (404) y conflictos de datos (409).
