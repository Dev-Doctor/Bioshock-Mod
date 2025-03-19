package net.devdoctor.bioshock.Items.Enums;

import net.devdoctor.bioshock.Items.ModItemTags;
import net.devdoctor.bioshock.Items.ModItems;
import net.devdoctor.bioshock.util.InventoryUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum EWeaponType {
    REVOLVER(
            5,
            5,
            6,
            1,
            () -> ModItemTags.REVOLVER_AMMO,
            5,
            new float[]{0, 0},
            new float[]{0, 0},
            EWeaponSounds.REVOLVER
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
    private final Supplier<TagKey<Item>> ammoType;
    private final int reloadCoolDown;
    private final float[] gunRecoil;
    private final float[] bulletSpread;
    private final EWeaponSounds weaponSounds;


    EWeaponType(float gunDamage, int rateOfFire, int magSize, int pelletCount, Supplier<TagKey<Item>> ammoType, int reloadCoolDown, float[] gunRecoil, float[] bulletSpread, EWeaponSounds weaponSounds) {
        this.gunDamage = gunDamage;
        this.rateOfFire = rateOfFire;
        this.magSize = magSize;
        this.pelletCount = pelletCount;
        this.ammoType = ammoType;
        this.reloadCoolDown = reloadCoolDown;
        this.gunRecoil = gunRecoil;
        this.bulletSpread = bulletSpread;
        this.weaponSounds = weaponSounds;
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

    public Item getAmmoType(PlayerEntity playerEntity) {
        return InventoryUtil.searchForItemMatchingTag(playerEntity, ammoType.get());
    }

    public TagKey<Item> getAmmoType() {
        return ammoType.get();
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

    public EWeaponSounds getWeaponSounds() {
        return weaponSounds;
    }
}
