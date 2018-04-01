package me.dcatcher.demonology.init;

import me.dcatcher.demonology.item.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static final ItemBase itemSoulFlask = new ItemBase("soul_flask");

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(
                itemSoulFlask
        );
    }

    public static void registerModels() {
        itemSoulFlask.registerItemModel();
    }

}
