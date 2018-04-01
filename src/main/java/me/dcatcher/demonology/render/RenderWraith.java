package me.dcatcher.demonology.render;

import me.dcatcher.demonology.entities.EntityDemonicEye;
import me.dcatcher.demonology.entities.EntityWraith;
import me.dcatcher.demonology.render.model.ModelWraith;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderWraith extends RenderBiped<EntityWraith> {

    private static final ResourceLocation WRAITH_TEXTURES = new ResourceLocation("demonology:textures/entity/wraith.png");

    public static final FactoryWraith FACTORY_WRAITH = new RenderWraith.FactoryWraith();

    public RenderWraith(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelWraith(), 0.1F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWraith entity) {
        return WRAITH_TEXTURES;
    }

    public static class FactoryWraith implements IRenderFactory<EntityWraith> {

        @Override
        public Render<? super EntityWraith> createRenderFor(RenderManager manager) {
            return new RenderWraith(manager);
        }

    }

}
