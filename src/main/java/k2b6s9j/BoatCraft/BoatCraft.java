package k2b6s9j.BoatCraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import k2b6s9j.BoatCraft.proxy.CommonProxy;
import k2b6s9j.BoatCraft.registry.*;
import k2b6s9j.BoatCraft.utilities.log.ModLogger;

@Mod(modid = BoatCraft.name, name = BoatCraft.name, version = BoatCraft.version, dependencies="after:Forestry;")
@NetworkMod(channels = {"BoatCraft"}, clientSideRequired = true, serverSideRequired = true)

public class BoatCraft {
	@Instance("BoatCraft")
    public static BoatCraft instance;
	
	@SidedProxy(clientSide="k2b6s9j.BoatCraft.proxy.ClientProxy", serverSide="k2b6s9j.BoatCraft.proxy.CommonProxy")
	public static CommonProxy proxy;

    //General Mod Information
    public static final String version = "2.0";
    public static final String name = "BoatCraft";

	@EventHandler
	public void PreInit (FMLPreInitializationEvent event)
	{
        ModLogger.info("BoatCraft");
        ModLogger.info("Copyright Kepler Sticka-Jones 2013");
        ModLogger.info("http://k2b6s9j.com/projects/minecraft/BoatCraft");

        //Configuration Registration
        ConfigRegistry.CreateConfig(event);

        //Item Registration
        ItemRegistry.InitItems();

        RecipeRegistration.RegisterRecipies();

        //Entity Registration
        proxy.registerRenderers();
        EntityRegistry.RegisterEntities();

        //Statistics Registration
        StatisticsRegistration.RegisterMCStats();
	}

	@EventHandler
	public void Init (FMLInitializationEvent event)
	{
        LanguageRegistry.RegisterName();
		
	}

	@EventHandler
	public void PostInit (FMLPostInitializationEvent event)
	{

	}

}
