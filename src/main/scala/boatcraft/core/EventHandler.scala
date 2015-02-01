package boatcraft.core

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.ExtendedBoat

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
			case boat: EntityCustomBoat if boat.getMaterial isImmuneToFire =>
				event.ammount = 0
		}
	}
}