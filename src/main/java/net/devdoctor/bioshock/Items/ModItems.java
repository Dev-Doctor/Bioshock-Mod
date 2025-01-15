package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.ModItemGroups;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item BRASS_NUGGET = register("brass_nugget", new Item(new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB)));
    public static final Item BRASS_INGOT = register("brass_ingot", new Item(new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB)));

    public static final Item BRASS_SWORD = register("brass_sword", new SwordItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB).maxCount(1)));
    public static final Item BRASS_PICKAXE = register("brass_pickaxe", new PickaxeItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB).maxCount(1)));
    public static final Item BRASS_AXE = register("brass_axe", new AxeItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB).maxCount(1)));
    public static final Item BRASS_SHOVEL = register("brass_shovel", new ShovelItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB).maxCount(1)));
    //public static final Item BRASS_HOE = register("brass_hoe", new HoeItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB).maxCount(1)));

    public static final Item EVE_PHIAL = register("eve_phial", new Evelike(EveType.EVE, new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB).maxCount(16)));

    public static final Item EMPTY_SYRINGE = register("empty_syringe", new Item(new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB)));

    public static final Item RAW_ZINC = register("raw_zinc", new Item(new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB)));
    public static final Item ZINC_INGOT = register("zinc_ingot", new Item(new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB)));
    public static final Item ZINC_NUGGET = register("zinc_nugget", new Item(new Item.Settings().group(ModItemGroups.BIOSHOCK_TAB)));


    public static void registerModItems() {

    }

    public static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, Identifier.of(BioshockMod.MOD_ID, id), item);
    }
}