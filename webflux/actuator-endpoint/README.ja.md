# webflux/actuator-endpoint

Spring WebFlux アプリケーションにおける、Spring Boot Actuator エンドポイントを使ったランタイムでのフィーチャーフラグ管理を示すサンプルです。

## 機能

- `GET /actuator/feature-flags` -- 全フィーチャーフラグの一覧取得
- `GET /actuator/feature-flags/{featureName}` -- 単一フィーチャーフラグの取得
- `POST /actuator/feature-flags` -- フィーチャーフラグの更新（有効状態および任意のロールアウト）
- `DELETE /actuator/feature-flags/{featureName}` -- フィーチャーフラグの削除
- `FeatureFlagChangedEvent` / `FeatureFlagRemovedEvent` -- `@EventListener` によるイベントハンドリング

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

## 動作の仕組み

`actuator` モジュールは `MutableInMemoryFeatureFlagProvider` と
`MutableInMemoryRolloutPercentageProvider` を自動的に登録します。
これらは `@ConditionalOnMissingBean` により `webflux` モジュールのデフォルト（読み取り専用）プロバイダーを置き換えます。

`FeatureFlagEndpoint` は `/actuator/feature-flags` に公開され、アプリケーションを再起動せずにランタイムでフィーチャーフラグへの完全なCRUD操作を可能にします。

書き込みまたは削除のたびにイベントが発行されます:
- `FeatureFlagChangedEvent`: `featureName()`、`enabled()`、`rolloutPercentage()`（変更されていない場合は null）
- `FeatureFlagRemovedEvent`: `featureName()`（フラグが実際に存在した場合のみ発行）

## デモ手順

1. アプリケーションを起動する
2. 全フラグを一覧表示: `GET /actuator/feature-flags`
3. 単一フラグを取得: `GET /actuator/feature-flags/demo-feature`
4. another-feature を有効化: `POST /actuator/feature-flags` にボディ `{"featureName":"another-feature","enabled":true}` を送信
5. another-feature を削除: `DELETE /actuator/feature-flags/another-feature`
6. コンソールログで発行されたイベントを確認する
