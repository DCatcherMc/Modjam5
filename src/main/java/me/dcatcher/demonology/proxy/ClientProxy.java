package me.dcatcher.demonology.proxy;

import me.dcatcher.demonology.Demonology;
import me.dcatcher.demonology.init.ModEntities;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ModEntities.initModels();
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Demonology.MODID + ":" + name, "inventory"));
    }

}
