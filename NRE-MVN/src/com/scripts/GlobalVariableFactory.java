package com.scripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.Phases;
import org.codehaus.groovy.tools.GroovyClass;

import com.constants.StringConstants;

import com.console.RunConsole;
import groovy.lang.GroovyClassLoader;

public class GlobalVariableFactory {

	public static Map<String,Object> GlobalVariables;
	
	public static void createGlobalVarFile(Map<String,Object> GlobalVariables)
	{
		String sourceCode = null;
		
		ArrayList<String> fileContent = new ArrayList<>();
		
		fileContent.add("package internal");
		fileContent.add("import com.console.RunConsole");
		fileContent.add("public class GlobalVariable {");
		
		GlobalVariables.forEach((key, value) -> 
		
			fileContent.add("public static Object " + key + " = " + "RunConsole.GlobalVariables.get('" + key +"')"));
		
		fileContent.add("}");
		
		sourceCode = String.join("\n", fileContent);
//		System.out.println(sourceCode);
//		System.out.println(RunConsole.GlobalVariables.get("url"));
		
		compileGroovyScript("GlobalVariable", sourceCode);
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
}
