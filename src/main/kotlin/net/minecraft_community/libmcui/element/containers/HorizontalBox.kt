package net.minecraft_community.libmcui.element.containers

import net.minecraft_community.libmcui.element.Container

class HorizontalBox : Container() {
    override fun layout() {
        super.layout()

        val expandChildren = children.filter({ child -> child.stretchRatio > 0 })
        val expandTotal = expandChildren.sumOf({ child -> child.stretchRatio })

        val shrinkWidth = children
            .filter({ child -> child.stretchRatio == 0 })
            .sumOf({ child -> maxOf(child.minWidth, child.width).coerceAtLeast(0) + child.marginLeft + child.marginRight })

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
