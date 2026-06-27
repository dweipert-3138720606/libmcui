plugins {
    id("fabric-loom") version "1.16-SNAPSHOT"
    kotlin("jvm") version "2.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
    `maven-publish`
}

group = "net.minecraft_community"
version = "0.1.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://maven.fabricmc.net/")
    maven("https://jitpack.io")
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())

    ktlintRuleset("de.dweipert:ktlint-rules-for-the-uninitiated:1.0.1")
}

tasks.processResources {
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(project.properties)
    }
}

kotlin {
    jvmToolchain(17)
}

ktlint {
    version.set("1.8.0")
    filter({
        exclude({ element -> element.file.name.endsWith(".gradle.kts") })
    })
}

val unremappedJar by tasks.registering(Jar::class) {
    dependsOn(tasks.compileKotlin, tasks.compileJava, tasks.processResources)
    from(tasks.compileKotlin.map { it.outputs })
    from(tasks.processResources.map { it.outputs })
    archiveClassifier.set("")
    destinationDirectory.set(layout.buildDirectory.dir("unremapped"))
}

publishing {
    publications {
        create<MavenPublication>("library") {
            artifact(unremappedJar)
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
