package net.ethan.tempestmod.registry;

import net.ethan.tempestmod.TempestMod;
import net.ethan.tempestmod.items.AdventureDiary;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TempestMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NEW_TAB = CREATIVE_MODE_TABS.register("new_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(Items.DIAMOND))
                    .title(Component.translatable("creativetab.new_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ItemRegistry.ENDER_BACKPACK.get());
                        output.accept(ItemRegistry.SOAP.get());
                        output.accept(ItemRegistry.CREATURE_ENCYCLOPEDIA.get());
                        output.accept(ItemRegistry.ADVENTURER_DIARY.get());

                        ///output.accept(BlockRegistry.AUTOFARMER_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
