package net.minecraft_community.libmcui.element.containers

import net.minecraft.client.gui.GuiGraphics
import net.minecraft_community.libmcui.element.Container

class ScrollContainer : Container() {
    var scrollOffset: Int = 0
    private var contentHeight: Int = 0

    override fun layout() {
        super.layout()

        val visibleHeight = height - marginTop - marginBottom - paddingTop - paddingBottom

        var currentY = y + marginTop + paddingTop
        for (child in children) {
            child.x = x + marginLeft + paddingLeft + child.marginLeft
            child.y = currentY + child.marginTop
            child.width = (width - marginLeft - marginRight - paddingLeft - paddingRight
                - child.marginLeft - child.marginRight).coerceAtLeast(0)
            if (child.height < child.minHeight) child.height = child.minHeight
            currentY = child.y + child.height + child.marginBottom + gap
        }
        contentHeight = currentY - (y + marginTop + paddingTop)
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        if (!visible) return

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
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (!visible) return false
        val adjY = mouseY + scrollOffset
        for (child in children.reversed()) {
            if (child.visible && child.mouseClicked(mouseX, adjY, button)) return true
        }
        return false
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean {
        if (!visible) return false
        if (mouseX.toInt() in x..<(x + width) && mouseY.toInt() in y..<(y + height)) {
            val visibleHeight = height - marginTop - marginBottom - paddingTop - paddingBottom
            val maxScroll = (contentHeight - visibleHeight).coerceAtLeast(0)
            scrollOffset = (scrollOffset - (delta * 20).toInt()).coerceIn(0, maxScroll)
            return true
        }
        for (child in children.reversed()) {
            if (child.visible && child.mouseScrolled(mouseX, mouseY + scrollOffset, delta)) return true
        }
        return false
    }
}
