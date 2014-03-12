package k2b6s9j.boatcraft

import k2b6s9j.boatcraft.api.boat.ItemCustomBoat
import net.minecraft.item.Item
import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

/** The main API used by all BoatCraft Content Modules and Add-ons.
  *
  * This API Package contains all of the methods needed to create new Materials,
  * create new Modifiers, and register anything you create with BoatCraft: Core to implement.
  */
package object api
{
	/**
	 * The function used to retrieve a boat item with
	 * the specified material and modifier
	 * 
     * @param mat the Material
     * @param mat the Modifier
	 * 
	 * @return the custom boat itemstack
	 */
    def getCustomBoat(mat: Material, mod: Modifier): ItemStack =
        getCustomBoat(mat toString, mod toString)
    
    /**
     * The function used to retrieve a boat item with
     * the specified material and modifier
     * 
     * @param mat the Material
     * @param mat the Modifier
     * 
     * @return the custom boat itemstack
     */
    def getCustomBoat(mat: String, mod: String) =
    {
    	var stack = new ItemStack(ItemCustomBoat)
    	stack.stackTagCompound = new NBTTagCompound
        stack.stackTagCompound.setString("material", mat)
        stack.stackTagCompound.setString("modifier", mod)
        
        stack
    }
}