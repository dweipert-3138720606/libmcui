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

    protected fun deriveWidthFromSumOfChildren(): Int {
        val naturalContentWidth = children.sumOf({ child ->
            maxOf(child.minWidth, child.width).coerceAtLeast(0) + child.marginLeft + child.marginRight
        }) + (children.size - 1) * gap

        return naturalContentWidth + borderLeftWidth + borderRightWidth + paddingLeft + paddingRight
    }

    protected fun maybeDeriveWidthFromSumOfChildren() {
        if (width == 0 && children.isNotEmpty()) {
            width = deriveWidthFromSumOfChildren()
        }
    }

    protected fun deriveHeightFromSumOfChildren(): Int {
        val naturalContentHeight = children.sumOf({ child ->
            maxOf(child.minHeight, child.height).coerceAtLeast(0) + child.marginTop + child.marginBottom
        }) + (children.size - 1) * gap

        return naturalContentHeight + borderTopWidth + borderBottomWidth + paddingTop + paddingBottom
    }

    protected fun maybeDeriveHeightFromSumOfChildren() {
        if (height == 0 && children.isNotEmpty()) {
            height = deriveHeightFromSumOfChildren()
        }
    }

    protected fun deriveWidthFromMaxOfChildren(): Int {
        val maxChildWidth = children.maxOf({ child ->
            child.width + child.marginLeft + child.marginRight
        })

        return maxChildWidth + borderLeftWidth + borderRightWidth + paddingLeft + paddingRight
    }

    protected fun maybeDeriveWidthFromMaxOfChildren() {
        if (width == 0 && children.isNotEmpty()) {
            width = deriveWidthFromMaxOfChildren()
        }
    }

    protected fun deriveHeightFromMaxOfChildren(): Int {
        val maxChildHeight = children.maxOf({ child ->
            child.height + child.marginTop + child.marginBottom
        })

        return maxChildHeight + borderTopWidth + borderBottomWidth + paddingTop + paddingBottom
    }

    protected fun maybeDeriveHeightFromMaxOfChildren() {
        if (height == 0 && children.isNotEmpty()) {
            height = deriveHeightFromMaxOfChildren()
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
