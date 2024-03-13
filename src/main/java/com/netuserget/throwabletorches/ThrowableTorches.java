package com.netuserget.throwabletorches;

import com.netuserget.throwabletorches.entity.ThrowableTorchEntity;
import com.netuserget.throwabletorches.item.ThrowableTorchItem;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThrowableTorches implements ModInitializer {

	public static final String MODID = "throwabletorches";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final EntityType<ThrowableTorchEntity> THROWABLE_TORCH_ENTITY_ENTITY_TYPE = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier(MODID, "thrown_torch"),
			FabricEntityTypeBuilder.<ThrowableTorchEntity>create(SpawnGroup.MISC, ThrowableTorchEntity::new)
					.dimensions(EntityDimensions.fixed(0.5F, 0.5F))
					.trackRangeBlocks(16).trackedUpdateRate(10)
					.build()
	);

	public static final Item THROWABLE_TORCH_ITEM = new ThrowableTorchItem(new Item.Settings());

	@Override
	public void onInitialize() {
		LOGGER.info("Init, " + MODID);
		Registry.register(Registries.ITEM, new Identifier(MODID, "throwable_torch"), THROWABLE_TORCH_ITEM);
	}
}