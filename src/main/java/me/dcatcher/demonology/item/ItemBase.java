package me.dcatcher.demonology.item;

import me.dcatcher.demonology.Demonology;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public String name;

    public ItemBase(String name) {
        super();
        this.name = name;
        this.setCreativeTab(Demonology.tabDemonology);
        this.setRegistryName(Demonology.MODID, name);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public void registerItemModel() {
        Demonology.proxy.registerItemRenderer(this, 0, name);
    }

}
