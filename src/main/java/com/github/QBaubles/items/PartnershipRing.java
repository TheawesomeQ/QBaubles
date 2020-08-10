package com.github.QBaubles.items;

import java.util.List;
import java.util.Random;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class PartnershipRing extends ItemBasic implements IBauble {
	// This should be set to read from a user-settable configuration.
	// For now though, a constant value is fine.
	static final int RANGE = 48;
	public PartnershipRing(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if (player.ticksExisted % 39 == 0) {

			// Check that world isn't remote (i.e. we're on the server side) and
			// make sure this ring is paired (i.e. it has a PairID tag)
			// System.out.println("hasTagCompound: "+itemstack.hasTagCompound());
			// System.out.println("hasKey: "+itemstack.getTagCompound().hasKey("PairID"));
			if ((!player.getEntityWorld().isRemote) && itemstack.hasTagCompound()
					&& itemstack.getTagCompound().hasKey("PairID")) {
				// System.out.println("Running a check for nearby players with rings");
				// Get all entities within a bounding box on the player.
				List<EntityPlayer> e = player.getEntityWorld().getEntitiesWithinAABB(EntityPlayer.class,
						new AxisAlignedBB(player.posX - RANGE, player.posY - RANGE, player.posZ - RANGE,
								player.posX + RANGE, player.posY + RANGE, player.posZ + RANGE));
				for (int i = 0; i < e.size(); i++) {
					// Ignore the player themself
					if (e.get(i) == player) {
						continue;
					}

					// Loop through their baubles to check for a PartnershipRing,
					// and if found, check if it has matching NBT PairID.
					boolean isMatch = false;
					IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(e.get(i));

					for (int j = 0; j < baubles.getSlots(); j++) {
						if (baubles.getStackInSlot(j).getItem() instanceof PartnershipRing) {
							// System.out.println("PartnershipRing found.");
							// Only act if the ring has an NBT Compound tag and
							// is paired (i.e. has tag PairID)
							if (baubles.getStackInSlot(j).hasTagCompound()
									&& baubles.getStackInSlot(j).getTagCompound().hasKey("PairID")) {
								isMatch = baubles.getStackInSlot(j).getTagCompound().getInteger("PairID") == itemstack
										.getTagCompound().getInteger("PairID");
							}
							// Regardless of if it was matched, if the player
							// was wearing an instanceof PartnershipRing we've found
							// what we were looking for and can stop looping
							// through their bauble slots
							break;
						}
					}

					// If they have a match, apply the buff and break the player search loop.
					if (isMatch) {
						// System.out.println("Player Ring Match Found ID: " +
						// itemstack.getTagCompound().getInteger("PairID"));

						// Reapplying the effect caused the boost health to be reset.
						// https://forums.minecraftforge.net/topic/22351-how-to-give-entities-a-health-boost-if-special-armour-is-worn/
						// I copied that code to check whether the effect was still active and if it
						// was,
						// extend it with the combine method.
						if (!player.isPotionActive(MobEffects.HEALTH_BOOST)) {
							player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 60, 0, true, true));
							player.heal(4);
						} else {
							PotionEffect potionEffect = player.getActivePotionEffect(MobEffects.HEALTH_BOOST);
							if (potionEffect.getDuration() < 60) {
								potionEffect.combine(new PotionEffect(MobEffects.HEALTH_BOOST, 60, 0, true, true));
							}

						}
						break;
					}
				}
			}
		}
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
			ItemStack equippedring = null;
			// Check slots for an already equipped partnershipRing
			boolean isEquipped = false;
			for (int i = 0; i < baubles.getSlots(); i++) {
				if (baubles.getStackInSlot(i).getItem() instanceof PartnershipRing) {
					isEquipped = true;
					equippedring = baubles.getStackInSlot(i);
					break;
				}
			}
			if (isEquipped) {
				// If player has one already equipped, do not equip another. Set the partner for
				// both rings here.
				
				// Get the held ring itemstack to work on. The other itemstack (equippedring)
				// was already found during the searching loop.
				ItemStack heldring = player.getHeldItem(hand);
				// Generate a random ID to use.
				Random rand = new Random();
				int randID = rand.nextInt();
						
				// Set this ring
				NBTTagCompound nbt;
				// Get or create NBT tag
				if(heldring.hasTagCompound()) {
					nbt = heldring.getTagCompound();
				} else {
					nbt = new NBTTagCompound();
				}
				// Set tag parameter
				nbt.setInteger("PairID", randID);
				// Set tag to itemstack
				heldring.setTagCompound(nbt);
				
				// Set other ring
				if(equippedring.hasTagCompound()) {
					nbt = equippedring.getTagCompound();
				} else {
					nbt = new NBTTagCompound();
				}
				nbt.setInteger("PairID", randID);
				equippedring.setTagCompound(nbt);
				System.out.println("Paired with ID: " + randID);
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
 * 
 * 
 * 
 * Rethink:
 * Set unique-enough ID NBT for each, and when pairing set
 * a partner ID NBT on the item.
 * Regularly check nearby players for the item, and if it matches, apply
 * the effect.
 * There should be no problem with conflicting ID's, even if it is the same
 * as the one it's paired with (assuming yourself isn't one of the
 * nearby entities scanned). The only time a problem with conflict arises
 * is if there are two other nearby players with matching ID's.
 * 
 * Rethink again:
 * When pairing, set a unique-enough NBT to the BOTH rings.
 * To check buff, check that nearby player is wearing the ring
 * and that it has the same NBT ID.
 * This will create a rare but pre-known bug of collisions,
 * where two pairs will randomly have chosen the
 * same ID, and so all four work for eachother
 * even though they are two separate pairs.
 * Pairing a previously paired ring will
 * not clear the old partner, it will only
 * cahnge itself and the new partner.
 * 
 * This will require a total rewrite of the item for
 * the next version.
 */