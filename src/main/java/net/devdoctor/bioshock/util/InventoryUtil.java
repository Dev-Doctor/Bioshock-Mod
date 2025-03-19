package net.devdoctor.bioshock.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

public class InventoryUtil {

    /**
     * Counts the number of copy of the passed itemToRemove.
     *
     * @param player the player to check
     * @param item the itemToRemove to count
     * @return the quantity of the itemToRemove in the player inventory
     */
    public static int countItemInInventory(PlayerEntity player, Item item) {
        PlayerInventory inventory = player.getInventory();

        if(item == null) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack current = inventory.getStack(i);
            if (!current.isEmpty() && current.getItem() == item) {
                result += current.getCount();
            }
        }
        return result;
    }

    public static boolean removeItemFromInventory(PlayerEntity player, Item itemToRemove, int amount) {
        int nOfItemsToRem = amount;
        PlayerInventory inventory = player.getInventory();

        if(itemToRemove == null) {
            return false;
        }

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack current = inventory.getStack(i);
            if(!current.isEmpty() && current.getItem() == itemToRemove) {
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

    public static Item searchForItemMatchingTag(PlayerEntity entity, TagKey<Item> tagKey) {
        PlayerInventory inventory = entity.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack current = inventory.getStack(i);
            if(current.streamTags().anyMatch(tagKey::equals)) {
                return current.getItem();
            }
        }
        return null;
    }
}
