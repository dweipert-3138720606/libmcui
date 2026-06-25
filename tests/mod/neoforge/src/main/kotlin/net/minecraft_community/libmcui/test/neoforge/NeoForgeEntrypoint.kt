package net.minecraft_community.libmcui.test.neoforge

import net.neoforged.fml.common.Mod
import net.minecraft_community.libmcui.test.TestMod

@Mod("libmcui_test")
class NeoForgeEntrypoint {
    init {
        TestMod.init()
    }
}
