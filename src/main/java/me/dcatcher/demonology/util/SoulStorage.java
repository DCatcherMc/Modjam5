package me.dcatcher.demonology.util;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class SoulStorage implements Capability.IStorage<ISoulHandler> {

    @Override
    public NBTBase writeNBT(Capability<ISoulHandler> capability, ISoulHandler instance, EnumFacing side) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("health_removed", instance.getHealthRemoved());
        return tag;

    }

    @Override
    public void readNBT(Capability<ISoulHandler> capability, ISoulHandler instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setHealthRemoved(tag.getDouble("health_removed"));
    }
}
