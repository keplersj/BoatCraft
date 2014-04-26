package k2b6s9j.boatcraft.core.modifiers

import java.io.IOException
import java.net.URI
import java.util.Arrays
import java.util.Locale
import java.util.logging.Logger
import java.lang.Iterable
import javax.tools.Diagnostic
import javax.tools.DiagnosticCollector
import javax.tools.JavaCompiler
import javax.tools.JavaCompiler.CompilationTask
import javax.tools.JavaFileObject
import javax.tools.SimpleJavaFileObject
import javax.tools.StandardJavaFileManager
import javax.tools.ToolProvider
import javax.tools.JavaFileObject.Kind
import scala.collection.JavaConversions._
import k2b6s9j.boatcraft.core.BoatCraft

/**
  * A test class to test dynamic compilation API.
  *
  */
class Thingy(val TEName: String)
{
	final var logger = Logger.getLogger(classOf[Thingy] getName)
	
	final val newName =
		TEName.substring(TEName.lastIndexOf(".") + 1) + "BoatInv"
	
	/**Java source code to be compiled dynamically*/
	val sourceCode =
		"package k2b6s9j.boatcraft.core.modifiers.dynamic;\n" +
		"\n" +
		"import k2b6s9j.boatcraft.api.boat.EntityCustomBoat;\n" +
		"import net.minecraft.entity.player.EntityPlayer;\n" +
		"\n" +
		"public class " + newName + " extends " + TEName + "\n" +
		"{\n" +
		    "public EntityCustomBoat boat;\n" +
		    "\n" +
		    "public " + newName + "(EntityCustomBoat boat)\n" +
		        "{this.boat = boat; worldObj = boat.worldObj;}\n" +
             "\n" +
             "@Override\n" +
             "public String getInventoryName()\n" +
             "{\n" +
                 "return \"" + newName.replaceAllLiterally("BoatInv", "") + "\";\n" +
             "}\n" +
             "\n" +
             "@Override\n" +
             "public boolean hasCustomInventoryName()\n" +
             "{\n" +
                 "return false;\n" +
             "}\n" +
             "\n" +
             "@Override\n" +
             "public boolean isUseableByPlayer(EntityPlayer player)\n" +
             "{\n" +
                 "return (player.getDistanceSqToEntity(boat)) <= 64;\n" +
             "}\n" +
             "\n" +
             "@Override\n" +
             "public void openInventory()\n" +
             "{\n" +
             "}\n" +
             "\n" +
             "@Override\n" +
             "public void closeInventory()\n" +
             "{\n" +
             "}\n" +
		"}"
	
	/**
	  * Does the required object initialization and compilation.
	  */
	def doCompilation
	{
	    BoatCraft.log info sourceCode
		/*Creating dynamic java source code file object*/
		val fileObject = new DynamicJavaSourceCodeObject(
				"k2b6s9j.boatcraft.core.modifiers.dynamic." + newName,
				sourceCode)
		/*Instantiating the java compiler*/
		var compiler = ToolProvider.getSystemJavaCompiler

		/* Prepare a list of compilation units (java source code file objects) to input to compilation task*/
		val compilationUnits = Arrays asList fileObject

		/*Create a diagnostic controller, which holds the compilation problems*/
		var diagnostics = new DiagnosticCollector[JavaFileObject]

		/*Create a compilation task from compiler by passing in the required input objects prepared above*/
		var compilerTask = compiler.getTask(null, null, diagnostics, null, null, compilationUnits)

		//Perform the compilation by calling the call method on compilerTask object.
		val status = compilerTask.call

		if (!status)
			//If compilation error occurs
			//Iterate through each compilation problem and print it
			for (diagnostic <- diagnostics.getDiagnostics)
				System.out.println("Error on line " + diagnostic.getLineNumber + " in " + diagnostic)
	}

}

/**
  * Creates a dynamic source code file object
  *
  * This is an example of how we can prepare a dynamic java source code for compilation.
  * This class reads the java code from a string and prepares a JavaFileObject
  *
  */
class DynamicJavaSourceCodeObject(var qualifiedName: String, var sourceCode: String)
	extends SimpleJavaFileObject(
			URI.create(
				"string:///"
				+ qualifiedName.replaceAll("\\.", "/")
				+ Kind.SOURCE.extension),
			Kind.SOURCE)
{
	@throws(classOf[IOException])
	override def getCharContent(ignoreEncodingErrors: Boolean) = sourceCode

	def getQualifiedName = qualifiedName

	def setQualifiedName(qualifiedName: String) =
		this.qualifiedName = qualifiedName

	def getSourceCode = sourceCode

	def setSourceCode(sourceCode: String) =
		this.sourceCode = sourceCode
}