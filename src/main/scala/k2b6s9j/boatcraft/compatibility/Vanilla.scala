package k2b6s9j.boatcraft.compatibility

import org.apache.logging.log4j.Logger
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import k2b6s9j.boatcraft.compatibility.vanilla.materials.wood.{Acacia, Birch, DarkOak, Jungle, Oak, Spruce}
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.Chest
import k2b6s9j.boatcraft.core.BoatCraft
import k2b6s9j.boatcraft.core.registry.{MaterialRegistry, ModifierRegistry}
import k2b6s9j.boatcraft.core.utilities.Recipes
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import cpw.mods.fml.common.network.NetworkRegistry
import k2b6s9j.boatcraft.compatibility.vanilla.VanillaGuiHandler
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.Workbench
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.Furnace
import k2b6s9j.boatcraft.compatibility.vanilla.materials.metal.Iron
import k2b6s9j.boatcraft.compatibility.vanilla.materials.metal.Gold

@Mod(modid = "boatcraft:compatibility:vanilla",
	name = "BoatCraft Vanilla Compatibility",
	modLanguage = "scala", dependencies = "required-after:boatcraft",
	version = "2.0")
object Vanilla
{
	var log: Logger = null
	
	@EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event getModLog

		printModInfo

		registerMaterials
		registerModifiers
		
		replaceBoatRecipe
		
		NetworkRegistry.INSTANCE registerGuiHandler(this, new VanillaGuiHandler)
	}
	
	private def printModInfo
	{
		log info "BoatCraft Vanilla Compatibility"
		log info "Adds Vanilla Woods to the BoatCraft Material Matrix"
		log info "Copyright Kepler Sticka-Jones 2014"
	}
	
	private def registerMaterials
	{
		MaterialRegistry addMaterial new Oak
		MaterialRegistry addMaterial new Spruce
		MaterialRegistry addMaterial new Birch
		MaterialRegistry addMaterial new Jungle
		MaterialRegistry addMaterial new Acacia
		
		MaterialRegistry addMaterial new Iron
		MaterialRegistry addMaterial new Gold
	}
	
	private def registerModifiers
	{
		ModifierRegistry addModifier new Chest
		ModifierRegistry addModifier new Furnace
		ModifierRegistry addModifier new Workbench
	}
	
	private def replaceBoatRecipe
	{
		Recipes removeRecipe new ItemStack(Items.boat)
		
		var stack = new ItemStack(BoatCraft.itemBoat)
		
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString("material", "oak")
		stack.stackTagCompound setString("modifier", "empty")
		
		GameRegistry addShapelessRecipe(stack, Items.boat)
	}
}
