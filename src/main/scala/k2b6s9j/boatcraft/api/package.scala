package k2b6s9j.boatcraft

import k2b6s9j.boatcraft.api.Boat
import net.minecraft.item.Item

/** The main API used by all BoatCraft Content Modules and Add-ons.
  *
  * This API Package contains all of the methods needed to create new Materials, create new Modifiers, and register anything you create with BoatCraft: Core to implement.
  */
package object api
{
	def getItemCustomBoat =
		Item.itemRegistry.getObject("boatcraft:customBoat").asInstanceOf[Boat.ItemCustomBoat]
}