# webflux/functional-endpoint

## Overview

This module demonstrates how to use `feature-flag-spring-boot-starter` with Spring WebFlux functional endpoints (`RouterFunction`), using `FeatureFlagHandlerFilterFunction` to gate access to routes.

## What This Example Demonstrates

- **Full gating** -- Using `featureFlagFilter.of("feature-name")` to completely gate a route behind a feature flag (`stableRoute`, `experimentalRoute`).
- **Rollout gating** -- Using `featureFlagFilter.of("feature-name", 50)` to enable a route for a percentage of requests (`betaRoute`).
- **RouterFunction integration** -- Applying feature flag filters within `RouterFunctions.route()` builder chains (`RoutingConfiguration`).

## How to Run

```bash
./gradlew :webflux:functional-endpoint:bootRun
```

## Endpoints

| Endpoint | Feature Flag | Rollout | Flag Value | Expected Behavior |
|---|---|---|---|---|
| `GET /api/stable` | `stable-api` | 100% | `true` | 200 -- returns response |
| `GET /api/beta` | `beta-api` | 50% | `true` | ~50% of requests succeed (random) |
| `GET /api/experimental` | `experimental-api` | 100% | `false` | Blocked by feature flag |

## Configuration

### application.yml

```yaml
feature-flags:
  features:
    stable-api:
      enabled: true
    beta-api:
      enabled: true
    experimental-api:
      enabled: false
```

## Key Differences from Annotation-based Approach

- Feature flags are applied via `FeatureFlagHandlerFilterFunction` as a `RouterFunction` filter, not via `@FeatureFlag` annotations.
- The webflux `FeatureFlagHandlerFilterFunction` does **not** use `RolloutPercentageProvider` -- configuration-based rollout values (`feature-flags.features.<name>.rollout`) are not applied to functional endpoints.
- Rollout percentage can only be specified through the `of(name, rollout)` parameter.
