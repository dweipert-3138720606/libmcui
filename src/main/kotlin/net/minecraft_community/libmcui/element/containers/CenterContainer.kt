package net.minecraft_community.libmcui.element.containers

import net.minecraft_community.libmcui.element.Container

class CenterContainer : Container() {
    override fun layout() {
        children.forEach({ child -> child.layout() })

        if (width == 0 && children.isNotEmpty()) {
            val maxChildWidth = children.maxOf({ child ->
                child.width + child.marginLeft + child.marginRight
            })
            width = maxChildWidth + borderLeftWidth + borderRightWidth + paddingLeft + paddingRight
        }

        if (height == 0 && children.isNotEmpty()) {
            val maxChildHeight = children.maxOf({ child ->
                child.height + child.marginTop + child.marginBottom
            })
            height = maxChildHeight + borderTopWidth + borderBottomWidth + paddingTop + paddingBottom
        }

        for (child in children) {
            child.x = contentLeft + (contentWidth - child.width) / 2
            child.y = contentTop + (contentHeight - child.height) / 2
        }

        layoutChildren()
    }
}
