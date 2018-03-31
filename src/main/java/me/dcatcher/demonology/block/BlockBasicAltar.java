package me.dcatcher.demonology.block;

import me.dcatcher.demonology.Demonology;
import me.dcatcher.demonology.tileentities.TileEntityBasicAltar;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBasicAltar extends BlockTileEntity<TileEntityBasicAltar> {

    public BlockBasicAltar(Material blockMaterialIn, String name) {
        super(blockMaterialIn, name);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityBasicAltar te = this.getTileEntity(world, pos);
            if (player.inventory.getCurrentItem() == ItemStack.EMPTY) {
                // empty hand - trigger the crafting
//                InventoryHelper.dropInventoryItems(world, pos, te.iStackHandler); todo fix dis
                player.getCapability(Demonology.CAPABILITY_SOUL, EnumFacing.DOWN).removeHealth(1.0, player);
            } else {
                // place it on the altar

            }
        }
        return true;
    }

    @Override
    public Class getTileEntityClass() {
        return TileEntityBasicAltar.class;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityBasicAltar();
    }

}
