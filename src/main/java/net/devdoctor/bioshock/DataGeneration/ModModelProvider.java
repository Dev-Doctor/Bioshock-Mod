package net.devdoctor.bioshock.DataGeneration;

import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.devdoctor.bioshock.Items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHECKED_FLOOR_TILES);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRASS_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ZINC_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ZINC_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BRASS_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRASS_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ZINC, Models.GENERATED);
        itemModelGenerator.register(ModItems.ZINC_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ZINC_NUGGET, Models.GENERATED);

        itemModelGenerator.register(ModItems.BRASS_SWORD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRASS_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRASS_AXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRASS_SHOVEL, Models.GENERATED);

        itemModelGenerator.register(ModItems.EMPTY_SYRINGE, Models.GENERATED);
    }
}
