package com.netuserget.throwabletorches;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class ThrowableTorchesClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(ThrowableTorches.THROWABLE_TORCH_ENTITY_ENTITY_TYPE, FlyingItemEntityRenderer::new);
	}
}