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

/**
  * A test class to test dynamic compilation API.
  *
  */
class Thingy
{
	final var logger = Logger.getLogger(classOf[Thingy] getName)

	/**Java source code to be compiled dynamically*/
	val sourceCode = "package com.accordess.ca" +
		"class DynamicCompilationHelloWorld{" +
		"public static void main (String args[]){" +
		"System.out.println (\"Hello, dynamic compilation world!\")" +
		"}" +
		"}"

	/**
	  * Does the required object initialization and compilation.
	  */
	def doCompilation
	{
		/*Creating dynamic java source code file object*/
		val fileObject = new DynamicJavaSourceCodeObject("com.accordess.ca.DynamicCompilationHelloWorld", sourceCode)
		/*Instantiating the java compiler*/
		var compiler = ToolProvider.getSystemJavaCompiler

		/**
		  * Retrieving the standard file manager from compiler object, which is used to provide
		  * basic building block for customizing how a compiler reads and writes to files.
		  *
		  * The same file manager can be reopened for another compiler task.
		  * Thus we reduce the overhead of scanning through file system and jar files each time
		  */
		var stdFileManager = compiler.getStandardFileManager(null, Locale.getDefault, null)

		/* Prepare a list of compilation units (java source code file objects) to input to compilation task*/
		val compilationUnits = Arrays asList fileObject

		/*Prepare any compilation options to be used during compilation*/
		//In this example, we are asking the compiler to place the output files under bin folder.
		val compilationOptionss = Arrays.asList("-d", "bin")

		/*Create a diagnostic controller, which holds the compilation problems*/
		var diagnostics = new DiagnosticCollector[JavaFileObject]

		/*Create a compilation task from compiler by passing in the required input objects prepared above*/
		var compilerTask = compiler.getTask(null, stdFileManager, diagnostics, compilationOptionss, null, compilationUnits)

		//Perform the compilation by calling the call method on compilerTask object.
		val status = compilerTask.call

		if (!status)
			//If compilation error occurs
			//Iterate through each compilation problem and print it
			for (diagnostic <- diagnostics.getDiagnostics)
				System.out.println("Error on line " + diagnostic.getLineNumber + " in " + diagnostic)
		try
		{
			stdFileManager.close //Close the file manager
		}
		catch
		{
			case e: IOException => e.printStackTrace
		}
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
	extends SimpleJavaFileObject(URI.create("string:///" + qualifiedName.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE)
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