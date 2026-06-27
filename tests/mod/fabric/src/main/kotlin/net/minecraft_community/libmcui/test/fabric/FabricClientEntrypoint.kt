package net.minecraft_community.libmcui.test.fabric

import com.mojang.brigadier.Command
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

class FabricClientEntrypoint : ClientModInitializer {
    private var shouldOpenScreen = false

    override fun onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register({ dispatcher, _ ->
            dispatcher.register(
                ClientCommandManager.literal("libmcui_test").executes({ _ ->
                    shouldOpenScreen = true
                    Command.SINGLE_SUCCESS
                })
            )
        })

        ClientTickEvents.END_CLIENT_TICK.register({ client ->
            if (shouldOpenScreen) {
                shouldOpenScreen = false
                client.setScreen(TestScreen())
            }
        })
    }
}
