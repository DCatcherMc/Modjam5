package me.dcatcher.demonology.block;

import me.dcatcher.demonology.Demonology;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

    private String name;

    public BlockBase(Material blockMaterialIn, String name) {
        super(blockMaterialIn);
        this.name = name;
        this.setCreativeTab(Demonology.tabDemonology);
        this.setRegistryName(Demonology.MODID, name);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    public void registerItemModel(Item ib) {
        Demonology.proxy.registerItemRenderer(ib, 0, name);
    }
}
