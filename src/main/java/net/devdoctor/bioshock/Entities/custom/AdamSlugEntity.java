package net.devdoctor.bioshock.Entities.custom;

import net.devdoctor.bioshock.Items.ModItems;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AdamSlugEntity extends WaterCreatureEntity implements GeoEntity, Bucketable {
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);
    private static final TrackedData<Boolean> FROM_BUCKET;

    public AdamSlugEntity(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAdamSlugAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .4f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.6f));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FROM_BUCKET, false);
    }

    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        return (ActionResult)Bucketable.tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
    }

    /* ###################################################################### */
    /* #########################  ANIMATION STUFF   ######################### */
    /* ###################################################################### */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<AdamSlugEntity> adamSlugEntityAnimationState) {
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationCache;
    }

    /* ###################################################################### */
    /* #########################    BUCKET STUFF    ######################### */
    /* ###################################################################### */
    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }

    @Override
    public ItemStack getBucketItem() {
        return ModItems.ADAM_SLUG_BUCKET.getDefaultStack();
    }

    @Override
    public boolean isFromBucket() {
        return (Boolean)this.dataTracker.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.dataTracker.set(FROM_BUCKET, fromBucket);
    }

    static {
        FROM_BUCKET = DataTracker.registerData(AdamSlugEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        if(!(damageSource.getAttacker() instanceof PlayerEntity player)) {
            return;
        }

        if(player.getWorld().isClient()) {
            return;
        }

        if(player.getMainHandStack().getItem() == ModItems.ADAM_SYRINGE) {
            dropStack(new ItemStack(ModItems.ADAM_BOTTLE, 10));
        } else {
            dropStack(new ItemStack(ModItems.ADAM_BOTTLE, 5));
        }
    }
}
