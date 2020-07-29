package com.github.QBaubles;
import com.github.QBaubles.init.ModItems;

/*
 * This is the main mod file. This class could be split into two files, separating the
 * Mod meta-information (name, id, version, etc), but I followed the forge example mod's
 * approach.
 * 
 * This is the tutorial I'm loosely following:
 * https://suppergerrie2.com/minecraft-1-12-modding-with-forge-3-custom-item/
 */
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

// This @ tag tells forge meta information about the mod and indicates this is the main class of the mod
@Mod(modid = QBaublesMod.MODID, name = QBaublesMod.NAME, version = QBaublesMod.VERSION)
public class QBaublesMod {
    public static final String MODID = "qbaubles";
    public static final String NAME = "Q's Baubles";
    public static final String VERSION = "0.1";
    
    // These @ tags are used to indicate each init function to forge
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	System.out.println(MODID + ":preInit");
    	
    	// Initialize custom items for this mod
    	ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

}
