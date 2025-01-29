package net.devdoctor.bioshock.Sound;

import net.devdoctor.bioshock.BioshockMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static SoundEvent PISTOL_EQUIP = registerSoundEvent("pistol_equip");
    public static SoundEvent PISTOL_FIRE = registerSoundEvent("pistol_fire");
    public static SoundEvent PISTOL_NOAMMO = registerSoundEvent("pistol_noammo");
    public static SoundEvent PISTOL_RELOAD = registerSoundEvent("pistol_reload");

    private static SoundEvent registerSoundEvent(String soundName) {
        Identifier id = new Identifier(BioshockMod.MOD_ID, soundName);

        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {

    }
}
