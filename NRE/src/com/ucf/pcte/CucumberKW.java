package com.ucf.pcte;

import org.junit.Assert;
import cucumber.api.cli.Main;
import console.RunConsole;
import console.Execute;

public class CucumberKW {
	
//	private static final KeywordLogger logger = KeywordLogger.getInstance(CucumberKW.class);
	
	public static void runFeatureFile(String relativeFilePath)
	{
		String reportDir = Execute.reportDir;
		String projectDir = RunConsole.project;
		
		System.out.println("Starting run keyword runFeatureFile: " + relativeFilePath + " and extract report to folder: " + reportDir + "...");
		
		
		String[] argv = new String[]{
				"-g",
				"",
				projectDir + "/" + relativeFilePath,
				"--strict",
                "--plugin",
                "pretty",
                "--plugin",
    			"junit:"+ reportDir +"/" + System.currentTimeMillis() + ".xml"
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
