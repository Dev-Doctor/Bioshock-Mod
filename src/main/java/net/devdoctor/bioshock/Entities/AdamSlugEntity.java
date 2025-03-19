package net.devdoctor.bioshock.Entities;

import net.devdoctor.bioshock.Items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class AdamSlugEntity extends FishEntity implements IAdamHolder {
    @Override
    public int getAdamQuantity() {
        return 5;
    }

    @Override
    public int getAdamWithSyringe() {
        return getAdamQuantity() *  2;
    }

    public AdamSlugEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {

    }

    public static DefaultAttributeContainer.Builder createAdamSlugAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .35);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    @Override
    public ItemStack getBucketItem() {
        return ModItems.ADAM_SLUG_BUCKET.getDefaultStack();
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        if(!(damageSource.getAttacker() instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) damageSource.getAttacker();

        if(player.getWorld().isClient()) {
            return;
        }

        if(player.getMainHandStack().getItem() == ModItems.ADAM_SYRINGE) {
            dropStack(new ItemStack(ModItems.ADAM_BOTTLE, getAdamWithSyringe()));
        } else {
            dropStack(new ItemStack(ModItems.ADAM_BOTTLE, getAdamQuantity()));
        }
    }
}
