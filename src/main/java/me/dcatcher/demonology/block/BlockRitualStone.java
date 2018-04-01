package me.dcatcher.demonology.block;

import me.dcatcher.demonology.entities.EntityDemonicEye;
import me.dcatcher.demonology.tileentities.TileEntityRitualStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRitualStone extends BlockTileEntity<TileEntityRitualStone> {

    public BlockRitualStone(Material blockMaterialIn, String name) {
        super(blockMaterialIn, name);
        this.setHardness(1.0f);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            EntityDemonicEye ede = new EntityDemonicEye(world);
            ede.setPosition(pos.getX(), pos.getY()+5, pos.getZ());
            ede.addPlayerToInfo((EntityPlayerMP) player);
            world.spawnEntity(ede);
        }
        return true;
    }

    @Override
    public Class<TileEntityRitualStone> getTileEntityClass() {
        return TileEntityRitualStone.class;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityRitualStone();
    }

}
