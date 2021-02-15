package com.ucf.pcte;

import java.text.MessageFormat;

import org.junit.Assert;

import com.kms.katalon.core.cucumber.keyword.internal.CucumberRunnerResultImpl;
import com.kms.katalon.core.logging.KeywordLogger;

import cucumber.api.cli.Main;
import console.RunConsole;
import console.Execute;

public class CucumberKW {
	
	private static final KeywordLogger logger = KeywordLogger.getInstance(CucumberKW.class);
	
	public static void runFeatureFile(String relativeFilePath)
	{
		String reportDir = Execute.reportDir;
		String projectDir = RunConsole.project;
		
		logger.logInfo(
				MessageFormat.format("Starting run keyword runFeatureFile: ''{0}'' and extract report to folder: ''{1}''...",
					relativeFilePath, reportDir));
		
		
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
		
		CucumberRunnerResultImpl cucumberResult = new CucumberRunnerResultImpl(
				runSuccess ? "passed" : "failed", reportDir);
			if (runSuccess) {
				System.out.println("Feature file: "+ relativeFilePath + " was passed");
			} else {
				Assert.fail("Cucumber feature file failed.");
				System.out.println("Feature file: "+ relativeFilePath + " failed");
			}
		
	}
	
}
