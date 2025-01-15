package net.devdoctor.bioshock.Entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class GunProjectileEntity extends PersistentProjectileEntity {
    private int projectileLife;
    private int currentLife;

    private float projectileDamage;

    public GunProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public GunProjectileEntity(LivingEntity bulletOwner, World world, float damage) {
        super(EntityType.ARROW, bulletOwner, world);

        projectileDamage = damage;
        projectileLife = 12;
        currentLife = 0;
        this.setNoGravity(true);
        this.setOwner(bulletOwner);
    }

    @Override
    public void tick() {
        super.tick();

        if(currentLife >= projectileLife) {
            this.discard();
        }

        currentLife++;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if(entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            Entity e = this;
            if(getOwner() != null) {
                e = getOwner();
            }

            livingEntity.damage(DamageSource.arrow(this, e), projectileDamage);
            timeUntilRegen = 0;
        }
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        discard();
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }
}
