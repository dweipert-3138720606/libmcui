package net.minecraft_community.libmcui.element.containers

import net.minecraft.client.gui.GuiGraphics
import net.minecraft_community.libmcui.element.Container

class ScrollContainer : Container() {
    var scrollOffset: Int = 0

    private var scrollHeight: Int = 0

    override fun layout() {
        super.layout()

        var currentY = contentTop
        for (child in children) {
            child.x = contentLeft + child.marginLeft
            child.y = currentY + child.marginTop
            child.width = (contentWidth - child.marginLeft - child.marginRight).coerceAtLeast(0)

            if (child.height < child.minHeight) {
                child.height = child.minHeight
            }

            currentY = child.y + child.height + child.marginBottom + gap
        }

        scrollHeight = currentY - contentTop

        layoutChildren()
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float): Boolean {
        if (!visible) {
            return false
        }

        renderBorder(guiGraphics)
        drawBackground(guiGraphics)

        guiGraphics.enableScissor(x, y, x + width, y + height)

        guiGraphics.pose().pushPose()
        guiGraphics.pose().translate(0.0, -scrollOffset.toDouble(), 0.0)

        val adjY = mouseY + scrollOffset
        for (child in children) {
            if (child.visible && child.y + child.height > y && child.y < y + height + scrollOffset) {
                child.render(guiGraphics, mouseX, adjY, partialTick)
            }
        }

        guiGraphics.pose().popPose()
        guiGraphics.disableScissor()

        return true
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (!visible) {
            return false
        }

        val adjY = mouseY + scrollOffset
        for (child in children.reversed()) {
            if (child.visible && child.mouseClicked(mouseX, adjY, button)) {
                return true
            }
        }

        return false
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean {
        if (!visible) {
            return false
        }

        if (containsPoint(mouseX.toInt(), mouseY.toInt())) {
            val maxScroll = (scrollHeight - contentHeight).coerceAtLeast(0)
            scrollOffset = (scrollOffset - (delta * 20).toInt()).coerceIn(0, maxScroll)

            return true
        }

        for (child in children.reversed()) {
            if (child.visible && child.mouseScrolled(mouseX, mouseY + scrollOffset, delta)) {
                return true
            }
        }

        return false
    }
}
