package boatcraft.compatibility

import scala.collection.JavaConversions.mapAsScalaMap

import org.apache.logging.log4j.Logger

import cpw.mods.fml.common.{Mod, Optional}
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import boatcraft.api.{Registry, getCustomBoat}
import net.minecraft.nbt.NBTTagString
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.ThaumcraftApi.EntityTagsNBT
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.{Aspect, AspectList}
import java.util

object Thaumcraft extends CompatModule("Thaumcraft", "Thaumcraft")
{
	var log: Logger = null

	var boatAspects:AnyRef = null

	@Optional.Method(modid = "thaumcraft")
	override def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog

    boatAspects = (new AspectList).add(Aspect.MOTION, 2).add(Aspect.WATER, 2)

		try
		{
			registerAspects
		}
		catch
		{
			case ex: NoClassDefFoundError => //Sure
			case err: NoSuchMethodError => //No problem
			case ex: NoSuchMethodException => //Normal
            case ex: NullPointerException => //If that's how you wanna roll...
			case thr: Throwable => thr printStackTrace //Odd...
		}
	}

	@Optional.Method(modid = "thaumcraft")
	private def registerAspects
	{
		for ((matName, material) <- Registry.materials)
			for ((modName, modifier) <- Registry.modifiers)
		{
			ThaumcraftApi.registerComplexObjectTag(getCustomBoat(matName, modName), boatAspects.asInstanceOf[AspectList])
		}
		for ((matName, material) <- Registry.materials)
			for ((modName, modifier) <- Registry.modifiers)
		{
			ThaumcraftApi.registerEntityTag("customBoat",
				ThaumcraftApiHelper.getObjectAspects(getCustomBoat(matName, modName)),
					new EntityTagsNBT("material", new NBTTagString(matName)),
					new EntityTagsNBT("modifier", new NBTTagString(modName)))
		}
	}
}