# custom-provider-database

## 概要

このモジュールは、データベースから Feature Flag の値を読み取るカスタム `FeatureFlagProvider` の実装方法を示します。データストアに PostgreSQL、永続化フレームワークに MyBatis を使用しています。

## このサンプルで確認できること

- **カスタム FeatureFlagProvider** -- リレーショナルデータベースから Feature Flag の状態を読み込む `FeatureFlagProvider` の実装 (`DatabaseFeatureFlagProvider`)。
- **MyBatis マッパー** -- `FeatureManagementMapper` と XML マッパー定義を使用して `feature_management` テーブルを検索する例。
- **Spring Boot Docker Compose サポート** -- アプリケーション起動時に PostgreSQL コンテナを自動的に起動し、手動でのデータベースセットアップを不要にする仕組み。
- **データベース駆動のフラグ評価** -- コントローラメソッドの `@FeatureFlag` アノテーションが、実行時にデータベースに格納された値に基づいて評価される。

## 起動方法

アプリケーションを起動する前に、Docker が実行されている必要があります。PostgreSQL コンテナは Spring Boot Docker Compose サポートにより自動的に管理されます。

```bash
./gradlew :webmvc:custom-provider-database:bootRun
```

初回起動時に、`docker/sql/init.sql` で定義されたスキーマとシードデータでデータベースが初期化されます。

## エンドポイント

| エンドポイント | Feature Flag | フラグの値 (初期データ) | 期待される動作 |
|---|---|---|---|
| `GET /api/experimental` | `experimental` | `true` | 200 -- レスポンスを返す |

## 設定

### application.yml

Docker Compose 連携と PostgreSQL データソースの設定:

```yaml
spring:
  docker:
    compose:
      file: ../../docker/compose.yaml
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres
```

### データベーススキーマ

`feature_management` テーブルが Feature Flag の状態を格納します:

| カラム | 型 | 制約 |
|---|---|---|
| `feature_name` | `varchar(100)` | 主キー |
| `enabled` | `boolean` | Not null |

### 初期データ

データベース初期化時に以下のレコードが挿入されます:

| feature_name | enabled |
|---|---|
| `experimental` | `true` |
| `development` | `false` |
