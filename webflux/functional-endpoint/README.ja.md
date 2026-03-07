# webflux/functional-endpoint

## 概要

このモジュールは、`feature-flag-spring-boot-starter` を Spring WebFlux のファンクショナルエンドポイント（`RouterFunction`）で使用する方法を示します。`FeatureFlagHandlerFilterFunction` を使ってルートへのアクセスを制御します。

## このサンプルで確認できること

- **フルゲーティング** -- `featureFlagFilter.of("feature-name")` を使い、ルート全体をフィーチャーフラグで制御する（`stableRoute`、`experimentalRoute`）。
- **ロールアウト付きゲーティング** -- `featureFlagFilter.of("feature-name", 50)` を使い、リクエストの一定割合に対してルートを有効にする（`betaRoute`）。
- **RouterFunction との統合** -- `RouterFunctions.route()` ビルダーチェーン内でフィーチャーフラグフィルターを適用する（`RoutingConfiguration`）。

## 起動方法

```bash
./gradlew :webflux:functional-endpoint:bootRun
```

## エンドポイント

| エンドポイント | フィーチャーフラグ | ロールアウト | フラグ値 | 期待される動作 |
|---|---|---|---|---|
| `GET /api/stable` | `stable-api` | 100% | `true` | 200 -- レスポンスを返す |
| `GET /api/beta` | `beta-api` | 50% | `true` | リクエストの約50%が成功（ランダム） |
| `GET /api/experimental` | `experimental-api` | 100% | `false` | フィーチャーフラグによりブロック |

## 設定

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

## アノテーション方式との主な違い

- フィーチャーフラグは `@FeatureFlag` アノテーションではなく、`FeatureFlagHandlerFilterFunction` を `RouterFunction` のフィルターとして適用します。
- webflux の `FeatureFlagHandlerFilterFunction` は `RolloutPercentageProvider` を**使用しません** -- 設定ファイルのロールアウト値（`feature-flags.features.<name>.rollout`）はファンクショナルエンドポイントには適用されません。
- ロールアウト割合は `of(name, rollout)` パラメータでのみ指定できます。
