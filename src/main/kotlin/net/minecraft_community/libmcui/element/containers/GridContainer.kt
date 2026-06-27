package net.minecraft_community.libmcui.element.containers

import net.minecraft_community.libmcui.element.Container
import kotlin.math.ceil

class GridContainer() : Container() {
    var columns: Int = 2

    constructor(columns: Int) : this() {
        this.columns = columns
    }

    override fun layout() {
        super.layout()

        val rows = maxOf(ceil(children.size.toDouble() / columns).toInt(), 1)
        val cellWidth = (contentWidth - (columns - 1) * gap) / columns
        val cellHeight = if (children.isEmpty()) {
            0
        } else {
            (contentHeight - (rows - 1) * gap) / rows
        }

        children.forEachIndexed({ index, child ->
            if (child.stretchRatio == 0) {
                child.width = maxOf(child.minWidth, child.width)
                    .coerceAtMost((cellWidth - child.marginLeft - child.marginRight).coerceAtLeast(0))
                child.height = maxOf(child.minHeight, child.height)
                    .coerceAtMost((cellHeight - child.marginTop - child.marginBottom).coerceAtLeast(0))
            } else {
                child.width = (cellWidth - child.marginLeft - child.marginRight).coerceAtLeast(child.minWidth)
                child.height = (cellHeight - child.marginTop - child.marginBottom).coerceAtLeast(child.minHeight)
            }
        })

        children.forEachIndexed({ index, child ->
            val col = index % columns
            val row = index / columns
            child.x = contentLeft + col * (cellWidth + gap) + child.marginLeft
            child.y = contentTop + row * (cellHeight + gap) + child.marginTop
        })

        relayoutChildren()
    }
}
