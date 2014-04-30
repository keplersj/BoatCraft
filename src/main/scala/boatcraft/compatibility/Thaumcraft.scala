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

@Mod(modid = "boatcraft:compatibility:thaumcraft",
	name = "BoatCraft Vanilla Compatibility",
	modLanguage = "scala", dependencies = "required-after:boatcraft;after:thaumcraft",
	version = "2.0")
object Thaumcraft
{
	var log: Logger = null

	val boatAspects = (new AspectList).add(Aspect.MOTION, 2).add(Aspect.WATER, 2)

	@Mod.EventHandler
	@Optional.Method(modid = "thaumcraft")
	def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog
		
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
			ThaumcraftApi.registerComplexObjectTag(getCustomBoat(matName, modName), boatAspects)
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