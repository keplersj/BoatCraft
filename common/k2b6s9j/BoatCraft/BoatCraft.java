package k2b6s9j.BoatCraft;

import java.io.IOException;
import java.util.logging.Logger;

import k2b6s9j.BoatCraft.blocks.BlockBoatBuilder;
import k2b6s9j.BoatCraft.entity.item.EntityBirchWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntityBoatChest;
import k2b6s9j.BoatCraft.entity.item.EntityBoatFurnace;
import k2b6s9j.BoatCraft.entity.item.EntityBoatHopper;
import k2b6s9j.BoatCraft.entity.item.EntityBoatTNT;
import k2b6s9j.BoatCraft.entity.item.EntityJungleWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntityOakWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntitySpruceWoodBoat;
import k2b6s9j.BoatCraft.item.boat.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.BoatChest;
import k2b6s9j.BoatCraft.item.boat.BoatFurnace;
import k2b6s9j.BoatCraft.item.boat.BoatHopper;
import k2b6s9j.BoatCraft.item.boat.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.BoatOak;
import k2b6s9j.BoatCraft.item.boat.BoatSpruce;
import k2b6s9j.BoatCraft.item.boat.BoatTNT;
import k2b6s9j.BoatCraft.items.ItemAnchor;
import k2b6s9j.BoatCraft.items.ItemBoatKayak;
import k2b6s9j.BoatCraft.items.ItemRope;
import k2b6s9j.BoatCraft.proxy.CommonProxy;
import k2b6s9j.BoatCraft.utilities.CraftingUtilities;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import org.mcstats.MetricsLite;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "BoatCraft", name = "BoatCraft", version = "2.0")
@NetworkMod(channels = {"BoatCraft"}, clientSideRequired = true, serverSideRequired = true)

public class BoatCraft {
	@Instance("BoatCraft")
    public static BoatCraft instance;
	
	@SidedProxy(clientSide="k2b6s9j.BoatCraft.proxy.ClientProxy", serverSide="k2b6s9j.BoatCraft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	//Mod Info
	public final String modName = "BoatCraft";
	public final String modVersion = "2.0-SeaCraft";
	
	//Config File Strings
	public final String itemBoats = "Boats in Item Form";
	public final String sticks = "Sticks";
	
	//Boat Items
	public BoatBirch birchBoat;
	public BoatJungle jungleBoat;
	public BoatOak oakBoat;
	public BoatSpruce spruceBoat;
	
	//Special Boats
	public BoatChest chestBoat;
	public BoatFurnace furnaceBoat;
	public BoatHopper hopperBoat;
	public BoatTNT tntBoat;
	
	// items
	public static ItemBoatKayak kayak;
	public static ItemAnchor anchor;
	public static ItemAnchor anchor_noRope;
	public static ItemRope rope;
	
	// blocks
	public static BlockBoatBuilder builder;
	
	public boolean OreDictWoodBoat;

	@EventHandler
	public void PreInit (FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		
		logger.info("BoatCraft");
		logger.info("Copyright Kepler Sticka-Jones 2013");
		
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
        	//Boats
        	birchBoat.ID = cfg.getItem(itemBoats, "Birch Boat", 25500).getInt(25500);
        	jungleBoat.ID = cfg.getItem(itemBoats, "Jungle Boat", 25501).getInt(25501);
        	oakBoat.ID = cfg.getItem(itemBoats, "Oak Boat", 25502).getInt(25502);
        	spruceBoat.ID = cfg.getItem(itemBoats, "Spruce Boat", 25503).getInt(25503);
        	
        	//Special Boats
        	chestBoat.ID = cfg.getItem(itemBoats, "Chest Boat", 25508).getInt(25508);
        	furnaceBoat.ID = cfg.getItem(itemBoats, "Furnace Boat", 25509).getInt(25509);
        	hopperBoat.ID = cfg.getItem(itemBoats, "Hopper Boat", 25510).getInt(25510);
        	tntBoat.ID = cfg.getItem(itemBoats, "TNT Boat", 25511).getInt(25511);
        	
        	//SeaCraft Block
        	builder.ID = cfg.getBlock("Blocks", "boatBuilder", 4000).getInt(4000);
        	
        	//SeaCraft Items
        	kayak.ID = cfg.getItem("SeaCraft Items", "Kayak", 25512).getInt(25512);
			anchor.ID = cfg.getItem("SeaCraft Items", "Anchor", 25513).getInt(25513);
			anchor_noRope.ID = cfg.getItem("SeaCraft Items", "Anchor_NoRope", 25514).getInt(25514);
			rope.ID = cfg.getItem("SeaCraft Items", "Rope", 25515).getInt(25515);
        	
        	//Modules
        	this.OreDictWoodBoat = cfg.get("Modules", "OreDictWoodBoats", false, "Use the OreDictionary to craft Wooden Boats").getBoolean(false);
        }
        catch (Exception e)
        {
            logger.severe("BoatCraft had a problem loading it's configuration");
        }
        finally
        {
            if (cfg.hasChanged())
                cfg.save();
        }
        InitItems();
        RegisterRecipes();
        EntityWork();
        try {
            MetricsLite metrics = new MetricsLite(this.modName, this.modVersion);
            metrics.start();
        } catch (IOException e) {
        	logger.severe("BoatCraft had a problem submitting data to MCStats");
        }
	}
	
