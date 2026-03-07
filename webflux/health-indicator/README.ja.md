# webflux/health-indicator

Spring WebFlux アプリケーションにおける、`FeatureFlagHealthIndicator` によるフィーチャーフラグ状態の Spring Boot Actuator ヘルスエンドポイントへの公開を示すサンプルです。

## 機能

- `GET /actuator/health` -- フィーチャーフラグの詳細を含むヘルスチェック
- `GET /actuator/health/featureFlag` -- フィーチャーフラグ専用のヘルス情報

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

## エンドポイント

| エンドポイント | フィーチャーフラグ | フラグ値 | 期待される動作 |
|---|---|---|---|
| `GET /api/active` | `active-feature` | `true` | 200 -- レスポンスを返す |
| `GET /api/inactive` | `inactive-feature` | `false` | フィーチャーフラグによりブロック |

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
