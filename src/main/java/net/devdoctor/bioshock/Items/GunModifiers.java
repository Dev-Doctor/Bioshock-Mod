package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.interfaces.IGunModifier;

public class GunModifiers {
    public static final IGunModifier REVOLVER_MAG_INCREASE = new IGunModifier() {
        @Override
        public float modifyWeaponMagazine(int magazine) {
            return magazine * 4;
        }
    };

    public static final IGunModifier DAMAGE_INCREASE = new IGunModifier() {
        @Override
        public float modifyWeaponDamage(int damage) {
            return damage + (damage * .25f);
        }
    };
}
