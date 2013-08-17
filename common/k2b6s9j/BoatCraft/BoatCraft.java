package k2b6s9j.BoatCraft;

import java.io.IOException;
import java.util.logging.Level;

import k2b6s9j.BoatCraft.entity.item.EntityCustomBoat;
import k2b6s9j.BoatCraft.item.boat.vanilla.wood.Birch;
import k2b6s9j.BoatCraft.item.boat.vanilla.wood.Jungle;
import k2b6s9j.BoatCraft.item.boat.vanilla.wood.Oak;
import k2b6s9j.BoatCraft.item.boat.vanilla.wood.Spruce;
import k2b6s9j.BoatCraft.proxy.CommonProxy;
import k2b6s9j.BoatCraft.utilities.CraftingUtilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import org.mcstats.MetricsLite;

import cpw.mods.fml.common.FMLLog;
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
	
	//Mod Info
	public final String modName = "BoatCraft";
	public final String modVersion = "2.0";
	
	//Configuration File Strings
	private final String vanillaBoat = "Vanilla Boats"; 
	//Config File Strings
	public final String itemBoats = "Boats in Item Form";
	public final String sticks = "Sticks";
	
	public Oak itemOak;
	public Spruce itemSpruce;
	public Birch itemBirch;
	public Jungle itemJungle;
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
	
	public boolean OreDictWoodBoat;

	@EventHandler
	public void PreInit (FMLPreInitializationEvent event)
	{
		FMLLog.log(Level.INFO, "BoatCraft");
		FMLLog.log(Level.INFO, "Copyright Kepler Sticka-Jones 2013");
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {	
        	itemOak.ID = cfg.getItem(vanillaBoat, 25500, "Oak Boat").getInt(25500);
        	itemSpruce.ID = cfg.getItem(vanillaBoat, 25501, "Spruce Boat").getInt(25501);
        	itemBirch.ID = cfg.getItem(vanillaBoat, 25502, "Birch Boat").getInt(25502);
        	itemJungle.ID = cfg.getItem(vanillaBoat, 25503, "Jungle Boat").getInt(25503);
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
        	
        	//Modules
        	this.OreDictWoodBoat = cfg.get("Modules", "OreDictWoodBoats", false, "Use the OreDictionary to craft Wooden Boats").getBoolean(false);
        }
        catch (Exception e)
        {
            FMLLog.log(Level.SEVERE, e, "BoatCraft had a problem loading it's configuration");
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
        	FMLLog.log(Level.SEVERE, e, "BoatCraft had a problem submitting data to MCStats");
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
		CraftingUtilities.RemoveRecipe(new ItemStack(Item.boat));
		CraftingUtilities.RegisterBoatRecipes();
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
		EntityRegistry.registerModEntity(EntityCustomBoat.class, "Boat", 1, this, 80, 3, true);
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
