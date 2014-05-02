package boatcraft.compatibility

import cpw.mods.fml.common.event._

abstract class CompatModule
{
	val code = maxID
	
	maxID = maxID + 1
	
	def preInit(event: FMLPreInitializationEvent)
	{
	}

	def init(event: FMLInitializationEvent)
	{
	}

	def postInit(event: FMLPostInitializationEvent)
	{
	}
}
