package me.dcatcher.demonology.util;

import me.dcatcher.demonology.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabDemonology extends CreativeTabs {

    public TabDemonology(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        // todo - when blocks and items actually added...
        return new ItemStack(ModItems.itemSoulFlask);
    }
}
