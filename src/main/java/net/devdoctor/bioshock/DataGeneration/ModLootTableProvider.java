package net.devdoctor.bioshock.DataGeneration;

import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.devdoctor.bioshock.Items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    protected ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CHECKED_FLOOR_TILES);
        addDrop(ModBlocks.ZINC_BLOCK);
        addDrop(ModBlocks.BRASS_BLOCK);
        addDrop(ModBlocks.ZINC_ORE, oreDrops(ModBlocks.ZINC_ORE, ModItems.RAW_ZINC));
    }
}
