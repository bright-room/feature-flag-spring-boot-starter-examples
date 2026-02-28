import util.libs

plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    developmentOnly(libs.spring.boot.devtools)
    annotationProcessor(libs.spring.boot.configuration.processor)
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/java", "src/main/resources")
        }
    }
    test {
        resources {
            srcDirs("src/main/java", "src/main/resources")
            exclude("**/*.java")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
    }
}

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    test {
        useJUnitPlatform()
    }
}
