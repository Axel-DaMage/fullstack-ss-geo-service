# Geo Service

[![Docker](https://github.com/Axel-DaMage/fullstack-ss-geo-service/actions/workflows/docker.yml/badge.svg)](https://github.com/Axel-DaMage/fullstack-ss-geo-service/actions/workflows/docker.yml)
![Java](https://img.shields.io/badge/java-17-orange)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.1.2-brightgreen)

Microservicio para la gestion de ubicaciones geograficas y zonas. Maneja las coordenadas asociadas a mascotas reportadas.

## Stack

- Java 17, Spring Boot 3.1.2
- Spring Data JPA, Hibernate, Liquibase, MySQL
- Eureka Discovery Client
- Maven, JaCoCo
- Docker multi-stage

## Patrones de Diseno

| Patron | Tipo | Donde |
|--------|------|-------|
| **Factory Method** | GoF | `LocationFactory` — crea objetos Location con timestamp y direccion opcional |
| **Singleton** | GoF | `AppConfig` — punto de acceso global a configuracion del servicio |
| **DTO** | GoF | Desacopla entidades JPA de la representacion API |
| **Template Method** | GoF | Entidades JPA con `@PrePersist`/`@PreUpdate` para timestamps automaticos |

## Endpoints

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| GET | `/api/locations` | Listar todas las ubicaciones |
| GET | `/api/locations/{id}` | Obtener ubicacion por ID |
| POST | `/api/locations` | Crear ubicacion |
| PUT | `/api/locations/{id}` | Actualizar ubicacion |
| DELETE | `/api/locations/{id}` | Eliminar ubicacion |
| GET | `/api/locations/search/zone/{zone}` | Buscar por zona |
| GET | `/api/locations/search/pet/{petId}` | Buscar por mascota |
| GET | `/api/locations/search/date-range` | Buscar por rango de fechas |
| GET | `/api/locations/totals/zone` | Totales por zona |
| GET | `/health` | Health check |

## Base de Datos

MySQL `geo_service` con tablas: `locations`, `zones`. Migraciones Liquibase en XML.

**Entidades:**
- `Location` → `locations` (id, mascotaId, latitud, longitud, zona, createdAt) — `@ManyToOne` → Zone
- `Zone` → `zones` (id, nombre, ciudad, conteoIncidencias) — `@OneToMany` → Location

## Pruebas

```bash
mvn clean test
mvn clean verify
```

24 tests en 4 archivos: `LocationServiceTest`, `GeoControllerTest`, `LocationControllerTest`, `LocationFactoryTest`.

## Docker

```bash
docker build -t d4mag3/geo-service .
docker run -p 3002:3002 d4mag3/geo-service
```

Imagen disponible en: `d4mag3/geo-service:latest`

## Variables de Entorno

| Variable | Default | Descripcion |
|----------|---------|-------------|
| `SERVER_PORT` | 3002 | Puerto del servicio |
| `DB_URL` | `jdbc:mysql://db-geo:3306/geo_service` | URL de base de datos |
| `DB_USER` | user | Usuario MySQL |
| `DB_PASSWORD` | password | Password MySQL |
| `EUREKA_URL` | `http://eureka-server:8761/eureka/` | URL de Eureka |
