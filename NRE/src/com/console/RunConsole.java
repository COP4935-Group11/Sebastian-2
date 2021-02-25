package com.console;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


import com.configuration.RunConfiguration;
import com.constants.StringConstants;
import com.scripts.*;
import com.util.DirectoryUtils;



public class RunConsole {
	
	
	public static ArrayList<String> testCases = new ArrayList<String>();
	public static Map<String,Object> GlobalVariables;
	
		

	public static void console() {
		
		
		GlobalVariables = GlobalVars.getGlobalVars(RunConfiguration.getProfile());
		//GlobalVariables.forEach((key, value) -> System.out.println(key + ":" + value + "class:" + value.getClass().getName()));
		GlobalVariableFactory.createGlobalVarFile(GlobalVariables);
		
		DirectoryUtils.copyDirectory("internal", "bin/internal");
		
		try {
			
			FeaturesFactory.copyFeatures();
			StepDefinitionsFactory.copyScripts();
			
			DirectoryUtils.copyDirectory(StringConstants.ROOT_DIR+StringConstants.ID_SEPARATOR+StringConstants.TEMP_COMPILED_STEPS_FOLDER,
										StringConstants.ROOT_DIR+StringConstants.ID_SEPARATOR+StringConstants.COMPILED_STEPS_FOLDER);
			DirectoryUtils.deleteDirectory("com");
						
			TestCaseFactory.copyTestCaseScripts();
			TestCaseFactory.changeImports();
						
			setTempFiles();			
			
						
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		
		TestCases.getTestCases(RunConfiguration.getTestSuite());
		
					
	
		try 
		{
			Execute.run();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		try {
						
			cleanProject();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	private static void cleanProject() throws IOException {
		
		DirectoryUtils.deleteDirectory("com");
		DirectoryUtils.deleteDirectory("features");
		DirectoryUtils.deleteDirectory("bin/com/stepdefinitions");
		DirectoryUtils.deleteDirectory("temp");
		
		setTempFiles();
						
	}
	
	private static void setTempFiles() {
		
		
		for(File file : StepDefinitionsFactory.listOfSteps) {
			
			file.deleteOnExit();
		}
	
		for(File file : FeaturesFactory.listOfNewFeatures) {
	
			file.deleteOnExit();
		}
		
	}
	
}
