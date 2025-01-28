package net.devdoctor.bioshock.util;

import net.minecraft.nbt.NbtCompound;

public class EveData {
    public static final int MAX_EVE = 50;
    public static final String BIOSHOCK_NBT_ID = "bioshock_data";
    public static final String EVE_NBT_ID = "eve";

    public static int addEve(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int eve = nbt.getInt(EVE_NBT_ID);
        if(eve + amount >= MAX_EVE) {
            eve = MAX_EVE;
        } else {
            eve += amount;
        }
        nbt.putInt(EVE_NBT_ID, eve);
        // sync data
        return eve;
    }

    public static int removeEve(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int eve = nbt.getInt(EVE_NBT_ID);
        if(eve - amount < 0) {
            eve = 0;
        } else {
            eve -= amount;
        }
        nbt.putInt(EVE_NBT_ID, eve);
        // sync data
        return eve;
    }
}
