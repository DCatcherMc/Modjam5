package me.dcatcher.demonology.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySoul extends EntityCreature {

    private long birthTime;

    public EntitySoul(World worldIn) {
        super(worldIn);
        this.birthTime = worldIn.getWorldTime();
        this.setAIMoveSpeed(0.25f);

    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISoulDisperse(this, 100));
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWander(this, 0.25));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
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
            System.out.println("DEATH TIME");
            super.startExecuting();
            this.entitySoul.setDead();
        }
    }

}
