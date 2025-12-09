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
include(":webmvc:in-memory-provider-example")
include(":webmvc:external-datasource-provider-example")
include(":webmvc:custom-response-example")

includeBuild("gradle-scripts")
