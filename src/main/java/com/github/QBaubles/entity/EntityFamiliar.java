package com.github.QBaubles.entity;

import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityFamiliar extends EntityMob {

	public EntityFamiliar(World worldIn) {
		super(worldIn);
        this.setSize(0.6F, 0.85F);
	}
	
//	@Override
//	public boolean shouldRenderInPass(int pass) {
//		return pass == 1;
//	}
	
	public void onLivingUpdate() {
		if (this.world.isRemote) {
			this.world.spawnParticle(EnumParticleTypes.SPELL,
					this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width*3,
					this.posY + this.rand.nextDouble() * (double) this.height,
					this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width*3, 0, 0, 0);
		}
		super.onLivingUpdate();
	}
} 

// Check blaze to figure out how particles are created