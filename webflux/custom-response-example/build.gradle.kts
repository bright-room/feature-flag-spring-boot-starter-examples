plugins {
    id("spring-boot-starter")
    id("spotless-java")
}

dependencies {
    implementation(libs.spring.boot.starter.webflux)

    // Jackson 3 XML support
    implementation("tools.jackson.dataformat:jackson-dataformat-xml:3.0.2")

    implementation(libs.feature.flag.spring.boot.starter.core)
}
