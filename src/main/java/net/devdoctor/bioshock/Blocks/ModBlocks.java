package net.devdoctor.bioshock.Blocks;

import net.devdoctor.bioshock.BioshockMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block CHECKED_FLOOR_TILES = registerBlockWithItem("checked_floor_tiles", new Block(FabricBlockSettings.copy(Blocks.BLACK_CONCRETE)));
    public static final Block ZINC_ORE = registerBlockWithItem("zinc_ore", new Block(FabricBlockSettings.copy(Blocks.IRON_ORE).requiresTool()));
    public static final Block ZINC_BLOCK = registerBlockWithItem("zinc_block", new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).requiresTool()));
    public static final Block BRASS_BLOCK = registerBlockWithItem("brass_block", new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).requiresTool()));

    // public static final Block TEMP_DOOR = registerBlockWithItem("door", new DoorBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_DOOR)), ModItemGroups.BIOSHOCK_BUILDING);

    public static void registerModBlocks() {}

    public static Block registerBlockWithItem(String name, Block block) {
        Identifier id = Identifier.of(BioshockMod.MOD_ID, name);

        // register the block item.
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
        Registry.register(Registries.ITEM, id, blockItem);

        // register the block
        return registerBlock(name, block);
    }

    public static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(BioshockMod.MOD_ID, name), block);
    }
}