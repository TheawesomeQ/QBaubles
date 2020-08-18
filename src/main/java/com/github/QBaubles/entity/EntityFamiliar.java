package com.github.QBaubles.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class EntityFamiliar extends EntityTameable {
	final long FAMILIAR_LIFETIME = 1000;
	public EntityFamiliar(World worldIn) {
		super(worldIn);
        this.setSize(0.6F, 0.85F);
	}
	
//	@Override
//	public boolean shouldRenderInPass(int pass) {
//		return pass == 1;
//	}
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	}
	
	public void onLivingUpdate() {
		
//		if (this.ticksExisted % 20 == 0 && this.getOwner() != null) {
//			if (this.getAttackTarget() != null) {
//				System.out.println(
//						"Familiar Owner: " + this.getOwnerId() + " Target: " + this.getAttackTarget().getName());
//			} else {
//				System.out.println("Familiar null target");
//			}
//		}
		
		if (this.ticksExisted > FAMILIAR_LIFETIME || this.getOwnerId() == null) {
			// When despawning, spawn a puff of particles
			if (this.world.isRemote) {
				for(int i=0; i<25; ++i) {
				this.world.spawnParticle(EnumParticleTypes.CLOUD,
						this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width*3,
						this.posY + this.rand.nextDouble() * (double) this.height,
						this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width*3, 0, 0, 0);
			}}
			// Despawn this entity when the lifetime is exceeded.
//			System.out.println("Despawning familiar with owner: " + this.getOwnerId());
			this.setDead();;
		}
		if (this.world.isRemote) {
			this.world.spawnParticle(EnumParticleTypes.SPELL,
					this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width*3,
					this.posY + this.rand.nextDouble() * (double) this.height,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width*3, 0, 0, 0);
		}
		super.onLivingUpdate();
	}

	
	
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(3,  new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
//        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	}
	
    public boolean attackEntityAsMob(Entity entityIn)
    {
    	System.out.println("Familiar attempted attack, owner: "+this.getOwner().getName());
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }
	
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }
	
	// This method must be implemented in order to extend EntityTameable.
	// I'm just leaving null because I don't want them to reproduce.
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
} 

/*
 * The wolf entity detects when its owner takes damage with:
 * EntityAIOwnerHurtByTarget
 * When its owner damage something with:
 * EntityAIOwnerHurtTarget
 * 
 */