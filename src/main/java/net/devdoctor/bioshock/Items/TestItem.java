package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.util.PlayerUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestItem extends Item {


    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient) {
            return TypedActionResult.fail(stack);
        }

        boolean ballz = PlayerUtil.IsUnderWaterPressure(world, player);
        if (ballz) {
            player.sendMessage(Text.literal("Currently under pressure"));
        } else {
            player.sendMessage(Text.literal("Currently NOT under pressure"));
        }


        return TypedActionResult.success(stack);
    }
}
