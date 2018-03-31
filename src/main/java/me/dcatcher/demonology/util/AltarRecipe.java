package me.dcatcher.demonology.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AltarRecipe {

    public List<Item> inItems;
    public Item output;
    public int count;

    public AltarRecipe(Item[] in, Item out, int count) {
        inItems = new ArrayList<>();
        int num = in.length > 6 ? 6 : in.length;
        for (int i = 0; i < num; i++) {
            inItems.add(in[i]);
        }

        this.output = out;
        this.count = count;
    }

    public boolean checkRecipe(Item[] in) {
        List<Item> cloned = new ArrayList<>(inItems);
        if (in.length > 6) return false;
        if (in.length != cloned.size()) return false;
        for (int i = 0; i < in.length; i++) {
            if (cloned.contains(in[i])) {
                cloned.remove(in[i]);
            }
        }
        if (cloned.size() == 0) return true;
        else return false;
    }
}
