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
	protected boolean isEquipped;
	protected EntityLivingBase holder;

	public ShieldingRing(String name) {
		super(name);
//		this.setEquipped(false);
//		this.holder = null;
		MinecraftForge.EVENT_BUS.register(new ShieldingEventHandler(this));
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		// TODO: Choose what type of bauble it should be
		return BaubleType.BELT;
	}

	public void onEquip(ItemStack itemstack, EntityLivingBase player) {
		this.setEquipped(true);
		this.setHolder(player);
		System.out.println("ShieldingRing equipped!");
//		System.out.println("Qbaubles: equipped: " + itemstack.getDisplayName() + " to Player: " + player.getName());
	}

	public void onUnequip(ItemStack itemstack, EntityLivingBase player) {
		isEquipped = false;
	}

	public boolean getEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}

	public EntityLivingBase getHolder() {
		return holder;
	}

	public void setHolder(EntityLivingBase holder) {
		this.holder = holder;
	}

}

class ShieldingEventHandler {
	private ShieldingRing shield_ring;

	public ShieldingEventHandler(ShieldingRing shieldingRing) {
		shield_ring = shieldingRing;
	}

	@SubscribeEvent
	public void deflectArrow(ProjectileImpactEvent.Arrow event) {
		System.out.println("Deflect Arrow Event Triggered!!!");
		if (shield_ring.getHolder() != null) {
			// Deflect the arrow (i.e. cancel the impact event) if the player is holding the
			// ring and is sneaking.
			event.setCanceled(shield_ring.getEquipped() && shield_ring.getHolder().isSneaking());
			event.getArrow();
			System.out.print(shield_ring.getEquipped());
			// System.out.print(shield_ring.getHolder().isSneaking());

		}
	}
}