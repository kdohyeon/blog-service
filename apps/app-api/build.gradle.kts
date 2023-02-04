dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

val appMainClassName = "sample.kdohyeon.blog.BlogApiApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
}
