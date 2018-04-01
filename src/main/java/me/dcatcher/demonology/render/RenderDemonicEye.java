package me.dcatcher.demonology.render;

import me.dcatcher.demonology.entities.EntityDemonicEye;
import me.dcatcher.demonology.render.model.ModelSoul;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderDemonicEye extends RenderLiving<EntityDemonicEye> {

    private ResourceLocation mobTexture = new ResourceLocation("demonology:textures/entity/demonic_eye.png");

    public static final FactoryDemonicEye FACTORY_DEMONIC_EYE = new FactoryDemonicEye();

    public RenderDemonicEye(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSoul(4.0f), 0.0f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDemonicEye entity) {
        return mobTexture;
    }

    public static class FactoryDemonicEye implements IRenderFactory<EntityDemonicEye> {

        @Override
        public Render<? super EntityDemonicEye> createRenderFor(RenderManager manager) {
            return new RenderDemonicEye(manager);
        }

    }

}
