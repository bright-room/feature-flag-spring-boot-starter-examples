import util.libs

plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    annotationProcessor(libs.spring.boot.configuration.processor)
}

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
    }
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        archiveClassifier.set("")
    }

    test {
        useJUnitPlatform()
    }
}
