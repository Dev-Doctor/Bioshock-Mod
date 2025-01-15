package net.devdoctor.bioshock.Blocks;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.ModItemGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block CHECKED_FLOOR_TILES = registerBlockWithItem("checked_floor_tiles", new Block(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE)), ModItemGroups.BIOSHOCK_BUILDING);
    public static final Block ZINC_ORE = registerBlockWithItem("zinc_ore", new Block(Block.Settings.copy(Blocks.IRON_ORE).requiresTool()), ModItemGroups.BIOSHOCK_BUILDING);
    public static final Block ZINC_BLOCK = registerBlockWithItem("zinc_block", new Block(Block.Settings.copy(Blocks.IRON_BLOCK).requiresTool()), ModItemGroups.BIOSHOCK_BUILDING);
    public static final Block BRASS_BLOCK = registerBlockWithItem("brass_block", new Block(Block.Settings.copy(Blocks.IRON_BLOCK).requiresTool()), ModItemGroups.BIOSHOCK_BUILDING);

    // public static final Block TEMP_DOOR = registerBlockWithItem("door", new DoorBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_DOOR)), ModItemGroups.BIOSHOCK_BUILDING);

    public static void registerModBlocks() {}

    public static Block registerBlockWithItem(String name, Block block, ItemGroup itemGroup) {
        Identifier id = Identifier.of(BioshockMod.MOD_ID, name);

        // register the block item.
        BlockItem blockItem = new BlockItem(block, new Item.Settings().group(itemGroup));
        Registry.register(Registry.ITEM, id, blockItem);

        // register the block
        return Registry.register(Registry.BLOCK, id, block);
    }

    public static Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, Identifier.of(BioshockMod.MOD_ID, name), block);
    }
}