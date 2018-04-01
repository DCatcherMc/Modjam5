package me.dcatcher.demonology.render;

import me.dcatcher.demonology.entities.EntitySoul;
import me.dcatcher.demonology.render.model.ModelSoul;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderSoul extends RenderLiving<EntitySoul> {

    private ResourceLocation mobTexture = new ResourceLocation("demonology:textures/entity/soul.png");

    public static final FactorySoul FACTORY_SOUL = new FactorySoul();


    public RenderSoul(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSoul(1.0f), 0.0f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySoul entity) {
        return mobTexture;
    }

    public static class FactorySoul implements IRenderFactory<EntitySoul> {

        @Override
        public Render<? super EntitySoul> createRenderFor(RenderManager manager) {
            return new RenderSoul(manager);
        }

    }



}
