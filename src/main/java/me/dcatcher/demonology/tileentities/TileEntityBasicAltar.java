package me.dcatcher.demonology.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBasicAltar extends TileEntity {

    public ItemStackHandler iStackHandler;

    public int count = 0;

    public TileEntityBasicAltar() {
        iStackHandler = new ItemStackHandler(6);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", iStackHandler.serializeNBT());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            iStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
    }

}
