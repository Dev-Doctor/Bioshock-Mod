package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.BioshockMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> REVOLVER_AMMO = TagKey.of(RegistryKeys.ITEM, new Identifier(BioshockMod.MOD_ID, "pistol_ammo"));
}