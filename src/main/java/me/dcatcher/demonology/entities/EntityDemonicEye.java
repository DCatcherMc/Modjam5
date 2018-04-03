package me.dcatcher.demonology.entities;

import com.google.common.base.Predicate;
import me.dcatcher.demonology.Demonology;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityDemonicEye extends EntityFlying implements IMob, IRangedAttackMob, IDemon{

    public BlockPos posStart;

    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public EntityDemonicEye(World worldIn) {
        super(worldIn);
        this.setSize(3.0F, 3.0F);
        this.experienceValue = 5;
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.setHealth(this.getMaxHealth());
        this.moveHelper = new EntityDemonicEye.DemonicEyeMoveHelper(this);
    }

    public EntityDemonicEye(World w, float x, float y, float z) {
        this(w);
        this.posStart = new BlockPos(x, y, z);
        this.setPosition(x, y, z);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return new ResourceLocation(Demonology.MODID, "loot_demonic_eye");
    }

    public void addPlayerToInfo(EntityPlayerMP player) {
        this.bossInfo.addPlayer(player);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(5, new AIRandomFly(this));
        this.tasks.addTask(7, new AILookAround(this));
        this.tasks.addTask(4, new EntityAIAttackRanged(this, 1.0D, 40, 20.0F));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        this.bossInfo.setVisible(false);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        this.world.playEvent((EntityPlayer)null, 1024, new BlockPos(this), 0);
        for (int i = 0; i < 9; i++) {
            int xModifier = (i % 3) - 1;
            int zModifier = Math.floorDiv(i, 3);

            double d0 = this.posX + xModifier;
            double d1 = this.posY;
            double d2 = this.posZ + zModifier;
            double d3 = target.posX - d0;
            double d4 = target.posY - d1;
            double d5 = target.posZ - d2;
            Vec3d accel = new Vec3d(d3, d4, d5);
            accel = accel.normalize();
            EntityPulse entityP = new EntityPulse(this.world, this.posX + accel.x, this.posY + accel.y, this.posZ + accel.z, d3, d4, d5);
//            EntityWitherSkull
            this.world.spawnEntity(entityP);
        }

    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }

    static class AIRandomFly extends EntityAIBase
    {
        private final EntityDemonicEye parentEntity;


        public AIRandomFly(EntityDemonicEye demonicEye)
        {
            this.parentEntity = demonicEye;
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();

            if (!entitymovehelper.isUpdating())
            {
                return true;
            }
            else
            {
                double d0 = entitymovehelper.getX() - this.parentEntity.posX;
                double d1 = entitymovehelper.getY() - this.parentEntity.posY;
                double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            Random random = this.parentEntity.getRNG();
            double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);

            if (this.parentEntity.posStart != null) {
                d0 = Math.min(d0, this.parentEntity.posStart.getX() + 20);
                d0 = Math.max(d0, this.parentEntity.posStart.getX() - 20);

                d1 = Math.min(d1, this.parentEntity.posStart.getY() + 10);
                d1 = Math.max(d1, this.parentEntity.posStart.getY() - 10);

                d2 = Math.min(d2, this.parentEntity.posStart.getZ() + 20);
                d2 = Math.max(d2, this.parentEntity.posStart.getZ() - 20);
            }
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 0.5D);
        }
    }

    /**
     *  Basically ripped straight out of GhastMoveHelper - both flying mobs
     **/
    static class DemonicEyeMoveHelper extends EntityMoveHelper
    {
        private final EntityDemonicEye parentEntity;
        private int courseChangeCooldown;

        public DemonicEyeMoveHelper(EntityDemonicEye demonicEye)
        {
            super(demonicEye);
            this.parentEntity = demonicEye;
        }

        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - this.parentEntity.posX;
                double d1 = this.posY - this.parentEntity.posY;
                double d2 = this.posZ - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.courseChangeCooldown-- <= 0)
                {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    d3 = (double) MathHelper.sqrt(d3);

                    if (this.isNotColliding(this.posX, this.posY, this.posZ, d3))
                    {
                        this.parentEntity.motionX += d0 / d3 * 0.1D;
                        this.parentEntity.motionY += d1 / d3 * 0.1D;
                        this.parentEntity.motionZ += d2 / d3 * 0.1D;
                    }
                    else
                    {
                        this.action = EntityMoveHelper.Action.WAIT;
                    }
                }
            }
        }

        /**
         * Checks if entity bounding box is not colliding with terrain
         */
        private boolean isNotColliding(double x, double y, double z, double p_179926_7_)
        {
            double d0 = (x - this.parentEntity.posX) / p_179926_7_;
            double d1 = (y - this.parentEntity.posY) / p_179926_7_;
            double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
            AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

            for (int i = 1; (double)i < p_179926_7_; ++i)
            {
                axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty())
                {
                    return false;
                }
            }

            return true;
        }
    }

    static class AILookAround extends EntityAIBase
    {
        private final EntityDemonicEye parentEntity;

        public AILookAround(EntityDemonicEye demonicEye)
        {
            this.parentEntity = demonicEye;
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            if (this.parentEntity.getAttackTarget() == null)
            {
                this.parentEntity.rotationYaw = -((float)MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float)Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            }
            else
            {
                EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
                double d0 = 64.0D;

                if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D)
                {
                    double d1 = entitylivingbase.posX - this.parentEntity.posX;
                    double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
                    this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
            }
        }
    }



}
