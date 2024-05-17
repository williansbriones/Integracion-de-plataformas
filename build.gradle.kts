import java.util.Properties

plugins {
//https://plugins.gradle.org/plugin/org.sonarqube
    id("org.sonarqube") version "5.0.0.4638"
    id("jacoco")
    id("java")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"

}

group = "com.duocuc"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("PASSED", "FAILED", "SKIPPED")
    }

    finalizedBy(tasks.jacocoTestReport)
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val sonarProperties = Properties().apply {
    file("sonar-project.properties").inputStream().use { load(it) }
}

sonar {
    properties {
        property("sonar.verbose", "true")
        sonarProperties.forEach { (key, value) ->
            property(key.toString(), value)
        }
        property("sonar.sources", "src/main/java")
        property("sonar.tests", "src/test/java")
        property("sonar.language","java")
        property("sonar.java.binaries", "build/classes/java/main")
        property("sonar.java.test.binaries", "build/classes/java/test")
        property("sonar.junit.reportPaths", "build/test-results/test")
        property("sonar.sources", "src/main/java")
        property("sonar.tests", "src/test/java")
        property("sonar.java.binaries", "build/classes")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco.xml")

        // Excluir directorios dto y entity de los tests
        property("sonar.exclusions", "src/main/java/com/motopapis/dto/**,src/main/java/com/motopapis/entity/**")
    }
}




tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation = file("./build/jacocoHtml")
        xml.outputLocation = file("./build/reports/jacoco.xml")
    }
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude(
                    "src/main/java/com/motopapis/dto/**/*",
                    "src/main/java/com/motopapis/exeption/**/*",
                    "src/main/java/com/motopapis/repository/**/*",
                    "src/main/java/com/motopapis/entity/**/*",
                    "src/main/java/com/motopapis/util/**/*"
                )
            }
        })
    )
}