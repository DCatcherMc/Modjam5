package me.dcatcher.demonology.util;

import com.sun.org.apache.bcel.internal.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class TabDemonology extends CreativeTabs {

    public TabDemonology(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        // todo - when blocks and items actually added...
        return new ItemStack(Item.getByNameOrId("skull"), 1);
    }
}
