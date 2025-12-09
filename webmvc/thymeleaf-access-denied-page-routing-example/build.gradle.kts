plugins {
    id("spring-boot-starter")
    id("spotless-java")
}

dependencies {
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.thymeleaf)

    implementation(libs.feature.flag.spring.boot.starter.core)
    implementation(libs.feature.flag.spring.boot.starter.webmvc)
}
