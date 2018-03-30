package me.dcatcher.demonology.init;

import me.dcatcher.demonology.Demonology;
import me.dcatcher.demonology.entities.EntitySoul;
import me.dcatcher.demonology.render.RenderSoul;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    public static void init() {
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation("demonology:soul"), EntitySoul.class, "soul", id++, Demonology.instance, 64, 3, true, 0xffffff, 0xaaaaaa);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySoul.class, RenderSoul.FACTORY);
    }
}
