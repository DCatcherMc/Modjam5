package me.dcatcher.demonology.util;

import me.dcatcher.demonology.Demonology;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.ItemStackHandler;

public class DefaultSoulHandler implements ISoulHandler {

    private int souls = 0;

    public static ISoulHandler getHandler(ItemStack itemStack) {
        if (itemStack.hasCapability(Demonology.CAPABILITY_SOUL, EnumFacing.DOWN))
            return itemStack.getCapability(Demonology.CAPABILITY_SOUL, EnumFacing.DOWN);
        return null;
    }

    @Override
    public void addSouls(int soulsToAdd) {
        this.souls = Math.min(100, this.souls + soulsToAdd);
    }

    @Override
    public int getSouls() {
        return this.souls;
    }

    @Override
    public void setSouls(int souls) {
        this.souls = souls;
    }
}
