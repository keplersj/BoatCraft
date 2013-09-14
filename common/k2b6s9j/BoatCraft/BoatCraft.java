package k2b6s9j.BoatCraft;

import java.io.IOException;
import java.util.logging.Level;

import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirch;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchChest;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchTNT;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungle;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleChest;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleTNT;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOak;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakChest;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakTNT;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruce;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceChest;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceTNT;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirchChest;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirchFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirchHopper;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirchTNT;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungleChest;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungleFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungleHopper;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungleTNT;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOak;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakChest;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakHopper;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakTNT;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruce;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceChest;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceHopper;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceTNT;
import k2b6s9j.BoatCraft.proxy.CommonProxy;
import k2b6s9j.BoatCraft.utilities.CraftingUtilities;
import net.minecraft.block.Block;
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

@Mod(modid = "BoatCraft", name = "BoatCraft", version = "2.0", dependencies="after:Forestry;")
@NetworkMod(channels = {"BoatCraft"}, clientSideRequired = true, serverSideRequired = true)

public class BoatCraft {
	@Instance("BoatCraft")
    public static BoatCraft instance;
	
	@SidedProxy(clientSide="k2b6s9j.BoatCraft.proxy.ClientProxy", serverSide="k2b6s9j.BoatCraft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	//Mod Info
	public final String modName = "BoatCraft";
	public final String modVersion = "2.0-ForestryWood";
	
	//Config File Strings
	public final String itemBoats = "Boats in Item Form";
	
	//Wooden Based Boat Items
	///Oak Wood Based
	public BoatOak oakBoat;
	public BoatOakChest oakChestBoat;
	public BoatOakFurnace oakFurnaceBoat;
	public BoatOakHopper oakHopperBoat;
	public BoatOakTNT oakTntBoat;
	///Spruce Wood Based
	public BoatSpruce spruceBoat;
	public BoatSpruceChest spruceChestBoat;
	public BoatSpruceFurnace spruceFurnaceBoat;
	public BoatSpruceHopper spruceHopperBoat;
	public BoatSpruceTNT spruceTNTBoat;
	///Birch Wood Based
	public BoatBirch birchBoat;
	public BoatBirchChest birchChestBoat;
	public BoatBirchFurnace birchFurnaceBoat;
	public BoatBirchHopper birchHopperBoat;
	public BoatBirchTNT birchTNTBoat;
	///Jungle Wood Based
	public BoatJungle jungleBoat;
	public BoatJungleChest jungleChestBoat;
	public BoatJungleFurnace jungleFurnaceBoat;
	public BoatJungleHopper jungleHopperBoat;
	public BoatJungleTNT jungleTNTBoat;
	///Forestry Wood Boats
	////Larch Wood Based
	////Teak Wood Based
	////Acacia Wood Based
	////Lime Wood Based
	////Chestnut Wood Based
	////Wenge Wood Based
	////Baobob Wood Based
	////Sequoia Wood Based
	////Kapok Wood Based
	////Ebony Wood Based
	////Mahogany Wood Based
	////Balsa Wood Based
	////Willow Wood Based
	////Walnut Wood Based
	////Greenheart Wood Based
	////Cherry Wood Based
	////Mahoe Wood Based
	////Poplar Wood Based
	////Palm Wood Based
	////Papaya Wood Based
	////Pine Wood Based
	////Plum Wood Based
	////Maple Wood Based
	////Citrus Wood Based
	
	public boolean OreDictWoodBoat;

	@EventHandler
	public void PreInit (FMLPreInitializationEvent event)
	{
		FMLLog.log(Level.INFO, "BoatCraft");
		FMLLog.log(Level.INFO, "Copyright Kepler Sticka-Jones 2013");
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
        	//Wooden Based Boats
        	///Oak Wood Based
        	oakBoat.ID = cfg.getItem(itemBoats, "Oak Boat", 25500).getInt(25500);
        	oakChestBoat.ID = cfg.getItem(itemBoats, "Oak Chest Boat", 25501).getInt(25501);
        	oakFurnaceBoat.ID = cfg.getItem(itemBoats, "Oak Furnace Boat", 25502).getInt(25502);
        	oakHopperBoat.ID = cfg.getItem(itemBoats, "Oak Hopper Boat", 25503).getInt(25503);
        	oakTntBoat.ID = cfg.getItem(itemBoats, "Oak TNT Boat", 25504).getInt(25504);
        	///Spruce Wood Based
        	spruceBoat.ID = cfg.getItem(itemBoats, "Spruce Boat", 25505).getInt(25505);
        	spruceChestBoat.ID = cfg.getItem(itemBoats, "Spruce Chest Boat", 25506).getInt(25506);
        	spruceFurnaceBoat.ID = cfg.getItem(itemBoats, "Spruce Furnace Boat", 25507).getInt(25507);
        	spruceHopperBoat.ID = cfg.getItem(itemBoats, "Spruce Hopper Boat", 25508).getInt(25508);
        	spruceTNTBoat.ID = cfg.getItem(itemBoats, "Spruce TNT Boat", 25509).getInt(25509);
        	///Birch Wood Based
        	birchBoat.ID = cfg.getItem(itemBoats, "Birch Boat", 25510).getInt(25510);
        	birchChestBoat.ID = cfg.getItem(itemBoats, "Birch Chest Boat", 25511).getInt(25511);
        	birchFurnaceBoat.ID = cfg.getItem(itemBoats, "Birch Furnace Boat", 25512).getInt(25512);
        	birchHopperBoat.ID = cfg.getItem(itemBoats, "Birch Hopper Boat", 25513).getInt(25513);
        	birchTNTBoat.ID = cfg.getItem(itemBoats, "Birch TNT Boat", 25514).getInt(25514);
        	///Jungle Wood Based
        	jungleBoat.ID = cfg.getItem(itemBoats, "Jungle Boat", 25515).getInt(25515);
        	jungleChestBoat.ID = cfg.getItem(itemBoats, "Jungle Chest Boat", 25516).getInt(25516);
        	jungleFurnaceBoat.ID = cfg.getItem(itemBoats, "Jungle Furnace Boat", 25517).getInt(25517);
        	jungleHopperBoat.ID = cfg.getItem(itemBoats, "Jungle Hopper Boat", 25518).getInt(25518);
        	jungleTNTBoat.ID = cfg.getItem(itemBoats, "Jungle TNT Boat", 25519).getInt(25519);
        	///Forestry Wood Boats
    		////Larch Wood Based
    		////Teak Wood Based
    		////Acacia Wood Based
    		////Lime Wood Based
    		////Chestnut Wood Based
    		////Wenge Wood Based
    		////Baobob Wood Based
    		////Sequoia Wood Based
    		////Kapok Wood Based
    		////Ebony Wood Based
    		////Mahogany Wood Based
    		////Balsa Wood Based
    		////Willow Wood Based
    		////Walnut Wood Based
    		////Greenheart Wood Based
    		////Cherry Wood Based
    		////Mahoe Wood Based
    		////Poplar Wood Based
    		////Palm Wood Based
    		////Papaya Wood Based
    		////Pine Wood Based
    		////Plum Wood Based
    		////Maple Wood Based
    		////Citrus Wood Based
        	
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
		OreDictionary.registerOre("boat", Item.boat);
		
		//Wooden Based Boats
		///Oak Based
		oakBoat = new BoatOak(oakBoat.ID);
		oakChestBoat = new BoatOakChest(oakChestBoat.ID);
		oakFurnaceBoat = new BoatOakFurnace(oakFurnaceBoat.ID);
		oakHopperBoat = new BoatOakHopper(oakHopperBoat.ID);
		oakTntBoat = new BoatOakTNT(oakTntBoat.ID);
		///Spruce Based
		spruceBoat = new BoatSpruce(spruceBoat.ID);
		spruceChestBoat = new BoatSpruceChest(spruceChestBoat.ID);
		spruceFurnaceBoat = new BoatSpruceFurnace(spruceFurnaceBoat.ID);
		spruceHopperBoat = new BoatSpruceHopper(spruceHopperBoat.ID);
		spruceTNTBoat = new BoatSpruceTNT(spruceTNTBoat.ID);
		///Birch Based
		birchBoat = new BoatBirch(birchBoat.ID);
		birchChestBoat = new BoatBirchChest(birchChestBoat.ID);
		birchFurnaceBoat = new BoatBirchFurnace(birchFurnaceBoat.ID);
		birchHopperBoat = new BoatBirchHopper(birchHopperBoat.ID);
		birchTNTBoat = new BoatBirchTNT(birchTNTBoat.ID);
		///Jungle Wood Based
		jungleBoat = new BoatJungle(jungleBoat.ID);
		jungleChestBoat = new BoatJungleChest(jungleChestBoat.ID);
		jungleFurnaceBoat = new BoatJungleFurnace(jungleFurnaceBoat.ID);
		jungleHopperBoat = new BoatJungleHopper(jungleHopperBoat.ID);
		jungleTNTBoat = new BoatJungleTNT(jungleTNTBoat.ID);
		///Forestry Wood Boats
		////Larch Wood Based
		////Teak Wood Based
		////Acacia Wood Based
		////Lime Wood Based
		////Chestnut Wood Based
		////Wenge Wood Based
		////Baobob Wood Based
		////Sequoia Wood Based
		////Kapok Wood Based
		////Ebony Wood Based
		////Mahogany Wood Based
		////Balsa Wood Based
		////Willow Wood Based
		////Walnut Wood Based
		////Greenheart Wood Based
		////Cherry Wood Based
		////Mahoe Wood Based
		////Poplar Wood Based
		////Palm Wood Based
		////Papaya Wood Based
		////Pine Wood Based
		////Plum Wood Based
		////Maple Wood Based
		////Citrus Wood Based
	}
	
	public void RegisterRecipes() {
		//Boat Recipes
		if (!OreDictWoodBoat) {
			//Wooden Based Boats
			CraftingUtilities.RemoveRecipe(new ItemStack(Item.boat));
			///Oak Wood Based
	        GameRegistry.addRecipe(new ItemStack(oakBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 0));
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(oakChestBoat), new ItemStack(Block.chest), "boatOak");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(oakFurnaceBoat), new ItemStack(Block.furnaceIdle), "boatOak");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(oakHopperBoat), new ItemStack(Block.tnt), "boatOak");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(oakTntBoat), new ItemStack(Block.hopperBlock), "boatOak");
	        ///Spruce Wood Based
	        GameRegistry.addRecipe(new ItemStack(spruceBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 1));
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(spruceChestBoat), new ItemStack(Block.chest), "boatSpruce");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(spruceFurnaceBoat), new ItemStack(Block.furnaceIdle), "boatSpruce");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(spruceHopperBoat), new ItemStack(Block.tnt), "boatSpruce");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(spruceTNTBoat), new ItemStack(Block.hopperBlock), "boatSpruce");
	        ///Birch Wood Based
	        GameRegistry.addRecipe(new ItemStack(birchBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 2));
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(birchChestBoat), new ItemStack(Block.chest), "boatBirch");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(birchFurnaceBoat), new ItemStack(Block.furnaceIdle), "boatBirch");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(birchHopperBoat), new ItemStack(Block.tnt), "boatBirch");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(birchTNTBoat), new ItemStack(Block.hopperBlock), "boatBirch");
	        ///Jungle Wood Based
	        GameRegistry.addRecipe(new ItemStack(jungleBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 3));
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(jungleChestBoat), new ItemStack(Block.chest), "boatJungle");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(jungleFurnaceBoat), new ItemStack(Block.furnaceIdle), "boatJungle");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(jungleHopperBoat), new ItemStack(Block.tnt), "boatJungle");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(jungleTNTBoat), new ItemStack(Block.hopperBlock), "boatJungle");
	      ///Forestry Wood Boats
			////Larch Wood Based
			////Teak Wood Based
			////Acacia Wood Based
			////Lime Wood Based
			////Chestnut Wood Based
			////Wenge Wood Based
			////Baobob Wood Based
			////Sequoia Wood Based
			////Kapok Wood Based
			////Ebony Wood Based
			////Mahogany Wood Based
			////Balsa Wood Based
			////Willow Wood Based
			////Walnut Wood Based
			////Greenheart Wood Based
			////Cherry Wood Based
			////Mahoe Wood Based
			////Poplar Wood Based
			////Palm Wood Based
			////Papaya Wood Based
			////Pine Wood Based
			////Plum Wood Based
			////Maple Wood Based
			////Citrus Wood Based
		}
		if (OreDictWoodBoat) {
			CraftingUtilities.RemoveRecipe(new ItemStack(Item.boat));
			CraftingUtilities.AddRecipe(new ItemStack(oakBoat), "W W", "WWW", Character.valueOf('W'), "plankWood");
			CraftingUtilities.AddShapelessRecipe(new ItemStack(oakChestBoat), new ItemStack(Block.chest), "boat");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(oakFurnaceBoat), new ItemStack(Block.furnaceIdle), "boat");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(oakHopperBoat), new ItemStack(Block.tnt), "boat");
	        CraftingUtilities.AddShapelessRecipe(new ItemStack(oakTntBoat), new ItemStack(Block.hopperBlock), "boat");
		}
	}
	
	public void EntityWork() {
		proxy.registerRenderers();
		//Wooden Based Boats
		///Oak Wood Based
		EntityRegistry.registerModEntity(EntityBoatOak.class, "Oak Wood Boat", 1, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatOakChest.class, "Oak Wood Chest Boat", 2, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatOakFurnace.class, "Oak Wood Furnace Boat", 3, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatOakHopper.class, "Oak Wood Hopper Boat", 4, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatOakTNT.class, "Oak Wood TNT Boat", 5, this, 80, 3, true);
		///Spruce Wood Based
		EntityRegistry.registerModEntity(EntityBoatSpruce.class, "Spruce Wood Boat", 6, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatSpruceChest.class, "Spruce Wood Chest Boat", 7, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatSpruceFurnace.class, "Spruce Wood Furnace Boat", 8, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatSpruceHopper.class, "Spruce Wood Hopper Boat", 9, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatSpruceTNT.class, "Spruce Wood TNT Boat", 10, this, 80, 3, true);
		///Birch Wood Based 
		EntityRegistry.registerModEntity(EntityBoatBirch.class, "Birch Wood Boat", 11, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatBirchChest.class, "Birch Wood Chest Boat", 12, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatBirchFurnace.class, "Birch Wood Furnace Boat", 13, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatBirchHopper.class, "Birch Wood Hopper Boat", 14, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatBirchTNT.class, "Birch Wood TNT Boat", 15, this, 80, 3, true);
		///Jungle Wood Based
		EntityRegistry.registerModEntity(EntityBoatJungle.class, "Jungle Wood Boat", 16, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatJungleChest.class, "Jungle Wood Chest Boat", 17, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatJungleFurnace.class, "Jungle Wood Furnace Boat", 18, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatJungleHopper.class, "Jungle Wood Hopper Boat", 19, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBoatJungleTNT.class, "Jungle Wood TNT Boat", 20, this, 80, 3, true);
		///Forestry Wood Boats
		////Larch Wood Based
		////Teak Wood Based
		////Acacia Wood Based
		////Lime Wood Based
		////Chestnut Wood Based
		////Wenge Wood Based
		////Baobob Wood Based
		////Sequoia Wood Based
		////Kapok Wood Based
		////Ebony Wood Based
		////Mahogany Wood Based
		////Balsa Wood Based
		////Willow Wood Based
		////Walnut Wood Based
		////Greenheart Wood Based
		////Cherry Wood Based
		////Mahoe Wood Based
		////Poplar Wood Based
		////Palm Wood Based
		////Papaya Wood Based
		////Pine Wood Based
		////Plum Wood Based
		////Maple Wood Based
		////Citrus Wood Based
	}

	@EventHandler
	public void Init (FMLInitializationEvent event)
	{
		LanguageRegistry.addName(Item.boat, "Vanilla Boat");
		
		//Boats
		if (!OreDictWoodBoat) {
			//Wooden Based Boats
			///Oak Wood Based
			LanguageRegistry.addName(oakBoat, "Oak Wood Boat");
			LanguageRegistry.addName(oakChestBoat, "Oak Wood Chest Boat");
			LanguageRegistry.addName(oakFurnaceBoat, "Oak Wood Furnace Boat");
			LanguageRegistry.addName(oakHopperBoat, "Oak Wood Hopper Boat");
			LanguageRegistry.addName(oakTntBoat, "Oak Wood TNT Boat");
			///Spruce Wood Based
			LanguageRegistry.addName(spruceBoat, "Spruce Wood Boat");
			LanguageRegistry.addName(spruceChestBoat, "Spruce Wood Chest Boat");
			LanguageRegistry.addName(spruceFurnaceBoat, "Spruce Wood Furnace Boat");
			LanguageRegistry.addName(spruceHopperBoat, "Spruce Wood Hopper Boat");
			LanguageRegistry.addName(spruceTNTBoat, "Spruce Wood TNT Boat");
			///Birch Wood Based
			LanguageRegistry.addName(birchBoat, "Birch Wood Boat");
			LanguageRegistry.addName(birchChestBoat, "Birch Wood Chest Boat");
			LanguageRegistry.addName(birchFurnaceBoat, "Birch Wood Furnace Boat");
			LanguageRegistry.addName(birchHopperBoat, "Birch Wood Hopper Boat");
			LanguageRegistry.addName(birchTNTBoat, "Birch Wood TNT Boat");
			///Jungle Wood Based
			LanguageRegistry.addName(jungleBoat, "Jungle Wood Boat");
			LanguageRegistry.addName(jungleChestBoat, "Jungle Wood Chest Boat");
			LanguageRegistry.addName(jungleFurnaceBoat, "Jungle Wood Furnace Boat");
			LanguageRegistry.addName(jungleHopperBoat, "Jungle Wood Hopper Boat");
			LanguageRegistry.addName(jungleTNTBoat, "Jungle Wood TNT Boat");
			///Forestry Wood Boats
			////Larch Wood Based
			////Teak Wood Based
			////Acacia Wood Based
			////Lime Wood Based
			////Chestnut Wood Based
			////Wenge Wood Based
			////Baobob Wood Based
			////Sequoia Wood Based
			////Kapok Wood Based
			////Ebony Wood Based
			////Mahogany Wood Based
			////Balsa Wood Based
			////Willow Wood Based
			////Walnut Wood Based
			////Greenheart Wood Based
			////Cherry Wood Based
			////Mahoe Wood Based
			////Poplar Wood Based
			////Palm Wood Based
			////Papaya Wood Based
			////Pine Wood Based
			////Plum Wood Based
			////Maple Wood Based
			////Citrus Wood Based
		}
		if (OreDictWoodBoat) {
			LanguageRegistry.addName(oakBoat, "Wooden Boat");
			LanguageRegistry.addName(oakChestBoat, "Chest Boat");
			LanguageRegistry.addName(oakFurnaceBoat, "Furnace Boat");
			LanguageRegistry.addName(oakHopperBoat, "Hopper Boat");
			LanguageRegistry.addName(oakTntBoat, "TNT Boat");
		}
		
	}

	@EventHandler
	public void PostInit (FMLPostInitializationEvent event)
	{

	}

}
