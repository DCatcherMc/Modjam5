package me.dcatcher.demonology.item;

import me.dcatcher.demonology.Demonology;
import me.dcatcher.demonology.entities.EntityPulse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemBase extends Item {

    public String name;

    public ItemBase(String name) {
        super();
        this.name = name;
        this.setCreativeTab(Demonology.tabDemonology);
        this.setRegistryName(Demonology.MODID, name);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public void registerItemModel() {
        Demonology.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (name.equals("pulse_test")) {
            if (!worldIn.isRemote) {
                System.out.println("Spawning");
                Vec3d lookvec = playerIn.getLookVec();
                lookvec = lookvec.normalize();
//                Vec3d lookvec2 = lookvec.scale(2.0);
                EntityPulse pulse = new EntityPulse(worldIn, playerIn.posX + lookvec.x, playerIn.posY + playerIn.height*.75, playerIn.posZ + lookvec.z, lookvec.x, lookvec.y, lookvec.z);
                worldIn.spawnEntity(pulse);
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.inventory.getCurrentItem());
    }
}
