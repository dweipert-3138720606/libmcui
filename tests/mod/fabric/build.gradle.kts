plugins {
    id("fabric-loom") version "1.16-SNAPSHOT"
    kotlin("jvm")
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://maven.fabricmc.net/")
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.17.2")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.116.11+1.21.1")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.10+kotlin.2.3.20")

    implementation(project(":common"))
    implementation("net.minecraft_community:libmcui:0.1.0")
}


