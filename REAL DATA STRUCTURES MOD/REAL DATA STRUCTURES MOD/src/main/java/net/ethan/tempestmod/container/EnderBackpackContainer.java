package net.ethan.tempestmod.container;

import net.ethan.tempestmod.custom.EnderBackpackInventory;
import net.ethan.tempestmod.registry.ContainerRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;


public class EnderBackpackContainer extends AbstractContainerMenu {
    private final EnderBackpackInventory inventory;

    public EnderBackpackContainer(int id, Inventory playerInventory, EnderBackpackInventory inventory) {
        super(ContainerRegistry.ENDER_BACKPACK_CONTAINER.get(), id);
        this.inventory = inventory;

        // Add backpack slots
        int numRows = inventory.getSlots() / 9;
        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new SlotItemHandler(inventory, col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }

        // Add player inventory slots
        int yOffset = 84 + (numRows - 1) * 18;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, yOffset + row * 18));
            }
        }

        // Add hotbar slots
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, yOffset + 58));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            int numRows = this.inventory.getSlots() / 9;
            if (index < numRows * 9) {
                if (!this.moveItemStackTo(itemstack1, numRows * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, numRows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public EnderBackpackInventory getInventory() {
        return this.inventory;
    }
}
