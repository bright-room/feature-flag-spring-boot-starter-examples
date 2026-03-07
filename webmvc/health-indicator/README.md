# webmvc/health-indicator

This example demonstrates the `FeatureFlagHealthIndicator` which exposes feature flag state in the Spring Boot Actuator health endpoint.

## Features

- `GET /actuator/health` — health check with feature flag details
- `GET /actuator/health/featureFlag` — feature flag-specific health

## Configuration

```yaml
feature-flags:
  features:
    active-feature:
      enabled: true
    inactive-feature:
      enabled: false
    rollout-feature:
      enabled: true
      rollout: 50

management:
  endpoints:
    web:
      exposure:
        include: health,feature-flags
  endpoint:
    health:
      show-details: always
```

## Expected health response

```json
{
  "status": "UP",
  "components": {
    "featureFlag": {
      "status": "UP",
      "details": {
        "provider": "MutableInMemoryFeatureFlagProvider",
        "totalFlags": 3,
        "enabledFlags": 2,
        "disabledFlags": 1,
        "defaultEnabled": false
      }
    }
  }
}
```
