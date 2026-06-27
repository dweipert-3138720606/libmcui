package net.minecraft_community.libmcui.element

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics

class Label() : Element() {
    var text: String = ""
    var textColor: Int = 0xFFFFFFFF.toInt()
    var shadow: Boolean = true

    constructor(text: String = "", textColor: Int = 0xFFFFFFFF.toInt(), shadow: Boolean = true) : this() {
        this.text = text
        this.textColor = textColor
        this.shadow = shadow
    }

    init {
        stretchRatio = 0
    }

    override fun layout() {
        val font = Minecraft.getInstance().font
        width = font.width(text)
        height = font.lineHeight
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float): Boolean {
        if (!super.render(guiGraphics, mouseX, mouseY, partialTick)) {
            return false
        }

        guiGraphics.drawString(Minecraft.getInstance().font, text, x, y, textColor, shadow)

        return true
    }
}
