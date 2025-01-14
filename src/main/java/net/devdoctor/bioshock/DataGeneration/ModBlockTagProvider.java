package net.devdoctor.bioshock.DataGeneration;

import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BlockTags;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ZINC_ORE)
                .add(ModBlocks.ZINC_BLOCK)
                .add(ModBlocks.BRASS_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ZINC_ORE)
                .add(ModBlocks.ZINC_BLOCK)
                .add(ModBlocks.BRASS_BLOCK);
    }
}
