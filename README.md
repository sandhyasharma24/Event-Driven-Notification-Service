# Event-Driven Notification Service

A Spring Boot backend service for registering events, processing notification workflows, and exposing administrative event statistics.

## Tech Stack

- Java 21
- Spring Boot 3.5.9
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
- Spring Mail
- Spring Validation
- Spring Boot Actuator
- Springdoc OpenAPI
- Maven

## Current API Areas

- Event registration through a REST endpoint
- Administrative event statistics
- Event listing and status-based querying
- Idempotency-key based event registration
- Scheduled processing
- Email notification integration
- Global exception handling

## Configuration

Database and mail credentials are supplied through environment variables rather than committed to the repository.

Example:

```text
DB_URL=jdbc:mysql://localhost:3306/springdatajpa
DB_USERNAME=root
DB_PASSWORD=<your-password>
MAIL_USERNAME=<your-email>
MAIL_PASSWORD=<your-mail-password>
```

## Run Locally

1. Create the MySQL database configured by `DB_URL`.
2. Set the database and mail environment variables.
3. Run:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
./mvnw.cmd spring-boot:run
```

The application is configured to run on port `8081`.

## API Documentation

When the application is running, Springdoc/OpenAPI provides interactive API documentation through the generated Swagger UI.

## Repository

[GitHub](https://github.com/sandhyasharma24/Event-Driven-Notification-Service)
