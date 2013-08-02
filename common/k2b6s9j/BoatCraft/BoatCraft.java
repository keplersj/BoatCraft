package k2b6s9j.BoatCraft;

import java.util.logging.Level;

import k2b6s9j.BoatCraft.item.boat.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.BoatOak;
import k2b6s9j.BoatCraft.item.boat.BoatSpruce;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "BoatCraft", name = "BoatCraft", version = "1.0.0")
public class BoatCraft {
	@Instance("BoatCraft")
    public static BoatCraft instance;
	
	//Config File Strings
	public final String itemBoats = "Boats in Item Form";
	
	//Boat Items
	public BoatBirch birchBoat;
	public BoatJungle jungleBoat;
	public BoatOak oakBoat;
	public BoatSpruce spruceBoat;

	@EventHandler
	public void PreInit (FMLPreInitializationEvent event)
	{
		FMLLog.log(Level.INFO, "BoatCraft");
		FMLLog.log(Level.INFO, "Copyright Kepler Sticka-Jones 2013");
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
        	birchBoat.ID = cfg.getItem(itemBoats, "Birch Boat", 25500).getInt(25500);
        	jungleBoat.ID = cfg.getItem(itemBoats, "Jungle Boat", 25501).getInt(25501);
        	oakBoat.ID = cfg.getItem(itemBoats, "Oak Boat", 25502).getInt(25502);
        	spruceBoat.ID = cfg.getItem(itemBoats, "Spruce Boat", 25503).getInt(25503);
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
        birchBoat = new BoatBirch(birchBoat.ID);
        jungleBoat = new BoatJungle(jungleBoat.ID);
        oakBoat = new BoatOak(oakBoat.ID);
        spruceBoat = new BoatSpruce(spruceBoat.ID);
	}

	@EventHandler
	public void Init (FMLInitializationEvent event)
	{
		
	}

	@EventHandler
	public void PostInit (FMLPostInitializationEvent event)
	{

	}

}
