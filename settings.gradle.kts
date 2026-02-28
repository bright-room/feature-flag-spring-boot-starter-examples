plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "feature-flag-spring-boot-starter-examples"

includeBuild("gradle-scripts")

include(
    "webmvc:1-1-class-level-annotation",
    "webmvc:1-2-method-level-annotation",
    "webmvc:1-3-class-method-priority",
    "webmvc:2-1-json-response",
    "webmvc:2-2-plain-text-response",
    "webmvc:2-3-html-response",
    "webmvc:3-1-fail-closed",
    "webmvc:3-2-fail-open",
    "webmvc:4-1-database-provider",
    "webmvc:4-2-external-config-provider",
    "webmvc:4-3-environment-variable-provider",
    "webmvc:5-1-custom-controller-advice",
    "webmvc:5-2-redirect-handler",
    "webmvc:6-1-include-pattern",
    "webmvc:6-2-exclude-pattern",
)
