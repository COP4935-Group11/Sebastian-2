package com.console;

import java.io.File;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import groovy.lang.GroovyShell;


public class RunScript {
	
	@Parameters({"path"})
	@Test
	public static void runScript(String path)
	{
		File script = new File(path);
		
		GroovyShell groovyShell = new GroovyShell();
		
		try {
			groovyShell.evaluate(script);
		}catch(Exception e)
		{
			Assert.fail(e.toString());
		}
	}
	
	public static Object getObject(String defaultValue)
	{
		ImportCustomizer importCustomizer = new ImportCustomizer();

        importCustomizer.addStaticImport("com.ucf.pcte.TestDataFinder", "findTestData");
		 
		CompilerConfiguration configuration = new CompilerConfiguration();
		configuration.addCompilationCustomizers(importCustomizer);
		String script = defaultValue;
		GroovyShell groovyShell = new GroovyShell(configuration);
		Object output = null;
		
		try {
			output = groovyShell.evaluate(script);
		}catch(Exception e)
		{
			Assert.fail(e.toString());
		}
		
		
		return output;
	}
}
