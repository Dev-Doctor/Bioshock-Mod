package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.Items.Enums.EveType;
import net.devdoctor.bioshock.util.EveData;
import net.devdoctor.bioshock.util.IEntityDataSaver;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class Evelike extends Item {
    EveType eveType;

    public Evelike(EveType eveType, Settings settings) {
        super(settings);
        this.eveType = eveType;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return eveType.getUseAction();
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return eveType.getMaxUseTime();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity) user : null;

        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity) playerEntity, stack);
        }

        if (!world.isClient) {
            assert playerEntity != null;
            switch (eveType.getDrugType()) {
                case SALT -> {
                    // add salt tho
                    playerEntity.sendMessage(Text.literal("That was salt tho"));
                    EveData.addEve(((IEntityDataSaver) playerEntity), eveType.getHealQuantity());
                }
                case EVE -> {

                    playerEntity.sendMessage(Text.literal("Healed by: " + eveType.getHealQuantity()));
                    EveData.addEve(((IEntityDataSaver) playerEntity), eveType.getHealQuantity());
                }
            }

            playerEntity.sendMessage(Text.literal("Eve: " + ((IEntityDataSaver) playerEntity).getPersistentData().getInt(EveData.EVE_NBT_ID))
                    .fillStyle(Style.EMPTY.withColor(Formatting.GOLD)), true);
        }

        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!playerEntity.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(eveType.getResultItemSupplier());
            }

            if (playerEntity != null) {
                playerEntity.getInventory().insertStack(new ItemStack(eveType.getResultItemSupplier()));
            }
        }

        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }
}
