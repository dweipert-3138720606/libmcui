package net.minecraft_community.libmcui.element

import net.minecraft.client.gui.GuiGraphics

open class InteractiveElement : Element() {
    var hovered: Boolean = false
    var onClick: (() -> Unit)? = null
    var onMouseEnter: (() -> Unit)? = null
    var onMouseLeave: (() -> Unit)? = null

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        if (!visible) return
        val wasHovered = hovered
        hovered = mouseX in x..<(x + width) && mouseY in y..<(y + height)
        if (hovered && !wasHovered) onMouseEnter?.invoke()
        if (!hovered && wasHovered) onMouseLeave?.invoke()
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (!visible) return false
        if (mouseX.toInt() in x..<(x + width) && mouseY.toInt() in y..<(y + height)) {
            if (button == 0) {
                onClick?.invoke()
                return true
            }
        }
        return false
    }
}
