package me.dcatcher.demonology.entities;

import com.sun.istack.internal.NotNull;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityPulse extends EntityFireball implements IDemon {

    public EntityPulse(World world) {
        super(world);
        this.setSize(1.0f, 0.5f);
    }

    public EntityPulse(World world, double x, double y, double z, double aX, double aY, double aZ) {
        super(world, x, y, z, aX, aY, aZ);
    }

    @Override
    protected boolean isFireballFiery() {
        return false;
    }

    @Override
    protected EnumParticleTypes getParticleType() {
        return EnumParticleTypes.SPELL;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        // trigger explosion
        if (result.entityHit != null && result.entityHit instanceof IDemon) return;
        if (!world.isRemote) {
            this.world.createExplosion(this, result.hitVec.x, result.hitVec.y, result.hitVec.z, 1.0f, true);
            this.setDead();
        }
    }
}
