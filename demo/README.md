# Practica 2: vistas con Bootstrap y daisyUI

## Requisitos del sistema

- Java 25, version definida en el `pom.xml`
- Maven
- Node.js y npm

## Levantar el sistema con contenedores separados de docker

- Pull de las imagenes `postgres` e `i-spring-empleados`

- Crear la network para comunicacion entre los contenedores

```
docker network create empleados-net  
```

- Creacion de los contenedores:

```BD
docker run -d --name c-db-nomina --network empleados-net \
  -e POSTGRES_DB=nomina \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=1234 \
  -p 5432:5432 \
  -v "$(pwd)/bd/bd.sql:/docker-entrypoint-initdb.d/nomina.sql:ro" \
  postgres
```

```App spring
docker run -d --name c-spring-empleados --network empleados-net \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://c-db-nomina:5432/nomina \
  -e SPRING_DATASOURCE_USERNAME=admin \
  -e SPRING_DATASOURCE_PASSWORD=1234 \
  i-spring-empleados
```

## Preparacion de Spring Boot

1. Lógica dentro de las vistas manejada con `thymeleaf`
2. Estilos "inline" manejada con DaisyUI

## Estilos CSS sin conexion

Los estilos se compilan localmente y Spring Boot los sirve desde `static/css`.
La instalacion de dependencias y la primera compilacion requieren internet:

```bash
npm install
npm run build:css
```

Despues, la aplicacion puede ejecutarse sin conexion:

```bash
./mvnw -o spring-boot:run
```

## Ejecucion del fuente de spring

Dentro de la carpeta "demo"

```bash
./mvnw spring-boot:run
```

El servidor de la pagina levanta en el puerto *8080*
