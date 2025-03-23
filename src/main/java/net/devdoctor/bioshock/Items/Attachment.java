package net.devdoctor.bioshock.Items;

import net.devdoctor.bioshock.BioshockMod;
import net.devdoctor.bioshock.interfaces.IGunModifier;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mutable;

import java.util.List;
import java.util.function.Supplier;

public class Attachment extends Item {
    final List<IGunModifier> modifiers;

    public Attachment(Settings settings, Supplier<List<IGunModifier>> modifiers) {
        super(settings);
        this.modifiers = modifiers.get();
    }

    public List<IGunModifier> getModifiers() {
        return modifiers;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltipList, TooltipContext context) {
        Text result = Text.of("\"" + Text.translatable("item." + BioshockMod.MOD_ID + "." + this.toString() + ".tooltip").getString() + "\"");

        tooltipList.add(((MutableText) result).formatted(Formatting.ITALIC, Formatting.GRAY));
        super.appendTooltip(stack, world, tooltipList, TooltipContext.BASIC);
    }
}
