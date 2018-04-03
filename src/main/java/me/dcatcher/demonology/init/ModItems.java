package me.dcatcher.demonology.init;

import me.dcatcher.demonology.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static final ItemSoulFlask itemSoulFlask = new ItemSoulFlask("soul_flask");
    public static final ItemReapersKnife itemReapersKnife = new ItemReapersKnife("reapers_knife");
    public static final ItemEvilEye itemEvilEye = new ItemEvilEye("evil_eye");
    public static final ItemDemonicPulsator itemDemonicPulsator = new ItemDemonicPulsator("demonic_pulsator");
    public static final ItemBase itemDemonicRod = new ItemBase("demonic_rod");

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(
                itemSoulFlask,
                itemReapersKnife,
                itemEvilEye,
                itemDemonicPulsator,
                itemDemonicRod
        );
    }

    public static void registerModels() {
        itemSoulFlask.registerItemModel();
        itemReapersKnife.registerItemModel();
        itemEvilEye.registerItemModel();
        itemDemonicPulsator.registerItemModel();
        itemDemonicRod.registerItemModel();
    }

}
