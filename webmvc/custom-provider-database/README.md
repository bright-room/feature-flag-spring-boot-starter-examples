# custom-provider-database

## Overview

This module demonstrates how to implement a custom `FeatureFlagProvider` that reads feature flag values from a database. It uses PostgreSQL as the data store and MyBatis as the persistence framework.

## What This Example Demonstrates

- **Custom FeatureFlagProvider** -- Implementing `FeatureFlagProvider` to load feature flag state from a relational database (`DatabaseFeatureFlagProvider`).
- **MyBatis mapper** -- Using `FeatureManagementMapper` with an XML mapper definition to query the `feature_management` table.
- **Spring Boot Docker Compose support** -- Automatically starting a PostgreSQL container when the application boots, removing the need for manual database setup.
- **Database-driven flag evaluation** -- The `@FeatureFlag` annotation on controller methods is evaluated against values stored in the database at runtime.

## How to Run

Docker must be running before starting the application. The PostgreSQL container is managed automatically via Spring Boot Docker Compose support.

```bash
./gradlew :webmvc:custom-provider-database:bootRun
```

On first startup, the database is initialized with the schema and seed data defined in `docker/sql/init.sql`.

## Endpoints

| Endpoint | Feature Flag | Flag Value (init data) | Expected Behavior |
|---|---|---|---|
| `GET /api/experimental` | `experimental` | `true` | 200 -- returns response |

## Configuration

### application.yml

The configuration file sets up Docker Compose integration and the PostgreSQL datasource:

```yaml
spring:
  docker:
    compose:
      file: ../../docker/compose.yaml
  datasource:
    url: jdbc:postgresql://localhost:65432/postgres
    username: postgres
    password: postgres
```

### Database Schema

The `feature_management` table stores feature flag state:

| Column | Type | Constraint |
|---|---|---|
| `feature_name` | `varchar(100)` | Primary key |
| `enabled` | `boolean` | Not null |

### Initial Data

The following records are inserted on database initialization:

| feature_name | enabled |
|---|---|
| `experimental` | `true` |
| `development` | `false` |
