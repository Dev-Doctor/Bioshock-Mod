package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.Entities.IAdamHolder;
import net.devdoctor.bioshock.ModDamageTypes;
import net.devdoctor.bioshock.util.InventoryUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.logging.Level;

public class AdamSyringe extends Item {

    public AdamSyringe(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity target, Hand hand) {
        if(!user.getWorld().isClient()) {
            if(target.isDead()) {
                return ActionResult.FAIL;
            }
            if(target instanceof IAdamHolder) {
                IAdamHolder adamHolder = (IAdamHolder) target;
                PlayerInventory inventory = user.getInventory();
                if(InventoryUtil.countItemInInventory(user, ModItems.EMPTY_ADAM_BOTTLE) > 0) {
                    InventoryUtil.removeItemFromInventory(user, ModItems.EMPTY_ADAM_BOTTLE, 1);
                    inventory.insertStack(new ItemStack(ModItems.ADAM_BOTTLE, 1));
                    target.damage(ModDamageTypes.of(user.getWorld(), DamageTypes.PLAYER_ATTACK, user), 3);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }
}
