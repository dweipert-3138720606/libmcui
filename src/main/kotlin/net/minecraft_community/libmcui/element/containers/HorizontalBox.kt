package net.minecraft_community.libmcui.element.containers

import net.minecraft_community.libmcui.element.Container

class HorizontalBox : Container() {
    override fun layout() {
        super.layout()

        val contentWidth = width - marginLeft - marginRight - paddingLeft - paddingRight
        val contentHeight = height - marginTop - marginBottom - paddingTop - paddingBottom

        val expandChildren = children.filter { it.stretchRatio > 0 }
        val expandTotal = expandChildren.sumOf { it.stretchRatio }

        val shrinkWidth = children.filter { it.stretchRatio == 0 }
            .sumOf { maxOf(it.minWidth, it.width).coerceAtLeast(0) + it.marginLeft + it.marginRight }

        val availableForExpand = (contentWidth - shrinkWidth - (children.size - 1) * gap).coerceAtLeast(0)

        for (child in children) {
            if (child.stretchRatio == 0) {
                child.width = maxOf(child.minWidth, child.width)
            } else {
                child.width = (availableForExpand * child.stretchRatio / expandTotal).coerceAtLeast(child.minWidth)
            }
            child.height = contentHeight - child.marginTop - child.marginBottom
        }

        var currentX = x + marginLeft + paddingLeft
        for (child in children) {
            child.x = currentX + child.marginLeft
            child.y = y + marginTop + paddingTop + child.marginTop
            currentX += child.marginLeft + child.width + child.marginRight + gap
        }
    }

}
