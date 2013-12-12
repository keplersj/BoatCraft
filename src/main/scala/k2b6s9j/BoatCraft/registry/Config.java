package k2b6s9j.BoatCraft.registry;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;

public class Config {

    public static Configuration CreateConfig (FMLPreInitializationEvent event) {
        return new Configuration(event.getSuggestedConfigurationFile());
    }

}
