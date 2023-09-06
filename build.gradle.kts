plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "test-task.beyonnex"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val kotestVersion = "5.7.1"

    testImplementation(kotlin("test"))

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

