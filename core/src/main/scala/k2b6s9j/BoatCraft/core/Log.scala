package k2b6s9j.BoatCraft.core

import cpw.mods.fml.common.FMLLog

import java.util.logging.Level
import java.util.logging.Logger

object Log {
    def log: Logger = Logger.getLogger("BoatCraft")

  def info(msg:String) {
        log.log(Level.INFO, msg)
  }

    def warning(msg:String) {
        log.log(Level.WARNING, msg)
    }

    def severe(msg:String) {
        log.log(Level.SEVERE, msg)
    }

    def getLogger() {
        log
    }

    def static() {
        log.setParent(FMLLog.getLogger)
    }
}