package boatcraft.core

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.ExtendedBoat
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing
import net.minecraftforge.event.entity.living.LivingHurtEvent

object EventHandler {
	
	@SubscribeEvent
	def constructEntity(event: EntityConstructing)
	{
		if (!event.entity.isInstanceOf[EntityCustomBoat]) return
		
		var boat = event.entity.asInstanceOf[EntityCustomBoat]
		
		if (boat.getExtendedProperties(ExtendedBoat.NAME) == null)
			boat.registerExtendedProperties(ExtendedBoat.NAME, new ExtendedBoat)
	}
	
	@SubscribeEvent
	def hurtEntity(event: LivingHurtEvent)
	{
		if (event.source.isFireDamage && event.entityLiving.ridingEntity != null)
			event.entityLiving.ridingEntity match
		{
			case boat: EntityCustomBoat if boat.getMaterial isFireResist =>
				event.ammount = 0
		}
	}
}