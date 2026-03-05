# Error Handling Example

## Overview

This module demonstrates how to customize the error handling behavior when a feature flag denies access. By default, `feature-flag-spring-boot-starter` throws a `FeatureFlagAccessDeniedException`. This example shows four approaches to handling that exception using `@ControllerAdvice`, switched via Spring profiles.

- **JSON error response** (`json-error` profile): Returns a structured 403 JSON response with error, feature, and message fields.
- **XML error response** (`xml-error` profile): Returns a structured 403 XML response using Jackson XML.
- **Thymeleaf error page** (`thymeleaf-error` profile): Renders a Thymeleaf error page with the feature name.
- **Redirect** (`redirect` profile): Redirects the user to a `/coming-soon` page.

## What This Example Demonstrates

- How to implement a custom `@ControllerAdvice` that catches `FeatureFlagAccessDeniedException` and returns a JSON error response.
- How to implement a `@ControllerAdvice` that catches `FeatureFlagAccessDeniedException` and returns an XML error response using Jackson XML.
- How to implement a `@ControllerAdvice` that catches `FeatureFlagAccessDeniedException` and renders a Thymeleaf error page.
- How to implement a `@ControllerAdvice` that catches `FeatureFlagAccessDeniedException` and redirects to a different page.
- How to use Spring `@Profile` to switch between different error handling strategies.

## How to Run

### JSON error response

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=json-error'
```

### XML error response

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=xml-error'
```

### Thymeleaf error page

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=thymeleaf-error'
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
| `xml-error` | `XmlFeatureFlagExceptionHandler` | Returns 403 XML with `<feature-flag-error>` root element containing `error`, `feature`, and `message` elements |
| `thymeleaf-error` | `ThymeleafFeatureFlagExceptionHandler` | Renders the `error/feature-unavailable` Thymeleaf template with 403 status |
| `redirect` | `RedirectFeatureFlagExceptionHandler` | Redirects to `/coming-soon` |

## Configuration

### `application.yml`

```yaml
feature-flags:
  features:
    premium-feature:
      enabled: false
    new-dashboard:
      enabled: false
```

Both feature flags are set to `false`, so all guarded endpoints will throw `FeatureFlagAccessDeniedException`. The active `@ControllerAdvice` (determined by the Spring profile) decides how to handle the exception.
