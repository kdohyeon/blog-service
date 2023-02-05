dependencies {
    implementation("org.springframework.boot:spring-boot-starter-json") // RestTemplateBuilder
    implementation("jakarta.annotation:jakarta.annotation-api") // annotations
    implementation("org.springframework.data:spring-data-commons") // Pageable
    implementation("org.springframework:spring-web") // RestTemplate
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // jpa
    implementation("org.springframework.boot:spring-boot-starter-validation") // valid

    // query dsl - QClass 생성
    implementation("com.querydsl:querydsl-core")
    annotationProcessor(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
}
