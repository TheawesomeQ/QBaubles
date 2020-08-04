package com.github.QBaubles.items;

// Currently, the shielding ring is actually a belt. To change this:
// - Change the texture to a ring
// - Change the value from BELT to RING below
// - Change the name of the item in asssets.qbaubles.lang/en_us.lang
import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ShieldingRing extends ItemBasic implements IBauble {
	public ShieldingRing(String name) {
		super(name);
		MinecraftForge.EVENT_BUS.register(new ShieldingEventHandler());
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.BELT;
	}

	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
	}

	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
	}

}

class ShieldingEventHandler {
	@SubscribeEvent
	public void deflectArrow(ProjectileImpactEvent.Arrow event) {
		Entity target = event.getRayTraceResult().entityHit;
		if(target != null) {
		if(target instanceof EntityPlayer) {
			IBaublesItemHandler bauble = BaublesApi.getBaublesHandler((EntityPlayer) target);
			boolean isEquipped = false;
			for (int i=0; i < bauble.getSlots(); i++) {
				if(bauble.getStackInSlot(i).getItem() instanceof ShieldingRing) {
					isEquipped = true;
					break;
				}
			}
			if(isEquipped && target.isSneaking()) {
				event.getArrow().setDamage(0);
			}
		}
		}
	}
}



/*
 * Rethink this event handling.
 * 
 * Event will trigger on every arrow hit. It will scan the target's bauble slots
 * for the ring and set the arrow damage to zero if it finds it.
 * */
