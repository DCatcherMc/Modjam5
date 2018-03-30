package me.dcatcher.demonology.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelSoul extends ModelBase {

    private ModelRenderer soulBody;

    public ModelSoul() {
        this.soulBody = new ModelRenderer(this, 0, 0);
        this.soulBody.addBox(-5.0f, -5.0f, -5.0f, 10, 10, 10);
        this.soulBody.setRotationPoint(0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        this.soulBody.render(scale);
        GL11.glDisable(GL11.GL_BLEND);
        GlStateManager.popMatrix();
    }
}
