package boatcraft.compatibility.ironchest

import cpw.mods.ironchest.IronChestType

package object blocks
{
	object Copper_Chest extends GenericIronChest(IronChestType.COPPER)

	object Crystal_Chest extends GenericIronChest(IronChestType.CRYSTAL)

	object Diamond_Chest extends GenericIronChest(IronChestType.DIAMOND)

	object DirtChest9000 extends GenericIronChest(IronChestType.DIRTCHEST9000)

	object Gold_Chest extends GenericIronChest(IronChestType.GOLD)

	object Iron_Chest extends GenericIronChest(IronChestType.IRON)

	object Silver_Chest extends GenericIronChest(IronChestType.SILVER)

	object Obsidian_Chest extends GenericIronChest(IronChestType.OBSIDIAN)

}
