package boatcraft.api.traits

import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound

abstract class Modifier {

	/**
		* The human-readable name of this Boat.
		* The `@link Modifier#toString` will make it computer-readable
		*
		* @return the name of the Modifier
		*/
	@deprecated("All modifiers must set both localized and unlocalized strings now.", "BoatCraft 2.0.1")
	def getName: String = getUnlocalizedName

	/**
		* The name used by `@link Modifier#toString` for computer-reading
		*
		* @return the unlocalized string
		*/
	def getUnlocalizedName: String = ""

	/**
		* The human-readable name of the Boat.
		*
		* @return the localization string.
		*/
	def getLocalizedName: String = getUnlocalizedName

	/**
		* This function allows a material to be faster or slower than normal
		*
		* @return the multiplier to apply to the speed of the boat
		*/
	def getSpeedMultiplier = 1.0

	/**
		* This function allows a material to act more resistant or more fragile
		*
		* @return the multiplier to apply to the boat's default crash resistance
		*/
	def getCrashResistance = 1.0

	/**
		* Opens the corresponding GUI for this Modifier, if any
		*
		* @param player the player interacting with the Boat
		* @param boat the Boat being interacted with
		*/
	def interact(player: EntityPlayer, boat: EntityCustomBoat) {}

	/**
		* Update the modifier's logic
		*
		* @param boat the boat being updated
		*/
	def update(boat: EntityCustomBoat) {}

	/**
		* Save the modifier's state to NBT
		*
		* @param boat the boat entity NBT data is being written to
		* @param tag the NBT data tag being written
		*/
	def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) {}

	/**
		* Load the modifier's state from NBT
		*
		* @param boat the boat entity NBT data is being read from
		* @param tag the NBT data tag being read
		*/
	def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) {}

	/**
		* Method is overridden to return the Modifier's actual name.
		*
		* @return name of the Material
		*/
	override def toString = (getUnlocalizedName replaceAll (" ", "")).toLowerCase

}
