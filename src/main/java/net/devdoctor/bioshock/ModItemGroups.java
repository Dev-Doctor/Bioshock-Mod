package net.devdoctor.bioshock;

import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.devdoctor.bioshock.Items.ModItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup BIOSHOCK_TAB = FabricItemGroupBuilder.build(
            new Identifier(BioshockMod.MOD_ID, "bioshock_tab"), () -> new ItemStack(ModItems.BRASS_INGOT));
    public static final ItemGroup BIOSHOCK_BUILDING = FabricItemGroupBuilder.build(
            new Identifier(BioshockMod.MOD_ID, "bioshock_building_tab"), () -> new ItemStack(ModBlocks.CHECKED_FLOOR_TILES));
}
