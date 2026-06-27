package net.minecraft_community.libmcui.test.neoforge

import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
import net.minecraft_community.libmcui.test.TestMod

@Mod("libmcui_test")
class NeoForgeEntrypoint {
    init {
        NeoForge.EVENT_BUS.register(NeoForgeClientEvents::class.java)
        TestMod.init()
    }
}
