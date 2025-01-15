package net.devdoctor.bioshock.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil {

    /**
     * Counts the number of copy of the passed item.
     *
     * @param player the player to check
     * @param item the item to count
     * @return the quantity of the item in the player inventory
     */
    public static int countItemInInventory(PlayerEntity player, Item item) {
        PlayerInventory inventory = player.getInventory();
        int result = 0;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack current = inventory.getStack(i);
            if (!current.isEmpty() && current.isItemEqual(new ItemStack(item))) {
                result += current.getCount();
            }
        }
        return result;
    }

    public static boolean removeItemFromInventory(PlayerEntity player, Item item, int amount) {
        int nOfItemsToRem = amount;
        PlayerInventory inventory = player.getInventory();

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack current = inventory.getStack(i);
            if(!current.isEmpty() && current.isItemEqual(new ItemStack(item))) {
                // if
                if(current.getCount() >= amount) {
                    current.decrement(nOfItemsToRem);
                } else {
                    nOfItemsToRem -= current.getCount();
                    current.setCount(0);
                }
                if(nOfItemsToRem == 0) return true;
            }
        }
        return false;
    }
}
