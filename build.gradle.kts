plugins {
    kotlin("jvm") version "2.1.0"
    `maven-publish`
}

group = "net.minecraft_community"
version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["kotlin"])
        }
    }
}

tasks.register<Exec>("buildTestMod") {
    group = "verification"
    description = "Build the test mod"
    workingDir = project.file("tests/mod")
    commandLine("gradle", "build")
}

tasks.register<Exec>("runFabricTest") {
    group = "verification"
    description = "Launch Fabric test client"
    workingDir = project.file("tests/mod")
    commandLine("gradle", ":fabric:runClient")
}

tasks.register<Exec>("runNeoForgeTest") {
    group = "verification"
    description = "Launch NeoForge test client"
    workingDir = project.file("tests/mod")
    commandLine("gradle", ":neoforge:runClient")
}
