# Geo Service

Microservicio de geolocalización para el proyecto **Sanos y Salvos**. Encargado de gestionar ubicaciones y zonas geográficas donde se reportan las mascotas.

## Objetivo

El Geo Service proporciona una API REST para la gestión de ubicaciones asociadas a mascotas perdidas o encontradas. Permite registrar ubicaciones con coordenadas GPS, asociar zonas geográficas y realizar búsquedas por diferentes criterios espaciales y temporales.

## Arquitectura

### Componentes

- [LocationController](src/main/java/com/sanosysalvos/geoservice/controller/LocationController.java): Endpoints REST principales para gestión de ubicaciones
- [GeoController](src/main/java/com/sanosysalvos/geoservice/controller/GeoController.java): Endpoints para zonas geográficas
- [LocationService](src/main/java/com/sanosysalvos/geoservice/service/LocationService.java): Lógica de negocio para ubicaciones
- [LocationFactory](src/main/java/com/sanosysalvos/geoservice/service/LocationFactory.java): Factory para creación de ubicaciones
- [LocationRepository](src/main/java/com/sanosysalvos/geoservice/repository/LocationRepository.java): Repositorio JPA para ubicaciones
- [ZoneRepository](src/main/java/com/sanosysalvos/geoservice/repository/ZoneRepository.java): Repositorio JPA para zonas
- [Location](src/main/java/com/sanosysalvos/geoservice/model/Location.java): Modelo de entidad ubicación
- [Zone](src/main/java/com/sanosysalvos/geoservice/model/Zone.java): Modelo de entidad zona

## Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/locations` | Listar todas las ubicaciones |
| GET | `/api/locations/{id}` | Obtener ubicación por ID |
| POST | `/api/locations` | Crear nueva ubicación |
| PUT | `/api/locations/{id}` | Actualizar ubicación |
| DELETE | `/api/locations/{id}` | Eliminar ubicación |
| GET | `/api/locations/search/zone/{zone}` | Buscar ubicaciones por zona |
| GET | `/api/locations/search/pet/{petId}` | Buscar ubicaciones por ID de mascota |
| GET | `/api/locations/search/date-range` | Buscar ubicaciones por rango de fechas |
| GET | `/api/locations/totals/zone` | Contar ubicaciones por zona |
| GET | `/health` | Verificar estado del servicio |
| GET | `/zones` | Listar zonas con incidentes |

## Tecnologías

- Java 17
- Spring Boot 3.1.2
- Spring Web (REST)
- Spring Data JPA
- Liquibase
- MySQL
- Maven

## Configuración

```properties
# Puerto del servicio
server.port=3002

# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/geo_service
spring.datasource.username=root
spring.datasource.password=password

# Liquibase
spring.liquibase.enabled=true
```

## Instalación

```bash
mvn clean install
mvn spring-boot:run
```

## Pruebas

```bash
mvn test
```

## Notas

- El servicio gestiona ubicaciones con coordenadas de latitud y longitud.
- Implementa relación con zonas geográficas para organizar territorialmente los reportes.
- Proporciona búsquedas por rango de fechas para análisis temporal de incidentes.
- Incluye endpoint para obtener estadísticas de incidentes por zona.
- Utiliza auditoría automática mediante @PrePersist y @PreUpdate.

---

## Despliegue

Este servicio se despliega automáticamente como parte del repositorio **pet-service** a la instancia **Backend (t3.medium)**.

Ver [Setup Guide](../fullstack-ss-pet-service/README.md#despliegue-en-aws-ec2) para detalles completos de la infraestructura.
