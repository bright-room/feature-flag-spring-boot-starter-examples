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
include(":webmvc:default-provider-example")
include(":webmvc:external-datasource-provider-example")
include(":webmvc:custom-response-example")
include(":webmvc:thymeleaf-access-denied-page-routing-example")

includeBuild("gradle-scripts")
