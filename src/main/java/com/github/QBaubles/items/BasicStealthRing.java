package com.github.QBaubles.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/*
 * This is the class for the Basic Stealth Ring, an item which will hide the user's playername when equipped
 */
public class BasicStealthRing extends ItemBasic implements IBauble{
	
	public BasicStealthRing(String name) {
		super(name);
	}
	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}
	
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		
	}
	
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		
	}
	
}
