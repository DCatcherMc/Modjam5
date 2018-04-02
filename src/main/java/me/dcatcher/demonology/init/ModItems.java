package me.dcatcher.demonology.init;

import me.dcatcher.demonology.item.ItemBase;
import me.dcatcher.demonology.item.ItemReapersKnife;
import me.dcatcher.demonology.item.ItemSoulFlask;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static final ItemSoulFlask itemSoulFlask = new ItemSoulFlask("soul_flask");
    public static final ItemReapersKnife itemReapersKnife = new ItemReapersKnife("reapers_knife");
    public static final ItemBase itemTestPulse = new ItemBase("pulse_test");

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(
                itemSoulFlask,
                itemReapersKnife,
                itemTestPulse
        );
    }

    public static void registerModels() {
        itemSoulFlask.registerItemModel();
        itemReapersKnife.registerItemModel();
        itemTestPulse.registerItemModel();
    }

}
