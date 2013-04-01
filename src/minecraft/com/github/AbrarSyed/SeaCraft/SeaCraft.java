package com.github.AbrarSyed.SeaCraft;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.github.AbrarSyed.SeaCraft.blocks.BlockBoatBuilder;
import com.github.AbrarSyed.SeaCraft.blocks.TileEntityBoatBuilder;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatChest;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatKayak;
import com.github.AbrarSyed.SeaCraft.items.ItemAnchor;
import com.github.AbrarSyed.SeaCraft.items.ItemBoatKayak;
import com.github.AbrarSyed.SeaCraft.items.ItemRope;
import com.github.AbrarSyed.SeaCraft.network.HandlerClient;
import com.github.AbrarSyed.SeaCraft.network.HandlerServer;
import com.github.AbrarSyed.SeaCraft.network.PacketSCBase;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = true,
		clientPacketHandlerSpec = @SidedPacketHandler(channels = { PacketSCBase.CHANNEL }, packetHandler = HandlerClient.class),
		serverPacketHandlerSpec = @SidedPacketHandler(channels = { PacketSCBase.CHANNEL }, packetHandler = HandlerServer.class))
@Mod(modid = SeaCraft.MODID, version = SeaCraft.VERSION, name = SeaCraft.MODID)
public class SeaCraft
{
	public static final String		MODID	= "SeaCraft";
	public static final String		VERSION	= "0.0.1";

	@Instance(value = MODID)
	public static SeaCraft			instance;

	@SidedProxy(serverSide = "com.github.AbrarSyed.SeaCraft.ProxyServer", clientSide = "com.github.AbrarSyed.SeaCraft.client.ProxyClient")
	public static ProxyServer		proxy;

	public static Logger			logger;

	// items
	public static ItemBoatKayak		kayak;
	public static ItemAnchor		anchor;
	public static ItemAnchor		anchor_noRope;
	public static ItemRope			rope;

	// blocks
	public static BlockBoatBuilder	builder;

	public static CreativeTabBoats	tab;

	private int[]					ids;

	@PreInit
	public void preLoad(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		ids = new int[]
				{
				config.getBlock("Blocks", "BoatBuilder", 4000).getInt(),
				config.getItem("Items", "Kayak", 9000+256).getInt(),
				config.getItem("Items", "Anchor", 9001+256).getInt(),
				config.getItem("Items", "Anchor_NoRope", 9002+256).getInt(),
				config.getItem("Items", "Rope", 9003+256).getInt()
				};
		
		if (config.hasChanged())
			config.save();

	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		// creative tab
		tab = new CreativeTabBoats();

		// blocks
		builder = new BlockBoatBuilder(ids[0], Material.iron);

		// items.
		kayak = new ItemBoatKayak(ids[1]);
		anchor = new ItemAnchor(ids[2], true);
		anchor_noRope = new ItemAnchor(ids[3], false);
		rope = new ItemRope(ids[4]);

		// registrations
		GameRegistry.registerItem(kayak, "SeaCraft_Kayak", MODID);
		GameRegistry.registerItem(anchor, "SeaCraft_Anchor", MODID);
		GameRegistry.registerItem(anchor_noRope, "SeaCraft_Anchor_NoRope", MODID);
		GameRegistry.registerItem(rope, "SeaCraft_Hitch", MODID);
		GameRegistry.registerBlock(builder, "SeaCraft_BoatBuilder");

		// localizations
		LanguageRegistry.addName(kayak, "Kayak");
		LanguageRegistry.addName(anchor, "Anchor");
		LanguageRegistry.addName(rope, "Hitch");
		LanguageRegistry.addName(builder, "Boat Builder");
		LanguageRegistry.instance().addStringLocalization("SeaCraft.boats.furnace", "Furnace Boat");
		LanguageRegistry.instance().addStringLocalization("SeaCraft.boats.chest", "Chest Boat");
		LanguageRegistry.instance().addStringLocalization("SeaCraft.boatbuilder", "Boat Builder");

		// entities.
		int ID = 1;
		EntityRegistry.registerModEntity(EntityBoatFurnace.class, "SeaCraft_Furnace", ID++, instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatKayak.class, "SeaCraft_Kayak", ID++, instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatChest.class, "SeaCraft_Chest", ID++, instance, 80, 3, true);

		// TileEntities
		GameRegistry.registerTileEntity(TileEntityBoatBuilder.class, "SeaCraft_BoatBuilder");

		// renderring stuff
		proxy.registerRenderStuff();

		// keybinding
		proxy.registerKeyBindings();

		// tick handler
		TickRegistry.registerTickHandler(new TickHandlerBoatHunger(), Side.SERVER);

		// the gui handler
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

		// recipes
		IRecipe recipe = new ShapedOreRecipe(new ItemStack(builder, 1), new Object[] {
				"X0X",
				"0@0",
				"X0X",
				'X', "plankWood",
				'0', Item.ingotIron,
				'@', Block.workbench
		});
		GameRegistry.addRecipe(recipe);

		GameRegistry.addRecipe(new ItemStack(rope, 1), new Object[] {
				"XX",
				"XX",
				"XX",
				'X', Item.silk
		});

		GameRegistry.addRecipe(new ItemStack(anchor_noRope, 1), new Object[] {
				" X ",
				" X ",
				"X@X",
				'X', Item.ingotIron,
				'@', Block.blockSteel
		});

		GameRegistry.addShapelessRecipe(new ItemStack(anchor, 1), new Object[] {
				rope,
				anchor_noRope
		});
	}
}
