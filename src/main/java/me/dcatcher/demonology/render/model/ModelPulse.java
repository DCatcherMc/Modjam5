package me.dcatcher.demonology.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelPulse extends ModelBase {

    private ModelRenderer pulse;

    public ModelPulse() {
        pulse = new ModelRenderer(this, 0, 0);
        pulse.addBox(-8.0f, -4.0f, -4.0f, 16, 8, 8, 1);
        pulse.setRotationPoint(0.0f, 0.0f, 0.0f);
        pulse.setTextureSize(64, 32);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.pulse.render(scale);
    }
}
