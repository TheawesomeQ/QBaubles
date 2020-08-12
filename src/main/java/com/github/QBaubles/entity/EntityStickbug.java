package com.github.QBaubles.entity;

import com.github.QBaubles.entity.ai.EntityAIStickbug;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.world.World;

public class EntityStickbug extends EntityCreature {
	
	public EntityStickbug(World worldIn) {
		super(worldIn);
	}
	
	protected void initEntityAI() {
		this.tasks.addTask(0,  new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIStickbug(this, 0.2D, 8));
		this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.25D));
	}
	
	protected void updateAITasks() {
		super.updateAITasks();
	}
	

}
