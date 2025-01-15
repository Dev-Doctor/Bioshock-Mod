package net.devdoctor.bioshock.Items;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.UseAction;

public enum EveType {
    EVE(UseAction.BOW, 100, 32, DrugType.EVE, ModItems.EMPTY_SYRINGE),
    SMALL_SALT(UseAction.DRINK, 25, 32, DrugType.SALT, Items.GLASS_BOTTLE),
    MEDIUM_SALT(UseAction.DRINK, 50, 32, DrugType.SALT, Items.GLASS_BOTTLE),
    LARGE_SALT(UseAction.DRINK, 100, 32, DrugType.SALT, Items.GLASS_BOTTLE),;


    private final UseAction useAction;
    private final int healQuantity;
    private final int maxUseTime;
    private final DrugType drugType;
    private final Item resultItem;

    EveType(UseAction useAction, int healQuantity, int maxUseTime, DrugType drugType, Item resultItem) {
        this.useAction = useAction;
        this.healQuantity = healQuantity;
        this.maxUseTime = maxUseTime;
        this.drugType = drugType;
        this.resultItem = resultItem;
    }

    public UseAction getUseAction() {
        return useAction;
    }

    public int getMaxUseTime() {
        return maxUseTime;
    }

    public int getHealQuantity() {
        return healQuantity;
    }

    public DrugType getDrugType() {
        return drugType;
    }

    public Item getResultItem() {
        return resultItem;
    }
}
