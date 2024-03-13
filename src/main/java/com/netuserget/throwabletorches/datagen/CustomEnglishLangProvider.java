package com.netuserget.throwabletorches.datagen;

import com.netuserget.throwabletorches.ThrowableTorches;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class CustomEnglishLangProvider extends FabricLanguageProvider {

    protected CustomEnglishLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ThrowableTorches.THROWABLE_TORCH_ITEM, "Throwable Torch");
        translationBuilder.add(ThrowableTorches.THROWABLE_TORCH_ENTITY_ENTITY_TYPE, "Thrown Torch");

    }
}
