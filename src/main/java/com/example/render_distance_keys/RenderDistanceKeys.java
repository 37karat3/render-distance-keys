package com.example.render_distance_keys;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;

public class RenderDistanceKeys implements ClientModInitializer {

    private static KeyBinding increaseKey;
    private static KeyBinding decreaseKey;

    @Override
    public void onInitializeClient() {
        increaseKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.render_distance.increase",
            GLFW.GLFW_KEY_KP_ADD,
            "category.render_distance"
        ));

        decreaseKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.render_distance.decrease",
            GLFW.GLFW_KEY_KP_SUBTRACT,
            "category.render_distance"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (increaseKey.wasPressed()) {
                MinecraftClient.getInstance().options.setViewDistance(
                    MinecraftClient.getInstance().options.getViewDistance() + 1
                );
            }
            if (decreaseKey.wasPressed()) {
                MinecraftClient.getInstance().options.setViewDistance(
                    Math.max(2, MinecraftClient.getInstance().options.getViewDistance() - 1)
                );
            }
        });
    }
}
