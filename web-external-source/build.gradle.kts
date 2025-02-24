plugins {
    id("spring-boot-starter")
    id("spotless-java")
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.mybatis.spring.boot.starter)

    runtimeOnly(libs.h2db)

    implementation(libs.feature.flag.spring.boot.starter.core)
    implementation(libs.feature.flag.spring.boot.starter.web)
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

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}
