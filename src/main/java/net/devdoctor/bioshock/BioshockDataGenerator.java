package net.devdoctor.bioshock;

import net.devdoctor.bioshock.DataGeneration.ModBlockTagProvider;
import net.devdoctor.bioshock.DataGeneration.ModLootTableProvider;
import net.devdoctor.bioshock.DataGeneration.ModModelProvider;
import net.devdoctor.bioshock.DataGeneration.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BioshockDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        /*
        generator.addProvider(ModBlockTagProvider::new);
        generator.addProvider(ModLootTableProvider::new);
        generator.addProvider(ModModelProvider::new);
        generator.addProvider(ModRecipeProvider::new);
         */
    }
}
