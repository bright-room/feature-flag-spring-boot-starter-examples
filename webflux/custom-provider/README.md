# webflux/custom-provider

## Overview

This module demonstrates how to implement a custom `ReactiveFeatureFlagProvider` for Spring WebFlux. The provider reads feature flag values from custom `@ConfigurationProperties` and exposes them as a reactive `Mono<Boolean>`.

## What This Example Demonstrates

- **Custom `ReactiveFeatureFlagProvider`** -- Implementing `ReactiveFeatureFlagProvider` to load feature flag state from custom configuration properties (`ExternalConfigReactiveFeatureFlagProvider`).
- **`@ConfigurationProperties` binding** -- Using `ExternalConfigProperties` to bind a `Map<String, Boolean>` under a custom prefix (`external-feature-flags.flags`).
- **Reactive flag evaluation** -- Returning `Mono<Boolean>` from the provider, which integrates with the WebFlux feature flag interception pipeline.

## How to Run

```bash
./gradlew :webflux:custom-provider:bootRun
```

## Endpoints

| Endpoint | Feature Flag | Flag Value | Expected Behavior |
|---|---|---|---|
| `GET /api/reactive-feature` | `reactive-feature` | `true` | 200 -- returns response |
| `GET /api/beta-feature` | `beta-feature` | `false` | Blocked by feature flag |

## Configuration

### application.yml

```yaml
external-feature-flags:
  flags:
    reactive-feature: true
    beta-feature: false
```

`ExternalConfigProperties` binds to the `external-feature-flags` prefix via `@ConfigurationProperties` and provides the flag map to `ExternalConfigReactiveFeatureFlagProvider`.

When a requested feature flag is not found in the map, the provider returns `Mono.empty()`, which causes the library to fall back to the configured `default-enabled` behavior (fail-closed by default).
