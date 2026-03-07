# event-listener

## Overview

This module demonstrates how to react to feature flag lifecycle events published by `feature-flag-spring-boot-starter`. Two listener beans are included: one that maintains an in-memory audit log, and one that invalidates a local cache whenever a flag changes or is removed.

## What This Example Demonstrates

- **Audit logging** -- Implementing `@EventListener` methods that handle `FeatureFlagChangedEvent` and `FeatureFlagRemovedEvent` and append timestamped entries to an in-memory audit log (`AuditEventListener`).
- **Cache invalidation** -- Implementing `@EventListener` methods that evict the affected feature flag entry from a local `FeatureFlagCache` whenever a flag changes or is removed (`CacheInvalidationListener`).
- **Multiple listeners** -- Registering more than one `@EventListener` component for the same event type; both listeners receive each event independently.
- **Actuator integration** -- Exposing the `feature-flags` Actuator endpoint to trigger flag changes at runtime and observe the resulting events.

## How to Run

```bash
./gradlew :webmvc:event-listener:bootRun
```

## Endpoints

### Application endpoints

| Endpoint | Description |
|---|---|
| `GET /api/demo` | Feature-flagged endpoint (guarded by `demo-feature`) |
| `GET /api/audit-log` | Returns all entries recorded by `AuditEventListener` |
| `GET /api/cache` | Returns the current contents of `FeatureFlagCache` |

### Actuator endpoints

| Endpoint | Description |
|---|---|
| `GET /actuator/feature-flags` | List all feature flags |
| `POST /actuator/feature-flags` | Update a feature flag (triggers events) |
| `DELETE /actuator/feature-flags/{featureName}` | Remove a feature flag (triggers events) |

## Demo Steps

1. Start the application.
2. Access the feature-flagged endpoint: `GET /api/demo`
3. Update the flag via the Actuator:
   ```bash
   curl -X POST http://localhost:8080/actuator/feature-flags \
     -H 'Content-Type: application/json' \
     -d '{"featureName":"demo-feature","enabled":false}'
   ```
4. Check the audit log: `GET /api/audit-log`
5. Check the cache state: `GET /api/cache`
6. Remove the flag: `DELETE http://localhost:8080/actuator/feature-flags/demo-feature`
7. Check the audit log again to see the removal entry.

## Configuration

### application.yml

```yaml
feature-flags:
  features:
    demo-feature:
      enabled: true

management:
  endpoints:
    web:
      exposure:
        include: feature-flags,health
```
