package net.devdoctor.bioshock.interfaces;

public interface IGunModifier {
    default float modifyWeaponMagazine(int magazine) {
        return magazine;
    }

    default float modifyWeaponDamage(int damage) {
        return damage;
    }

    default float modifyFireRate(int rate) {
        return rate;
    }

    default boolean selfDamage() {
        return true;
    }
}
