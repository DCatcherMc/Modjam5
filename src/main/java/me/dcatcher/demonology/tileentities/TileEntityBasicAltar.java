package me.dcatcher.demonology.tileentities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBasicAltar extends TileEntity {

    public ItemStackHandler iStackHandler;
    public int itemCount = 0;

    public TileEntityBasicAltar() {
        iStackHandler = new ItemStackHandler(6);
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

    public void placeItem(Item item) {
        iStackHandler.setStackInSlot(itemCount++, new ItemStack(item, 1));
    }

}
