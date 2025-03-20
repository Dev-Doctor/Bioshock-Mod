package net.devdoctor.bioshock;

import net.devdoctor.bioshock.Entities.ModEntities;
import net.devdoctor.bioshock.Entitites.AdamSlugModel;
import net.devdoctor.bioshock.Entitites.AdamSlugRenderer;
import net.devdoctor.bioshock.Events.KeyInputHandler;
import net.devdoctor.bioshock.Networking.ModPackaces;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BioshockModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyInputHandler.registerKeyBindings();
		ModPackaces.registerS2CPackets();

		//EntityModelLayerRegistry.registerModelLayer(AdamSlugModel.ADAM_SLUG, AdamSlugModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.ADAM_SLUG, AdamSlugRenderer::new);
	}
}