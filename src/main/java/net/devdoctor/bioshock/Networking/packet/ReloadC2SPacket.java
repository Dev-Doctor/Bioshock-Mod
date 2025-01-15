package net.devdoctor.bioshock.Networking.packet;

import net.devdoctor.bioshock.Items.GunLike;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ReloadC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player,
                               ServerPlayNetworkHandler handler, PacketByteBuf bug, PacketSender responseSender) {
        if(player.getMainHandStack().getItem() instanceof GunLike) {
            ItemStack itemStack = player.getMainHandStack();
            itemStack.getOrCreateNbt().putBoolean("isReloading", bug.readBoolean());
        }
    }
}
