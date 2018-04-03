package me.dcatcher.demonology.item;

import me.dcatcher.demonology.entities.EntityPulse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import sun.audio.AudioPlayer;

public class ItemDemonicPulsator extends ItemBase {

    public ItemDemonicPulsator(String name) {
        super(name);
        this.setMaxDamage(100);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            ItemStack current = playerIn.inventory.getCurrentItem();
            current.setItemDamage(current.getItemDamage() + 1);
            Vec3d v = playerIn.getLookVec();
            v = v.normalize();
            EntityPulse pulse = new EntityPulse(worldIn, playerIn.posX + v.x, playerIn.posY + playerIn.eyeHeight, playerIn.posZ + v.z, v.x, v.y, v.z);
            worldIn.spawnEntity(pulse);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.inventory.getCurrentItem());
    }
}
