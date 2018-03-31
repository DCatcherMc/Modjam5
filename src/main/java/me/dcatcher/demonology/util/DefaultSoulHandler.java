package me.dcatcher.demonology.util;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;

public class DefaultSoulHandler implements ISoulHandler {

    private double healthRemoved = 0;

    @Override
    public void removeHealth(double removing, EntityPlayer player) {
        this.healthRemoved += removing;

        double current = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(current - removing);
    }

    @Override
    public double getHealthRemoved() {
        return healthRemoved;
    }

    @Override
    public void resetHealth(EntityPlayer player) {
        double current = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(current + healthRemoved);
        this.healthRemoved = 0;
    }

    @Override
    public void setHealthRemoved(double removing) {
        this.healthRemoved = removing;
    }
}
