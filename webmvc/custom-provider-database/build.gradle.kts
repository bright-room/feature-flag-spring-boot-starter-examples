plugins {
    id("spring-boot-starter")
    id("spotless-java")
}

dependencies {
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.feature.flag.spring.boot.starter.core)
    implementation(libs.feature.flag.spring.boot.starter.webmvc)
    implementation(libs.mybatis.spring.boot.starter)
    runtimeOnly(libs.postgresql.jdbc)
    developmentOnly(libs.spring.boot.docker.compose)
}
