pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases/")
    }
}

rootProject.name = "libmcui-testmod"

includeBuild("../../") {
    name = "libmcui"
}

include("common")
include("fabric")
include("neoforge")
