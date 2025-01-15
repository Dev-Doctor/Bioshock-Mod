package net.devdoctor.bioshock.Sound;

import net.devdoctor.bioshock.BioshockMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static SoundEvent PISTOL_SHOOT = registerSoundEvent("pistol_shoot");

    private static SoundEvent registerSoundEvent(String soundName) {
        Identifier id = new Identifier(BioshockMod.MOD_ID, soundName);

        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerModSounds() {

    }
}
