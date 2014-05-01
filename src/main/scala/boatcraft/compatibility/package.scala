package boatcraft

import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.Loader
import boatcraft.core.BoatCraft
import scala.collection.mutable

package object compatibility {

  val mods = mutable.HashMap[String, CompatModule](
    ("", Vanilla),
    ("IronChest", IronChests),
    ("Thaumcraft", Thaumcraft),
    ("IC2", IC2)
  )

  def preInit(event: FMLPreInitializationEvent) {
    for (module <- mods) {
      if (module._1.isEmpty) {
        module._2.preInit(event)
      }
      else if (Loader.isModLoaded(module._1)) {
        module._2.preInit(event)
      }
      else {
        BoatCraft.log.warn("Could not preInit module:" + module._1)
      }
    }
  }

  def init(event: FMLInitializationEvent) {
    for (module <- mods) {
      if (module._1.isEmpty) {
        module._2.init(event)
      }
      else if (Loader.isModLoaded(module._1)) {
        module._2.init(event)
      }
      else {
        BoatCraft.log.warn("Could not Init module:" + module._1)
      }
    }
  }

  def postInit(event: FMLPostInitializationEvent) {
    for (module <- mods) {
      if (module._1.isEmpty) {
        module._2.postInit(event)
      }
      else if (Loader.isModLoaded(module._1)) {
        module._2.postInit(event)
      }
      else {
        BoatCraft.log.warn("Could not postInit module:" + module._1)
      }
    }
  }

}
