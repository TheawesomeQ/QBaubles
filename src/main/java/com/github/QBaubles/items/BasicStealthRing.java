package com.github.QBaubles.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

/*
 * This is the class for the Basic Stealth Ring, an item which will hide the user's playername when equipped
 */
public class BasicStealthRing extends ItemBasic implements IBauble {
	// Number of ticks a player must crouch to become invisible
	long STEALTH_TIME = 200;
	long TICKS_PER_CHECK = 40;
	public BasicStealthRing(String name) {
		super(name);
		this.setCreativeTab(CreativeTabs.MISC);
		this.setMaxStackSize(1);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		System.out.println("BasicStealthRing equipped!");
	}

	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
	}

	long ticksCrouched = 0;
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if (!player.getEntityWorld().isRemote && (player.ticksExisted%TICKS_PER_CHECK==0)) {
			if (player.isSneaking()) {
				ticksCrouched += TICKS_PER_CHECK;
			} else {
				ticksCrouched = 0;
			}
//			System.out.println("Stealth Ring: Ticks Crouched: " + ticksCrouched);
			if (ticksCrouched >= STEALTH_TIME) {
//				System.out.println("Stealth Ring: Trigger Invisibility");
				// Player has been crouching for at least STEALTH_TIME ticks
				if (!player.isPotionActive(MobEffects.INVISIBILITY)) {
					player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 60, 0, true, true));
					player.heal(4);
				} else {
					PotionEffect potionEffect = player.getActivePotionEffect(MobEffects.INVISIBILITY);
					if (potionEffect.getDuration() < 60) {
						potionEffect.combine(new PotionEffect(MobEffects.INVISIBILITY, 60, 0, true, true));
					}

				}
			}
		}
	}

}

/*
 * New plan: turn player invisible if you crouch for long enough
 */
