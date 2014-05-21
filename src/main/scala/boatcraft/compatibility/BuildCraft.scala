package boatcraft.compatibility

import boatcraft.api.modifiers.Block
import boatcraft.compatibility.buildcraft.modifiers.blocks.Tank
import cpw.mods.fml.common.Optional

object BuildCraft extends CompatModule {
	
	@Optional.Method(modid = "BuildCraft")
	override protected def getBlocks = Array[Block](
			Tank)
}