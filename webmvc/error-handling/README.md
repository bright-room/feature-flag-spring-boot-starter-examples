# Error Handling Example

## Overview

This module demonstrates how to customize the error handling behavior when a feature flag denies access. By default, `feature-flag-spring-boot-starter` throws a `FeatureFlagAccessDeniedException`. This example shows two approaches to handling that exception using `@ControllerAdvice`, switched via Spring profiles.

- **JSON error response** (`json-error` profile): Returns a structured 403 JSON response with error, feature, and message fields.
- **Redirect** (`redirect` profile): Redirects the user to a `/coming-soon` page.

## What This Example Demonstrates

- How to implement a custom `@ControllerAdvice` that catches `FeatureFlagAccessDeniedException` and returns a JSON error response.
- How to implement a `@ControllerAdvice` that catches `FeatureFlagAccessDeniedException` and redirects to a different page.
- How to use Spring `@Profile` to switch between different error handling strategies.

## How to Run

### JSON error response

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=json-error'
```

### Redirect

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=redirect'
```

## Endpoints

### PremiumController

| Endpoint | Feature Flag | Flag Value | Expected Result |
|----------|--------------|------------|-----------------|
| `GET /api/premium` | `premium-feature` | `false` | Blocked -- handled by the active `@ControllerAdvice` |

### DashboardController

| Endpoint | Feature Flag | Flag Value | Expected Result |
|----------|--------------|------------|-----------------|
| `GET /dashboard` | `new-dashboard` | `false` | Blocked -- handled by the active `@ControllerAdvice` |

### ComingSoonController

| Endpoint | Feature Flag | Flag Value | Expected Result |
|----------|--------------|------------|-----------------|
| `GET /coming-soon` | (none) | -- | 200 OK -- always accessible (redirect target) |

### Error Handling Behavior by Profile

| Profile | Handler | Behavior |
|---------|---------|----------|
| `json-error` | `CustomFeatureFlagExceptionHandler` | Returns 403 JSON with `error`, `feature`, and `message` fields |
| `redirect` | `RedirectFeatureFlagExceptionHandler` | Redirects to `/coming-soon` |

## Configuration

### `application.yml`

```yaml
feature-flags:
  feature-names:
    premium-feature: false
    new-dashboard: false
```

Both feature flags are set to `false`, so all guarded endpoints will throw `FeatureFlagAccessDeniedException`. The active `@ControllerAdvice` (determined by the Spring profile) decides how to handle the exception.
