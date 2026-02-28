# custom-provider-simple

## 概要

このモジュールは、カスタム `FeatureFlagProvider` Bean を実装し、Spring Profile で切り替える方法を示します。外部設定プロパティから読み取るプロバイダと、環境変数から読み取るプロバイダの 2 つが含まれています。

## このサンプルで確認できること

- **外部設定プロバイダ** -- カスタム `@ConfigurationProperties` からフラグの値を読み取る `FeatureFlagProvider` の実装 (`ExternalConfigFeatureFlagProvider`、`external-config` プロファイルで有効)。
- **環境変数プロバイダ** -- `FF_` プレフィックス付きの環境変数からフラグの値を読み取る `FeatureFlagProvider` の実装 (`EnvironmentVariableFeatureFlagProvider`、`env-variable` プロファイルで有効)。
- **プロファイルによるプロバイダ切り替え** -- `@Profile` を使用して、アクティブな Spring Profile に応じて 1 つのプロバイダ実装を選択する仕組み。

## 起動方法

### 外部設定プロファイル

```bash
./gradlew :webmvc:custom-provider-simple:bootRun --args='--spring.profiles.active=external-config'
```

### 環境変数プロファイル

```bash
FF_DARK_MODE=true ./gradlew :webmvc:custom-provider-simple:bootRun --args='--spring.profiles.active=env-variable'
```

`dark-mode` Feature Flag を有効にするには、アプリケーション起動前に環境変数 `FF_DARK_MODE` を `true` に設定してください。

## エンドポイント

### external-config プロファイル

| エンドポイント | Feature Flag | フラグの値 | 期待される動作 |
|---|---|---|---|
| `GET /api/cloud` | `cloud-feature` | `true` | 200 -- レスポンスを返す |
| `GET /api/beta` | `beta-feature` | `false` | Feature Flag によりブロック |

### env-variable プロファイル

| エンドポイント | Feature Flag | 環境変数 | 期待される動作 |
|---|---|---|---|
| `GET /ui/dark-mode` | `dark-mode` | `FF_DARK_MODE` | `FF_DARK_MODE=true` の場合 200、それ以外はブロック |

## 設定

### application-external-config.yml

`external-config` プロファイルでは、カスタム設定プレフィックスの下に Feature Flag の値を定義しています:

```yaml
external-feature-flags:
  flags:
    cloud-feature: true
    beta-feature: false
```

`ExternalConfigProperties` は `@ConfigurationProperties` で `external-feature-flags` プレフィックスにバインドし、`ExternalConfigFeatureFlagProvider` にフラグのマップを提供します。

### 環境変数の命名規則

`EnvironmentVariableFeatureFlagProvider` は、Feature Flag 名を以下のルールで環境変数キーに変換します:

1. `FF_` プレフィックスを付与する。
2. 大文字に変換する。
3. ハイフン (`-`) をアンダースコア (`_`) に置換する。

| Feature Flag | 環境変数 |
|---|---|
| `dark-mode` | `FF_DARK_MODE` |
