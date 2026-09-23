# Proyecto. Sistema de Nomina

## Requisitos del sistema

- Java 25, version definida en el `pom.xml`
- Maven
- Node.js y npm

## Levantar el sistema con docker compose

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

## Levantar el sistema en contenedores y redes diferentes

1. Descargar imagen postgres

```bash
docker pull postgres:18
```

2. Crear imagen de la aplicacion de spring boot

```bash
docker build -t i-spring-nomina ./demo
```

3. Crear redes

```bash
docker network create web-net
docker network create db-net
```

4. Crear volumen para postgres

```bash
docker volume create postgres-data-v18
```

5. Crear contenedores para la base de datos y app Spring

```bash
docker run -d --name c-db-nomina --network db-net -e POSTGRES_DB=nomina -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=1234 --health-cmd="pg_isready -U admin -d nomina" --health-interval=5s --health-timeout=5s --health-retries=10 -v "$(pwd)/bd/bd.sql:/docker-entrypoint-initdb.d/nomina.sql:ro" -v postgres-data-v18:/var/lib/postgresql postgres:18

docker create --name c-spring-nomina --network web-net -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://c-db-nomina:5432/nomina -e SPRING_DATASOURCE_USERNAME=admin -e SPRING_DATASOURCE_PASSWORD=1234 i-spring-nomina
```
6. Conectar contenedor web a contenedor db

```bash
docker network connect db-net c-spring-nomina
```

7. Levantar aplicacion web 

```bash
docker start c-spring-nomina
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




