package boatcraft.compatibility

import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

class CompatModule(compatModId:String, compatModName:String) {

  val modId = compatModId
  val modName = compatModName

  def preInit(event: FMLPreInitializationEvent) {

  }

  def init(event: FMLInitializationEvent) {

  }

  def postInit(event: FMLPostInitializationEvent) {

  }

}
