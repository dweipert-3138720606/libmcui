package net.minecraft_community.libmcui.test

import net.minecraft_community.libmcui.LibMcUI

object TestMod {
    fun init() {
        println("Test mod loaded. LibMcUI loaded: ${LibMcUI::class.simpleName}")
    }
}
