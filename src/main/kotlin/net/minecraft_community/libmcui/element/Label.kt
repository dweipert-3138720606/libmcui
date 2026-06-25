package net.minecraft_community.libmcui.element

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics

class Label(
    var text: String = "",
    var textColor: Int = 0xFFFFFFFF.toInt(),
    var shadow: Boolean = true
) : Element() {
    init {
        stretchRatio = 0
    }

    override fun layout() {
        val font = Minecraft.getInstance().font
        width = font.width(text)
        height = font.lineHeight
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        if (!visible) return
        guiGraphics.drawString(Minecraft.getInstance().font, text, x, y, textColor, shadow)
    }
}
