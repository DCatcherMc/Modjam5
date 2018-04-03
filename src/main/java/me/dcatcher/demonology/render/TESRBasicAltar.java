package me.dcatcher.demonology.render;

import me.dcatcher.demonology.tileentities.TileEntityBasicAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TESRBasicAltar extends TileEntitySpecialRenderer<TileEntityBasicAltar> {

    @Override
    public void render(TileEntityBasicAltar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        float degs = 360.0f / te.itemCount;
        for (int i = 0; i < te.itemCount; i++) {
            ItemStack is = te.iStackHandler.getStackInSlot(i);
            EntityItem item = new EntityItem(te.getWorld(), x, y + 1, z, is);

            // deffo not ripping off the rendering code from dropped items
            boolean flag = false;
            if (this.bindEntityTexture(item)) {
                Minecraft.getMinecraft().getRenderManager().renderEngine.getTexture(this.getEntityTexture(item)).setBlurMipmap(false, false);
                flag = true;
            }
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,GlStateManager.DestFactor.ZERO);
            GlStateManager.pushMatrix();
            IBakedModel ibakedmodel = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(is, te.getWorld(), null);
            int j = this.transformModelCount(x + 0.5, y+0.75, z + 0.5, ibakedmodel, degs, i, partialTicks, te.isCrafting, te);

            boolean flag1 = ibakedmodel.isGui3d();

            if (!flag1)
            {
                float f3 = -0.0F * (float)(j - 1) * 0.5F; float f4 = -0.0F * (float)(j - 1) * 0.5F; float f5 = -0.09375F * (float)(j - 1) * 0.5F;
                GlStateManager.translate(f3, f4, f5);
            }

            if (flag1)
            {
                GlStateManager.pushMatrix();
                ibakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel,ItemCameraTransforms.TransformType.GROUND, false);
                Minecraft.getMinecraft().getRenderItem().renderItem(is, ibakedmodel);
                GlStateManager.popMatrix();
            }
            else
            {
                GlStateManager.pushMatrix();
                ibakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel,ItemCameraTransforms.TransformType.GROUND, false);
                Minecraft.getMinecraft().getRenderItem().renderItem(is, ibakedmodel);
                GlStateManager.popMatrix();
                GlStateManager.translate(0.0F, 1.0F, 0.09375F);
            }


            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            this.bindEntityTexture(item);


            if (flag) {
                Minecraft.getMinecraft().renderEngine.getTexture(this.getEntityTexture(item)).restoreLastBlurMipmap();
            }

        }
    }

    protected boolean bindEntityTexture(EntityItem entity) {
        ResourceLocation resourcelocation = this.getEntityTexture(entity);
        if (resourcelocation == null) {
            return false;
        } else {
            this.bindTexture(resourcelocation);
            return true;
        }
    }


    protected ResourceLocation getEntityTexture(EntityItem entity) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }


    private int transformModelCount(double d1, double d2, double d3, IBakedModel model, double angle, int iter, float partialTicks, boolean crafting, TileEntityBasicAltar te) {
        boolean flag = model.isGui3d();
        int i = 1;
        float f2 = ItemCameraTransforms.DEFAULT.ground.scale.y;
        GlStateManager.translate((float) d1, (float) d2 + 0.25F * f2, (float) d3);


        if (flag || Minecraft.getMinecraft().getRenderManager().options != null) {
            if (crafting) {
                float percentDone = ((float)(te.getWorld().getTotalWorldTime() - te.worldTimeCraftStarted))/te.craftingTime;
                GlStateManager.rotate((float) (iter * angle), 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(0.01 + 0.5 * (1 - percentDone), 0, 0);
            } else {
                float f3 = (0.05f * (Minecraft.getSystemTime() + partialTicks)) % 360;
                GlStateManager.rotate((float) (iter * angle + f3), 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(0.5, 0, 0);
            }
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        return i;
    }



}
