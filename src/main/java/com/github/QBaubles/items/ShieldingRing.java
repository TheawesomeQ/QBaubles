package com.github.QBaubles.items;

// Currently, the shielding ring is actually a belt. To change this:
// - Change the texture to a ring
// - Change the value from BELT to RING below
// - Change the name of the item in asssets.qbaubles.lang/en_us.lang
import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ShieldingRing extends ItemBasic implements IBauble {
	protected boolean shielded;
	protected EntityLivingBase holder;

	public ShieldingRing(String name) {
		super(name);
		MinecraftForge.EVENT_BUS.register(new ShieldingEventHandler(this));
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		holder = player;
		shielded = true;
//		System.out.println("ShieldingRing equipped to player: " + player.getName());
	}

	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		holder = null;
		shielded = false;
	}



}

class ShieldingEventHandler {
	private ShieldingRing shield_ring;

	public ShieldingEventHandler(ShieldingRing shieldingRing) {
		shield_ring = shieldingRing;
	}

	@SubscribeEvent
	public void deflectArrow(ProjectileImpactEvent.Arrow event) {
//		if (shield_ring.holder!=null) {System.out.println("Deflect Arrow Event Triggered!!! Player: "+shield_ring.holder.getName());}
		if ((shield_ring.holder != null) && (event.isCancelable())) {
			// Arrows are bounced if damage = 0. So, set damage to 0 if equipped & sneaking
			// Old approach canceled the impact and caused arrows to fly right through.
			if(shield_ring.shielded && shield_ring.holder.isSneaking()) {
			//event.setCanceled(true);
			event.getArrow().setDamage(0);}
			System.out.print(shield_ring.shielded);
			// System.out.print(shield_ring.getHolder().isSneaking());

		}
	}
}