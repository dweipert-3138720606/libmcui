package net.minecraft_community.libmcui.element

import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.Screen as MinecraftScreen
import net.minecraft.network.chat.Component

open class Screen(title: Component = Component.literal("")) : MinecraftScreen(title) {
    lateinit var root: Container

    override fun init() {
        super.init()
        if (::root.isInitialized) {
            root.width = width
            root.height = height
            root.layout()
        }
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        super.render(guiGraphics, mouseX, mouseY, partialTick)

        if (::root.isInitialized) {
            root.render(guiGraphics, mouseX, mouseY, partialTick)
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (::root.isInitialized && root.mouseClicked(mouseX, mouseY, button)) {
            return true
        }

        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, scrollX: Double, scrollY: Double): Boolean {
        if (::root.isInitialized && root.mouseScrolled(mouseX, mouseY, scrollY)) {
            return true
        }

        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY)
    }
}
