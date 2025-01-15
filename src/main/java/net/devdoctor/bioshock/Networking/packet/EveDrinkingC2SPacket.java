package net.devdoctor.bioshock.Networking.packet;

import net.devdoctor.bioshock.util.EveData;
import net.devdoctor.bioshock.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class EveDrinkingC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler, PacketByteBuf bug, PacketSender responseSender) {
        // ONLY SERVER SIDE!
        EveData.removeEve(((IEntityDataSaver) player), 100);
        player.sendMessage(Text.literal("Eve: " + ((IEntityDataSaver) player).getPersistentData().getInt(EveData.EVE_NBT_ID))
                .fillStyle(Style.EMPTY.withColor(Formatting.DARK_RED)), true);
        // sync data
    }
}
