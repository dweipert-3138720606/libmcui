plugins {
    kotlin("jvm")
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
