# rollout

## 概要

このモジュールは `feature-flag-spring-boot-starter` の段階的ロールアウト機能を示します。アノテーションベースのロールアウト、設定ファイルベースのロールアウト、およびカスタム `FeatureFlagContextResolver` を使ったスティッキーロールアウトを取り上げます。

## このサンプルで確認できること

- **アノテーションベースのロールアウト** -- `@FeatureFlag(value = "feature-name", rollout = 50)` を使い、リクエストの一定割合に対してフィーチャーを有効にする例 (`AnnotationRolloutController`)。
- **設定ファイルベースのロールアウト** -- `feature-flags.features.<name>.rollout` で設定ファイルからロールアウト割合を指定する例 (`ConfigRolloutController`)。
- **スティッキーロールアウト** -- ユーザー ID を使って決定論的なロールアウト判定を行うカスタム `FeatureFlagContextResolver` の実装例 (`StickyRolloutController`, `UserIdContextResolver`)。
- **ロールアウトの優先順位** -- 設定ファイルのロールアウト値はアノテーションのロールアウト値より優先される。`features` にフィーチャーが定義されている場合、`RolloutPercentageProvider` の値が使用され、アノテーションの `rollout` 属性は無視される。

## 起動方法

デフォルトプロファイル（ランダム・非スティッキーロールアウト）でアプリケーションを起動します:

```bash
./gradlew :webmvc:rollout:bootRun
```

ユーザー ID ベースのスティッキーロールアウトを有効にするには:

```bash
./gradlew :webmvc:rollout:bootRun --args='--spring.profiles.active=sticky'
```

> **注意:** `FeatureFlagContextResolver` はアプリケーション全体で1つの Bean です。`sticky` プロファイルを有効にすると、sticky-rollout エンドポイントだけでなく、全てのロールアウトエンドポイントに影響します。

## エンドポイント

| エンドポイント | Feature Flag | ロールアウト | 指定元 | 期待される動作 |
|---|---|---|---|---|
| `GET /api/annotation-rollout` | `annotation-rollout` | 50% | アノテーション | リクエストの約50%が成功（ランダム） |
| `GET /api/config-rollout` | `config-rollout` | 30% | 設定ファイル | リクエストの約30%が成功（ランダム） |
| `GET /api/sticky-rollout` | `sticky-rollout` | 50% | アノテーション | ユーザーの約50%が成功（`sticky` プロファイルで決定論的） |

`sticky` プロファイルでは、`userId` クエリパラメータを渡すと決定論的な結果が得られます:

```bash
curl http://localhost:8080/api/sticky-rollout?userId=user-123
```

同じ `userId` は、同じフィーチャーに対して常に同じ結果を返します。

## 設定

### application.yml

```yaml
feature-flags:
  default-enabled: true
  features:
    config-rollout:
      enabled: true
      rollout: 30
```

- `default-enabled: true` により fail-open 動作となり、`features` に未定義のフィーチャー（`annotation-rollout` や `sticky-rollout`）も有効として扱われます。
- `annotation-rollout` は意図的に `features` に**定義していません** -- これにより、アノテーションの `rollout = 50` 属性が有効になります。もし定義した場合、設定ファイルのデフォルトロールアウト値 `100` がアノテーションの値を上書きしてしまいます。

### application-sticky.yml

`sticky` プロファイルを有効にすると、`UserIdContextResolver` が `FeatureFlagContextResolver` Bean として登録されます。これによりデフォルトの `RandomFeatureFlagContextResolver` が置き換えられ、ユーザー ID に基づく決定論的なロールアウトが可能になります。

## ロールアウトの仕組み

- **`DefaultRolloutStrategy`** は SHA-256 ハッシュによるバケッティングを使用します: `hash(featureName:userIdentifier) % 100`。
- 同じ入力は常に同じ結果を返すため、スティッキーロールアウトが実現できます。
- **`RandomFeatureFlagContextResolver`**（デフォルト）はリクエストごとにランダムな UUID を生成するため、ロールアウトは非スティッキーになります。
- **`UserIdContextResolver`**（sticky プロファイル）は `userId` クエリパラメータを使用するため、ユーザーごとに決定論的なロールアウトになります。
