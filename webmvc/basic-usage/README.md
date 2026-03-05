# basic-usage

## Overview

This module demonstrates the basic usage of `feature-flag-spring-boot-starter`, covering annotation placement patterns and response type configuration when a feature flag is disabled.

## What This Example Demonstrates

- **Class-level annotation** -- Applying `@FeatureFlag` to a controller class so that all endpoints in the class are controlled by a single flag (`GreetingController`).
- **Method-level annotation** -- Applying `@FeatureFlag` to individual handler methods, allowing per-endpoint control. Endpoints without the annotation remain always accessible (`UserController`).
- **Annotation priority** -- When both class-level and method-level `@FeatureFlag` are present, the method-level annotation takes priority (`LegacyController`).
- **JSON response type** -- Response format when a disabled flag returns a JSON body (`JsonDemoController`).
- **Plain text response type** -- Response format when a disabled flag returns a plain text body (`PlainTextDemoController`).
- **HTML response type** -- Response format when a disabled flag returns an HTML body (`HtmlDemoController`).

## How to Run

Start the application with the default profile:

```bash
./gradlew :webmvc:basic-usage:bootRun
```

To test response type behavior, activate one of the profile-specific configurations:

```bash
# JSON response
./gradlew :webmvc:basic-usage:bootRun --args='--spring.profiles.active=json-response'

# Plain text response
./gradlew :webmvc:basic-usage:bootRun --args='--spring.profiles.active=plain-text-response'

# HTML response
./gradlew :webmvc:basic-usage:bootRun --args='--spring.profiles.active=html-response'
```

When no profile is specified, the library's default response type is used.

## Endpoints

### Annotation Placement

| Endpoint | Annotation | Feature Flag | Flag Value | Expected Behavior |
|---|---|---|---|---|
| `GET /hello` | Class-level | `greeting` | `true` | 200 -- returns response |
| `GET /goodbye` | Class-level | `greeting` | `true` | 200 -- returns response |
| `GET /users/search` | Method-level | `new-search` | `true` | 200 -- returns response |
| `GET /users/export` | Method-level | `new-export` | `false` | Blocked by feature flag |
| `GET /users/list` | None | -- | -- | 200 -- always accessible |
| `GET /legacy/data` | Class-level | `legacy-api` | `false` | Blocked by feature flag |
| `GET /legacy/special` | Class-level + Method-level | `special-endpoint` | `true` | 200 -- method-level flag takes priority |

### Response Types

These endpoints all have their feature flags set to `false`, so they will return the disabled-flag response. The format of that response depends on the active profile.

| Endpoint | Feature Flag | Flag Value |
|---|---|---|
| `GET /response/json` | `json-demo` | `false` |
| `GET /response/plain-text` | `plain-text-demo` | `false` |
| `GET /response/html` | `html-demo` | `false` |

## Configuration

### application.yml

The default configuration file defines all feature flag values used by the controllers:

```yaml
feature-flags:
  features:
    greeting:
      enabled: true
    new-search:
      enabled: true
    new-export:
      enabled: false
    legacy-api:
      enabled: false
    special-endpoint:
      enabled: true
    json-demo:
      enabled: false
    plain-text-demo:
      enabled: false
    html-demo:
      enabled: false
```

### Profile-specific YAMLs

Each profile overrides `feature-flags.response.type` to control the response format when a feature flag is disabled:

- `application-json-response.yml` -- sets `feature-flags.response.type: JSON`
- `application-plain-text-response.yml` -- sets `feature-flags.response.type: PLAIN_TEXT`
- `application-html-response.yml` -- sets `feature-flags.response.type: HTML`
