package me.dcatcher.demonology.block;

import me.dcatcher.demonology.tileentities.TileEntityRitualStone;
import me.dcatcher.demonology.util.ISoulHandler;
import me.dcatcher.demonology.util.Ritual;
import me.dcatcher.demonology.util.RitualExecutor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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
            // summoning time!
            // dont actually need a TE rn
            for (Ritual r : RitualExecutor.rituals) {
                ISoulHandler ish;
                if ((ish = r.canComplete(world, pos)) != null) {
                    RitualExecutor.executeRitual(r, world, pos, ish, player);
                }
            }
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
