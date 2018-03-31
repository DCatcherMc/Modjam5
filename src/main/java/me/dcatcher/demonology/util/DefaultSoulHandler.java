package me.dcatcher.demonology.util;

import me.dcatcher.demonology.Demonology;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

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

    public static ISoulHandler getHandler(Entity entity) {
        if (entity.hasCapability(Demonology.CAPABILITY_SOUL, EnumFacing.DOWN))
            return entity.getCapability(Demonology.CAPABILITY_SOUL, EnumFacing.DOWN);
        return null;
    }
}
