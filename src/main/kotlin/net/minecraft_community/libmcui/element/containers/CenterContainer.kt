package net.minecraft_community.libmcui.element.containers

import net.minecraft_community.libmcui.element.Container

class CenterContainer : Container() {
    override fun layout() {
        super.layout()

        val contentWidth = width - marginLeft - marginRight - paddingLeft - paddingRight
        val contentHeight = height - marginTop - marginBottom - paddingTop - paddingBottom

        for (child in children) {
            child.x = x + marginLeft + paddingLeft + (contentWidth - child.width) / 2
            child.y = y + marginTop + paddingTop + (contentHeight - child.height) / 2
        }
    }
}
