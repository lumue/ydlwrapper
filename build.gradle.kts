plugins {
    java
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

repositories {
    mavenCentral()
}


sourceSets {
    create("intTest") {
        java.srcDirs("src/integration-test/java")
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val intTestImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
}
val intTestRuntimeOnly by configurations.getting

configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

val integrationTest = task<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = sourceSets["intTest"].output.classesDirs
    classpath = sourceSets["intTest"].runtimeClasspath
    shouldRunAfter("test")

    useJUnit()

    testLogging {
        events("passed")
    }
}

tasks.check { dependsOn(integrationTest) }




dependencies {

    val validationApiVersion = "1.1.0.Final"
    val slf4jApiVersion = "1.7.18"
    val hamcrestAllVersion = "1.3"
    val commonsLang3Version = "3.4"
    val commonsIoVersion = "2.15.1"
    val jacksonVersion = "2.16.1"
    val junitVersion = "4.13.1"

    implementation("javax.validation:validation-api:${validationApiVersion}")
    implementation("org.slf4j:slf4j-api:${slf4jApiVersion}")
    implementation("org.hamcrest:hamcrest-all:${hamcrestAllVersion}")
    implementation("org.apache.commons:commons-lang3:${commonsLang3Version}")
    implementation("commons-io:commons-io:${commonsIoVersion}")
    implementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}")
    testImplementation("junit:junit:${junitVersion}")
    intTestImplementation("junit:junit:${junitVersion}")
}