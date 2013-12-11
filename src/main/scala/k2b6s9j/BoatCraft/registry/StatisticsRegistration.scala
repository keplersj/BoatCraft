package k2b6s9j.BoatCraft.registry

import org.mcstats.Metrics
import k2b6s9j.BoatCraft.utilities.log.ModLogger

object StatisticsRegistration {


    def RegisterMCStats() {
      try {
        var metrics: Metrics = new Metrics("BoatCraft", "2.0")
        metrics.start()
      }
      catch
        {
          case e:Exception => ModLogger.severe("Unable to Submit Statistics")
          case e:Exception => e.printStackTrace()
        }
    }
}
