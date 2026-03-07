# webflux/fail-behavior

## Overview

This module demonstrates the **fail-closed** (default) and **fail-open** behavior of `feature-flag-spring-boot-starter` in a Spring WebFlux application when a feature flag is not explicitly defined in the configuration.

- **Fail-closed** (default): Undefined feature flags are treated as disabled. Access to endpoints guarded by unknown flags is blocked.
- **Fail-open**: Undefined feature flags are treated as enabled. Access is allowed unless the flag is explicitly set to `false`.

## What This Example Demonstrates

- How undefined feature flags behave under the default `fail-closed` policy (`default-enabled: false`).
- How to switch to a `fail-open` policy by setting `default-enabled: true`.
- The difference between explicitly defined flags and undefined flags under each policy.

## How to Run

### Fail-closed (default)

```bash
./gradlew :webflux:fail-behavior:bootRun
```

### Fail-open

```bash
./gradlew :webflux:fail-behavior:bootRun --args='--spring.profiles.active=fail-open'
```

## Endpoints

### FailClosedController (default profile)

| Endpoint | Feature Flag | Defined | Expected Result |
|---|---|---|---|
| `GET /fail-closed/known` | `known-feature` | Yes (`true`) | 200 OK -- access allowed |
| `GET /fail-closed/unknown` | `undefined-feature` | No | Blocked -- flag is undefined and `default-enabled` is `false` |

### FailOpenController (fail-open profile)

| Endpoint | Feature Flag | Defined | Expected Result |
|---|---|---|---|
| `GET /fail-open/known-disabled` | `known-disabled` | Yes (`false`) | Blocked -- explicitly disabled |
| `GET /fail-open/unknown` | `undefined-feature` | No | 200 OK -- flag is undefined but `default-enabled` is `true` |

## Configuration

### Default profile (`application.yml`)

```yaml
feature-flags:
  features:
    known-feature:
      enabled: true
    known-disabled:
      enabled: false
```

`default-enabled` is `false` by default, so any feature flag not listed under `features` will be treated as disabled.

### Fail-open profile (`application-fail-open.yml`)

```yaml
feature-flags:
  features:
    known-disabled:
      enabled: false
  default-enabled: true
```

Setting `default-enabled: true` means any feature flag not listed under `features` will be treated as enabled.
