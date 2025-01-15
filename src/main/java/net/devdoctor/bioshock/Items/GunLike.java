package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.Entities.GunProjectileEntity;
import net.devdoctor.bioshock.Events.KeyInputHandler;
import net.devdoctor.bioshock.Items.Enums.EWeaponType;
import net.devdoctor.bioshock.Networking.ModPackaces;
import net.devdoctor.bioshock.util.InventoryUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class GunLike extends RangedWeaponItem {
    EWeaponType weaponType;

    public GunLike(Settings settings, EWeaponType weaponType) {
        super(settings.maxCount(1));
        this.weaponType = weaponType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (hand != Hand.MAIN_HAND) {
            return TypedActionResult.fail(stack);
        }

        if (!player.getItemCooldownManager().isCoolingDown(this) && isLoaded(stack)) {
            this.shootWeapon(world, player, stack);
            player.getItemCooldownManager().set(this, weaponType.getRateOfFire());
            // return TypedActionResult.success(stack);
        }

        return TypedActionResult.fail(stack);
    }

    private void shootWeapon(World world, PlayerEntity playerEntity, ItemStack itemStack) {
        itemStack.getOrCreateNbt().putInt("rldTick", 0);
        itemStack.getOrCreateNbt().putBoolean("isReloading", false);

        // if server
        if (!world.isClient) {
            // for each pellet
            for (int i = 0; i < weaponType.getPelletCount(); i++) {
                // create a new projectile entity
                GunProjectileEntity projectile = new GunProjectileEntity(playerEntity, world, weaponType.getGunDamage());

                // set its position to the eye height
                projectile.setPosition(playerEntity.getX(), playerEntity.getEyeY(), playerEntity.getZ());

                // set the velocity for the projectile
                projectile.setVelocity(playerEntity, playerEntity.getPitch(),
                        playerEntity.getYaw(), 0, 20, 0);
                // projectile.setBaseVelocity();

                // spawn the projectile
                world.spawnEntity(projectile);
            }

            // if the player is not in creative
            if (!playerEntity.getAbilities().creativeMode) {
                // decrease the ammo
                itemStack.getOrCreateNbt().putInt("ammo", itemStack.getOrCreateNbt().getInt("ammo") - 1);
                // damage the item by 10
                itemStack.damage(10, playerEntity, event -> {
                    event.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                });
            }

            /*
            world.playSound(null,
                    playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),

                    );
            */
        }
    }

    public static int remainingAmmo(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        return nbtCompound.getInt("ammo");
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        ItemStack mainHandGun = ((PlayerEntity) entity).getMainHandStack();

        if (world.isClient()) {
            if (mainHandGun == stack
                    // REMINDER TO ADD CHECK FOR KEYBIND
                    && KeyInputHandler
                    && remainingAmmo(stack) < weaponType.getMagSize()
                    && InventoryUtil.countItemInInventory(((PlayerEntity) entity), weaponType.getAmmoType()) > 0
                    && !nbtCompound.getBoolean("isReloading")
            //        && !isSprinting
            ){
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeBoolean(true);
                ClientPlayNetworking.send(ModPackaces.RELOAD_ID, buf);
            }
        }

        //The actual reload process/tick
        // removed && !isSprinting
        if (nbtCompound.getBoolean("isReloading")) {
            if ((mainHandGun != stack
                    || (InventoryUtil.countItemInInventory((PlayerEntity) entity, weaponType.getAmmoType()) <= 0)
                    || (nbtCompound.getInt("rldTick") >= weaponType.getReloadCoolDown())
                    || (remainingAmmo(stack) >= weaponType.getMagSize())))
                nbtCompound.putBoolean("isReloading", false);

            reloadTick(world, nbtCompound, (PlayerEntity) entity, stack);
        } else {
            if (nbtCompound.getInt("rldTick") <= weaponType.getReloadCoolDown())
                finishReload((PlayerEntity) entity, stack);

            nbtCompound.putBoolean("isReloading", false);
            nbtCompound.putInt("rldTick", 0);
        }

    }

    public void reloadTick(World world, NbtCompound nbt, PlayerEntity playerEntity, ItemStack itemStack) {
        int currentTick = nbt.getInt("rldTick");

        nbt.putInt("rldTick", currentTick + 1);

        if (currentTick >= weaponType.getReloadCoolDown()
                && InventoryUtil.countItemInInventory(playerEntity, weaponType.getAmmoType()) > 0) {
            finishReload(playerEntity, itemStack);
            nbt.putInt("rldTick", 0);
        }
    }

    public void finishReload(PlayerEntity playerEntity, ItemStack itemStack) {
        NbtCompound nbt = itemStack.getOrCreateNbt();

        int missingAmmo = weaponType.getMagSize() - nbt.getInt("ammo");
        int availableAmmoInInventory = InventoryUtil.countItemInInventory(playerEntity, itemStack.getItem());

        if (availableAmmoInInventory >= missingAmmo) {
            nbt.putInt("ammo", weaponType.getMagSize());
            InventoryUtil.removeItemFromInventory(playerEntity, weaponType.getAmmoType(), missingAmmo);
        } else {
            nbt.putInt("ammo", nbt.getInt("ammo") + availableAmmoInInventory);
            InventoryUtil.removeItemFromInventory(playerEntity, weaponType.getAmmoType(), availableAmmoInInventory);
        }

        itemStack.setDamage(this.getMaxDamage() - (nbt.getInt("ammo") * 10) + 1);
    }

    private boolean isLoaded(ItemStack stack) {
        return currentAmmo(stack) > 0;
        // return true;
    }

    private int currentAmmo(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        return nbtCompound.getInt("ammo");
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        setDefaultNBT(stack);

        super.onCraft(stack, world, player);
    }

    private void setDefaultNBT(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putInt("rldTick", 0);
        nbtCompound.putBoolean("isReloading", false);
        nbtCompound.putInt("ammo", 0);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.packRgb(.0f, .1f, .1f);
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return null;
    }

    @Override
    public int getRange() {
        return 0;
    }
}
