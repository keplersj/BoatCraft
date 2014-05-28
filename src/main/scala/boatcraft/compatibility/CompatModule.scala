package boatcraft.compatibility

import org.apache.logging.log4j.Logger

import boatcraft.api.Registry
import boatcraft.api.modifiers.{Block, Material, Modifier}
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}

abstract class CompatModule {
	
	private[boatcraft] var log: Logger = null

	val code = maxID

	maxID = maxID + 1

	final def registerModifiers() {
		Registry register getMaterials
		Registry register getBlocks
	}

	protected def getMaterials = Array[Material]()

	protected def getBlocks = Array[Block]()

	final def preInit(event: FMLPreInitializationEvent) {
		log = event.getModLog
		doPreInit(event)
	}

	protected def doPreInit(event: FMLPreInitializationEvent) {
	}

	final def init(event: FMLInitializationEvent) {
		doInit(event)
	}

	protected def doInit(event: FMLInitializationEvent) {
	}

	final def postInit(event: FMLPostInitializationEvent) {
		doPostInit(event)
	}

	protected def doPostInit(event: FMLPostInitializationEvent) {
	}
}
