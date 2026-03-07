# webmvc/health-indicator

`FeatureFlagHealthIndicator` によるフィーチャーフラグ状態の Spring Boot Actuator ヘルスエンドポイント表示のサンプルです。

## デモする機能

- `GET /actuator/health` — フィーチャーフラグ詳細を含むヘルスチェック
- `GET /actuator/health/featureFlag` — フィーチャーフラグ専用ヘルス

## 設定

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

## 期待されるヘルスレスポンス

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
