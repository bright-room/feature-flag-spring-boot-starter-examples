# functional-endpoint

## 概要

このモジュールは `feature-flag-spring-boot-starter` を Spring MVC の Functional Endpoints（`RouterFunction`）で使用する方法を示します。`FeatureFlagHandlerFilterFunction` を使ってルートへのアクセスを制御します。

## このサンプルで確認できること

- **フルゲーティング** -- `featureFlagFilter.of("feature-name")` を使い、ルート全体を Feature Flag で制御する例 (`stableRoute`, `experimentalRoute`)。
- **ロールアウト付きゲーティング** -- `featureFlagFilter.of("feature-name", 50)` を使い、リクエストの一定割合に対してルートを有効にする例 (`betaRoute`)。
- **RouterFunction との統合** -- `RouterFunctions.route()` ビルダーチェーン内で Feature Flag フィルターを適用する例 (`RoutingConfiguration`)。

## 起動方法

```bash
./gradlew :webmvc:functional-endpoint:bootRun
```

## エンドポイント

| エンドポイント | Feature Flag | ロールアウト | フラグの値 | 期待される動作 |
|---|---|---|---|---|
| `GET /api/stable` | `stable-api` | 100% | `true` | 200 -- レスポンスを返す |
| `GET /api/beta` | `beta-api` | 50% | `true` | リクエストの約50%が成功（ランダム） |
| `GET /api/experimental` | `experimental-api` | 100% | `false` | Feature Flag によりブロック |

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

- Feature Flag はアノテーション (`@FeatureFlag`) ではなく、`FeatureFlagHandlerFilterFunction` を `RouterFunction` のフィルターとして適用します。
- webmvc の `FeatureFlagHandlerFilterFunction` は `RolloutPercentageProvider` を**使用しません** -- 設定ファイルのロールアウト値 (`feature-flags.features.<name>.rollout`) は Functional Endpoints には適用されません。
- ロールアウト割合は `of(name, rollout)` パラメータでのみ指定できます。
