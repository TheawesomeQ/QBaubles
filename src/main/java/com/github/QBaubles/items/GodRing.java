package com.github.QBaubles.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class GodRing extends ItemBasic implements IBauble{
	
	public GodRing(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}
	
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		player.setEntityInvulnerable(true);
	}
	
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		player.setEntityInvulnerable(false);
	}
	
}