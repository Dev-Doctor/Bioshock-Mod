package net.devdoctor.bioshock.DataGeneration;

import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ZINC_ORE)
                .add(ModBlocks.ZINC_BLOCK)
                .add(ModBlocks.BRASS_BLOCK)
                .add(ModBlocks.CHECKED_FLOOR_TILES);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ZINC_ORE)
                .add(ModBlocks.ZINC_BLOCK)
                .add(ModBlocks.BRASS_BLOCK);
    }
}
