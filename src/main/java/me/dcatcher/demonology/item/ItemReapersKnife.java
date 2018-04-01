package me.dcatcher.demonology.item;

import me.dcatcher.demonology.Demonology;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemReapersKnife extends ItemSword {

    private String name;

    public ItemReapersKnife(String name) {
        super(ToolMaterial.IRON);
        this.name = name;
        this.setRegistryName(Demonology.MODID, name);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setCreativeTab(Demonology.tabDemonology);
    }

    public void registerItemModel() {
        Demonology.proxy.registerItemRenderer(this, 0, name);
    }


}
