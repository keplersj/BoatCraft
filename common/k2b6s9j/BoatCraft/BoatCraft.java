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
	private final String item = "Items"; 
	
	public Oak itemOak;
	public Spruce itemSpruce;
	public Birch itemBirch;
	public Jungle itemJungle;

	@EventHandler
	public void PreInit (FMLPreInitializationEvent event)
	{
		FMLLog.log(Level.INFO, "BoatCraft");
		FMLLog.log(Level.INFO, "Copyright Kepler Sticka-Jones 2013");
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {	
        	itemOak.ID = cfg.getItem(item, 25500, "Oak Boat").getInt(25500);
        	itemSpruce.ID = cfg.getItem(item, 25501, "Spruce Boat").getInt(25501);
        	itemBirch.ID = cfg.getItem(item, 25502, "Birch Boat").getInt(25502);
        	itemJungle.ID = cfg.getItem(item, 25503, "Jungle Boat").getInt(25503);
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
		
	}
	
	public void RegisterRecipes() {
		//Boat Recipes
		CraftingUtilities.RemoveRecipe(new ItemStack(Item.boat));
		CraftingUtilities.RegisterBoatRecipes();
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
	}

	@EventHandler
	public void PostInit (FMLPostInitializationEvent event)
	{

	}

}
