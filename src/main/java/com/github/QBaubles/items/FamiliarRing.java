package com.github.QBaubles.items;

import com.github.QBaubles.entity.EntityFamiliar;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FamiliarRing extends ItemBasic implements IBauble {

	public FamiliarRing(String name) {
		super(name);
		this.setMaxStackSize(1);
		MinecraftForge.EVENT_BUS.register(new FamiliarEventHandler());
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		// TODO Auto-generated method stub
		return BaubleType.RING;
	}
	
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);

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
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
}

// Still have to make this server side only

class FamiliarEventHandler {
	@SubscribeEvent
	public void wearerHit(LivingDamageEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer && (
				event.getSource().getTrueSource() != null
				)) {
			// Check that the familiar ring is equipped
			IBaublesItemHandler bauble = BaublesApi.getBaublesHandler((EntityPlayer) event.getEntityLiving());
			boolean isEquipped = false;
			for (int i=0; i < bauble.getSlots(); i++) {
				if(bauble.getStackInSlot(i).getItem() instanceof FamiliarRing) {
					isEquipped = true;
					break;
				}
			}

			
			if (isEquipped) {
				// Check that a familiar isn't already spawned
				// If not, summon a familiar with correct target and owner.
				//System.out.println("UUID:" + ((EntityPlayer) event.getEntityLiving()).getGameProfile().getId());
				if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
					EntityFamiliar familiar = new EntityFamiliar(event.getEntityLiving().getEntityWorld());
					familiar.setPosition(event.getEntityLiving().posX, event.getEntityLiving().posY,
							event.getEntityLiving().posZ);
					familiar.setOwnerId(event.getEntityLiving().getPersistentID());
					familiar.setTamedBy((EntityPlayer) event.getEntityLiving());
					//System.out.println("TrueSource: " + event.getSource().getTrueSource().getName());

					familiar.setAttackTarget((EntityLivingBase) event.getSource().getTrueSource());
					familiar.setRevengeTarget(event.getEntityLiving().getRevengeTarget());
					event.getEntityLiving().getEntityWorld().spawnEntity(familiar);
				}
			}
		}
	}
}