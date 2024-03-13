package com.netuserget.throwabletorches.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ThrowableTorchesDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(CustomEnglishLangProvider::new);
        pack.addProvider(CustomRecipeProvider::new);
        pack.addProvider(CustomModelProvider::new);
    }
}
