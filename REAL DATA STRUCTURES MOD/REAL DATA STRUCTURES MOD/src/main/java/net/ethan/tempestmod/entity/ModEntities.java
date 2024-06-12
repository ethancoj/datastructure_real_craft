package net.ethan.tempestmod.entity;

import net.ethan.tempestmod.TempestMod;
import net.ethan.tempestmod.entity.custom.DwarfEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
          DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TempestMod.MOD_ID);

       public static final RegistryObject<EntityType<DwarfEntity>> DWARF =
               ENTITY_TYPES.register("dwarf", () -> EntityType.Builder.of(DwarfEntity::new, MobCategory.CREATURE)
                       .sized(1.5f,1.5f).build("dwarf"));


  public static void register(IEventBus eventBus) {
      ENTITY_TYPES.register(eventBus);
  }
}
