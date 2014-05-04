package boatcraft.compatibility.industrialcraft2.materials

import boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import ic2.api.item.IC2Items

object Carbon extends Material {
	override def getTexture = new ResourceLocation("ic2", "textures/models/boatCarbon.png")

	override def getName = "Carbon Fibre"

	override def getItem = IC2Items getItem "carbonFiber"

	override def getStick = IC2Items getItem "coalDust"

	//TODO Balance! These are completely meaningless numbers, someone PLEASE balance it!
	override def getCrashResistance = 2
}