	public void InitItems() {
		OreDictionary.registerOre("itemBoat", Item.boat);
		
		//Boats
		oakBoat = new BoatOak(oakBoat.ID);
		spruceBoat = new BoatSpruce(spruceBoat.ID);
		birchBoat = new BoatBirch(birchBoat.ID);
		jungleBoat = new BoatJungle(jungleBoat.ID);
		
		//Special Boats
		chestBoat = new BoatChest(chestBoat.ID);
		furnaceBoat = new BoatFurnace(furnaceBoat.ID);
		hopperBoat = new BoatHopper(hopperBoat.ID);
		tntBoat = new BoatTNT(tntBoat.ID);
	}
	
	public void RegisterRecipes() {
		//Boat Recipes
		if (!OreDictWoodBoat) {
			CraftingUtilities.RemoveRecipe(new ItemStack(Item.boat));
	        GameRegistry.addRecipe(new ItemStack(oakBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 0));
	        GameRegistry.addRecipe(new ItemStack(spruceBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 1));
	        GameRegistry.addRecipe(new ItemStack(birchBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 2));
	        GameRegistry.addRecipe(new ItemStack(jungleBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 3));
		}
		if (OreDictWoodBoat) {
			CraftingUtilities.RemoveRecipe(new ItemStack(Item.boat));
			CraftingUtilities.AddRecipe(new ItemStack(oakBoat), "W W", "WWW", Character.valueOf('W'), "plankWood");
		}
        
        //Special Boat Recipes
        CraftingUtilities.AddShapelessRecipe(new ItemStack(chestBoat), new ItemStack(Block.chest), "itemBoat");
        CraftingUtilities.AddShapelessRecipe(new ItemStack(furnaceBoat), new ItemStack(Block.furnaceIdle), "itemBoat");
        CraftingUtilities.AddShapelessRecipe(new ItemStack(tntBoat), new ItemStack(Block.tnt), "itemBoat");
        CraftingUtilities.AddShapelessRecipe(new ItemStack(hopperBoat), new ItemStack(Block.hopperBlock), "itemBoat");
	}
	
	public void EntityWork() {
		proxy.registerRenderers();
		// Regular Boats
		EntityRegistry.registerModEntity(EntityOakWoodBoat.class, "Oak Wood Boat", 1, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpruceWoodBoat.class, "Spruce Wood Boat", 2, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBirchWoodBoat.class, "Birch Wood Boat", 3, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityJungleWoodBoat.class, "Jungle Wood Boat", 4, this, 80, 3, true);
		
		//Special Boats
		EntityRegistry.registerModEntity(EntityBoatChest.class, "Chest Boat", 5, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatFurnace.class, "Furnace Boat", 6, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatHopper.class, "Hopper Boat", 7, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatTNT.class, "TNT Boat", 8, this, 80, 3, true);
		
	}

	@EventHandler
	public void Init (FMLInitializationEvent event)
	{
		LanguageRegistry.addName(Item.boat, "Vanilla Boat");
		
		//Boats
		if (!OreDictWoodBoat) {
			LanguageRegistry.addName(oakBoat, "Oak Wood Boat");
			LanguageRegistry.addName(spruceBoat, "Spruce Wood Boat");
			LanguageRegistry.addName(birchBoat, "Birch Wood Boat");
			LanguageRegistry.addName(jungleBoat, "Jungle Wood Boat");
		}
		if (OreDictWoodBoat) {
			LanguageRegistry.addName(oakBoat, "Wooden Boat");
		}
		
		//Special Boats
		LanguageRegistry.addName(chestBoat, "Chest Boat");
		LanguageRegistry.addName(furnaceBoat, "Furnace Boat");
		LanguageRegistry.addName(hopperBoat, "Hopper Boat");
		LanguageRegistry.addName(tntBoat, "TNT Boat");
	}

	@EventHandler
	public void PostInit (FMLPostInitializationEvent event)
	{

	}

}
