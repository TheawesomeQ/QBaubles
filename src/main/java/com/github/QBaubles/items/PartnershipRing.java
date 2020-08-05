package com.github.QBaubles.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class PartnershipRing extends ItemBasic implements IBauble {
	PartnershipRing partnerRing = null;
	public PartnershipRing getPartnerRing() {
		return partnerRing;
	}

	public void setPartnerRing(PartnershipRing newPartnerRing) {
		// Recursive call: If this item is paired, set the other item's field to null.
		// if this is being called to set to null, to prevent infinitely calling eachother back and forth, assume that 
		// the caller is setting itself to null and don't call it again here.
		if (this.partnerRing!=null && newPartnerRing!=null) {
			this.partnerRing.setPartnerRing(null);
		}
		this.partnerRing = newPartnerRing;
	}

	public PartnershipRing(String name) {
		super(name);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

	public void onWornTick(ItemStack itemstack, EntityLivingBase target) {

	}
	
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {		
	}


	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {		
	}
	
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		boolean alreadyEquipped = false;
		// I'm assuming this method is only called for players.
		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayer) player);
		
		for (int i = 0; i < baubles.getSlots(); i++) {
			if (baubles.getStackInSlot(i).getItem() instanceof PartnershipRing) {
				alreadyEquipped = true;
				break;
			}
		}
		
		return !alreadyEquipped;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);

			// Check slots for an already equipped partnershipRing
			boolean isEquipped = false;
			PartnershipRing newPartner = null;
			for (int i = 0; i < baubles.getSlots(); i++) {
				if (baubles.getStackInSlot(i).getItem() instanceof PartnershipRing) {
					isEquipped = true;
					newPartner = (PartnershipRing) baubles.getStackInSlot(i).getItem();
					break;
				}
			}
			if (isEquipped) {
				// If player has one already equipped, do not equip another. Set the partner for
				// both rings here.
				
				//Unpair old ring if it's already paired
				if(this.getPartnerRing() != null ) {
					this.getPartnerRing().setPartnerRing(null);
				}
				// Set this ring
				this.setPartnerRing(newPartner);
				
				// Set other ring
				newPartner.setPartnerRing(this);
			} else {

				for (int i = 0; i < baubles.getSlots(); i++)
					if ((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty())
							&& baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
						baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
						if (!player.capabilities.isCreativeMode) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
						}
						onEquipped(player.getHeldItem(hand), player);
						break;
					}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

}

/*
 * To pair two rings, equip one and right click while holding the other.
 * Held ring will search player's equipped slots for another partnershipring.
 * If found, it will set its own partnerRing to that ring, and set the
 * partnerRing of the other ring to itself.
 * 
 * Set canEquip to check whether the player already has one equipped
 * 
 * Setting partnerRing:
 * If the ring is already paired, it must set the partnerRing of its previous partner to null before unpairing with it.
 * 
 * Pairing process:
 * Each ring has a field for another ring.
 * 
 *
 * Buff Procedure:
 * Check whether paired
 * Get & compare dimension
 * Get & compare world position
 */