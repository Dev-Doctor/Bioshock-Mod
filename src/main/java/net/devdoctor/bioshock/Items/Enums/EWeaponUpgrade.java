package net.devdoctor.bioshock.Items.Enums;

public enum EWeaponUpgrade {
    // REVOLVER
    REV_CLIP_SIZE(.1f, .1f, 18),
    REV_DMG(.1f, .25f, 0);

    private float fireRate;
    private float damage;
    private int ammoCapacity;

    EWeaponUpgrade(float fireRate, float damage, int ammoCapacity) {
        this.fireRate = fireRate;
        this.damage = damage;
        this.ammoCapacity = ammoCapacity;
    }

    public float getFireRate() {
        return fireRate;
    }

    public float getDamage() {
        return damage;
    }

    public float getAmmoCapacity() {
        return ammoCapacity;
    }
}
