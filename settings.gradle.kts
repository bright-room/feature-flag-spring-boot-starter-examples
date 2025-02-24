plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
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
include("web-in-memory")
include("web-external-source")
include("webflux-in-memory")

includeBuild("gradle-scripts")
