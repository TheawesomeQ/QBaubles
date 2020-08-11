package com.github.QBaubles.init;

import com.github.QBaubles.QBaublesMod;
import com.github.QBaubles.entity.EntityStickbug;
import com.github.QBaubles.entity.render.RenderStickbug;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = QBaublesMod.MODID)
public class ModEntities {
	static EntityEntry stickbug;
	public static void init() {
		stickbug = EntityEntryBuilder.create()
				.entity(EntityStickbug.class)
				.factory(EntityStickbug::new)
				.id(new ResourceLocation("stickbug"), 420)
				.name("stickbug")
				.egg(0xb08e4a, 0xbdab35)
				.tracker(50, 5, false)
				.build();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityStickbug.class, new IRenderFactory<EntityStickbug>() {

			@Override
			public Render<? super EntityStickbug> createRenderFor(RenderManager manager) {
				// TODO Auto-generated method stub
				return new RenderStickbug(manager);
			}
			
		});
	}
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().registerAll(stickbug);
	}
}

//https://bedrockminer.jimdofree.com/modding-tutorials/basic-modding-1-8/proxies/
//https://forums.minecraftforge.net/topic/60373-112-help-with-registering-entity-into-game/
//https://forums.minecraftforge.net/topic/61054-112-registering-entities/
//https://forums.minecraftforge.net/topic/42512-random-entity-id/
// https://youtu.be/p6FFl3yN18M
//https://blockbench.net/2019/10/02/minecraft-modeling-texturing-tips/
//https://github.com/MinecraftForge/MinecraftForge/commit/f2b07e8db1e2a6cf3a5b47393783bb367f2afdb2
/*
 * Adding a new entity:
 * Create an entity class which extends (directly or indirectly) the Entity class.
 * 
 */