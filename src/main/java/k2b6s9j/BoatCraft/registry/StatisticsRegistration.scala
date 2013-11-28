package k2b6s9j.BoatCraft.registry

import org.mcstats.MetricsLite
import k2b6s9j.BoatCraft.BoatCraft
import k2b6s9j.BoatCraft.utilities.log.ModLogger

class StatisticsRegistration {

  def mod:BoatCraft
  def log:ModLogger.type

    def RegisterMCStats() {
      try
        MetricsLite.metrics = new MetricsLite(mod.name, mod.version)
        metrics.start()
      catch
        {
          case e:Exception => log.severe("Unable to Submit Statistics")
          case e:Exception => e.printStackTrace()
        }


    }
}
