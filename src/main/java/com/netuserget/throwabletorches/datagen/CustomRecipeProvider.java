package com.netuserget.throwabletorches.datagen;

import com.netuserget.throwabletorches.ThrowableTorches;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

public class CustomRecipeProvider extends FabricRecipeProvider {
    public CustomRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ThrowableTorches.THROWABLE_TORCH_ITEM, 4)
                .pattern("k ")
                .pattern("sc")
                .input('k', Items.COAL)
                .input('s', Items.STICK)
                .input('c', Items.COBBLESTONE)
                .criterion(FabricRecipeProvider.hasItem(Items.TORCH), FabricRecipeProvider.conditionsFromItem(Items.TORCH))
                .offerTo(exporter);
    }
}
