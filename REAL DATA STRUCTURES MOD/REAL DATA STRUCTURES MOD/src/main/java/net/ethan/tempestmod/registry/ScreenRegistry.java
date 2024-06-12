package net.ethan.tempestmod.registry;

import net.ethan.tempestmod.TempestMod;
import net.ethan.tempestmod.screen.EnderBackpackScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(modid = TempestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ScreenRegistry {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ContainerRegistry.ENDER_BACKPACK_CONTAINER.get(), EnderBackpackScreen::new);
    }

    public static void registerScreens(IEventBus eventBus) {
        eventBus.register(ScreenRegistry.class);
    }
}
