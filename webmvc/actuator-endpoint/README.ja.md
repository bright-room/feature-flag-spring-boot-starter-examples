# webmvc/actuator-endpoint

Spring Boot Actuator エンドポイントを使ったフィーチャーフラグのランタイム管理のサンプルです。

## デモする機能

- `GET /actuator/feature-flags` — 全フラグ一覧取得
- `GET /actuator/feature-flags/{featureName}` — 個別フラグ取得
- `POST /actuator/feature-flags` — フラグの更新 (有効/無効 + ロールアウト割合)
- `DELETE /actuator/feature-flags/{featureName}` — フラグの削除
- `FeatureFlagChangedEvent` / `FeatureFlagRemovedEvent` の `@EventListener` ハンドリング

## 設定

```yaml
feature-flags:
  features:
    demo-feature:
      enabled: true
      rollout: 80
    another-feature:
      enabled: false

management:
  endpoints:
    web:
      exposure:
        include: feature-flags,health
```

## 仕組み

`actuator` モジュールは `MutableInMemoryFeatureFlagProvider` と `MutableInMemoryRolloutPercentageProvider` を自動登録します。
これらは `@ConditionalOnMissingBean` により `webmvc` モジュールのデフォルト読み取り専用プロバイダーより優先されます。

`FeatureFlagEndpoint` が `/actuator/feature-flags` に公開され、アプリを再起動せずにランタイムでフラグのCRUD操作が可能です。

イベントは書き込み・削除のたびに発行されます:
- `FeatureFlagChangedEvent`: `featureName()`, `enabled()`, `rolloutPercentage()` (変更なしの場合 null)
- `FeatureFlagRemovedEvent`: `featureName()` (フラグが実際に存在した場合のみ発行)

## デモ手順

1. アプリを起動する
2. 全フラグ確認: `GET /actuator/feature-flags`
3. 個別フラグ確認: `GET /actuator/feature-flags/demo-feature`
4. `another-feature` を有効化: `POST /actuator/feature-flags` (ボディ: `{"featureName":"another-feature","enabled":true}`)
5. `another-feature` を削除: `DELETE /actuator/feature-flags/another-feature`
6. コンソールログでイベント発行を確認する
