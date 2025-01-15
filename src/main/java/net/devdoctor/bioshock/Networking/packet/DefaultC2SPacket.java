package net.devdoctor.bioshock.Networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class DefaultC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler, PacketByteBuf bug, PacketSender responseSender) {
        EntityType.COW.spawn(player.getWorld(), null, null, player, player.getBlockPos(),
                SpawnReason.TRIGGERED, true, false);
    }
}
