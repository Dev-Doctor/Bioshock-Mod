package net.devdoctor.bioshock.Entitites;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.Entities.custom.AdamSlugEntity;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class AdamSlugModel extends GeoModel<AdamSlugEntity> {
    @Override
    public Identifier getModelResource(AdamSlugEntity adamSlugEntity) {
        return new Identifier(BioshockMod.MOD_ID, "geo/adam_slug.json");
    }

    @Override
    public Identifier getTextureResource(AdamSlugEntity adamSlugEntity) {
        return new Identifier(BioshockMod.MOD_ID, "textures/entity/adam_slug/adam_slug.png");
    }

    @Override
    public Identifier getAnimationResource(AdamSlugEntity adamSlugEntity) {
        return new Identifier(BioshockMod.MOD_ID, "animations/adam_slug.animation.json");
    }

    @Override
    public void setCustomAnimations(AdamSlugEntity animatable, long instanceId, AnimationState<AdamSlugEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
