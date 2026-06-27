package net.minecraft_community.libmcui.element.containers

import net.minecraft_community.libmcui.element.Container

class CenterContainer : Container() {
    override fun layout() {
        super.layout()

        for (child in children) {
            child.x = contentLeft + (contentWidth - child.width) / 2
            child.y = contentTop + (contentHeight - child.height) / 2
        }

        relayoutChildren()
    }
}
