package me.dcatcher.demonology.tileentities;

import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBasicAltar extends TileEntity {

    public ItemStackHandler iStackHandler;
    public InventoryHelper invHelper;
    public int itemCount = 0;

    public TileEntityBasicAltar() {
        iStackHandler = new ItemStackHandler(6);
        invHelper = new InventoryHelper();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", iStackHandler.serializeNBT());
        compound.setInteger("itemCount", itemCount);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            iStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
        if (compound.hasKey("itemCount")) {
            itemCount = compound.getInteger("itemCount");
        }
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound tagCom = pkt.getNbtCompound();
        this.readFromNBT(tagCom);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, -1, getUpdateTag());
    }

    @Override
    public void markDirty() {
        super.markDirty();
        this.getWorld().markBlockRangeForRenderUpdate(pos, pos);
        this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos),3);
        this.getWorld().scheduleBlockUpdate(pos,this.getBlockType(),0,0);
    }

    public Item[] getContents() {
        Item[] contents = new Item[this.itemCount];
        for (int i = 0; i < this.itemCount; i++) {
            contents[i] = this.iStackHandler.getStackInSlot(i).getItem();
        }
        return contents;
    }

    public void clear() {
        this.itemCount = 0;
        this.markDirty();
    }
}
