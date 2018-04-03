package me.dcatcher.demonology.init;

import me.dcatcher.demonology.Demonology;
import me.dcatcher.demonology.entities.EntityDemonicEye;
import me.dcatcher.demonology.entities.EntityPulse;
import me.dcatcher.demonology.entities.EntitySoul;
import me.dcatcher.demonology.entities.EntityWraith;
import me.dcatcher.demonology.render.RenderDemonicEye;
import me.dcatcher.demonology.render.RenderPulse;
import me.dcatcher.demonology.render.RenderSoul;
import me.dcatcher.demonology.render.RenderWraith;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    public static void init() {
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation("demonology:soul"), EntitySoul.class, "soul", id++, Demonology.instance, 64, 3, true, 0xffffff, 0xaaaaaa);
        EntityRegistry.registerModEntity(new ResourceLocation("demonology:demonic_eye"), EntityDemonicEye.class, "demonic_eye", id++, Demonology.instance, 64, 3, true, 0xff5555, 0xff0000);
        EntityRegistry.registerModEntity(new ResourceLocation("demonology:wraith"), EntityWraith.class, "wraith", id++, Demonology.instance, 64, 3, true, 0xff8888, 0xffffff);
        EntityRegistry.registerModEntity(new ResourceLocation("demonology:demonic_pulse"), EntityPulse.class, "demonic_pulse", id++, Demonology.instance, 64, 3, true);


        EntityRegistry.addSpawn(EntityWraith.class, 6, 1, 5, EnumCreatureType.MONSTER, Biomes.DEFAULT);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySoul.class, RenderSoul.FACTORY_SOUL);
        RenderingRegistry.registerEntityRenderingHandler(EntityDemonicEye.class, RenderDemonicEye.FACTORY_DEMONIC_EYE);
        RenderingRegistry.registerEntityRenderingHandler(EntityWraith.class, RenderWraith.FACTORY_WRAITH);
        RenderingRegistry.registerEntityRenderingHandler(EntityPulse.class, RenderPulse.FACTORY_PULSE);
    }
}
