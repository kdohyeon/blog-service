import com.linecorp.support.project.multi.recipe.configureByLabels

plugins {
    java
    id("io.spring.dependency-management") version Versions.springDependencyManagementPlugin apply false
    id("org.springframework.boot") version Versions.springBoot apply false
    id("io.freefair.lombok") version Versions.lombokPlugin apply false
    id("com.linecorp.build-recipe-plugin") version Versions.lineRecipePlugin

    kotlin("jvm") version Versions.kotlin apply false
    kotlin("kapt") version Versions.kotlin apply false
    kotlin("plugin.spring") version Versions.kotlin apply false
    kotlin("plugin.jpa") version Versions.kotlin apply false
}

allprojects {
    group = "sample.kdohyeon.blog"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.restlet.com")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

subprojects {
    apply(plugin = "idea")
}

configureByLabels("java") {
    apply(plugin = "org.gradle.java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.freefair.lombok")

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}")
            mavenBom("org.jetbrains.kotlin:kotlin-bom:${Versions.kotlin}")
            mavenBom("com.google.guava:guava-bom:${Versions.guava}")
            mavenBom("com.querydsl:querydsl-bom:${Versions.querydsl}")
        }

        dependencies {
            dependency("org.apache.commons:commons-lang3:${Versions.apacheCommonsLang}")
            dependency("org.apache.commons:commons-collections4:${Versions.apacheCommonsCollections}")
            dependency("com.navercorp.fixturemonkey:fixture-monkey-starter:${Versions.fixtureMonkey}")
            dependency("com.google.code.gson:gson:${Versions.gson}")
            dependency("org.mapstruct:mapstruct:${Versions.mapstruct}")
            dependency("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
            dependency("com.fasterxml.jackson.core:jackson-databind:${Versions.jacksonCore}")
            dependency("com.querydsl:querydsl-core:${Versions.querydsl}")
            dependency("com.querydsl:querydsl-jpa:${Versions.querydsl}")
            dependency("com.querydsl:querydsl-apt:${Versions.querydsl}")

            dependency("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
            dependency("org.junit.jupiter:junit-jupiter-params:${Versions.junit}")
            dependency("org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
            dependency("org.assertj:assertj-core:${Versions.assertjCore}")
            dependency("org.mockito:mockito-junit-jupiter:${Versions.mockitoCore}")
        }
    }

    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.restlet.com")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }

    dependencies {
        val implementation by configurations
        val annotationProcessor by configurations
        val testImplementation by configurations
        val testRuntimeOnly by configurations

        implementation("com.google.guava:guava")

        implementation("org.apache.commons:commons-lang3")
        implementation("org.apache.commons:commons-collections4")
        implementation("com.google.code.gson:gson")
        implementation("org.mapstruct:mapstruct")

        annotationProcessor("org.mapstruct:mapstruct-processor")

        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.assertj:assertj-core")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testImplementation("org.mockito:mockito-core")
        testImplementation("org.mockito:mockito-junit-jupiter")
        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter")

        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}

configureByLabels("boot") {
    apply(plugin = "org.springframework.boot")

    tasks.getByName<Jar>("jar") {
        enabled = false
    }

    tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = true
        archiveClassifier.set("boot")
    }
}

configureByLabels("library") {
    apply(plugin = "java-library")

    tasks.getByName<Jar>("jar") {
        enabled = true
    }
}
