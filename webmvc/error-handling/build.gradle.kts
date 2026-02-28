plugins {
    id("spring-boot-starter")
    id("spotless-java")
}

dependencies {
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.thymeleaf)

    // Jackson 3 XML support
    implementation("tools.jackson.dataformat:jackson-dataformat-xml:3.1.0")

    implementation(libs.feature.flag.spring.boot.starter.core)
    implementation(libs.feature.flag.spring.boot.starter.webmvc)
}
