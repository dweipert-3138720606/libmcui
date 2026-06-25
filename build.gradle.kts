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
