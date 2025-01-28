package net.devdoctor.bioshock.Events;

import net.devdoctor.bioshock.Networking.ModPackaces;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_BIOSHOCK = "key.category.bioshock";
    public static final String KEY_NEXT_PLASMIT = "key.bioshock.next_plasmit";
    public static final String KEY_PREVIOUS_PLASMIT = "key.bioshock.previous_plasmit";
    public static final String KEY_RELOAD = "key.bioshock.reload";

    public static KeyBinding nextPlasmit;
    public static KeyBinding previousPlasmit;
    public static KeyBinding reload;

    private static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(nextPlasmit.wasPressed()) {
                client.player.sendMessage(Text.literal("I hate life"));
                ClientPlayNetworking.send(ModPackaces.USE_EVE_ID, PacketByteBufs.create());
            }
            if(previousPlasmit.wasPressed()) {
                client.player.sendMessage(Text.literal("HELLO I AM THE OTHER ONE"));
            }
            if(reload.wasPressed()) {
                client.player.sendMessage(Text.literal("RELOADING"));
                PacketByteBuf packet = PacketByteBufs.create();
                packet.writeBoolean(true);
                ClientPlayNetworking.send(ModPackaces.RELOAD_ID, packet);
            }
        });
    }

    public static void registerKeyBindings() {
        nextPlasmit = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_NEXT_PLASMIT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                KEY_CATEGORY_BIOSHOCK
        ));
        previousPlasmit = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_PREVIOUS_PLASMIT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                KEY_CATEGORY_BIOSHOCK
        ));
        reload = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_RELOAD,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_BIOSHOCK
        ));

        registerKeyInputs();
    }
}
