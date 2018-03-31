package me.dcatcher.demonology.block;

import me.dcatcher.demonology.tileentities.TileEntityBasicAltar;
import me.dcatcher.demonology.util.DefaultSoulHandler;
import me.dcatcher.demonology.util.ISoulHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class BlockBasicAltar extends BlockTileEntity<TileEntityBasicAltar> {

    public BlockBasicAltar(Material blockMaterialIn, String name) {
        super(blockMaterialIn, name);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityBasicAltar te = this.getTileEntity(world, pos);
            if (player.inventory.getCurrentItem() == ItemStack.EMPTY || player.inventory.getCurrentItem().getItem() == Item.getByNameOrId("air")) {
                // empty hand - trigger the crafting
                ISoulHandler ish = DefaultSoulHandler.getHandler(player);
                if (ish != null) {
                    ish.removeHealth(1, player);
                }
            } else {
                // place it on the altar
                if (te.itemCount < 6) {
                    Item toAdd = player.inventory.getCurrentItem().getItem();
                    player.inventory.decrStackSize(player.inventory.currentItem, 1);
                    te.iStackHandler.setStackInSlot(te.itemCount++, new ItemStack(toAdd, 1));
                    player.sendMessage(new TextComponentString("Now added " + toAdd.getUnlocalizedName()));
                } else {
                    this.dropAllItems(world, pos);
                }
            }
            te.markDirty();
        }
        return true;
    }

    private void dropAllItems(World world, BlockPos pos) {
        TileEntityBasicAltar te = this.getTileEntity(world, pos);
        for (int i = 0; i < te.itemCount; i++) {
            ItemStack is = te.iStackHandler.getStackInSlot(i);
            EntityItem toDrop = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), is);
            world.spawnEntity(toDrop);
        }
        te.itemCount = 0;
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

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        this.dropAllItems(world, pos);
    }
}
