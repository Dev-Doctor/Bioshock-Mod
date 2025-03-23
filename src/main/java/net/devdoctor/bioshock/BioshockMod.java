package net.devdoctor.bioshock;

import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.devdoctor.bioshock.Entities.ModEntities;
import net.devdoctor.bioshock.Entities.custom.AdamSlugEntity;
import net.devdoctor.bioshock.Items.ModItems;
import net.devdoctor.bioshock.Networking.ModPackaces;
import net.devdoctor.bioshock.util.PlayerUtil;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BioshockMod implements ModInitializer {
	public static final String MOD_ID = "bioshock";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModItemGroups.registerItemGroups();

		ModEntities.registerModEntities();

		ModPackaces.registerC2SPackets();

		FabricDefaultAttributeRegistry.register(ModEntities.ADAM_SLUG, AdamSlugEntity.createAdamSlugAttributes());


		/* WATER CRUSHING MECHANIC */
		ServerTickEvents.END_SERVER_TICK.register(client -> {
			client.getPlayerManager().getPlayerList().forEach(player -> {
				if(PlayerUtil.IsUnderWaterPressure(player.getWorld(), player)) {
					// MISSING STUFF
				}
			});
		});

		LOGGER.info("Hello Fabric world!");
	}
}