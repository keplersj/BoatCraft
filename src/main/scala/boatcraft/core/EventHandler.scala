package boatcraft.core

import net.minecraftforge.event.entity.EntityEvent.EntityConstructing
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraft.entity.player.EntityPlayer
import boatcraft.api.modifiers.ExtendedBoat
import boatcraft.api.boat.EntityCustomBoat

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