package net.devdoctor.bioshock;

import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.devdoctor.bioshock.Items.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup BIOSHOCK_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BioshockMod.MOD_ID,"bioshock_tab"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bioshock_tab"))
                    .icon( () -> new ItemStack(ModItems.BRASS_INGOT)).entries(((displayContext, entries) -> {
                        entries.add(ModItems.BRASS_INGOT);
                        entries.add(ModItems.ZINC_INGOT);
                        entries.add(ModItems.BRASS_NUGGET);
                        entries.add(ModItems.ZINC_NUGGET);
                        entries.add(ModItems.BRASS_SWORD);
                        entries.add(ModItems.BRASS_PICKAXE);
                        entries.add(ModItems.BRASS_AXE);
                        entries.add(ModItems.BRASS_SHOVEL);
                        entries.add(ModItems.EMPTY_SYRINGE);
                        entries.add(ModItems.EVE_PHIAL);
                        entries.add(ModItems.ADAM_SYRINGE);
                        entries.add(ModItems.REVOLVER);
                        entries.add(ModItems.S_PISTOL_ROUNDS);
                        entries.add(ModItems.AP_PISTOL_ROUNDS);
                        entries.add(ModItems.AnP_PISTOL_ROUNDS);
                        entries.add(ModItems.ADAM_SLUG_BUCKET);
                        entries.add(ModItems.EMPTY_ADAM_BOTTLE);
                        entries.add(ModItems.ADAM_BOTTLE);
                        entries.add(ModItems.TEST_ITEM);
                    })).build()
    );
    public static final ItemGroup BIOSHOCK_BUILDING = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BioshockMod.MOD_ID,"bioshock_building_tab"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bioshock.bioshock_building_tab"))
                    .icon( () -> new ItemStack(ModBlocks.CHECKED_FLOOR_TILES)).entries(((displayContext, entries) -> {
                        entries.add(ModBlocks.CHECKED_FLOOR_TILES);
                        entries.add(ModBlocks.BRASS_BLOCK);
                        entries.add(ModBlocks.ZINC_BLOCK);
                        entries.add(ModBlocks.ZINC_ORE);
                    })).build()
    );

    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(ModItems.ADAM_SLUG_SPAWN_EGG);
        });
    }
}
