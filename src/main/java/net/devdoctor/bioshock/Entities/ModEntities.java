package net.devdoctor.bioshock.Entities;

import net.devdoctor.bioshock.BioshockMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<AdamSlugEntity> ADAM_SLUG = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(BioshockMod.MOD_ID, "adam_slug"),
            EntityType.Builder.create(AdamSlugEntity::new, SpawnGroup.CREATURE)
            .setDimensions(1f, .25f)
                    .build("adam_slug"));

    public static void registerModEntities() {
        // BioshockMod.LOGGER.info("Registering Bioshock entities");
    }

//    public static Entity register(String id, EntityType.Builder item) {
//        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(BioshockMod.MOD_ID, id), item);
//    }
}
