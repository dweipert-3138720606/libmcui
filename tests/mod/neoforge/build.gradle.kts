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

dependencies {
    implementation("net.neoforged:neoforge:21.1.182")
    implementation(project(":common"))
    implementation("net.minecraft_community:libmcui:0.1.0")
    implementation("thedarkcolour:kotlinforforge-neoforge:5.11.0")
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
}
