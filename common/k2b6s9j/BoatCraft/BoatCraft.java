package k2b6s9j.BoatCraft;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "BoatCraft", name = "BoatCraft", version = "1.0.0")
public class BoatCraft {
	@Instance("BoatCraft")
    public static BoatCraft instance;

	@EventHandler
	public void PreInit (FMLPreInitializationEvent event)
	{
		FMLLog.log(Level.INFO, "BoatCraft");
		FMLLog.log(Level.INFO, "Copyright Kepler Sticka-Jones 2013");
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
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
