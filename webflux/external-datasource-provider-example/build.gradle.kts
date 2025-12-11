plugins {
    id("spring-boot-starter")
    id("spotless-java")
}

dependencies {
    implementation(libs.spring.boot.starter.webflux)

    implementation(libs.spring.boot.starter.data.r2dbc)
    runtimeOnly(libs.r2dbc.postgresql)

    implementation(libs.spring.boot.docker.compose)

    implementation(libs.feature.flag.spring.boot.starter.core)
}
