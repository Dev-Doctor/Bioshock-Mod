package net.devdoctor.bioshock.Networking;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.Networking.packet.EveDrinkingC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModPackaces {
    public static final Identifier USE_EVE_ID = new Identifier(BioshockMod.MOD_ID, "use_eve");
    public static final Identifier EVE_SYNC_ID = new Identifier(BioshockMod.MOD_ID, "eve_sync");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(USE_EVE_ID, EveDrinkingC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}
