plugins {
    id("net.neoforged.moddev") version "2.0.141"
    kotlin("jvm")
}

neoForge {
    neoFormVersion = "1.21.1-20240808.144430"
}

repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation("net.minecraft_community:libmcui:0.1.0")
}

configurations {
    create("commonKotlin") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    create("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    sourceSets.main.get().kotlin.srcDirs.forEach { dir ->
        add("commonKotlin", dir)
    }
    sourceSets.main.get().resources.srcDirs.forEach { dir ->
        add("commonResources", dir)
    }
}

tasks.named("compileKotlin") { enabled = false }
tasks.named("compileJava") { enabled = false }
