package com.github.QBaubles.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * This is the class for the Basic Stealth Ring, an item which will hide the user's playername when equipped
 */
public class BasicStealthRing extends ItemBasic implements IBauble {
	protected boolean hidden;
	protected EntityLivingBase holder;

	public BasicStealthRing(String name) {
		super(name);
		MinecraftForge.EVENT_BUS.register(new NameRenderEventHandler(this));
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		// Does this method run when loading in a character too? Is it remembered
		// between restarts?
		holder = player;
		hidden = true;
		System.out.println("BasicStealthRing equipped!");
	}

	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		hidden = false;
	}



}

class NameRenderEventHandler {
	private BasicStealthRing bsring;
	public NameRenderEventHandler(BasicStealthRing basicStealthRing) {
		bsring = basicStealthRing;
	}

	@SubscribeEvent
	public void setHidden(RenderLivingEvent.Pre<EntityLivingBase> event) {
		if (event.isCancelable() && bsring.hidden) {
			event.setCanceled(true);
		} else {
			event.setCanceled(false);
		}
	}
}

/*
 * New approach: Create new class containing static methods. Have arguments
 * passed from the item class to the event handler class. Other class hide functioN: bool hidden, player,
 */
