package boatcraft.compatibility

import scala.collection.JavaConversions.mapAsScalaMap

import boatcraft.api.{Registry, getCustomBoat}
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.nbt.NBTTagString
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.ThaumcraftApi.EntityTagsNBT
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.{Aspect, AspectList}

object Thaumcraft extends CompatModule
{
	var boatAspects: AnyRef = null
	
	override protected def doPreInit(e: FMLPreInitializationEvent)
	{
		boatAspects = (new AspectList).add(Aspect.MOTION, 2).add(Aspect.WATER, 2)
		
		registerAspects
	}
	
	private def registerAspects
	{
		for ((matName, material) <- Registry.materials)
			for ((blockName, block) <- Registry.blocks)
		{
			ThaumcraftApi.registerComplexObjectTag(getCustomBoat(matName, blockName), boatAspects.asInstanceOf[AspectList])
		}
		for ((matName, material) <- Registry.materials)
			for ((blockName, block) <- Registry.blocks)
		{
			ThaumcraftApi.registerEntityTag("customBoat",
				ThaumcraftApiHelper.getObjectAspects(getCustomBoat(matName, blockName)),
					new EntityTagsNBT("material", new NBTTagString(matName)),
					new EntityTagsNBT("block", new NBTTagString(blockName)))
		}
	}
}