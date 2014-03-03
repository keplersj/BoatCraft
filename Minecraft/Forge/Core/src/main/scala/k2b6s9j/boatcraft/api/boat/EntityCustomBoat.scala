package k2b6s9j.boatcraft.api.boat

import net.minecraft.entity.item.EntityBoat
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.entity.player.EntityPlayer
import k2b6s9j.boatcraft.api.Registry
import net.minecraft.item.Item
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import k2b6s9j.boatcraft.api.getItemCustomBoat
import k2b6s9j.boatcraft.api.traits.{Material, Modifier}

//TODO: Fill Documentation
/**
  *
  * @param world
  * @param x
  * @param y
  * @param z
  */
case class EntityCustomBoat(world: World, x: Double, y: Double, z: Double)
	extends EntityBoat(world, x, y, z)
{
	def this(world: World) = this(world, 0, 0, 0 /*, null, null*/ )
	
	override protected def entityInit
	{
		super.entityInit
		dataWatcher addObject (20, "")
		dataWatcher addObject (21, "")
	}
	
	override protected def writeEntityToNBT(tag: NBTTagCompound)
	{
		tag setString ("material", getMaterialName)
		tag setString ("modifier", getModifierName)
	}
	
	override protected def readEntityFromNBT(tag: NBTTagCompound)
	{
		setMaterial(tag getString "material")
		setModifier(tag getString "modifier")
	}
	
	override def onUpdate
	{
		super.onUpdate

		getModifier update this
	}
	
	/*
  override def func_145778_a(item: Item, count: Int, f: Float): EntityItem =
	{
		var stack: ItemStack = new ItemStack(item, count)

		if (item == Items.boat)
		{
			stack = new ItemStack(getItemCustomBoat, count)
			stack.stackTagCompound = new NBTTagCompound
			stack.stackTagCompound setString ("material", getMaterialName)
			stack.stackTagCompound setString ("modifier", getModifierName)
		}
		else if (item == Item.getItemFromBlock(Blocks.planks))
			stack = getMaterial getItem
		else if (item == Items.stick)
		{
			stack = getMaterial getStick

			if (stack == null && rand.nextBoolean) stack = getMaterial getItem
		}
		else return super.func_145778_a(item, count, f)

		if (stack != null) entityDropItem(stack, f)
		else null
	}
	*/
	
	override def interactFirst(player: EntityPlayer) =
	{
		if (getModifier isRideable) super.interactFirst(player)
		getModifier interact (player, this)
		true
	}
	
	override def setDead
	{
		if (!world.isRemote && getModifier.getContent != null)
			entityDropItem(getModifier getContent, 0F)
		super.setDead
	}
	
	//TODO: Fill Documentation
	/**
	  *
	  * @param material
	  */
	def setMaterial(material: String) {
		dataWatcher updateObject (20, material)
	}

	//TODO: Fill Documentation
	/**
	  *
	  * @param modifier
	  */
	def setModifier(modifier: String) {
		dataWatcher updateObject (21, modifier)
	}

	//TODO: Fill Documentation
	/**
	  *
	  * @return
	  */
	def getMaterial: Material =
    (Registry find (dataWatcher getWatchableObjectString 20)).asInstanceOf[Material]

	//TODO: Fill Documentation
	/**
	  *
	  * @return
	  */
	def getModifier: Modifier =
    (Registry find (dataWatcher getWatchableObjectString 21)).asInstanceOf[Modifier]

	//TODO: Fill Documentation
	/**
	  *
	  * @return
	  */
	def getMaterialName =
		dataWatcher getWatchableObjectString 20

	//TODO: Fill Documentation
	/**
	  *
	  * @return
	  */
	def getModifierName =
		dataWatcher getWatchableObjectString 21
}