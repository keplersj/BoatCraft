package k2b6s9j.boatcraft.compatibility.microblock.boat

import k2b6s9j.boatcraft.api.boat.ItemCustomBoat
import net.minecraft.item.{Item, ItemStack}
import codechicken.microblock.MicroMaterialRegistry.IMicroMaterial
import codechicken.microblock.{MicroMaterialRegistry, MicroblockClassRegistry}
import net.minecraft.util.StatCollector
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.nbt.NBTTagCompound
import codechicken.microblock.MicroblockClassRegistry._

abstract class ItemMicroblockBoat extends ItemCustomBoat {
  setUnlocalizedName("microblockBoat")
  setHasSubtypes(true)

  override def getItemStackDisplayName(stack:ItemStack):String =
  {
    val material = ItemMicroblockBoat.getMaterial(stack)
    val mcrClass = MicroblockClassRegistry.getMicroClass(stack.getItemDamage)
    val size = stack.getItemDamage&0xFF
    if(material == null || mcrClass == null)
      return "Unnamed"

    StatCollector.translateToLocalFormatted(mcrClass.getName+"."+size+".name", material.getLocalizedName)
  }

  def getSubItems(item:Item, tab:CreativeTabs, list$:List[_])
  {
    val list = List.asInstanceOf[List[ItemStack]]
    for(classId <- 0 until classes.length)
    {
      val mcrClass = classes(classId)
      if(mcrClass != null)
        for(size <- Seq(1, 2, 4))
          MicroMaterialRegistry.getIdMap.foreach(e => list.+:(ItemMicroblockBoat.create(classId<<8|size, e._1)))
    }
  }

  override def registerIcons(register:IIconRegister){}
}

object ItemMicroblockBoat extends ItemCustomBoat {
  def checkTagCompound(stack:ItemStack)
  {
    if(!stack.hasTagCompound)
      stack.setTagCompound(new NBTTagCompound())
  }

  def create(damage:Int, material:Int):ItemStack = create(damage, MicroMaterialRegistry.materialName(material))

  def create(damage:Int, material:String):ItemStack = create(1, damage, material)

  def create(amount:Int, damage:Int, material:String):ItemStack =
  {
    val stack = new ItemStack(ItemCustomBoat, amount, damage)
    checkTagCompound(stack)
    stack.getTagCompound.setString("mat", material)
    return stack
  }

  def getMaterial(stack:ItemStack):IMicroMaterial =
  {
    checkTagCompound(stack)
    if(!stack.getTagCompound.hasKey("mat"))
      return null

    return MicroMaterialRegistry.getMaterial(stack.getTagCompound.getString("mat"))
  }

  def getMaterialID(stack:ItemStack):Int =
  {
    checkTagCompound(stack)
    if(!stack.getTagCompound.hasKey("mat"))
      return 0

    return MicroMaterialRegistry.materialID(stack.getTagCompound.getString("mat"))
  }
}
