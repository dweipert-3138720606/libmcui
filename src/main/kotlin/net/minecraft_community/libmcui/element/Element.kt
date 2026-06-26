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

    var visible: Boolean = true

    var parent: Container? = null

    enum class MouseFilter { Stop, Pass, Ignore }

    var mouseFilter: MouseFilter = MouseFilter.Stop

    abstract fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float)

    open fun layout() {}

    open fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean = false

    open fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean = false

    fun containsPoint(pointX: Int, pointY: Int): Boolean = pointX in x..<(x + width) && pointY in y..<(y + height)
}
