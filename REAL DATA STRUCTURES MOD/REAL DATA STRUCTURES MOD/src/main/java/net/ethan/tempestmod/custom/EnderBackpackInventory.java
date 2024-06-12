package net.ethan.tempestmod.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EnderBackpackInventory implements IItemHandlerModifiable, INBTSerializable<CompoundTag> {
    private static final int INITIAL_SLOTS = 9; // 1 row of 9 slots
    private static final int MAX_SLOTS = 45;   // 5 rows of 9 slots
    private List<ItemStack> stacks;

    public EnderBackpackInventory() {
        this.stacks = new ArrayList<>();
        for (int i = 0; i < INITIAL_SLOTS; i++) {
            stacks.add(ItemStack.EMPTY);
        }
    }

    @Override
    public int getSlots() {
        return stacks.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks.get(slot);
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        stacks.set(slot, stack);
        onContentsChanged(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack existing = stacks.get(slot);

        if (!existing.isEmpty() && (!ItemStack.isSameItemSameTags(existing, stack) || existing.getCount() >= getSlotLimit(slot))) {
            return stack;
        }

        int limit = Math.min(stack.getMaxStackSize(), getSlotLimit(slot));

        if (existing.isEmpty()) {
            if (!simulate) {
                stacks.set(slot, stack.copy());
                onContentsChanged(slot);
            }
            return ItemStack.EMPTY;
        }

        int toInsert = Math.min(stack.getCount(), limit - existing.getCount());

        if (toInsert == 0) {
            return stack;
        }

        if (!simulate) {
            existing.grow(toInsert);
            onContentsChanged(slot);
        }

        ItemStack remainder = stack.copy();
        remainder.shrink(toInsert);
        return remainder;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0) {
            return ItemStack.EMPTY;
        }

        ItemStack existing = stacks.get(slot);

        if (existing.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int toExtract = Math.min(amount, existing.getCount());

        ItemStack extracted = existing.copy();
        extracted.setCount(toExtract);

        if (!simulate) {
            existing.shrink(toExtract);
            onContentsChanged(slot);
        }

        return extracted;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return true;
    }

    protected void onContentsChanged(int slot) {
        adjustSlots();
    }

    private void adjustSlots() {
        int nonEmptySlots = 0;
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                nonEmptySlots++;
            }
        }

        int currentSize = stacks.size();
        int halfSize = currentSize / 2;

        if (nonEmptySlots > halfSize && currentSize < MAX_SLOTS) {
            // Expand slots
            int newSize = Math.min(currentSize + INITIAL_SLOTS, MAX_SLOTS);
            resize(newSize);
        } else if (nonEmptySlots <= halfSize - (INITIAL_SLOTS / 2) && currentSize > INITIAL_SLOTS) {
            // Contract slots
            int newSize = Math.max(INITIAL_SLOTS, currentSize - INITIAL_SLOTS);
            resize(newSize);
        }
    }

    private void resize(int newSize) {
        List<ItemStack> newStacks = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; i++) {
            if (i < stacks.size()) {
                newStacks.add(stacks.get(i));
            } else {
                newStacks.add(ItemStack.EMPTY);
            }
        }
        stacks = newStacks;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        ListTag list = new ListTag();
        for (int i = 0; i < stacks.size(); i++) {
            ItemStack stack = stacks.get(i);
            if (!stack.isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                stack.save(itemTag);
                list.add(itemTag);
            }
        }
        nbt.put("Items", list);
        nbt.putInt("Size", stacks.size());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ListTag list = nbt.getList("Items", 10);
        int size = nbt.getInt("Size");
        stacks = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            stacks.add(ItemStack.EMPTY);
        }
        for (int i = 0; i < list.size(); i++) {
            CompoundTag itemTag = list.getCompound(i);
            int slot = itemTag.getInt("Slot");
            if (slot >= 0 && slot < size) {
                stacks.set(slot, ItemStack.of(itemTag));
            }
        }
    }
}
