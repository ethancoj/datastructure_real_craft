package net.ethan.tempestmod.registry;

import net.ethan.tempestmod.TempestMod;
import net.ethan.tempestmod.container.EnderBackpackContainer;
import net.ethan.tempestmod.custom.EnderBackpackInventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TempestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegistry {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TempestMod.MOD_ID);

    public static final RegistryObject<MenuType<EnderBackpackContainer>> ENDER_BACKPACK_CONTAINER =
            CONTAINERS.register("ender_backpack_container", () -> IForgeMenuType.create((id, inv, data) -> new EnderBackpackContainer(id, inv, new EnderBackpackInventory())));

    public static void register(IEventBus eventBus) {

        CONTAINERS.register(eventBus);
    }
}
