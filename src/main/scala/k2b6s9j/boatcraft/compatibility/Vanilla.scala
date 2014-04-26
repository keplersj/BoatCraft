package k2b6s9j.boatcraft.compatibility

import java.util.ArrayList
import scala.collection.JavaConversions.{asScalaBuffer, mapAsScalaMap}
import org.apache.logging.log4j.Logger
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.registry.GameRegistry
import k2b6s9j.boatcraft.api.Registry
import k2b6s9j.boatcraft.compatibility.vanilla.VanillaGuiHandler
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.{Chest, Furnace, Workbench}
import k2b6s9j.boatcraft.core.BoatCraft
import k2b6s9j.boatcraft.core.utilities.Recipes
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{CraftingManager, IRecipe}
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.ShapedOreRecipe
import k2b6s9j.boatcraft.api.boat.ItemCustomBoat
import k2b6s9j.boatcraft.core.modifiers.Empty

//TODO: Fill Documentation
/**
  *
  */
@Mod(modid = "boatcraft:compatibility:vanilla",
	name = "BoatCraft Vanilla Compatibility",
	modLanguage = "scala", dependencies = "required-after:boatcraft",
	version = "2.0")
object Vanilla
{
	private[boatcraft] var log: Logger = null
	
	private var useOreDictWood = false
	
	@Mod.EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event getModLog

		readConfig

		printModInfo

		registerModifiers

		replaceBoatRecipe

		NetworkRegistry.INSTANCE registerGuiHandler (this, VanillaGuiHandler)
	}
	
	@Mod.EventHandler
	def postInit(event: FMLPostInitializationEvent)
	{
		var toRemove = new ArrayList[IRecipe]

		for (recipe <- CraftingManager.getInstance getRecipeList)
		{
			if (recipe.isInstanceOf[IRecipe]
				&& !recipe.isInstanceOf[ShapedOreRecipe]
				&& recipe.asInstanceOf[IRecipe].getRecipeOutput != null
				&& recipe.asInstanceOf[IRecipe].getRecipeOutput.stackTagCompound != null
				&& recipe.asInstanceOf[IRecipe].getRecipeOutput.stackTagCompound.getString("material") != null) {
				toRemove add recipe.asInstanceOf[IRecipe]
				log info "Removed Vanilla Boat Recipe: " + recipe
			}
		}

		CraftingManager.getInstance.getRecipeList removeAll toRemove
	}
	
	private def printModInfo
	{
		log info "BoatCraft Vanilla Compatibility"
		log info "Adds Vanilla Woods to the BoatCraft Material Matrix"
		log info "Copyright Kepler Sticka-Jones 2014"
	}
	
	private def registerModifiers
	{
		Registry register Chest
		Registry register Furnace
		Registry register Workbench
	}
	
	private def replaceBoatRecipe
	{
		Recipes removeRecipe new ItemStack(Items.boat)
		
		var stack = new ItemStack(ItemCustomBoat)
		
		stack.stackTagCompound = new NBTTagCompound
		//Default Boat Material will be made of Oak Wood Planks when the ForgeMultiPart upgrade is completed.
		//stack.stackTagCompound setString ("material", Oak toString)
		stack.stackTagCompound setString ("modifier", Empty toString)
		
		GameRegistry addShapelessRecipe (stack, Items.boat)
	}
	
	private def readConfig
	{
		if (BoatCraft.config hasChanged)
			BoatCraft.config save
	}
}
