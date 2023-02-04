dependencies {
    implementation(project(":commons:protocol"))
    implementation(project(":libs:application"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation") // valid
    implementation("org.springframework.data:spring-data-commons") // Pageable
}

val appMainClassName = "sample.kdohyeon.blog.BlogApiApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
}
