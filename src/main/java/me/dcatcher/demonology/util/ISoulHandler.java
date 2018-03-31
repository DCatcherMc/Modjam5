package me.dcatcher.demonology.util;

import net.minecraft.entity.player.EntityPlayer;

public interface ISoulHandler {

    void removeHealth(double removing, EntityPlayer player);
    double getHealthRemoved();
    void resetHealth(EntityPlayer player);

    void setHealthRemoved(double removing);

}
