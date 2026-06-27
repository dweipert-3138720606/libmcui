package net.minecraft_community.libmcui.test

import net.minecraft.network.chat.Component
import net.minecraft_community.libmcui.element.Button
import net.minecraft_community.libmcui.element.Label
import net.minecraft_community.libmcui.element.Screen
import net.minecraft_community.libmcui.element.containers.CenterContainer
import net.minecraft_community.libmcui.element.containers.HorizontalBox
import net.minecraft_community.libmcui.element.containers.VerticalBox

class TabbedTestScreen(title: Component = Component.literal("LibMcUI Tabs")) : Screen(title) {
    private var activeTab = 0

    private val tab1Content = VerticalBox().apply({
        addChild(Label().apply({ text = "This is Tab 1" }))
        addChild(Button().apply({
            text = "Tab 1 Action"
            onClick = { println("Tab 1 action clicked") }
        }))
    })

    private val tab2Content = VerticalBox().apply({
        addChild(Label().apply({ text = "This is Tab 2" }))
        addChild(Button().apply({
            text = "Tab 2 Action"
            onClick = { println("Tab 2 action clicked") }
        }))
    })

    private val tab1Button = Button().apply({
        text = "Tab 1"
        onClick = { switchTab(0) }
    })

    private val tab2Button = Button().apply({
        text = "Tab 2"
        onClick = { switchTab(1) }
    })

    private val contentContainer = VerticalBox()

    init {
        root = VerticalBox().apply({
            margin = 10
            padding = 5
            paddingTop = 0
            borderWidth = 1

            addChild(CenterContainer().apply({
                stretchRatio = 0
                addChild(HorizontalBox().apply({
                    addChild(tab1Button)
                    addChild(tab2Button)
                }))
            }))
            addChild(contentContainer)
        })

        switchTab(0)
    }

    private fun switchTab(index: Int) {
        activeTab = index
        contentContainer.children.clear()
        contentContainer.addChild(if (index == 0) tab1Content else tab2Content)

        tab1Button.color = if (index == 0) 0xFF555555.toInt() else 0xFF333333.toInt()
        tab2Button.color = if (index == 1) 0xFF555555.toInt() else 0xFF333333.toInt()

        root.layout()
    }
}
