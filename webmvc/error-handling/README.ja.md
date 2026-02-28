# Error Handling Example

## 概要

このモジュールは、フィーチャーフラグによってアクセスが拒否された場合のエラーハンドリングをカスタマイズする方法を示します。デフォルトでは、`feature-flag-spring-boot-starter` は `FeatureFlagAccessDeniedException` をスローします。このサンプルでは、`@ControllerAdvice` を使用した4つのアプローチを Spring プロファイルで切り替えて確認できます。

- **JSON エラーレスポンス**（`json-error` プロファイル）: error、feature、message フィールドを含む構造化された 403 JSON レスポンスを返します。
- **XML エラーレスポンス**（`xml-error` プロファイル）: Jackson XML を使用して、構造化された 403 XML レスポンスを返します。
- **Thymeleaf エラーページ**（`thymeleaf-error` プロファイル）: フィーチャー名を表示する Thymeleaf エラーページをレンダリングします。
- **リダイレクト**（`redirect` プロファイル）: ユーザーを `/coming-soon` ページにリダイレクトします。

## このサンプルで確認できること

- `FeatureFlagAccessDeniedException` をキャッチして JSON エラーレスポンスを返すカスタム `@ControllerAdvice` の実装方法。
- `FeatureFlagAccessDeniedException` をキャッチして Jackson XML で XML エラーレスポンスを返すカスタム `@ControllerAdvice` の実装方法。
- `FeatureFlagAccessDeniedException` をキャッチして Thymeleaf エラーページをレンダリングする `@ControllerAdvice` の実装方法。
- `FeatureFlagAccessDeniedException` をキャッチして別のページにリダイレクトする `@ControllerAdvice` の実装方法。
- Spring の `@Profile` を使用して異なるエラーハンドリング戦略を切り替える方法。

## 実行方法

### JSON エラーレスポンス

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=json-error'
```

### XML エラーレスポンス

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=xml-error'
```

### Thymeleaf エラーページ

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=thymeleaf-error'
```

### リダイレクト

```shell
./gradlew :webmvc:error-handling:bootRun --args='--spring.profiles.active=redirect'
```

## エンドポイント

### PremiumController

| エンドポイント | フィーチャーフラグ | フラグ値 | 期待される結果 |
|----------|--------------|------------|-----------------|
| `GET /api/premium` | `premium-feature` | `false` | ブロック -- アクティブな `@ControllerAdvice` によって処理 |

### DashboardController

| エンドポイント | フィーチャーフラグ | フラグ値 | 期待される結果 |
|----------|--------------|------------|-----------------|
| `GET /dashboard` | `new-dashboard` | `false` | ブロック -- アクティブな `@ControllerAdvice` によって処理 |

### ComingSoonController

| エンドポイント | フィーチャーフラグ | フラグ値 | 期待される結果 |
|----------|--------------|------------|-----------------|
| `GET /coming-soon` | (なし) | -- | 200 OK -- 常にアクセス可能（リダイレクト先） |

### プロファイル別のエラーハンドリング動作

| プロファイル | ハンドラー | 動作 |
|---------|---------|----------|
| `json-error` | `CustomFeatureFlagExceptionHandler` | `error`、`feature`、`message` フィールドを含む 403 JSON を返す |
| `xml-error` | `XmlFeatureFlagExceptionHandler` | `<feature-flag-error>` ルート要素に `error`、`feature`、`message` 要素を含む 403 XML を返す |
| `thymeleaf-error` | `ThymeleafFeatureFlagExceptionHandler` | `error/feature-unavailable` Thymeleaf テンプレートを 403 ステータスでレンダリング |
| `redirect` | `RedirectFeatureFlagExceptionHandler` | `/coming-soon` にリダイレクト |

## 設定

### `application.yml`

```yaml
feature-flags:
  feature-names:
    premium-feature: false
    new-dashboard: false
```

両方のフィーチャーフラグが `false` に設定されているため、保護されたすべてのエンドポイントは `FeatureFlagAccessDeniedException` をスローします。アクティブな `@ControllerAdvice`（Spring プロファイルによって決定）が例外の処理方法を決定します。
