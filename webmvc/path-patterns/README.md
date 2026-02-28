# Path Patterns Example

## Overview

This module demonstrates how to use **path pattern filtering** in `feature-flag-spring-boot-starter` to control which request paths are subject to feature flag checks. By configuring include or exclude patterns, you can fine-tune the scope of feature flag enforcement without modifying controller code.

- **Include patterns** (`include` profile): Only requests matching the specified patterns are subject to feature flag checks. All other paths bypass the checks.
- **Exclude patterns** (`exclude` profile): All requests are subject to feature flag checks except those matching the specified patterns.

## What This Example Demonstrates

- How to use `path-patterns.includes` to limit feature flag checks to specific paths.
- How to use `path-patterns.excludes` to exempt specific paths from feature flag checks.
- How controllers with `@FeatureFlag` annotations behave differently depending on whether their paths match the configured patterns.

## How to Run

### Include pattern

```shell
./gradlew :webmvc:path-patterns:bootRun --args='--spring.profiles.active=include'
```

### Exclude pattern

```shell
./gradlew :webmvc:path-patterns:bootRun --args='--spring.profiles.active=exclude'
```

## Endpoints

### Include profile

| Endpoint | Controller | Feature Flag | Flag Value | Expected Result |
|----------|------------|--------------|------------|-----------------|
| `GET /api/v1/data` | `V1Controller` | `v2-api` | `false` | 200 OK -- path is outside the include pattern `/api/v2/**` |
| `GET /api/v2/data` | `V2Controller` | `v2-api` | `false` | Blocked -- path matches the include pattern `/api/v2/**` |

### Exclude profile

| Endpoint | Controller | Feature Flag | Flag Value | Expected Result |
|----------|------------|--------------|------------|-----------------|
| `GET /api/data` | `ApiController` | `api-feature` | `false` | Blocked -- path is not excluded |
| `GET /api/health` | `ApiController` | `api-feature` | `false` | 200 OK -- path matches the exclude pattern |
| `GET /api/info` | `ApiController` | `api-feature` | `false` | 200 OK -- path matches the exclude pattern |

## Configuration

### Include profile (`application-include.yml`)

```yaml
feature-flags:
  path-patterns:
    includes:
      - "/api/v2/**"
  feature-names:
    v2-api: false
```

Only paths matching `/api/v2/**` are subject to feature flag checks. The `v2-api` flag is `false`, so `/api/v2/data` is blocked while `/api/v1/data` remains accessible.

### Exclude profile (`application-exclude.yml`)

```yaml
feature-flags:
  path-patterns:
    excludes:
      - "/api/health"
      - "/api/info"
  feature-names:
    api-feature: false
```

All paths are subject to feature flag checks except `/api/health` and `/api/info`. The `api-feature` flag is `false`, so `/api/data` is blocked while `/api/health` and `/api/info` remain accessible.
