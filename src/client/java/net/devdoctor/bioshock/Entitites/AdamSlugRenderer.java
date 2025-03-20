package net.devdoctor.bioshock.Entitites;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.Entities.custom.AdamSlugEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AdamSlugRenderer extends GeoEntityRenderer<AdamSlugEntity> {
    public AdamSlugRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new AdamSlugModel());
    }

    @Override
    public Identifier getTexture(AdamSlugEntity animatable) {
        return new Identifier(BioshockMod.MOD_ID, "textures/entity/adam_slug/adam_slug.png");
    }

    @Override
    public void render(AdamSlugEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(.4f, .4f, .4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
