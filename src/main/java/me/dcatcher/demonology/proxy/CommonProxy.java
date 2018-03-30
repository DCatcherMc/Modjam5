package me.dcatcher.demonology.proxy;

import me.dcatcher.demonology.init.ModEntities;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        ModEntities.init();
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String name) {
        // dont render on the serverside
    }
}
