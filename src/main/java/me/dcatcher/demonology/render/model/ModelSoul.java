package me.dcatcher.demonology.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelSoul extends ModelBase {

    private ModelRenderer soulBody;

    private float scale;

    public ModelSoul(float scale) {
        this.soulBody = new ModelRenderer(this, 0, 0);
        this.soulBody.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
        this.soulBody.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.soulBody.setTextureSize(64, 32);
        this.scale = scale;
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glScalef(this.scale, this.scale, this.scale);
        this.soulBody.render(scale);
        GL11.glDisable(GL11.GL_BLEND);
        GlStateManager.popMatrix();
    }
}
