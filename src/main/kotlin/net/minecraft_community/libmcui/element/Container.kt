package net.minecraft_community.libmcui.element

import net.minecraft.client.gui.GuiGraphics

abstract class Container : Element() {
    val children: MutableList<Element> = mutableListOf()

    var gap: Int = 0

    fun addChild(child: Element) {
        child.parent = this
        children.add(child)
    }

    protected fun layoutChildren() {
        for (child in children) {
            child.layout()
        }
    }

    override fun layout() {
        for (child in children) {
            child.layout()
        }
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float): Boolean {
        if (!super.render(guiGraphics, mouseX, mouseY, partialTick)) {
            return false
        }

        for (child in children) {
            if (child.visible) {
                child.render(guiGraphics, mouseX, mouseY, partialTick)
            }
        }

        return true
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (!visible) {
            return false
        }

        for (child in children.reversed()) {
            if (!child.visible) {
                continue
            }

            if (child.mouseFilter == Element.MouseFilter.Ignore) {
                continue
            }

            if (child.mouseClicked(mouseX, mouseY, button) && child.mouseFilter == Element.MouseFilter.Stop) {
                return true
            }
        }

        return false
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean {
        if (!visible) {
            return false
        }

        for (child in children.reversed()) {
            if (!child.visible) {
                continue
            }

            if (child.mouseFilter == Element.MouseFilter.Ignore) {
                continue
            }

            if (child.mouseScrolled(mouseX, mouseY, delta) && child.mouseFilter == Element.MouseFilter.Stop) {
                return true
            }
        }

        return false
    }
}
