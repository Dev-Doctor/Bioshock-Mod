package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.Entities.ModEntities;
import net.devdoctor.bioshock.Items.Enums.EWeaponType;
import net.devdoctor.bioshock.Items.Enums.EveType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item BRASS_NUGGET = register("brass_nugget", new Item(new Item.Settings()));
    public static final Item BRASS_INGOT = register("brass_ingot", new Item(new Item.Settings()));

    public static final Item BRASS_SWORD = register("brass_sword", new SwordItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().maxCount(1)));
    public static final Item BRASS_PICKAXE = register("brass_pickaxe", new PickaxeItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().maxCount(1)));
    public static final Item BRASS_AXE = register("brass_axe", new AxeItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().maxCount(1)));
    public static final Item BRASS_SHOVEL = register("brass_shovel", new ShovelItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().maxCount(1)));
    //public static final Item BRASS_HOE = register("brass_hoe", new HoeItem(ModToolMaterials.BRASS, 3, -2.4F, new Item.Settings().maxCount(1)));

    public static final Item EVE_PHIAL = register("eve_phial", new Evelike(EveType.EVE, new Item.Settings().maxCount(16)));

    public static final Item EMPTY_SYRINGE = register("empty_syringe", new Item(new Item.Settings()));

    public static final Item RAW_ZINC = register("raw_zinc", new Item(new Item.Settings()));
    public static final Item ZINC_INGOT = register("zinc_ingot", new Item(new Item.Settings()));
    public static final Item ZINC_NUGGET = register("zinc_nugget", new Item(new Item.Settings()));

    /* ################################# WEAPONS ################################# */
    public static final Item ADAM_SYRINGE = register("adam_syringe", new AdamSyringe(new Item.Settings().maxCount(1)));

    public static final Item REVOLVER = register("revolver", new GunLike(new Item.Settings(), EWeaponType.REVOLVER));

    /* #################################   AMMO  ################################# */
    public static final Item S_PISTOL_ROUNDS = register("standard_pistol_rounds", new Item(new Item.Settings()));
    public static final Item AP_PISTOL_ROUNDS = register("ap_pistol_rounds", new Item(new Item.Settings()));
    public static final Item AnP_PISTOL_ROUNDS = register("antipersonnel_pistol_rounds", new Item(new Item.Settings()));

    public static final Item ADAM_SLUG_SPAWN_EGG = register("adam_slug_spawn_egg", new SpawnEggItem(
            ModEntities.ADAM_SLUG, 0x000000, 0xef0000, new Item.Settings()));
    public static final Item ADAM_SLUG_BUCKET = register("adam_slug_bucket", new EntityBucketItem(ModEntities.ADAM_SLUG, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, new Item.Settings().maxCount(1)));

    public static final Item TEST_ITEM = register("test_item", new TestItem(new Item.Settings()));

    public static final Item ADAM_BOTTLE = register("adam_bottle", new Item(new Item.Settings()));
    public static final Item EMPTY_ADAM_BOTTLE = register("empty_adam_bottle", new Item(new Item.Settings()));

    public static void registerModItems() {

    }

    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(BioshockMod.MOD_ID, id), item);
    }
}