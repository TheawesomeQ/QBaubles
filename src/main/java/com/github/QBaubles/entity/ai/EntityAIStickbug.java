package com.github.QBaubles.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIStickbug extends EntityAIMoveToBlock {
	EntityCreature stickbug;
	public EntityAIStickbug(EntityCreature stickbug, double speedIn, int length) {
		super(stickbug, speedIn, length);
		this.stickbug = stickbug;
	}

	@Override
	protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
//		System.out.println("ShouldMoveTo check performed");

		if(!worldIn.isAirBlock(pos.up())) {
			return false;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();
			// If the bug is in water, go for any block that isn't water.
//			if (this.stickbug.isInWater() && block != Blocks.WATER && block != Blocks.FLOWING_WATER) {
//				return true;
//			}
			
			// Otherwise, prefer leaves.
			if (block == Blocks.LEAVES || block == Blocks.LEAVES2) {
//				System.out.println("TRUE for pos X" + pos.getX() + " Y" + pos.getY() + " Z" + pos.getZ());
				return true;
			}
			
			return false;
		}
	}
}
