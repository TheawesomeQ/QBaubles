package com.github.QBaubles.items;
/*
 * This is a basic item class which implements name and registry behavior.
 */
import net.minecraft.item.Item;

public class ItemBasic extends Item {
	public ItemBasic(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
	}
}
