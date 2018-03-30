package me.dcatcher.demonology.block;

import me.dcatcher.demonology.tileentities.TileEntityBasicAltar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntityBasicAltar te = this.getTileEntity(worldIn, pos);
            te.count++;
            playerIn.sendMessage(new TextComponentString("Count is now " + te.count));
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
