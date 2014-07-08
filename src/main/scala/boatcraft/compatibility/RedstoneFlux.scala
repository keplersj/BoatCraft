package boatcraft.compatibility

import java.util.Arrays
import boatcraft.api.modifiers.Block
import cpw.mods.fml.common.Optional

object RedstoneFlux extends CompatModule
{
	@Optional.Method(modid = "CoFHAPI|energy")
	override def getBlocks = Arrays.asList(EnergyCell.CELLS) toArray[Block](Array[Block]())
}