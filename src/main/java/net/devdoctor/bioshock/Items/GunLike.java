package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.Entities.GunProjectileEntity;
import net.devdoctor.bioshock.Items.Enums.EWeaponType;
import net.devdoctor.bioshock.util.InventoryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class GunLike extends RangedWeaponItem {
    public static String NBT_AMMO_ID = "ammo";
    public static String NBT_RELOADING_ID = "isReloading";
    public static String NBT_LOADED_AMMO = "AmmoType";

    EWeaponType weaponType;

    public GunLike(Settings settings, EWeaponType weaponType) {
        super(settings.maxCount(1).maxDamage(weaponType.getMagSize() * 10 + 1));
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
            return TypedActionResult.fail(stack);
        }

        return TypedActionResult.fail(stack);
    }

    private void shootWeapon(World world, PlayerEntity playerEntity, ItemStack itemStack) {
        itemStack.getOrCreateNbt().putBoolean(NBT_RELOADING_ID, false);

        // ONLY ON SERVER
        if (playerEntity instanceof ServerPlayerEntity) {
            // for each pellet
            for (int i = 0; i < weaponType.getPelletCount(); i++) {
                // create a new projectile entity
                GunProjectileEntity projectile = new GunProjectileEntity(playerEntity, world, weaponType.getGunDamage());

                // FireballEntity projectile = new FireballEntity(world, playerEntity, 0, 0, 0, 0);

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
                int newAmmo = remainingAmmo(itemStack) - 1;
                playerEntity.sendMessage(Text.literal(remainingAmmo(itemStack) - 1 + "/" + weaponType.getMagSize()), true);
                itemStack.getOrCreateNbt().putInt(NBT_AMMO_ID, newAmmo);
                // damage the item by 10
                itemStack.damage(10, playerEntity, event -> {
                    event.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                });
            }

            playerEntity.getWorld().playSound(
                    null,
                    playerEntity.getBlockPos(),
                    weaponType.getWeaponSounds().getShoot(),
                    SoundCategory.PLAYERS,
                    .5F,
                    1F
            );

        }
    }

    public static int remainingAmmo(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        return nbtCompound.getInt(NBT_AMMO_ID);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        ItemStack mainHandGun = ((PlayerEntity) entity).getMainHandStack();

        // executed on server
        if (entity instanceof ServerPlayerEntity) {
            //The actual reload process/tick

            // if the player pressed the reloading key
            if (nbtCompound.getBoolean(NBT_RELOADING_ID)) {
                entity.sendMessage(Text.literal("NOW IS REALLY RELOADING"));
                finishReload((ServerPlayerEntity) entity, stack);
            }
        }
    }

    public void reloadTick(World world, NbtCompound nbt, ServerPlayerEntity playerEntity, ItemStack itemStack) {
        if (InventoryUtil.countItemInInventory(playerEntity, weaponType.getAmmoType(playerEntity)) > 0) {
            finishReload(playerEntity, itemStack);
        }
    }

    /**
     * @location ONLY server
     */
    public void finishReload(ServerPlayerEntity playerEntity, ItemStack itemStack) {
        NbtCompound nbt = itemStack.getOrCreateNbt();
//        NbtList ammoList = nbt.getList(NBT_AMMO_ID, 10);
        // if the weapon is full don't do shit
        if (remainingAmmo(itemStack) == weaponType.getMagSize()) {
            nbt.putBoolean(NBT_RELOADING_ID, false);
            return;
        }

        // get the first available ammo type from the player inventory
        Item firstAvailableAmmoType = InventoryUtil.searchForItemMatchingTag(playerEntity, weaponType.getAmmoType());
        // get the missing ammo in the weapon
        int missingAmmo = weaponType.getMagSize() - nbt.getInt(NBT_AMMO_ID);
        // count the available ammo in the inventory
        int availableAmmoInInventory = InventoryUtil.countItemInInventory(playerEntity, firstAvailableAmmoType);

        // if there are no ammunition in the player inventory
        if (firstAvailableAmmoType == null) {
            // play the appropriate sound and return
            playerEntity.getWorld().playSound(
                    null,
                    playerEntity.getBlockPos(),
                    weaponType.getWeaponSounds().getNoAmmo(),
                    SoundCategory.PLAYERS
            );
            nbt.putBoolean(NBT_RELOADING_ID, false);
            return;
        }

        if (availableAmmoInInventory >= missingAmmo) {
            nbt.putInt(NBT_AMMO_ID, weaponType.getMagSize());
            InventoryUtil.removeItemFromInventory(playerEntity, firstAvailableAmmoType, missingAmmo);
        } else {
            nbt.putInt(NBT_AMMO_ID, nbt.getInt(NBT_AMMO_ID) + availableAmmoInInventory);
            InventoryUtil.removeItemFromInventory(playerEntity, firstAvailableAmmoType, availableAmmoInInventory);
        }

        // set damage and reload cooldown
        playerEntity.getItemCooldownManager().set(this, weaponType.getReloadCoolDown());
        itemStack.setDamage(this.getMaxDamage() - ((nbt.getInt(NBT_AMMO_ID) * 10)) - 1);

        // play shooting sound
        playerEntity.getWorld().playSound(
                null,
                playerEntity.getBlockPos(),
                weaponType.getWeaponSounds().getReload(),
                SoundCategory.PLAYERS
        );
        nbt.putString(NBT_LOADED_AMMO, Identifier.of(BioshockMod.MOD_ID, firstAvailableAmmoType.toString()).toString());
        nbt.putBoolean(NBT_RELOADING_ID, false);
    }

    private boolean isLoaded(ItemStack stack) {
        return currentAmmo(stack) > 0;
    }

    private int currentAmmo(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        return nbtCompound.getInt(NBT_AMMO_ID);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        setDefaultNBT(stack);

        super.onCraft(stack, world, player);
    }

    private void setDefaultNBT(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean(NBT_RELOADING_ID, false);
        nbtCompound.putInt(NBT_AMMO_ID, 6);
        nbtCompound.putString(NBT_LOADED_AMMO, null);
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
        return MathHelper.packRgb(.0f, .5f, .0f);
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
