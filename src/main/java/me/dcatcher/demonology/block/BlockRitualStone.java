package me.dcatcher.demonology.block;

import me.dcatcher.demonology.tileentities.TileEntityBasicAltar;
import me.dcatcher.demonology.tileentities.TileEntityRitualStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRitualStone extends BlockTileEntity<TileEntityRitualStone> {

    public BlockRitualStone(Material blockMaterialIn, String name) {
        super(blockMaterialIn, name);
        this.setHardness(1.0f);
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
