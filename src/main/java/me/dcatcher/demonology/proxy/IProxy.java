package me.dcatcher.demonology.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {

    void preInit(FMLPreInitializationEvent e);

    void registerItemRenderer(Item item, int meta, String name);

}
