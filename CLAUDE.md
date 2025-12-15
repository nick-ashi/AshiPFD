# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

AshiPFD is a personal finance dashboard application built with Spring Boot 4.0.0 and Java 21. The application uses PostgreSQL for data persistence and includes Spring Security for authentication/authorization.

**Key Technologies:**
- Spring Boot 4.0.0
- Java 21
- PostgreSQL database
- Spring Data JPA with Hibernate
- Spring Security
- Lombok for code generation
- Jakarta Validation API
- Maven build system

## Common Development Commands

### Building and Running

```bash
# Build the project (from AshiPFD directory)
./mvnw clean install

# Run the application
./mvnw spring-boot:run

# Run with Spring DevTools hot reload (enabled by default in dev)
./mvnw spring-boot:run

# Package as JAR
./mvnw package
```

### Testing

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=AshiPfdApplicationTests

# Run tests with coverage
./mvnw test jacoco:report
```

### Database

The application connects to a PostgreSQL database named `finance_dashboard` running on `localhost:5432`. Database configuration is in `src/main/resources/application.properties`.

Hibernate is configured with `ddl-auto=update`, which automatically updates the database schema based on entity changes during development.

## Architecture and Code Structure

### Package Organization

The codebase follows standard Spring Boot layered architecture under `com.finance.ashipfd`:

- `model/` - JPA entities representing database tables (e.g., `User`)
- `repository/` - Spring Data JPA repositories for data access
- `controller/` - REST controllers exposing HTTP endpoints
- `service/` - Service layer (currently empty, business logic to be added here)
- `config/` - Spring configuration classes (e.g., `SecurityConfig`)
- `security/` - Security-related classes (currently empty)
- `dto/` - Data Transfer Objects for API requests/responses

### Key Architectural Patterns

**DTO Pattern:** The application uses DTOs to separate API contracts from database entities. For example, `RegisterRequest` DTO is used for user registration instead of exposing the `User` entity directly. This provides:
- Security: prevents exposing internal database structure
- Flexibility: different validation rules per operation
- Decoupling: API independent of database schema

**Repository Pattern:** Spring Data JPA repositories (extending `JpaRepository`) are used for data access. Spring automatically implements these interfaces at runtime, including custom query methods like `findByEmail()` which are generated based on method names.

**Security Configuration:** Currently configured with CSRF disabled and all requests permitted (`permitAll()`), indicating the authentication system is under development. Spring Security is enabled but not yet enforcing authentication.

### Entity Management

**Lombok Integration:** All entities and DTOs use Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) to generate boilerplate code at compile time. The Maven compiler is configured to process Lombok annotations via annotation processors.

**JPA Lifecycle Hooks:** Entities use JPA callbacks like `@PrePersist` (see `User.onCreate()`) for automatic field population (e.g., timestamps).

### Application Configuration

Key settings in `application.properties`:
- Server runs on port 8080
- PostgreSQL connection with credentials (note: credentials should be externalized for production)
- JPA shows SQL queries (`show-sql=true`) with formatted output for debugging
- Spring Security debug logging enabled

## Development Notes

- The application is in early development stages - service layer and security implementation are not yet complete
- Current security configuration permits all requests, authentication needs to be implemented
- Database credentials are currently hardcoded in `application.properties` and should be moved to environment variables or secure configuration
- The `TestController` serves as a health check endpoint at `/hello`
