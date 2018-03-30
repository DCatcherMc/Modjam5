package me.dcatcher.demonology.render;

import me.dcatcher.demonology.entities.EntitySoul;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderSoul extends RenderLiving<EntitySoul> {

    private ResourceLocation mobTexture = new ResourceLocation("textures/entity/zombie/zombie.png");

    public static final Factory FACTORY = new Factory();


    public RenderSoul(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySoul entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntitySoul> {

        @Override
        public Render<? super EntitySoul> createRenderFor(RenderManager manager) {
            return new RenderSoul(manager);
        }

    }

}
