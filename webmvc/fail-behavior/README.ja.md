# Fail Behavior Example

## 概要

このモジュールは、`feature-flag-spring-boot-starter` における **fail-closed**（デフォルト）と **fail-open** の動作を示します。設定に明示的に定義されていないフィーチャーフラグがどのように扱われるかを確認できます。

- **Fail-closed**（デフォルト）: 未定義のフィーチャーフラグは無効として扱われます。未知のフラグで保護されたエンドポイントへのアクセスはブロックされます。
- **Fail-open**: 未定義のフィーチャーフラグは有効として扱われます。フラグが明示的に `false` に設定されていない限り、アクセスが許可されます。

## このサンプルで確認できること

- デフォルトの `fail-closed` ポリシー（`default-enabled: false`）における未定義フィーチャーフラグの動作。
- `default-enabled: true` を設定して `fail-open` ポリシーに切り替える方法。
- 各ポリシーにおける、明示的に定義されたフラグと未定義フラグの違い。

## 実行方法

### Fail-closed（デフォルト）

```shell
./gradlew :webmvc:fail-behavior:bootRun
```

### Fail-open

```shell
./gradlew :webmvc:fail-behavior:bootRun --args='--spring.profiles.active=fail-open'
```

## エンドポイント

### FailClosedController（デフォルトプロファイル）

| エンドポイント              | フィーチャーフラグ    | 定義済み | 期待される結果             |
|-----------------------|----------------------|---------|-----------------------------|
| `GET /fail-closed/known`   | `known-feature`      | はい（`true`）  | 200 OK -- アクセス許可   |
| `GET /fail-closed/unknown` | `undefined-feature`  | いいえ      | ブロック -- フラグが未定義かつ `default-enabled` が `false` |

### FailOpenController（fail-open プロファイル）

| エンドポイント                     | フィーチャーフラグ    | 定義済み | 期待される結果             |
|------------------------------|----------------------|---------|-----------------------------|
| `GET /fail-open/known-disabled` | `known-disabled`     | はい（`false`） | ブロック -- 明示的に無効 |
| `GET /fail-open/unknown`        | `undefined-feature`  | いいえ      | 200 OK -- フラグは未定義だが `default-enabled` が `true`  |

## 設定

### デフォルトプロファイル（`application.yml`）

```yaml
feature-flags:
  feature-names:
    known-feature: true
```

`default-enabled` はデフォルトで `false` のため、`feature-names` に記載されていないフィーチャーフラグは無効として扱われます。

### Fail-open プロファイル（`application-fail-open.yml`）

```yaml
feature-flags:
  feature-names:
    known-disabled: false
  default-enabled: true
```

`default-enabled: true` を設定すると、`feature-names` に記載されていないフィーチャーフラグは有効として扱われます。
