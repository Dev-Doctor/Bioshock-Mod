package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.Blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {
    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BioshockMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BIOSHOCK_TAB = CREATIVE_MODE_TABS.register("bioshock_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ZINC_INGOT.get()))
                    .title(Component.translatable("itemGroup.bioshock.bioshock_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.RAW_ZINC.get());
                        pOutput.accept(ModItems.ZINC_INGOT.get());
                        pOutput.accept(ModItems.BRASS_INGOT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> BIOSHOCK_BUILDING = CREATIVE_MODE_TABS.register("bioshock_building",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.COPPER_BLOCK))
                    .title(Component.translatable("itemGroup.bioshock.bioshock_building_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.BRASS_BLOCK.get());
                        pOutput.accept(ModBlocks.ZINC_BLOCK.get());
                        pOutput.accept(ModBlocks.ZINC_ORE.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
