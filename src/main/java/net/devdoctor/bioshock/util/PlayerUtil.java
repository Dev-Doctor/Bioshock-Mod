package net.devdoctor.bioshock.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerUtil {
    public static final int CRUSH_DEPTH = 15;

    public static boolean IsUnderWaterPressure(World world, PlayerEntity player) {
        BlockPos currentPos = player.getBlockPos();
        for (int i = 0; i < CRUSH_DEPTH; i++) {
            if(world.getBlockState(currentPos).getFluidState().isEmpty()) {
                if(!world.getBlockState(currentPos).contains(Properties.WATERLOGGED)) {
                    return false;
                }

                if(!world.getBlockState(currentPos).get(Properties.WATERLOGGED)) {
                    return false;
                }
            }

            currentPos = currentPos.up();
        }

        return true;
    }
}
