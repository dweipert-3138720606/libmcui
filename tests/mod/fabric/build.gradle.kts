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

val commonKotlin by configurations.creating {
    isCanBeResolved = true
}

val commonResources by configurations.creating {
    isCanBeResolved = true
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.17.2")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.116.11+1.21.1")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.10+kotlin.2.3.20")
    implementation("net.minecraft_community:libmcui:0.1.0")

    commonKotlin(project(":common", configuration = "commonKotlin"))
    commonResources(project(":common", configuration = "commonResources"))
}

sourceSets {
    main {
        kotlin.srcDirs(commonKotlin)
        resources.srcDirs(commonResources)
    }
}


