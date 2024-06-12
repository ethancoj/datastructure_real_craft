package net.ethan.tempestmod.items;

import net.ethan.tempestmod.container.EnderBackpackContainer;
import net.ethan.tempestmod.custom.EnderBackpackInventory;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class EnderBackpack extends Item {

    public EnderBackpack(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            stack.getOrCreateTag().put("inventory", getInventory(stack).serializeNBT());

            MenuProvider menuProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.translatable("container.ender_backpack");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, net.minecraft.world.entity.player.Inventory playerInventory, Player playerEntity) {
                    return new EnderBackpackContainer(id, playerInventory, getInventory(stack));
                }
            };

            NetworkHooks.openScreen((ServerPlayer) player, menuProvider, buf -> buf.writeItemStack(stack, false));
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }

    public EnderBackpackInventory getInventory(ItemStack stack) {
        EnderBackpackInventory inventory = new EnderBackpackInventory();
        if (stack.hasTag() && stack.getTag().contains("inventory")) {
            inventory.deserializeNBT(stack.getTag().getCompound("inventory"));
        }
        return inventory;
    }
}
