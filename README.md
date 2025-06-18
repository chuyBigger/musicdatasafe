# 🎵 MusicDataSafe

Aplicación Java + Spring Boot para gestionar información musical obtenida por inteligencia artificial (Gemini) y almacenada de forma estructurada en una base de datos PostgreSQL.  
La información consultada (canciones, artistas, álbumes) se guarda y manipula completamente desde la base de datos usando JPA.

---

## 📌 Características principales

- Consulta de canciones mediante inteligencia artificial (Google Gemini).
- Registro automático en la base de datos de:
  - Canciones
  - Álbumes
  - Artistas
- Asociación entre entidades (relaciones muchos-a-muchos y uno-a-muchos).
- Acceso a la base de datos mediante Spring Data JPA (Hibernate).
- Organización modular: cada clase tiene una responsabilidad clara.
- Basado en la arquitectura MVC.

---

## 🧱 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Hibernate
- Google Gemini API (para generación de datos musicales)
- Maven

---

## 📂 Estructura del proyecto

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com.aluracursos.musicdatasafe
 ┃ ┃ ┃ ┣ 📂model → Entidades: Cancion, Album, Artista
 ┃ ┃ ┃ ┣ 📂repository → Interfaces JpaRepository para CRUD
 ┃ ┃ ┃ ┣ 📂service → Servicios para consumir APIs y parsear JSON
 ┃ ┃ ┃ ┗ 📂principal → Funciones del menú principal y lógica de flujo
 ┃ ┣ 📂resources
 ┃ ┃ ┗ application.properties → Configuración de DB y puertos
```

---

## ⚙️ Base de datos

- El sistema está 100% conectado a una base de datos PostgreSQL.
- Las operaciones como guardar, buscar, actualizar y eliminar se realizan usando repositorios JPA.
- Relación entre tablas:
  - `Cancion` tiene muchos `Artista`
  - `Album` tiene muchas `Cancion`
  - `Artista` puede estar en muchos `Album`

> 💾 Asegúrate de tener PostgreSQL corriendo y la base de datos configurada correctamente en `application.properties`.

---

## 🔍 Ejemplo de flujo

1. El usuario busca una canción por nombre (y opcionalmente por artista).
2. Se envía la consulta a Gemini.
3. Gemini responde con un JSON estructurado.
4. El sistema convierte ese JSON en un objeto `Cancion`.
5. Se guarda en la base de datos, relacionando automáticamente con `Artista` y `Album` si existen.

---

## ▶️ Cómo ejecutar

1. Clona el repositorio:
```bash
git clone https://github.com/tuusuario/MusicDataSafe.git
```

2. Configura tu archivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/musicdatasafe
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. Ejecuta el proyecto desde tu IDE o consola:
```bash
./mvnw spring-boot:run
```

---

## 💡 Ideas futuras

- API REST para exponer los datos musicales.
- Interfaz web o móvil.
- Registro de usuarios y listas personalizadas.
- Análisis musical con IA.

---

## 🙌 Autor

Proyecto desarrollado por **Jesús Medina Casas** como parte del programa **ONE - Oracle Next Education** y en proceso de mejora continua.

---

## 🛠️ Contribuciones

¿Quieres contribuir? Bienvenido. Puedes abrir un PR o sugerir mejoras por issues.

---

## 📜 Licencia

MIT