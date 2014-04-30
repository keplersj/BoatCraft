package boatcraft.compatibility

import java.util.ArrayList

import scala.collection.JavaConversions.{asScalaBuffer, mapAsScalaMap}

import org.apache.logging.log4j.Logger

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.registry.GameRegistry
import boatcraft.api.Registry
import boatcraft.api.boat.ItemCustomBoat
import boatcraft.compatibility.vanilla.VanillaGuiHandler
import boatcraft.compatibility.vanilla.materials.crystal._
import boatcraft.compatibility.vanilla.materials.metal._
import boatcraft.compatibility.vanilla.materials.wood._
import boatcraft.compatibility.vanilla.modifiers._
import boatcraft.core.BoatCraft
import boatcraft.core.modifiers.Empty
import boatcraft.core.utilities.Recipes
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{CraftingManager, IRecipe}
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.ShapedOreRecipe

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

		registerMaterials
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
				&& recipe.asInstanceOf[IRecipe].getRecipeOutput.stackTagCompound.getString("material")
				!= null
				&& recipe.asInstanceOf[IRecipe].getRecipeOutput.stackTagCompound.getString("material")
				.equals(OreDict_Wood toString)) {
				toRemove add recipe.asInstanceOf[IRecipe]
				log info "Removed non-oredict ore-dict wood boat recipe: " + recipe
			}
		}

		CraftingManager.getInstance.getRecipeList removeAll toRemove
	}
	
	private def registerMaterials
	{
		if (!useOreDictWood)
		{
			Registry register Oak
			Registry register Spruce
			Registry register Birch
			Registry register Jungle
			Registry register Acacia
			Registry register DarkOak
		}
		else
		{
			Registry register OreDict_Wood

			var stack = new ItemStack(ItemCustomBoat)
			stack.stackTagCompound = new NBTTagCompound
			stack.stackTagCompound setString ("material", OreDict_Wood toString)

			for ((name, modifier) <- Registry.modifiers) {
				stack.stackTagCompound setString ("modifier", name)
				if (modifier.getContent != null)
					GameRegistry.addRecipe(new ShapedOreRecipe(stack,
						"wmw", "www",
						Character valueOf 'w', "planksWood",
						Character valueOf 'm', modifier getContent))
				else
					GameRegistry.addRecipe(new ShapedOreRecipe(stack,
						"w w", "www",
						Character valueOf 'w', "planksWood"))
			}
		}

		Registry register Iron
		Registry register Gold

		Registry register Obsidian
		Registry register Diamond
		Registry register Emerald
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
		stack.stackTagCompound setString ("material", Oak toString)
		stack.stackTagCompound setString ("modifier", Empty toString)
		
		GameRegistry addShapelessRecipe (stack, Items.boat)
	}
	
	private def readConfig
	{
		useOreDictWood = BoatCraft.config get ("Vanilla.General", "useOreDictWoods", false,
			"If set to true, the different wood types will not be generated.\n" +
			"Instead, there will be only one \"wood\" Boat") getBoolean false

		if (BoatCraft.config hasChanged)
			BoatCraft.config save
	}
}
