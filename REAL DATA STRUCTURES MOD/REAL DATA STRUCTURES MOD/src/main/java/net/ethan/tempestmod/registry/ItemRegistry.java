package net.ethan.tempestmod.registry;

import net.ethan.tempestmod.TempestMod;
import net.ethan.tempestmod.items.AdventureDiary;
import net.ethan.tempestmod.items.CreatureEncyclopedia;
import net.ethan.tempestmod.items.EnderBackpack;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TempestMod.MOD_ID);

    public static final RegistryObject<Item> ENDER_BACKPACK = ITEMS.register("ender_backpack",
            () -> new EnderBackpack(new Item.Properties()));

    public static final RegistryObject<Item> SOAP = ITEMS.register("soap",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CREATURE_ENCYCLOPEDIA = ITEMS.register("creature_encyclopedia",
            () -> new CreatureEncyclopedia(new Item.Properties()));

    public static final RegistryObject<Item> ADVENTURER_DIARY = ITEMS.register("adventure_diary",
            () -> new AdventureDiary(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
