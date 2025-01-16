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
1. **ubicacion-service**:
   - Maneja la lógica de negocio de los puntos de venta.
   - Utiliza MySQL y Redis.

2. **viaje-service**:
   - Gestiona la lógica de negocio de los costos de viaje entre puntos de venta.
   - Utiliza MySQL y Redis.

3. **Librería Personalizada**:
   - Contiene clases reutilizables comunes a todos los microservicios para evitar duplicación de código.

### Docker
- Contenedores para MySQL, Redis y cada microservicio, lo que facilita el despliegue.

---

## Requisitos Previos

1. **Docker** instalado y configurado.
2. **Java 23**.
3. **Maven** para la gestión de dependencias.

---

## Configuración y Ejecución

### Paso 1: Clonar el Repositorio
```bash
git clone https://github.com/usuario/sistema-calculo-costos.git
cd sistema-calculo-costos
```

### Paso 2: Configurar y ejecutar los Contenedores en Docker
Mysql8 para el microservicio ubicacion-service

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

Redis para el microservicio ubicacion-service

```Redis
docker run -d \
  --name redis-ubicacion-service \
  -p 6379:6379 \
  redis:latest

```

Mysql8 para el microservicio costos-service

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
cd ubicacion-service
mvn clean install
cd ../viaje-service
mvn clean install
```

### Paso 4: Iniciar los Servicios
Ejecuta los miroservicios en el siguiente orden:

1. **eureka-server**.
2. **Iniciar contenedores**.
3. **ubicacion-service**.
4. **ecostos-service**.
---

## Probar el Sistema

### Endpoints Disponibles

1. **ubicacion-service**:
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
     delte /api/v1/ubicacion/{id}
     ```

2. **viaje-service**:
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
