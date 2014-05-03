package boatcraft

import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.Loader
import boatcraft.core.BoatCraft
import scala.collection.mutable

package object compatibility
{
	var maxID = 0
	
	val mods = mutable.HashMap[String, CompatModule](
			("", Vanilla),
			("IronChest", IronChests),
			("Thaumcraft", Thaumcraft),
			("IC2", IC2))
	
	def addMaterialsAndModifiers
	{
		for (module <- mods)
		{
			if (module._1.isEmpty || Loader.isModLoaded(module._1))
				module._2.addMaterialsAndModifiers
			else BoatCraft.log warn "Could not add Materials and Modifiers for module:" + module._1
		}
	}

	def preInit(event: FMLPreInitializationEvent)
	{
		for (module <- mods)
		{
			if (module._1.isEmpty || Loader.isModLoaded(module._1))
				module._2 preInit event
			else BoatCraft.log warn "Could not preInit module:" + module._1
		}
	}

	def init(event: FMLInitializationEvent)
	{
		for (module <- mods)
		{
			if (module._1.isEmpty || Loader.isModLoaded(module._1))
				module._2 init event
			else BoatCraft.log warn "Could not Init module:" + module._1
		}
	}

	def postInit(event: FMLPostInitializationEvent)
	{
		for (module <- mods)
		{
			if (module._1.isEmpty || Loader.isModLoaded(module._1))
				module._2 postInit event
			else BoatCraft.log warn "Could not postInit module:" + module._1
		}
	}

}
