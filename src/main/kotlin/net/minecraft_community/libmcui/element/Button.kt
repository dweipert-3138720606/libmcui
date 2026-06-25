package net.minecraft_community.libmcui.element

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation

class Button : InteractiveElement() {
    var color: Int? = null
    var texture: ResourceLocation? = null
    var hoverColor: Int? = null
    var text: String = ""
    var textColor: Int = 0xFFFFFFFF.toInt()
    var shadow: Boolean = true

    override fun layout() {
        val font = Minecraft.getInstance().font
        if (width == 0) width = font.width(text) + 8
        if (height == 0) height = font.lineHeight + 4
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        if (!visible) return
        super.render(guiGraphics, mouseX, mouseY, partialTick)

        val drawColor = if (hovered && hoverColor != null) hoverColor!! else color

        if (drawColor != null) {
            guiGraphics.fill(x, y, x + width, y + height, drawColor)
        }

        texture?.let { tex ->
            guiGraphics.blit(tex, x, y, 0f, 0f, width, height, width, height)
        }

        if (text.isNotEmpty()) {
            val font = Minecraft.getInstance().font
            val tx = x + (width - font.width(text)) / 2
            val ty = y + (height - font.lineHeight) / 2
            guiGraphics.drawString(font, text, tx, ty, textColor, shadow)
        }
    }
}
