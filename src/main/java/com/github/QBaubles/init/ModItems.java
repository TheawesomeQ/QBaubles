package com.github.QBaubles.init;

import com.github.QBaubles.QBaublesMod;
import com.github.QBaubles.items.BasicStealthRing;
import com.github.QBaubles.items.ItemBasic;
import com.github.QBaubles.items.ItemRing;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * This class instantiates and registers custom items.
 */
// This @ tag marks this class as one which listens to forge events and can handle them using the @SubscribeEvent tag on its methods
@Mod.EventBusSubscriber(modid = QBaublesMod.MODID)
public class ModItems {
	static Item basicstealthring;
	
	// This method will be called to initialize the items specified in this file
	public static void init() {
		basicstealthring = new BasicStealthRing("basicstealthring"); //new ItemRing("basicstealthring").setCreativeTab(CreativeTabs.MISC);// new ItemBasic("basicstealthring").setCreativeTab(CreativeTabs.MISC);
	}
	
	// This @ tag is used to assign event handlers for forge events. This one triggers during the registerItems event
	// Its argument is the event type it is handling: an item type registry event
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(basicstealthring);
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(basicstealthring);
	}
	
	private static void registerRender(Item item) {
		//https://forums.minecraftforge.net/topic/36459-modelresourcelocation-parameters/
		ModelLoader.setCustomModelResourceLocation(basicstealthring, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
