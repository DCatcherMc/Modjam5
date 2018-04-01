package me.dcatcher.demonology.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowOwnerFlying;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class EntitySoul extends EntityCreature {

    private long birthTime;

    public EntitySoul(World worldIn) {
        super(worldIn);
        this.birthTime = worldIn.getWorldTime();
        this.setAIMoveSpeed(0.25f);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISoulDisperse(this, 100));
        this.tasks.addTask(1, new EntityAISoulPanic(this, 0.5));
    }

    class EntityAISoulDisperse extends EntityAIBase {

        private EntitySoul entitySoul;
        private int disperseTime;

        public EntityAISoulDisperse(EntitySoul e, int disperseTime) {
            this.entitySoul = e;
            this.disperseTime = disperseTime;
        }

        @Override
        public boolean shouldExecute() {
            return this.entitySoul.world.getWorldTime() - this.entitySoul.birthTime > disperseTime;
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            this.entitySoul.setDead();
        }
    }

    class EntityAISoulPanic extends EntityAIBase {

        private EntitySoul entitySoul;
        private double targetX;
        private double targetY;
        private double targetZ;
        private double speed;

        public EntityAISoulPanic(EntitySoul entitySoul, double speed) {
            this.entitySoul = entitySoul;
            this.speed = speed;
        }

        @Override
        public boolean shouldExecute() {
            // generate a random point within 5 horiz and 4 vert blocks
            Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entitySoul, 5, 4);


            // check if we failed to gen for some reason
            if (vec3d == null) {
                return false;
            } else {
                this.targetX = vec3d.x;
                this.targetY = vec3d.y;
                this.targetZ = vec3d.z;
                return true;
            }
        }

        public void startExecuting() {
            // use the random spot to generate a path + move there
            this.entitySoul.getNavigator().tryMoveToXYZ(this.targetX, this.targetY, this.targetZ, this.speed);
        }


        public boolean shouldContinueExecuting() {
            // keep going until we cant get to the point anymore
            return !this.entitySoul.getNavigator().noPath();
        }


        @Override
        public void updateTask() {
            super.updateTask();
            // not much to do here!
        }
    }

}
