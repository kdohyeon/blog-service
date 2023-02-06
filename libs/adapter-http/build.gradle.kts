dependencies {
    implementation(project(":libs:application"))

    implementation("org.springframework.boot:spring-boot-starter-json") // RestTemplateBuilder
    implementation("jakarta.annotation:jakarta.annotation-api") // annotations
    implementation("org.springframework:spring-web") // RestTemplate

    testImplementation("org.junit.jupiter:junit-jupiter")
    integrationImplementation("org.springframework.boot:spring-boot-starter-test")
}
