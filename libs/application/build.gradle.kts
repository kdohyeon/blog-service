dependencies {
    implementation(project(":libs:adapter-http"))
    implementation(project(":libs:adapter-persistence"))

    implementation("org.springframework:spring-context") // annotation
    implementation("org.springframework.data:spring-data-commons") // Pageable
}
