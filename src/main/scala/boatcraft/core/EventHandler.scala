package boatcraft.core

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.ExtendedBoat
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing

object EventHandler {
	
	@SubscribeEvent
	def constructEntity(event: EntityConstructing)
	{
		if (!event.entity.isInstanceOf[EntityCustomBoat]) return
		
		var boat = event.entity.asInstanceOf[EntityCustomBoat]
		
		if (boat.getExtendedProperties(ExtendedBoat.NAME) == null)
			boat.registerExtendedProperties(ExtendedBoat.NAME, new ExtendedBoat)
	}
}