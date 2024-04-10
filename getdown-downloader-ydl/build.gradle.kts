plugins {
    id("java")
}

group = "io.github.lumue.getdown.downloader.ydl"
version = "master-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":getdown-core"))

    implementation("com.github.lumue:ydlwrapper:1.1")
    implementation( "org.slf4j:slf4j-api:1.7.21")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.8.3")
    implementation ("org.springframework:spring-context:5.3.31")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}