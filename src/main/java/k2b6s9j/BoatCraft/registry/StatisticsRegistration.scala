package k2b6s9j.BoatCraft.registry;

import k2b6s9j.BoatCraft.BoatCraft;
import k2b6s9j.BoatCraft.utilities.log.ModLogger;
import org.mcstats.MetricsLite;

public class StatisticsRegistration {

    public static void RegisterMCStats() {
        try {
            MetricsLite metrics = new MetricsLite(BoatCraft.name, BoatCraft.version);
            metrics.start();
        } catch (Exception e) {
            ModLogger.severe("Failed to Submit Stats to MCStats");
        }
    }
}
