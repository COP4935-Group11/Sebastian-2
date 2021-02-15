package console;


import java.io.File;

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
}
