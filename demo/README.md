# Proyecto. Sistema de Nomina

## Requisitos del sistema

- Java 25, version definida en el `pom.xml`
- Maven
- Node.js y npm

## Levantar el sistema con contenedores separados de Docker

El archivo `docker-compose.yml` crea dos redes:

- `web-net`: conecta la aplicación con la red del host.
- `db-net`: red interna que conecta la aplicación con PostgreSQL. PostgreSQL no pertenece a `web-net` y no publica ningún puerto al host.

La aplicación web pertenece a ambas redes, por lo que puede acceder a PostgreSQL usando el nombre de servicio `db`. PostgreSQL solo pertenece a `db-net`, así que no puede iniciar conexiones hacia la red web.

Desde la raíz del repositorio, construye la imagen web y levanta los dos contenedores:

```bash
docker compose up --build -d
```

Para comprobar el estado:

```bash
docker compose ps
docker compose logs -f web
```

El servidor de la página queda disponible en el puerto `8080`:

```
http://localhost:8080/Empleados
```

Para detener los contenedores:

```bash
docker compose down
```

Para detenerlos y borrar también los datos persistidos de PostgreSQL:

```bash
docker compose down -v
```

## Preparacion de Spring Boot

1. Lógica dentro de las vistas manejada con `thymeleaf`
2. Estilos "inline" manejada con DaisyUI

## Ejecucion del fuente de spring

Dentro de la carpeta "demo"

```bash
./mvnw spring-boot:run
```

## Estilos CSS sin conexion (En revision)

Los estilos se compilan localmente y Spring Boot los sirve desde `static/css`.
La instalacion de dependencias y la primera compilacion requieren internet:

```bash
npm install
npm run build:css
```

Despues, la aplicacion puede ejecutarse sin conexion:

```bash
./mvnw spring-boot:run
```




