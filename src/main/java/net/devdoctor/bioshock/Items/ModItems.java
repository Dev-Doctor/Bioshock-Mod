package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.BioshockMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BioshockMod.MOD_ID);

    public static final RegistryObject<Item> BRASS_INGOT = ITEMS.register("brass_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRASS_NUGGET = ITEMS.register("brass_nugget", () -> new Item(new Item.Properties()));

    // INGOTS
    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget", () -> new Item(new Item.Properties()));

    /* ################################# WEAPONS ################################# */
    public static final RegistryObject<Item> REVOLVER = ITEMS.register("revolver", () -> new Item(new Item.Properties()));

    /* #################################   AMMO  ################################# */
    public static final RegistryObject<Item> S_PISTOL_ROUNDS = ITEMS.register("standard_pistol_rounds", () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
