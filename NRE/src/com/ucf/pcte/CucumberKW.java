package com.ucf.pcte;

import org.junit.Assert;
import cucumber.api.cli.Main;


import com.constants.StringConstants;


public class CucumberKW {
	
//	private static final KeywordLogger logger = KeywordLogger.getInstance(CucumberKW.class);
	
	
	public static void runFeatureFile(String relativeFilePath)
	{
				
		relativeFilePath = StringConstants.FEATURES_FOLDER.concat(relativeFilePath
				.substring(relativeFilePath.lastIndexOf('/'), relativeFilePath.length()));
		
		//System.out.println(relativeFilePath);
		
		System.out.println("Starting run keyword runFeatureFile: " + relativeFilePath 
				+ " and extract report to folder: " + StringConstants.REPORT_DIR + "...");
		
		
		String[] argv = new String[]{
				relativeFilePath,
				"--glue",
				StringConstants.STEP_DEFS_GLUE,
				"--no-strict",
                "-p",
                "pretty",
                "-p",
    			"junit:"+ StringConstants.REPORT_DIR + StringConstants.ID_SEPARATOR + System.currentTimeMillis() + StringConstants.XML_EXT
                };
		
		
	

		boolean runSuccess = Main.run(argv, CucumberKW.class.getClassLoader()) == 0;
	
			if (runSuccess) {
				System.out.println("Feature file: "+ relativeFilePath + " was passed");
			} else {
				Assert.fail("Cucumber feature file failed.");
				System.out.println("Feature file: "+ relativeFilePath + " failed");
			}
		
	}
	
}
