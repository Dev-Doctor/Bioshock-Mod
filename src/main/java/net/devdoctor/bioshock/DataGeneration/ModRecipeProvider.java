package net.devdoctor.bioshock.DataGeneration;

import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.devdoctor.bioshock.Items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> ZINC_SMELTABLES = List.of(ModBlocks.ZINC_ORE, ModItems.RAW_ZINC);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        offerSmelting(consumer, ZINC_SMELTABLES, RecipeCategory.MISC, ModItems.ZINC_INGOT, .7f, 200, "zinc");
        offerBlasting(consumer, ZINC_SMELTABLES, RecipeCategory.MISC, ModItems.ZINC_INGOT, .7f, 100, "zinc");
        offerReversibleCompactingRecipes(consumer, RecipeCategory.MISC, ModItems.ZINC_INGOT, RecipeCategory.MISC, ModBlocks.ZINC_BLOCK);
        offerReversibleCompactingRecipes(consumer, RecipeCategory.MISC, ModItems.BRASS_INGOT, RecipeCategory.MISC, ModBlocks.BRASS_BLOCK);
        // offerReversibleCompactingRecipes(consumer, ModItems.ZINC_NUGGET, ModItems.ZINC_INGOT);
        // offerReversibleCompactingRecipes(consumer, ModItems.BRASS_NUGGET, ModItems.BRASS_INGOT);
    }
}
