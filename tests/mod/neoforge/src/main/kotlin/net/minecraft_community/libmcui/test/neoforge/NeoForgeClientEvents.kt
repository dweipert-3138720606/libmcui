package net.minecraft_community.libmcui.test.neoforge

import com.mojang.brigadier.Command
import net.minecraft.client.Minecraft
import net.minecraft.commands.Commands
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent
import net.minecraft_community.libmcui.test.TestScreen

object NeoForgeClientEvents {
    private var shouldOpenScreen = false

    @SubscribeEvent
    @JvmStatic
    fun onRegisterClientCommands(event: RegisterClientCommandsEvent) {
        event.dispatcher.register(
            Commands.literal("libmcui_test").executes({ _ ->
                shouldOpenScreen = true
                Command.SINGLE_SUCCESS
            })
        )
    }

    @SubscribeEvent
    @JvmStatic
    fun onClientTick(event: ClientTickEvent.Post) {
        if (shouldOpenScreen) {
            shouldOpenScreen = false
            Minecraft.getInstance().setScreen(TestScreen())
        }
    }
}
