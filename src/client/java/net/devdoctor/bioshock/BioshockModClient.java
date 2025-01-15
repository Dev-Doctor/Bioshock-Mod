package net.devdoctor.bioshock;

import net.devdoctor.bioshock.Events.KeyInputHandler;
import net.devdoctor.bioshock.Networking.ModPackaces;
import net.fabricmc.api.ClientModInitializer;

public class BioshockModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyInputHandler.registerKeyBindings();
		ModPackaces.registerS2CPackets();
	}
}