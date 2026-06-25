package net.minecraft_community.libmcui.test.fabric

import net.fabricmc.api.ModInitializer
import net.minecraft_community.libmcui.test.TestMod

class FabricEntrypoint : ModInitializer {
    override fun onInitialize() {
        TestMod.init()
    }
}
