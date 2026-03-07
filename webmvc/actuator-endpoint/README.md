# webmvc/actuator-endpoint

This example demonstrates runtime feature flag management via the Spring Boot Actuator endpoint.

## Features

- `GET /actuator/feature-flags` — list all feature flags
- `GET /actuator/feature-flags/{featureName}` — get a single feature flag
- `POST /actuator/feature-flags` — update a feature flag (enabled state and optional rollout)
- `DELETE /actuator/feature-flags/{featureName}` — remove a feature flag
- `FeatureFlagChangedEvent` / `FeatureFlagRemovedEvent` — `@EventListener` handling

## Configuration

```yaml
feature-flags:
  features:
    demo-feature:
      enabled: true
      rollout: 80
    another-feature:
      enabled: false

management:
  endpoints:
    web:
      exposure:
        include: feature-flags,health
```

## How it works

The `actuator` module automatically registers `MutableInMemoryFeatureFlagProvider` and
`MutableInMemoryRolloutPercentageProvider`, which replace the default read-only providers
from the `webmvc` module via `@ConditionalOnMissingBean`.

The `FeatureFlagEndpoint` is exposed at `/actuator/feature-flags` and allows full CRUD
operations on feature flags at runtime without restarting the application.

Events are published on every write or delete:
- `FeatureFlagChangedEvent`: `featureName()`, `enabled()`, `rolloutPercentage()` (null if not changed)
- `FeatureFlagRemovedEvent`: `featureName()` (only fired when the flag actually existed)

## Demo steps

1. Start the application
2. List all flags: `GET /actuator/feature-flags`
3. Get a single flag: `GET /actuator/feature-flags/demo-feature`
4. Enable another-feature: `POST /actuator/feature-flags` with body `{"featureName":"another-feature","enabled":true}`
5. Delete another-feature: `DELETE /actuator/feature-flags/another-feature`
6. Check the console log for published events
