package boatcraft.core.asm

import java.util.Map
import cpw.mods.fml.relauncher.IFMLLoadingPlugin
import boatcraft.core.BoatCraft

//TODO: Fill Documentation
/**
 *
 */
class BoatCraftFMLLoadingPlugin extends IFMLLoadingPlugin
{
  //TODO: Fill Documentation
  /**
   *
   * @return
   */
  override def getASMTransformerClass =
		Array(classOf[BoatCraftClassTransformer] getName)

  //TODO: Fill Documentation
  /**
   *
   * @return
   */
	override def getModContainerClass = BoatCraft.getClass getName

  //TODO: Fill Documentation
  /**
   *
   * @return
   */
	override def getSetupClass: String = null

  //TODO: Fill Documentation
  /**
   *
   * @param data
   */
	override def injectData(data: Map[String, AnyRef]) {}

  //TODO: Fill Documentation
  /**
   *
   * @return
   */
  override def getAccessTransformerClass: String = null
}