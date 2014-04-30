package boatcraft

import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.Loader

package object compatibility {

  val mods = List[CompatModule](
    Vanilla,
    IronChests,
    Thaumcraft,
    IC2
  )

  def preInit(event: FMLPreInitializationEvent) {
    for (module <- mods) {
      if (Loader isModLoaded module.modName)
        module.preInit(event)
    }
  }

  def init(event: FMLInitializationEvent) {
    for (module <- mods) {
      if (Loader isModLoaded module.modName)
        module.init(event)
    }
  }

  def postInit(event: FMLPostInitializationEvent) {
    for (module <- mods) {
      if (Loader isModLoaded module.modName)
        module.postInit(event)
    }

  }

}
