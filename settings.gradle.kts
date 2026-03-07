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
    "webmvc:basic-usage",
    "webmvc:fail-behavior",
    "webmvc:custom-provider-database",
    "webmvc:custom-provider-simple",
    "webmvc:error-handling",
    "webmvc:rollout",
    "webmvc:functional-endpoint",
    "webmvc:actuator-endpoint",
    "webmvc:health-indicator",
    "webflux:functional-endpoint",
    "webmvc:custom-rollout-strategy",
    "webmvc:event-listener",
)
