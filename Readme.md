# API para la Gestión de Tickets e Incidencias con Autenticación y Autorización 🚀

## Introducción
Esta API está diseñada para la gestión de tickets e incidencias, implementada con autenticación y autorización de usuarios. Utiliza Java 17, Spring Security 6.0.1, MySQL 8, Maven 3.8.1, OpenAPI y Docker.

Con esta API, los usuarios pueden crear, gestionar y asignar tickets con diferentes roles y permisos.

## 🧑‍💻 Roles de Usuario
### 1. `USER`
- Puede crear tickets que serán gestionados.
- Puede añadir comentarios y cambiar el estado de sus tickets.

### 2. `SUPPORT`
- Gestiona los tickets.
- No puede crear ni eliminar tickets.

### 3. `ADMIN`
- Control total sobre la API.
- Crea y gestiona tickets, usuarios y asignaciones.
- Puede cambiar el estado de los tickets y asignarlos a usuarios `SUPPORT`.
- Crea nuevos usuarios con el rol `USER` por defecto.

### Notas sobre creación de usuarios:
- **Username:** Generado automáticamente usando: **primera letra del primer nombre** + **primera letra del primer apellido** + **número de documento**
    Ejemplo: **Juan Pérez Mendoza (10031001)** → `jp10031001`
- **Contraseña:** Asignada automáticamente con el número de identificación del usuario.

## ⚙️ Estados de los Tickets
Los tickets pueden estar en los siguientes estados:
- **ACTIVE:** Estado inicial al crear un ticket.
- **ATTENDING:** El ticket está siendo gestionado.
- **RESOLVED:** El ticket ha sido resuelto.
- **CLOSED:** El ticket ha sido cerrado y no puede ser modificado.

## 🗂️ Secciones de la API
La API está organizada en tres secciones principales:
    1. **Gestión de Tickets** 🎟️
    2. **Autenticación** 🔑
    3. **Registro y Gestión de Usuarios** 👥
---

## 🔑 Autenticación
Para acceder a la API, se debe autenticar el usuario a través del endpoint de login. Al iniciar sesión, se obtiene un token de autenticación necesario para realizar acciones protegidas.

**Endpoint de Login:**  

**POST /auth/login**

**Ejemplo de body:**  
`{ "username": "fmartinez", "password": "1003189162" }` *(Usuario admin)*

El token JWT recibido debe incluirse en los headers de cada solicitud como:  
**Authorization:** `Bearer <token>`

---

## 🎟️ Gestión de Tickets
### Crear, Actualizar y Gestionar Tickets:
- **POST /tickets/new**  
  Crea un nuevo ticket. *(Roles: `USER`, `ADMIN`)*

- **PUT /tickets/update/{ticketId}**  
  Actualiza un ticket por su ID. *(Roles: `USER`, `ADMIN`)*

- **POST /tickets/management**  
  Permite crear comentarios y cambiar el estado de un ticket. *(Roles: Todos)*

### Ver Tickets:
- **GET /tickets/all/page={page}/size={size}**  
  Muestra todos los tickets (paginado).

- **POST /tickets/all/page={page}/size={size}**  
  Muestra tickets dentro de un rango de fechas.

- **GET /tickets/{ticketId}**  
  Busca un ticket por su ID.

### Eliminar Tickets con todos sus comentarios:
- **GET /tickets/delete/{ticketId}**  
  Elimina un ticket. *(Rol: `ADMIN`)*

### Filtrar Tickets:
- **GET /tickets/all/username={username}/page={page}/size={size}**  
  Muestra los tickets creados por un usuario específico.

- **GET /tickets/all/type={ticketType}/page={page}/size={size}**  
  Muestra tickets por tipo (`BUG`, `REQUEST`).

- **GET /tickets/all/status={ticketStatus}/page={page}/size={size}**  
  Muestra tickets según el estado (`ACTIVE`, `ATTENDING`, `RESOLVED`, `CLOSED`).

- **GET /tickets/all/s={search}/page={page}/size={size}**  
  Busca tickets por nombre o descripción.

---

## 👥 Gestión de Usuarios
*Solo accesible para usuarios con rol `ADMIN`.*

### Crear, Actualizar y Ver Usuarios:
- **POST /users/create**  
  Crea un nuevo usuario con rol `USER`.

- **PUT /users/update/{personId}**  
  Actualiza los datos o rol de un usuario.

- **GET /users/{personId}**  
  Busca un usuario por su ID.

- **GET /users/all**  
  Muestra todos los usuarios registrados.

---

# 🚀 Guía de configuración y ejecución

 - **Clonar el repositorio**  

   **git clone https://github.com/devmf19/ticketmanagement.git**  
   **cd ticketmanagement**

- **Instalar las dependencias**  
   Ejecuta el siguiente comando para descargar las dependencias definidas en el archivo **pom.xml**:

   **mvn clean install**

- **Generar el archivo .jar**  
   Generalmente, el archivo **.jar** se genera automáticamente durante el paso anterior y se guarda en el directorio **target**. Sin embargo, si necesitas generarlo manualmente, ejecuta:

   **mvn package**

Una vez se haya creado el **.jar**, ejecuta el siguiente comando de Docker:

**docker-compose up --build**

Cuando todo haya ejecutado correctamente, accede desde el navegador a la siguiente ruta:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)


---

## 🔒 Seguridad
La seguridad de la API está garantizada mediante **Spring Security**.  
- Todos los endpoints requieren autenticación y autorización según el rol del usuario.
- Se utiliza un token **JWT** para autenticar a los usuarios en cada solicitud.
