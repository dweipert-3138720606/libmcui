package net.minecraft_community.libmcui.element.containers

import net.minecraft_community.libmcui.element.Container

class VerticalBox : Container() {
    override fun layout() {
        super.layout()

        val expandChildren = children.filter({ child -> child.stretchRatio > 0 })
        val expandTotal = expandChildren.sumOf({ child -> child.stretchRatio })

        val shrinkHeight = children
            .filter({ child -> child.stretchRatio == 0 })
            .sumOf({ child -> maxOf(child.minHeight, child.height).coerceAtLeast(0) + child.marginTop + child.marginBottom })

        val availableForExpand = (contentHeight - shrinkHeight - (children.size - 1) * gap).coerceAtLeast(0)

        for (child in children) {
            if (child.stretchRatio == 0) {
                child.height = maxOf(child.minHeight, child.height)
            } else {
                child.height = (availableForExpand * child.stretchRatio / expandTotal).coerceAtLeast(child.minHeight)
            }
            child.width = contentWidth - child.marginLeft - child.marginRight
        }

        var currentY = contentTop
        for (child in children) {
            child.x = contentLeft + child.marginLeft
            child.y = currentY + child.marginTop
            currentY += child.marginTop + child.height + child.marginBottom + gap
        }

        relayoutChildren()
    }
}
