package k2b6s9j.boatcraft.compatibility

import java.util.ArrayList
import scala.collection.JavaConversions.{asScalaBuffer, mapAsScalaMap}
import org.apache.logging.log4j.Logger
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.registry.GameRegistry
import k2b6s9j.boatcraft.api.Registry
import k2b6s9j.boatcraft.api.boat.ItemCustomBoat
import k2b6s9j.boatcraft.compatibility.vanilla.VanillaGuiHandler
import k2b6s9j.boatcraft.compatibility.vanilla.materials.crystal.{Diamond, Emerald, Obsidian}
import k2b6s9j.boatcraft.compatibility.vanilla.materials.metal.{Gold, Iron}
import k2b6s9j.boatcraft.compatibility.vanilla.materials.wood.{Acacia, Birch, DarkOak, Jungle, Oak, OreDict_Wood, Spruce}
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.{Furnace, Workbench}
import k2b6s9j.boatcraft.core.BoatCraft
import k2b6s9j.boatcraft.core.modifiers.{DynamicModifier, Empty}
import k2b6s9j.boatcraft.core.utilities.Recipes
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{CraftingManager, IRecipe}
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.ShapedOreRecipe
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.ContainerChest

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

		registerMaterials
		/*// */registerModifiers

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
	
	private def printModInfo
	{
		log info "BoatCraft Vanilla Compatibility"
		log info "Adds Vanilla Woods to the BoatCraft Material Matrix"
		log info "Copyright Kepler Sticka-Jones 2014"
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
		/*Registry register Chest
		Registry register Furnace
		Registry register Workbench*/
		Registry register new DynamicModifier(Blocks.chest, 0,
			classOf[GuiChest].getName, classOf[ContainerChest].getName)
        //Registry register new DynamicModifier(Blocks.furnace, 0)
        //Registry register new DynamicModifier(Blocks.crafting_table, 0)
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
