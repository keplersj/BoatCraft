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

@Mod(name = "boatcraft:compatibility:vanilla:materials:wood",
	modid = "BoatCraft Compatibility, Vanilla Materials, Wood",
	modLanguage = "scala", dependencies = "required-after:boatcraft")
object Vanilla
{
	var log: Logger = null

	@EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event.getModLog

		printModInfo

		registerMaterials
		registerModifiers
		
		replaceBoatRecipe
		
		NetworkRegistry.INSTANCE registerGuiHandler(this, new VanillaGuiHandler)
	}

	private def printModInfo
	{
		log info "BoatCraft:Compatibility:Vanilla:Materials:Wood"
		log info "Adds Vanilla Woods to the BoatCraft Material Matrix"
		log info "Copyright Kepler Sticka-Jones 2014"
	}

	private def registerMaterials
	{
		MaterialRegistry addMaterial new Oak {}
		MaterialRegistry addMaterial new Spruce {}
		MaterialRegistry addMaterial new Birch {}
		MaterialRegistry addMaterial new Jungle {}
		MaterialRegistry addMaterial new Acacia {}
		MaterialRegistry addMaterial new DarkOak {}
	}

	private def registerModifiers
	{
		ModifierRegistry addModifier new Chest
	}
	
	private def replaceBoatRecipe
	{
		Recipes.removeRecipe(new ItemStack(Items.boat))
		
		var stack = new ItemStack(BoatCraft.itemBoat)
		
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString("material", "oak")
		stack.stackTagCompound setString("modifier", "empty")
		
		GameRegistry addShapelessRecipe(stack, Items.boat)
	}
}
