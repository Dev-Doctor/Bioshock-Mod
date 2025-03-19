package net.devdoctor.bioshock;

import net.devdoctor.bioshock.Entities.AdamSlugEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AdamSlugRenderer extends MobEntityRenderer<AdamSlugEntity, AdamSlugModel<AdamSlugEntity>> {

    public AdamSlugRenderer(EntityRendererFactory.Context context) {
        super(context, new AdamSlugModel<>(context.getPart(AdamSlugModel.ADAM_SLUG)), .4f);
    }

    @Override
    public Identifier getTexture(AdamSlugEntity entity) {
        return Identifier.of(BioshockMod.MOD_ID, "textures/entity/adam_slug/adam_slug.png");
    }

    @Override
    public void render(AdamSlugEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
