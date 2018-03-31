package me.dcatcher.demonology.util;

import me.dcatcher.demonology.Demonology;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SoulProvider implements ICapabilitySerializable<NBTTagCompound> {

    ISoulHandler instance = Demonology.CAPABILITY_SOUL.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == Demonology.CAPABILITY_SOUL;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? Demonology.CAPABILITY_SOUL.cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) Demonology.CAPABILITY_SOUL.getStorage().writeNBT(Demonology.CAPABILITY_SOUL, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        Demonology.CAPABILITY_SOUL.getStorage().readNBT(Demonology.CAPABILITY_SOUL, instance, null, nbt);
    }
}
