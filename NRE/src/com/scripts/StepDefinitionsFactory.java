package com.scripts;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.Phases;
import org.codehaus.groovy.tools.GroovyClass;

import com.constants.StringConstants;
import console.RunConsole;
import groovy.lang.GroovyClassLoader;




public class StepDefinitionsFactory {


	private static ArrayList<File> listOfScripts = null;
	public static ArrayList<File> listOfSteps = null;


	
	public static void copyScripts() throws IllegalAccessException, InstantiationException, 
											IOException, ClassNotFoundException {


		listOfScripts = getScriptFiles();
		
		listOfSteps = new ArrayList<>();
		//System.out.println(listOfScripts.size()); debugging mode
		
		String className = null;
		String scriptSource = null;
		
		
		for(int i = 0; i < listOfScripts.size(); i++) {


			//String temp = getScript(listOfScripts.get(i));	//debugging mode
			//System.out.println(temp);	//debugging mode

			listOfSteps.add(i, getScript(listOfScripts.get(i)));
			
			className = listOfSteps.get(i).getName().replace(StringConstants.GROOVY_EXT, "");
			
			scriptSource = String.join(StringConstants.NEW_LINE ,readLines(listOfSteps.get(i)));
			
			 compileGroovyScript(className, scriptSource);
										
		}
		
		
		

	}

	private static ArrayList<File> getScriptFiles() {

		ArrayList<File> scripts = new ArrayList<>();

		String folder = StringConstants.SCRIPTS_SOURCE;
		File fld = new File(folder);

		//System.out.println(fld.toString()); //debugging mode

		while(fld.isDirectory()) {

			File[] ls = fld.listFiles();

			for(File file : ls) {


				if(!file.getName().contains(StringConstants.CUSTOM_ATTRIBUTES_FILE))
					if(!file.isDirectory()) {
						scripts.add(file);
						fld = file;
					}
					else
						fld = file;

			}

		}

		return scripts;
	}

	static File getScript(File rootScript) throws IOException {

		String sourceCode= null;


		ArrayList<String> fileContent = readLines(rootScript);
		
		//fileContent.remove(0);
		fileContent.set(0, StringConstants.DEFAULT_SCRIPTS_PACKAGE);
		sourceCode = String.join("\n", fileContent);
		
		new File(StringConstants.SCRIPTS_FOLDER).mkdirs();

		Path targetPath = Paths.get(new File(StringConstants.SCRIPTS_FOLDER + StringConstants.ID_SEPARATOR + rootScript.getName()).getAbsolutePath());
		Files.writeString(targetPath, sourceCode, StringConstants.STANDARD_CHARSET);
		
		
		File filee = new File(targetPath.toAbsolutePath().toString());
		System.out.println(filee);
		
		ImportFactory.removeAndAdd(filee);

		return 	filee;
	}
	
		
	
	public static ArrayList<String> readLines(File rootScript) throws IOException{
		
		ArrayList<String> fileContent = new ArrayList<String>(Files.readAllLines(Paths.get(rootScript.toURI()), 
																StringConstants.STANDARD_CHARSET));
	
		return fileContent;
	}
	
	@SuppressWarnings("unused")
	public static void compileGroovyScript(final String className, final String script) {
	    //byte[] compiledScriptBytes = null;
		CompilationUnit compileUnit = new CompilationUnit();
	    
	    
	    compileUnit.addSource(className, script);
	    compileUnit.compile(Phases.ALL);

	    for (Object compileClass : compileUnit.getClasses()) {
	        GroovyClass groovyClass = (GroovyClass) compileClass;
	        //compiledScriptBytes = groovyClass.getBytes();
	    }

	}
	
	@SuppressWarnings("rawtypes")
	public static Class getGroovyScript(final String className, String script) {
	    Class clazz = null;

	    try (GroovyClassLoader classLoader = new GroovyClassLoader(RunConsole.rootClassLoader)) {
	        clazz = classLoader.parseClass(script, className);
	                
	          
	        //classLoader.addClasspath(className);
	        
	        
	        
	    } catch (IOException e) {
	    } catch (Exception e) {
	    }

	    return clazz;
	}
	
	
	public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) throws IOException {
	    File sourceDirectory = new File(sourceDirectoryLocation);
	    File destinationDirectory = new File(destinationDirectoryLocation);
	    FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
	}
	
	public static void deleteDirectory(String dir) throws IOException {
		FileUtils.deleteDirectory(new File(dir));
	}

}
