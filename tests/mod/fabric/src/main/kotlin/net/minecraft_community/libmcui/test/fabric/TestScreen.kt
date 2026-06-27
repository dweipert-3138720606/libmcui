package net.minecraft_community.libmcui.test.fabric

import net.minecraft.network.chat.Component
import net.minecraft_community.libmcui.element.Button
import net.minecraft_community.libmcui.element.Label
import net.minecraft_community.libmcui.element.Screen
import net.minecraft_community.libmcui.element.containers.*

class TestScreen(title: Component = Component.literal("LibMcUI Test")) : Screen(title) {
    init {
        root = VerticalBox().apply({
            margin = 20
            padding = 5
            borderColor = 0xFFFFFFFF.toInt()
            borderWidth = 1
            addChild(
                Label().apply({
                    text = "LibMcUI Test - all elements"
                    textColor = 0xFFFFAA00.toInt()
                })
            )

            addChild(
                HorizontalBox().apply({
                    addChild(
                        Button().apply({
                            text = "Click Me"
                            color = 0xFF333333.toInt()
                            hoverColor = 0xFF555555.toInt()
                            onClick = { println("Clicked 'Click Me'") }
                        })
                    )
                    addChild(
                        Button().apply({
                            text = "And Me"
                            color = 0xFF333333.toInt()
                            hoverColor = 0xFF555555.toInt()
                            onClick = { println("Clicked 'And Me'") }
                        })
                    )
                    addChild(
                        Button().apply({
                            text = "Me Too"
                            color = 0xFF333333.toInt()
                            hoverColor = 0xFF555555.toInt()
                            onClick = { println("Clicked 'Me Too'") }
                        })
                    )
                })
            )

            addChild(
                GridContainer(columns = 3).apply({
                    for (i in 1..6) {
                        addChild(
                            Button().apply({
                                text = "Grid $i"
                                color = 0xFF333333.toInt()
                                hoverColor = 0xFF555555.toInt()
                                onClick = { println("Clicked Grid $i") }
                            })
                        )
                    }
                })
            )

            addChild(
                CenterContainer().apply({
                    addChild(
                        Label().apply({
                            text = "Centered Label"
                        })
                    )
                })
            )

            addChild(
                ScrollContainer().apply({
                    for (i in 1..20) {
                        addChild(
                            Label().apply({
                                text = "Scrollable item $i"
                            })
                        )
                    }
                })
            )
        })
    }
}
