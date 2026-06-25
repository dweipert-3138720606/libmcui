plugins {
    id("net.neoforged.moddev") version "2.0.141"
    kotlin("jvm")
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

dependencies {
    implementation("net.neoforged:neoforge:21.1.182")
    implementation(project(":common"))
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
