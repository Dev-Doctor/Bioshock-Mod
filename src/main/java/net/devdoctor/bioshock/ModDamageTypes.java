package net.devdoctor.bioshock;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {

    public static final RegistryKey<DamageType> SHOOT_DAMAGE_TYPE = register("shoot");

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world
                .getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(key));
    }

    public static DamageSource of(World world, RegistryKey<DamageType> key, Entity attacker) {
        return new DamageSource(world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(key),
                attacker);
    }

    private static RegistryKey<DamageType> register(String id) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(BioshockMod.MOD_ID, id));
    }

    public static void registerModDamageTypes() {

    }
}
