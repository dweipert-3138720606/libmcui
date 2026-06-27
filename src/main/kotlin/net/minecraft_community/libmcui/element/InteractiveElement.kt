package net.minecraft_community.libmcui.element

import net.minecraft.client.gui.GuiGraphics

open class InteractiveElement : Element() {
    var hovered: Boolean = false

    var onClick: (() -> Unit)? = null

    var onMouseEnter: (() -> Unit)? = null
    var onMouseLeave: (() -> Unit)? = null

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float): Boolean {
        if (!super.render(guiGraphics, mouseX, mouseY, partialTick)) {
            return false
        }

        val wasHovered = hovered
        hovered = containsPoint(mouseX, mouseY)

        if (hovered && !wasHovered) {
            onMouseEnter?.invoke()
        }

        if (!hovered && wasHovered) {
            onMouseLeave?.invoke()
        }

        return true
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (!visible) {
            return false
        }

        if (containsPoint(mouseX.toInt(), mouseY.toInt())) {
            if (button == 0) {
                onClick?.invoke()

                return true
            }
        }

        return false
    }
}
