package boatcraft.compatibility

/*import scala.collection.JavaConversions.mapAsScalaMap
import boatcraft.api.{Registry, getCustomBoat}
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraft.nbt.NBTTagString
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.ThaumcraftApi.EntityTagsNBT
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.{Aspect, AspectList}
import net.minecraftforge.fml.common.Optional*/

object Thaumcraft extends CompatModule {
	
	/*var boatAspects: AnyRef = null
	
	@Optional.Method(modid = "Thaumcraft")
	override protected def doPreInit(e: FMLPreInitializationEvent) {
		boatAspects = (new AspectList).add(Aspect.MOTION, 2).add(Aspect.WATER, 2)

		registerAspects()
	}
	
	@Optional.Method(modid = "Thaumcraft")
	private def registerAspects() {
		for ((matName, material) <- Registry.materials)
			for ((blockName, block) <- Registry.blocks) {
				ThaumcraftApi.registerComplexObjectTag(getCustomBoat(matName, blockName), boatAspects.asInstanceOf[AspectList])
			}
		for ((matName, material) <- Registry.materials)
			for ((blockName, block) <- Registry.blocks) {
				ThaumcraftApi.registerEntityTag("customBoat",
					ThaumcraftApiHelper.getObjectAspects(getCustomBoat(matName, blockName)),
					new EntityTagsNBT("material", new NBTTagString(matName)),
					new EntityTagsNBT("block", new NBTTagString(blockName)))
			}
	}*/
}