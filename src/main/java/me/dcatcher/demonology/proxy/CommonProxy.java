package me.dcatcher.demonology.proxy;

import net.minecraft.item.Item;

public class CommonProxy implements IProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String name) {
        // dont render on the serverside
    }
}
