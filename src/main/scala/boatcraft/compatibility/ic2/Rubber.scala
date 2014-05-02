package boatcraft.compatibility.ic2

import boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import ic2.api.item.IC2Items

object Rubber extends Material
{
	override def getTexture = new ResourceLocation("ic2", "textures/models/boatRubber.png")
	override def getName = "Rubber"
	override def getItem = IC2Items getItem "rubber"
	override def getStick = IC2Items getItem "resin"
	//TODO Balance! These are completely meaningless numbers, someone PLEASE balance it!
	override def getCrashResistance = 0.5
	override def getSpeedMultiplier = 2
}