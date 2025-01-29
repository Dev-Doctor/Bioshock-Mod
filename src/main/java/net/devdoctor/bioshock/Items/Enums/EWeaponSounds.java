package net.devdoctor.bioshock.Items.Enums;

import net.devdoctor.bioshock.Sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public enum EWeaponSounds {
    REVOLVER(
            ModSounds.PISTOL_EQUIP,
            null,
            ModSounds.PISTOL_NOAMMO,
            ModSounds.PISTOL_RELOAD,
            ModSounds.PISTOL_FIRE
    );

    private final SoundEvent equip;
    private final SoundEvent unEquip;
    private final SoundEvent noAmmo;
    private final SoundEvent reload;
    private final SoundEvent shoot;

    EWeaponSounds(SoundEvent equip, SoundEvent unEquip, SoundEvent noAmmo, SoundEvent reload, SoundEvent shoot) {
        this.equip = equip;
        this.unEquip = unEquip;
        this.noAmmo = noAmmo;
        this.reload = reload;
        this.shoot = shoot;
    }

    public SoundEvent getEquip() {
        return equip;
    }

    public SoundEvent getUnEquip() {
        return unEquip;
    }

    public SoundEvent getNoAmmo() {
        return noAmmo;
    }

    public SoundEvent getReload() {
        return reload;
    }

    public SoundEvent getShoot() {
        return shoot;
    }
}
