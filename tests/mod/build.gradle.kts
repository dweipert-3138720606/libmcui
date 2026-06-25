plugins {
    kotlin("jvm") version "2.3.20" apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "net.minecraft_community"
    version = "0.1.0"
}
