package net.devdoctor.bioshock.Blocks;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.Items.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BioshockMod.MOD_ID);

    public static final RegistryObject<Block> BRASS_BLOCK = registerBlock("brass_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> ZINC_BLOCK = registerBlock("zinc_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> ZINC_ORE = registerBlock("zinc_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> CHECKED_FLOOR_TILES = registerBlock("checked_floor_tiles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));


    /* ------------------------------ REGISTER ------------------------------ */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blockObject = BLOCKS.register(name, block);
        registerBlockItem(name, blockObject);
        return blockObject;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block>RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }





    /* ------------------------------ BLOCKS WITH TOOLTIPS ------------------------------ */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, RegistryObject<T> block, String tooltipKey) {
        RegistryObject<T> blockObject = BLOCKS.register(name, block);
        registerBlockItem(name, blockObject, tooltipKey);
        return blockObject;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, String tooltipKey) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()) {
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(Component.literal(tooltipKey));
            }
        });
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
