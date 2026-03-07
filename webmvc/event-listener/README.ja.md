# event-listener

## 概要

このモジュールは、`feature-flag-spring-boot-starter` が発行するフィーチャーフラグのライフサイクルイベントに反応する方法を示します。イベントを受信して監査ログを記録するリスナーと、ローカルキャッシュを無効化するリスナーの2つが含まれています。

## このサンプルで確認できること

- **監査ログ** -- `FeatureFlagChangedEvent` と `FeatureFlagRemovedEvent` を処理する `@EventListener` メソッドを実装し、タイムスタンプ付きエントリをインメモリ監査ログに追記する（`AuditEventListener`）。
- **キャッシュ無効化** -- フラグの変更または削除のたびに、影響するフラグのエントリをローカル `FeatureFlagCache` から削除する `@EventListener` メソッドを実装する（`CacheInvalidationListener`）。
- **複数リスナー** -- 同じイベントタイプに対して複数の `@EventListener` コンポーネントを登録する。各リスナーはイベントを独立して受け取る。
- **Actuator 連携** -- `feature-flags` Actuator エンドポイントでランタイムにフラグを変更し、発行されるイベントを確認する。

## 起動方法

```bash
./gradlew :webmvc:event-listener:bootRun
```

## エンドポイント

### アプリケーションエンドポイント

| エンドポイント | 説明 |
|---|---|
| `GET /api/demo` | フィーチャーフラグ制御済みエンドポイント（`demo-feature` で保護） |
| `GET /api/audit-log` | `AuditEventListener` が記録した全エントリを返す |
| `GET /api/cache` | `FeatureFlagCache` の現在の内容を返す |

### Actuator エンドポイント

| エンドポイント | 説明 |
|---|---|
| `GET /actuator/feature-flags` | 全フィーチャーフラグの一覧 |
| `POST /actuator/feature-flags` | フィーチャーフラグの更新（イベントが発行される） |
| `DELETE /actuator/feature-flags/{featureName}` | フィーチャーフラグの削除（イベントが発行される） |

## デモ手順

1. アプリケーションを起動する。
2. フィーチャーフラグ制御済みエンドポイントにアクセス: `GET /api/demo`
3. Actuator 経由でフラグを更新する:
   ```bash
   curl -X POST http://localhost:8080/actuator/feature-flags \
     -H 'Content-Type: application/json' \
     -d '{"featureName":"demo-feature","enabled":false}'
   ```
4. 監査ログを確認する: `GET /api/audit-log`
5. キャッシュの状態を確認する: `GET /api/cache`
6. フラグを削除する: `DELETE http://localhost:8080/actuator/feature-flags/demo-feature`
7. 再度監査ログを確認し、削除エントリが追加されていることを確認する。

## 設定

### application.yml

```yaml
feature-flags:
  features:
    demo-feature:
      enabled: true

management:
  endpoints:
    web:
      exposure:
        include: feature-flags,health
```
