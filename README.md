# Geo Service

[![CI](https://github.com/Axel-DaMage/fullstack-ss-geo-service/actions/workflows/ci.yml/badge.svg)](https://github.com/Axel-DaMage/fullstack-ss-geo-service/actions/workflows/ci.yml)
[![Docker](https://github.com/Axel-DaMage/fullstack-ss-geo-service/actions/workflows/docker.yml/badge.svg)](https://github.com/Axel-DaMage/fullstack-ss-geo-service/actions/workflows/docker.yml)
![Java](https://img.shields.io/badge/java-17-orange)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.1.2-brightgreen)

Microservice for geolocation management. Handles locations and geographic zones for reported pets.

## Stack

- Java 17, Spring Boot 3.1.2
- Spring Data JPA, Liquibase, MySQL
- Eureka Discovery Client
- Maven, JaCoCo

## Quick start

```bash
mvn clean install
mvn spring-boot:run
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/locations` | List all |
| GET | `/api/locations/{id}` | Get by ID |
| POST | `/api/locations` | Create |
| PUT | `/api/locations/{id}` | Update |
| DELETE | `/api/locations/{id}` | Delete |
| GET | `/api/locations/search/zone/{zone}` | Search by zone |
| GET | `/api/locations/search/pet/{petId}` | Search by pet ID |
| GET | `/api/locations/search/date-range` | Search by date range |
| GET | `/api/locations/totals/zone` | Count by zone |
| GET | `/health` | Health check |

## Tests

```bash
mvn test
mvn clean verify
```

## Database

MySQL `geo_service` with tables: `locations`, `zones`. Managed via Liquibase changelogs.
