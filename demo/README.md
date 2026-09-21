# Practica 2: vistas con Bootstrap y daisyUI

## Requisitos del sistema

- Java 25, version definida en el `pom.xml`
- Maven
- Node.js y npm

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


## Ejecucion

Dentro de la carpeta "demo"

```bash
./mvnw spring-boot:run
```

El servidor de la pagina levanta en el puerto *8080*
