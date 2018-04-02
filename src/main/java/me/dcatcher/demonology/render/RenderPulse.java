package me.dcatcher.demonology.render;

import me.dcatcher.demonology.entities.EntityPulse;
import me.dcatcher.demonology.render.model.ModelPulse;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderPulse extends Render<EntityPulse> {

    private ResourceLocation texture_pulse = new ResourceLocation("demonology:textures/entity/soul.png");

    public static final FactoryPulse FACTORY_PULSE = new FactoryPulse();

    private float scale;

    private ModelPulse pulse;

    protected RenderPulse(RenderManager renderManager) {
        super(renderManager);
        this.scale = 1.0f;
        this.pulse = new ModelPulse();
    }

    @Override
    public void doRender(EntityPulse entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        bindTexture(this.texture_pulse);
        GL11.glColor4f(0.0f, 1.0f, 0.0f, 0.8f);
        GL11.glTranslated(x, y, z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        this.pulse.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F/2);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPulse entity) {
        return texture_pulse;
    }

    public static class FactoryPulse implements IRenderFactory<EntityPulse> {

        @Override
        public Render<? super EntityPulse> createRenderFor(RenderManager manager) {
            return new RenderPulse(manager);
        }

    }



}
