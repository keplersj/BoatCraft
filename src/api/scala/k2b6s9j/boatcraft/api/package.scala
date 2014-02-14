package k2b6s9j.boatcraft

import k2b6s9j.boatcraft.api.Boat
import net.minecraft.item.Item

package object api
{
	def getItemCustomBoat =
		Item.itemRegistry.getObject("boatcraft:customBoat").asInstanceOf[Boat.ItemCustomBoat]
}