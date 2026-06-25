package net.minecraft_community.libmcui.element.containers

import net.minecraft_community.libmcui.element.Container

class VerticalBox : Container() {
    override fun layout() {
        super.layout()

        val contentWidth = width - marginLeft - marginRight - paddingLeft - paddingRight
        val contentHeight = height - marginTop - marginBottom - paddingTop - paddingBottom

        val expandChildren = children.filter { it.stretchRatio > 0 }
        val expandTotal = expandChildren.sumOf { it.stretchRatio }

        val shrinkHeight = children.filter { it.stretchRatio == 0 }
            .sumOf { maxOf(it.minHeight, it.height).coerceAtLeast(0) + it.marginTop + it.marginBottom }

        val availableForExpand = (contentHeight - shrinkHeight - (children.size - 1) * gap).coerceAtLeast(0)

        for (child in children) {
            if (child.stretchRatio == 0) {
                child.height = maxOf(child.minHeight, child.height)
            } else {
                child.height = (availableForExpand * child.stretchRatio / expandTotal).coerceAtLeast(child.minHeight)
            }
            child.width = contentWidth - child.marginLeft - child.marginRight
        }

        var currentY = y + marginTop + paddingTop
        for (child in children) {
            child.x = x + marginLeft + paddingLeft + child.marginLeft
            child.y = currentY + child.marginTop
            currentY += child.marginTop + child.height + child.marginBottom + gap
        }
    }

}
