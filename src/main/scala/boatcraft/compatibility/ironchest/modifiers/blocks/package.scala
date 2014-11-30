package boatcraft.compatibility.ironchest.modifiers

import cpw.mods.ironchest.IronChestType
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import boatcraft.compatibility.ironchest.packets.IronChestSyncMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import boatcraft.compatibility.ironchest.packets.IronChestSyncMessage
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import net.minecraftforge.fml.common.FMLCommonHandler
import boatcraft.api.boat.EntityCustomBoat

package object blocks {
	
	object Iron_Chest extends GenericIronChest(IronChestType.IRON)

	object Gold_Chest extends GenericIronChest(IronChestType.GOLD)

	object Diamond_Chest extends GenericIronChest(IronChestType.DIAMOND)

	object Copper_Chest extends GenericIronChest(IronChestType.COPPER)

	object Silver_Chest extends GenericIronChest(IronChestType.SILVER)

	object Crystal_Chest extends GenericIronChest(IronChestType.CRYSTAL)

	object Obsidian_Chest extends GenericIronChest(IronChestType.OBSIDIAN)

	object DirtChest9000 extends GenericIronChest(IronChestType.DIRTCHEST9000)
	
	val chests = Array[GenericIronChest](
			Iron_Chest, Gold_Chest, Diamond_Chest, Copper_Chest, Silver_Chest,
			Crystal_Chest, Obsidian_Chest, DirtChest9000)
}
