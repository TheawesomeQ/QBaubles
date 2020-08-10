package com.github.QBaubles.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class PhotosynthesisAmulet extends ItemBasic implements IBauble {

	public PhotosynthesisAmulet(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		// TODO Auto-generated method stub
		return BaubleType.AMULET;
	}
	
//	long timeprev;
//	long time = 0;
	long tickprev;
	long tick = 0;
	
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if (!player.getEntityWorld().isRemote) {
		if (player.ticksExisted % 200 == 0) {
//			timeprev = time;
//			time = java.lang.System.currentTimeMillis();
			tickprev = tick;
			tick = player.ticksExisted;
			if ((tick - tickprev) >= 200) {
//				System.out.println("Timer ping: PhotosynthesisAmulet \n"
//					+ "Time diff: "+(time-timeprev)+"\n"
//						+ "Tick diff: " + (tick - tickprev));
				if (player instanceof EntityPlayer) {
					if (((EntityPlayer) player).getFoodStats().getFoodLevel()<20 && 
							player.getEntityWorld().getLightFor(EnumSkyBlock.SKY, player.getPosition())>10 &&
							player.getEntityWorld().getWorldTime()>=0 && player.getEntityWorld().getWorldTime()<=12000) {
						((EntityPlayer) player).getFoodStats().setFoodLevel(((EntityPlayer) player).getFoodStats().getFoodLevel()+1);
					}
				}
			}
		}}
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) { 
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			for(int i = 0; i < baubles.getSlots(); i++) 
				if((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
					baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
					if(!player.capabilities.isCreativeMode){
						player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
					}
					onEquipped(player.getHeldItem(hand), player);
					break;
				}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	// Sprinting uses one food in a bit under 10 seconds.
	// I will restore 1 food per 10 seconds.
	// 10 seconds is 200 ticks
}
