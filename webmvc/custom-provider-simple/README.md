# custom-provider-simple

## Overview

This module demonstrates how to implement custom `FeatureFlagProvider` beans and switch between them using Spring Profiles. Two providers are included: one that reads from external configuration properties, and another that reads from environment variables.

## What This Example Demonstrates

- **External configuration provider** -- Implementing `FeatureFlagProvider` to read flag values from custom `@ConfigurationProperties` (`ExternalConfigFeatureFlagProvider`, active with the `external-config` profile).
- **Environment variable provider** -- Implementing `FeatureFlagProvider` to read flag values from environment variables with a `FF_` prefix (`EnvironmentVariableFeatureFlagProvider`, active with the `env-variable` profile).
- **Profile-based provider switching** -- Using `@Profile` to activate exactly one provider implementation depending on the active Spring profile.

## How to Run

### External Configuration Profile

```bash
./gradlew :webmvc:custom-provider-simple:bootRun --args='--spring.profiles.active=external-config'
```

### Environment Variable Profile

```bash
FF_DARK_MODE=true ./gradlew :webmvc:custom-provider-simple:bootRun --args='--spring.profiles.active=env-variable'
```

Set the `FF_DARK_MODE` environment variable to `true` before starting the application to enable the `dark-mode` feature flag.

## Endpoints

### external-config Profile

| Endpoint | Feature Flag | Flag Value | Expected Behavior |
|---|---|---|---|
| `GET /api/cloud` | `cloud-feature` | `true` | 200 -- returns response |
| `GET /api/beta` | `beta-feature` | `false` | Blocked by feature flag |

### env-variable Profile

| Endpoint | Feature Flag | Environment Variable | Expected Behavior |
|---|---|---|---|
| `GET /ui/dark-mode` | `dark-mode` | `FF_DARK_MODE` | 200 if `FF_DARK_MODE=true`, otherwise blocked |

## Configuration

### application-external-config.yml

The `external-config` profile defines feature flag values under a custom configuration prefix:

```yaml
external-feature-flags:
  flags:
    cloud-feature: true
    beta-feature: false
```

`ExternalConfigProperties` binds to the `external-feature-flags` prefix via `@ConfigurationProperties` and provides the flag map to `ExternalConfigFeatureFlagProvider`.

### Environment Variable Naming Convention

`EnvironmentVariableFeatureFlagProvider` converts the feature flag name to an environment variable key using the following rule:

1. Add the `FF_` prefix.
2. Convert to uppercase.
3. Replace hyphens (`-`) with underscores (`_`).

| Feature Flag | Environment Variable |
|---|---|
| `dark-mode` | `FF_DARK_MODE` |
