# Path Patterns Example

## 概要

このモジュールは、`feature-flag-spring-boot-starter` における**パスパターンフィルタリング**を使用して、フィーチャーフラグチェックの対象となるリクエストパスを制御する方法を示します。include または exclude パターンを設定することで、コントローラーのコードを変更せずにフィーチャーフラグの適用範囲を細かく調整できます。

- **Include パターン**（`include` プロファイル）: 指定されたパターンに一致するリクエストのみがフィーチャーフラグチェックの対象となります。その他のパスはチェックをバイパスします。
- **Exclude パターン**（`exclude` プロファイル）: 指定されたパターンに一致するリクエストを除き、すべてのリクエストがフィーチャーフラグチェックの対象となります。

## このサンプルで確認できること

- `path-patterns.includes` を使用してフィーチャーフラグチェックを特定のパスに限定する方法。
- `path-patterns.excludes` を使用して特定のパスをフィーチャーフラグチェックから除外する方法。
- `@FeatureFlag` アノテーションが付与されたコントローラーが、パスが設定されたパターンに一致するかどうかによってどのように異なる動作をするか。

## 実行方法

### Include パターン

```shell
./gradlew :webmvc:path-patterns:bootRun --args='--spring.profiles.active=include'
```

### Exclude パターン

```shell
./gradlew :webmvc:path-patterns:bootRun --args='--spring.profiles.active=exclude'
```

## エンドポイント

### Include プロファイル

| エンドポイント | コントローラー | フィーチャーフラグ | フラグ値 | 期待される結果 |
|----------|------------|--------------|------------|-----------------|
| `GET /api/v1/data` | `V1Controller` | `v2-api` | `false` | 200 OK -- パスが include パターン `/api/v2/**` の範囲外 |
| `GET /api/v2/data` | `V2Controller` | `v2-api` | `false` | ブロック -- パスが include パターン `/api/v2/**` に一致 |

### Exclude プロファイル

| エンドポイント | コントローラー | フィーチャーフラグ | フラグ値 | 期待される結果 |
|----------|------------|--------------|------------|-----------------|
| `GET /api/data` | `ApiController` | `api-feature` | `false` | ブロック -- パスが除外対象外 |
| `GET /api/health` | `ApiController` | `api-feature` | `false` | 200 OK -- パスが exclude パターンに一致 |
| `GET /api/info` | `ApiController` | `api-feature` | `false` | 200 OK -- パスが exclude パターンに一致 |

## 設定

### Include プロファイル（`application-include.yml`）

```yaml
feature-flags:
  path-patterns:
    includes:
      - "/api/v2/**"
  feature-names:
    v2-api: false
```

`/api/v2/**` に一致するパスのみがフィーチャーフラグチェックの対象となります。`v2-api` フラグが `false` のため、`/api/v2/data` はブロックされますが、`/api/v1/data` はアクセス可能です。

### Exclude プロファイル（`application-exclude.yml`）

```yaml
feature-flags:
  path-patterns:
    excludes:
      - "/api/health"
      - "/api/info"
  feature-names:
    api-feature: false
```

`/api/health` と `/api/info` を除くすべてのパスがフィーチャーフラグチェックの対象となります。`api-feature` フラグが `false` のため、`/api/data` はブロックされますが、`/api/health` と `/api/info` はアクセス可能です。
