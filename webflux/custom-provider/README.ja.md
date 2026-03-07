# webflux/custom-provider

## 概要

このモジュールは、Spring WebFlux 向けのカスタム `ReactiveFeatureFlagProvider` の実装方法を示します。カスタム `@ConfigurationProperties` からフィーチャーフラグの値を読み取り、リアクティブな `Mono<Boolean>` として返します。

## このサンプルで確認できること

- **カスタム `ReactiveFeatureFlagProvider`** -- カスタム設定プロパティからフィーチャーフラグの状態を読み込む `ReactiveFeatureFlagProvider` の実装（`ExternalConfigReactiveFeatureFlagProvider`）。
- **`@ConfigurationProperties` バインディング** -- `ExternalConfigProperties` を使用して、カスタムプレフィックス（`external-feature-flags.flags`）の下にある `Map<String, Boolean>` をバインドする。
- **リアクティブなフラグ評価** -- プロバイダーから `Mono<Boolean>` を返すことで、WebFlux のフィーチャーフラグインターセプションパイプラインと統合する。

## 起動方法

```bash
./gradlew :webflux:custom-provider:bootRun
```

## エンドポイント

| エンドポイント | フィーチャーフラグ | フラグ値 | 期待される動作 |
|---|---|---|---|
| `GET /api/reactive-feature` | `reactive-feature` | `true` | 200 -- レスポンスを返す |
| `GET /api/beta-feature` | `beta-feature` | `false` | フィーチャーフラグによりブロック |

## 設定

### application.yml

```yaml
external-feature-flags:
  flags:
    reactive-feature: true
    beta-feature: false
```

`ExternalConfigProperties` は `@ConfigurationProperties` で `external-feature-flags` プレフィックスにバインドし、フラグのマップを `ExternalConfigReactiveFeatureFlagProvider` に提供します。

リクエストされたフィーチャーフラグがマップに存在しない場合、プロバイダーは `Mono.empty()` を返します。ライブラリはこれを受け取り、設定された `default-enabled` の動作（デフォルトは fail-closed）にフォールバックします。
