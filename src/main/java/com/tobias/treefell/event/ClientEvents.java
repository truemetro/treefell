package com.tobias.treefell.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.tobias.treefell.TreeFell;
import com.tobias.treefell.util.KeyBinding;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = TreeFell.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.FELLING_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed a key"));
            }
        }
    }

    @Mod.EventBusSubscriber(modid = TreeFell.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.FELLING_KEY);
        }
    }
}