package k2b6s9j.BoatCraft.compatibility.vanilla

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.Mod.EventHandler
import k2b6s9j.BoatCraft.core.registry.MaterialRegistry
import k2b6s9j.BoatCraft.compatibility.vanilla.boat.wood._

@Mod(name = "BoatCraft:Compatibility:Vanilla:Materials:Wood", modid = "BoatCraft:Compatibility:Vanilla:Materials:Wood", modLanguage = "scala", dependencies = "required-before:BoatCraft")
object Mod {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) {
    MaterialRegistry.addMaterial(new Oak {} )
    MaterialRegistry.addMaterial(new Spruce {} )
    MaterialRegistry.addMaterial(new Birch {} )
    MaterialRegistry.addMaterial(new Jungle {} )
    MaterialRegistry.addMaterial(new Acacia {} )
    MaterialRegistry.addMaterial(new DarkOak {} )
  }

}
