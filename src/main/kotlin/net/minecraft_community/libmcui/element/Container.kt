package net.minecraft_community.libmcui.element

import net.minecraft.client.gui.GuiGraphics

abstract class Container : Element() {
    val children: MutableList<Element> = mutableListOf()

    var paddingLeft: Int = 0
    var paddingRight: Int = 0
    var paddingTop: Int = 0
    var paddingBottom: Int = 0
    var padding: Int
        get() = ((paddingLeft + paddingRight + paddingTop + paddingBottom) / 4).toInt()
        set(v) {
            paddingLeft = v
            paddingRight = v
            paddingTop = v
            paddingBottom = v
        }

    var gap: Int = 0

    fun addChild(child: Element) {
        child.parent = this
        children.add(child)
    }

    override fun layout() {
        for (child in children) {
            child.layout()
        }
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        if (!visible) return
        for (child in children) {
            if (child.visible) {
                child.render(guiGraphics, mouseX, mouseY, partialTick)
            }
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (!visible) return false
        for (child in children.reversed()) {
            if (!child.visible || child.mouseFilter == Element.MouseFilter.Ignore) continue
            if (child.mouseClicked(mouseX, mouseY, button) && child.mouseFilter == Element.MouseFilter.Stop) return true
        }
        return false
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean {
        if (!visible) return false
        for (child in children.reversed()) {
            if (!child.visible || child.mouseFilter == Element.MouseFilter.Ignore) continue
            if (child.mouseScrolled(mouseX, mouseY, delta) && child.mouseFilter == Element.MouseFilter.Stop) return true
        }
        return false
    }
}
