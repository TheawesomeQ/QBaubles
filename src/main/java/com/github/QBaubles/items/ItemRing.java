package com.github.QBaubles.items;
/*
 * This is a ring, treated as an inventory item and equipabble with the baubles api.
 * This could be used as a base class for all rings, but I think instead I'm just going
 * to create java classes for each different ring.
 */
import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ItemRing extends ItemBasic implements IBauble {

	public ItemRing(String name) {
		super(name);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		System.out.println("Ring item equipped!!! -Q");
	}

}
