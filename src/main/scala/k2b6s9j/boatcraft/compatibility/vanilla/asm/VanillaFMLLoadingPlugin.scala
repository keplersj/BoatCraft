package k2b6s9j.boatcraft.compatibility.vanilla.asm

import java.util.Map

import cpw.mods.fml.common.Mod
import cpw.mods.fml.relauncher.IFMLLoadingPlugin
import k2b6s9j.boatcraft.compatibility.vanilla.Materials

class VanillaFMLLoadingPlugin extends IFMLLoadingPlugin
{
	override def getASMTransformerClass: Array[String] =
		Array(classOf[VanillaClassTransformer] getName)
	
	override def getModContainerClass: String = null
		//Materials getClass() getName
	
	override def getSetupClass: String = null
	
	override def injectData(data: Map[String, AnyRef]) {}

    override def getAccessTransformerClass: String = null
}