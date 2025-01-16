# Sistema de Cálculo de Costos entre Puntos de Venta

Este proyecto es un sistema que calcula los costos de viaje entre diferentes puntos de venta y registra la información de cada viaje realizado. La arquitectura implementada está basada en microservicios, utilizando tanto bases de datos relacionales como no relacionales para optimizar la performance de las solicitudes HTTP.

---

## Tecnologías Implementadas

### Backend
- **Java**: Lenguaje principal de programación.
- **Spring Boot**: Framework para desarrollar microservicios.
- **Eureka Server**: Para la gestión del registro y descubrimiento de servicios.
- **Redis**: Base de datos NoSQL para almacenamiento en caché y mejora de la performance.
- **MySQL**: Base de datos relacional para el almacenamiento de datos persistentes.

### Microservicios
1. **puntos-de-venta-service**:
   - Maneja la lógica de negocio de los puntos de venta.
   - Utiliza MySQL y Redis.

2. **viaje-service**:
   - Gestiona la lógica de negocio de los costos de viaje entre puntos de venta.
   - Utiliza MySQL y Redis.

3. **commons-service**:
   - Contiene clases reutilizables comunes a todos los microservicios para evitar duplicación de código.

### Docker
- Contenedores para MySQL y Redis, lo que facilita el despliegue sin tener que instalar, configuar y verificar, versiones necesarias de cada programa.

---

## Requisitos Previos

1. **Docker** instalado.
2. **Java 23**.
3. **Maven** para la gestión de dependencias.

---

## Configuración y Ejecución

### Paso 1: Clonar el Repositorio
```bash

git clone https://github.com/briann-duran/demo-viajes-cache.git
```

### Paso 2: Configurar y ejecutar los Contenedores en Docker
Mysql8 para el microservicio `puntos-de-venta-service`

```Mysql
docker run -d \
  --name mysql-ubicacion-service \
  -e MYSQL_ROOT_PASSWORD=admin \
  -e MYSQL_USER=brian \
  -e MYSQL_PASSWORD=admin \
  -e MYSQL_DATABASE=ubicaciones-bd \
  -p 3308:3306 \
  mysql:8

```

Redis para el microservicio `puntos-de-venta-service`

```Redis
docker run -d \
  --name redis-ubicacion-service \
  -p 6379:6379 \
  redis:latest

```

Mysql8 para el microservicio `costo-service`

```Mysql
docker run -d \
  --name mysql-costos-service \
  -e MYSQL_ROOT_PASSWORD=admin \
  -e MYSQL_USER=brian \
  -e MYSQL_PASSWORD=admin \
  -e MYSQL_DATABASE=viajes-bd \
  -p 3309:3306 \
  mysql:8

```
Para almcenar los datos en caché, se utilza el mismo contenedor del microservicio ubicacion-service, `redis-ubicacion-service`.


### Paso 3: Construir los Microservicios
Compila los microservicios utilizando Maven:
```bash
cd puntos-de-venta-service
mvn clean install
cd ../costo-service
mvn clean install
```

### Paso 4: Iniciar los Servicios
Ejecuta los miroservicios en el siguiente orden:

1. **eureka-server**.
2. **Iniciar contenedores**.
3. **puntos-de-veta-service**.
4. **costo-service**.
---

## Probar el Sistema

### Endpoints Disponibles

1. **puntos-de-veta-service**:
   - Crear un punto de venta:
     ```http
     POST /api/v1/ubicacion
     {
       "nombre": "JUJUY"
     }
     ```
   - Obtener todas las ubicaciones:
     ```http
     GET /api/v1/ubicacion
     ```
    - Eliminar ubicacion por Id:
     ```http
     delete /api/v1/ubicacion/{id}
     ```

2. **costo-service**:
   - Calcular el costo de un viaje (en mantenimiento):
     ```http
     POST /api/v1/viajes
     {
       "provinciaPartida": "BUENOS_AIRES",
       "provinciaLlegada": "CABA",
       "costo": 2025.00
     }
     ```
   - Obtener información de viajes:
     ```http
     GET /api/v1/viajes
     ```

### Visualizar los Logs
Podes monitorear los logs de los microservicios utilizando:
```bash
docker logs -f <nombre-del-contenedor>
```

---
### Verificar los servicios registrados por Eureka
Podes verificar los servicios utilizando la siguiente url: 
```bash
http://localhost:8761
```
## Autor
Brian Durán - [LinkedIn](https://www.linkedin.com/in/brian-duran/)
