plugins {
    id("net.neoforged.moddev") version "2.0.141"
    kotlin("jvm")
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

val commonKotlin by configurations.creating {
    isCanBeResolved = true
}

val commonResources by configurations.creating {
    isCanBeResolved = true
}

dependencies {
    implementation("net.neoforged:neoforge:21.1.182")
    implementation("net.minecraft_community:libmcui:0.1.0")
    jarJar("net.minecraft_community:libmcui:0.1.0")
    implementation("thedarkcolour:kotlinforforge-neoforge:5.11.0")

    commonKotlin(project(":common", configuration = "commonKotlin"))
    commonResources(project(":common", configuration = "commonResources"))
}

sourceSets {
    main {
        kotlin.srcDirs(commonKotlin)
        resources.srcDirs(commonResources)
    }
}

neoForge {
    version = "21.1.182"

    runs {
        register("client") {
            client()
        }
        register("server") {
            server()
        }
    }

    mods {
        register("libmcui_test") {
            sourceSet(sourceSets.main.get())
        }
    }
}
