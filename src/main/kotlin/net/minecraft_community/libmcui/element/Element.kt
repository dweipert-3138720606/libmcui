package net.minecraft_community.libmcui.element

import net.minecraft.client.gui.GuiGraphics

abstract class Element {
    var x: Int = 0
    var y: Int = 0

    var width: Int = 0
    var height: Int = 0

    var minWidth: Int = 0
    var minHeight: Int = 0

    var stretchRatio: Int = 1

    var marginLeft: Int = 0
    var marginRight: Int = 0
    var marginTop: Int = 0
    var marginBottom: Int = 0

    var margin: Int
        get() = ((marginLeft + marginRight + marginTop + marginBottom) / 4).toInt()
        set(value) {
            marginLeft = value
            marginRight = value
            marginTop = value
            marginBottom = value
        }

    var borderLeftColor: Int = 0xFFFFFF
    var borderRightColor: Int = 0xFFFFFF
    var borderTopColor: Int = 0xFFFFFF
    var borderBottomColor: Int = 0xFFFFFF

    var borderColor: Int
        get() = ((borderLeftColor + borderRightColor + borderTopColor + borderBottomColor) / 4).toInt()
        set(value) {
            borderLeftColor = value
            borderRightColor = value
            borderTopColor = value
            borderBottomColor = value
        }

    var borderLeftWidth: Int = 0
    var borderRightWidth: Int = 0
    var borderTopWidth: Int = 0
    var borderBottomWidth: Int = 0

    var borderWidth: Int
        get() = ((borderLeftWidth + borderRightWidth + borderTopWidth + borderBottomWidth) / 4).toInt()
        set(value) {
            borderLeftWidth = value
            borderRightWidth = value
            borderTopWidth = value
            borderBottomWidth = value
        }

    var paddingLeft: Int = 0
    var paddingRight: Int = 0
    var paddingTop: Int = 0
    var paddingBottom: Int = 0
    var padding: Int
        get() = ((paddingLeft + paddingRight + paddingTop + paddingBottom) / 4).toInt()
        set(value) {
            paddingLeft = value
            paddingRight = value
            paddingTop = value
            paddingBottom = value
        }

    open val contentLeft: Int
        get() = x + marginLeft + borderLeftWidth + paddingLeft

    open val contentTop: Int
        get() = y + marginTop + borderTopWidth + paddingTop

    open val contentWidth: Int
        get() = width - marginLeft - marginRight - borderLeftWidth - borderRightWidth - paddingLeft - paddingRight

    open val contentHeight: Int
        get() = height - marginTop - marginBottom - borderTopWidth - borderBottomWidth - paddingTop - paddingBottom

    var visible: Boolean = true

    var parent: Container? = null

    enum class MouseFilter { Stop, Pass, Ignore }

    var mouseFilter: MouseFilter = MouseFilter.Stop

    open fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float): Boolean {
        if (!visible) {
            return false
        }

        renderBorder(guiGraphics)

        return true
    }

    open fun layout() {}

    open fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean = false

    open fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean = false

    protected fun renderBorder(guiGraphics: GuiGraphics) {
        if (borderWidth <= 0) {
            return
        }

        val boxLeft = x + marginLeft
        val boxRight = x + width - marginRight
        val boxTop = y + marginTop
        val boxBottom = y + height - marginBottom

        if (borderLeftWidth > 0) {
            guiGraphics.fill(boxLeft, boxTop, boxLeft + borderLeftWidth, boxBottom, borderLeftColor)
        }

        if (borderRightWidth > 0) {
            guiGraphics.fill(boxRight - borderRightWidth, boxTop, boxRight, boxBottom, borderRightColor)
        }

        if (borderTopWidth > 0) {
            guiGraphics.fill(boxLeft, boxTop, boxRight, boxTop + borderTopWidth, borderTopColor)
        }

        if (borderBottomWidth > 0) {
            guiGraphics.fill(boxLeft, boxBottom - borderBottomWidth, boxRight, boxBottom, borderBottomColor)
        }
    }

    fun containsPoint(pointX: Int, pointY: Int): Boolean = pointX in x..<(x + width) && pointY in y..<(y + height)
}
