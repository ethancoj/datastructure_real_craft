package net.ethan.tempestmod;

import com.mojang.logging.LogUtils;
import net.ethan.tempestmod.entity.ModEntities;
import net.ethan.tempestmod.entity.client.DwarfRenderer;
import net.ethan.tempestmod.entity.custom.DwarfEntity;
import net.ethan.tempestmod.event.DiaryEventListener;
import net.ethan.tempestmod.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

@Mod(TempestMod.MOD_ID)
public class TempestMod {
    public static final String MOD_ID = "tempestmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TempestMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TabRegistry.register(modEventBus);
        ItemRegistry.register(modEventBus);
        //BlockRegistry.register(modEventBus);
        ContainerRegistry.register(modEventBus);
        ScreenRegistry.registerScreens(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
        DiaryEventListener.register(MinecraftForge.EVENT_BUS);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {


    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ItemRegistry.ENDER_BACKPACK.get());
            event.accept(ItemRegistry.SOAP.get());
            event.accept(ItemRegistry.CREATURE_ENCYCLOPEDIA.get());
            event.accept(ItemRegistry.ADVENTURER_DIARY.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Server starting code here
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.DWARF.get(), DwarfRenderer::new);
        }
    }


}
