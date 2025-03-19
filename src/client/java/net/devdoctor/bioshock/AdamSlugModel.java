package net.devdoctor.bioshock;

import net.devdoctor.bioshock.Entities.AdamSlugEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class AdamSlugModel<T extends AdamSlugEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer ADAM_SLUG = new EntityModelLayer(Identifier.of(BioshockMod.MOD_ID, "adam_slug"), "main");

    private final ModelPart adam_slug;
    private final ModelPart head;

    public AdamSlugModel(ModelPart root) {
        this.adam_slug = root.getChild("adam_slug");
        this.head = this.adam_slug.getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData adam_slug = modelPartData.addChild("adam_slug", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData head = adam_slug.addChild("head", ModelPartBuilder.create().uv(0, -1).cuboid(-1.7F, -2.0F, -1.0F, 3.4F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(1, 0).cuboid(-1.7F, -2.5F, 0.0F, 3.4F, 0.5F, 2.0F, new Dilation(0.0F))
                .uv(1, 1).cuboid(-1.0F, -3.0F, 0.2F, 2.0F, 0.5F, 1.5F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -7.0F));

        ModelPartData body = adam_slug.addChild("body", ModelPartBuilder.create().uv(-3, -2).cuboid(-2.0F, -2.7F, -5.0F, 4.0F, 2.7F, 4.0F, new Dilation(0.0F))
                .uv(-3, -2).cuboid(-1.8F, -2.5F, -1.0F, 3.5F, 2.5F, 4.0F, new Dilation(0.0F))
                .uv(-3, -2).cuboid(-1.5F, -2.3F, 3.0F, 3.0F, 2.3F, 4.0F, new Dilation(0.0F))
                .uv(-1, -1).cuboid(-1.3F, -2.0F, 7.0F, 2.6F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void setAngles(AdamSlugEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw);
    }

    private void setHeadAngles(float headYaw) {
        headYaw = MathHelper.clamp(headYaw, -30.F, 30.F);

        this.head.yaw = headYaw;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        adam_slug.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return adam_slug;
    }
}
