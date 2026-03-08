# ForoHub API - Challenge Alura & Oracle Next Education

ForoHub es una **API REST** desarrollada en Java utilizando **Spring Boot**. Proyecto actualizado para Java 25, con seguridad JWT, migraciones Flyway y documentación OpenAPI (Swagger UI).

---

### 🚀 Funcionalidades Principales
* **CRUD de Tópicos:** Creación, consulta, actualización y eliminación de discusiones.
* **Seguridad:** Autenticación y autorización mediante **Spring Security**.
* **JWT:** Generación y validación de tokens con la librería **Auth0 JWT**.
* **Persistencia:** Uso de **MySQL** para almacenamiento de datos.
* **Migraciones:** Gestión de esquemas de base de datos con **Flyway**.
* **Validaciones:** Reglas de negocio aplicadas con **Jakarta Validation**.

---

### 🛠️ Stack Tecnológico
* **Java 25**
* **Spring Boot** (configurado en pom)
* **Spring Data JPA**
* **Spring Security**
* **MySQL**
* **Maven** (Gestor de dependencias)

---

### 🔐 Seguridad y Autenticación

La API implementa un modelo de autenticación **Stateless**. Para acceder a las funcionalidades, sigue estos pasos:

1. **Login:** Realiza una petición `POST` al endpoint `/auth/login` enviando `email` y `password` en el cuerpo JSON.
2. **Obtención del Token:** El servidor valida las credenciales y responde con un objeto JSON que contiene el JWT.
3. **Peticiones Autorizadas:** En cada petición posterior (GET, POST, etc.), incluye el token en el encabezado:
   `Authorization: Bearer <tu_token_aqui>`

---

### 📌 Endpoints principales

| Método | URL | Descripción | Acceso |
| :--- | :--- | :--- | :--- |
| `POST` | `/auth/login` | Autenticación del usuario (recibe `email` y `password`) | Público |
| `GET` | `/topics` | Listar todos los topics | Protegido |
| `POST` | `/topics` | Crear un nuevo topic | Protegido |
| `GET` | `/topics/{id}`| Detalles de un topic específico | Protegido |
| `PUT` | `/topics/{id}`| Actualizar información de un topic | Protegido |
| `DELETE` | `/topics/{id}`| Eliminar un topic | Protegido |

---

**Ejecución rápida con Docker (recomendada)**

1. Levanta la base de datos y la aplicación con Docker Compose:

```bash
docker-compose up --build
```

2. Variables usadas por `docker-compose.yml` y `application.properties`:

- `DB_URL` - URL JDBC hacia la base de datos en el contenedor
- `SB_USER` - usuario de la base de datos
- `DB_PASSWORD` - contraseña de la base de datos
- `JWT_SECRET` - secreto para firmar tokens JWT

3. Swagger / OpenAPI:

Accede a `http://localhost:8080/swagger-ui.html` (o `/swagger-ui/index.html`) para explorar la API y ver la documentación interactiva generada a partir de las anotaciones OpenAPI en los controladores.

4. Ejecutar localmente sin Docker:

```bash
mvn -DskipTests package
java -jar target/forohub-0.0.1-SNAPSHOT.jar
```

---

### 👤 Autor
Desarrollado por **Josue Leon** - [JLeonG09](https://github.com/JLeonG09)