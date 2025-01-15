package net.devdoctor.bioshock.Items.Enums;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum EWeaponType {
    REVOLVER(
            5,
            5,
            6,
            1,
            Items.ARROW,
            5,
            new float[]{0, 0},
            new float[]{0, 0}
    );
    // MACHINE_GUN(, , , , , , ),
    // SHOTGUN(, , , , , , ),
    // GRENADE_LAUNCHER(, , , , , , ),
    // CHEMICAL_THROWER(, , , , , , ),
    // CROSSBOW(, , , , , , );

    private final float gunDamage;
    private final int rateOfFire;
    private final int magSize;
    private final int pelletCount;
    private final Item ammoType;
    private final int reloadCoolDown;
    private final float[] gunRecoil;
    private final float[] bulletSpread;


    EWeaponType(float gunDamage, int rateOfFire, int magSize, int pelletCount, Item ammoType, int reloadCoolDown, float[] gunRecoil, float[] bulletSpread) {
        this.gunDamage = gunDamage;
        this.rateOfFire = rateOfFire;
        this.magSize = magSize;
        this.pelletCount = pelletCount;
        this.ammoType = ammoType;
        this.reloadCoolDown = reloadCoolDown;
        this.gunRecoil = gunRecoil;
        this.bulletSpread = bulletSpread;
    }

    public float getGunDamage() {
        return gunDamage;
    }


    public int getRateOfFire() {
        return rateOfFire;
    }

    public int getMagSize() {
        return magSize;
    }

    public int getPelletCount() {
        return pelletCount;
    }

    public Item getAmmoType() {
        return ammoType;
    }

    public int getReloadCoolDown() {
        return reloadCoolDown;
    }

    public float[] getGunRecoil() {
        return gunRecoil;
    }

    public float[] getBulletSpread() {
        return bulletSpread;
    }
}
