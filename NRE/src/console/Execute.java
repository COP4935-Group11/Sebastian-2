package console;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import junit.xml.parser.Merger;


public class Execute {
	
	public static String reportDir;
	public static boolean error = false;
	public static int currTest = 0;

	
	public static void run() throws Exception
	{
		LocalDateTime now = LocalDateTime.now();  
		
		File reportdir = new File(RunConsole.project + "/cucumber_report/" + now);
		reportDir = (RunConsole.project + "/cucumber_report/" + now);
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		List<XmlClass> classes = new ArrayList<XmlClass>();
	
		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite");
		classes.add(new XmlClass("console.RunScript"));
		
		for(String eachCase : RunConsole.testCases)
		{
			eachCase = eachCase.replace("Test Cases", "Scripts");
			File dir = new File(RunConsole.project + "/" + eachCase);
			
			XmlTest test = new XmlTest(suite);
			test.setName(eachCase.replace("Scripts/", ""));

			test.setXmlClasses(classes);
			test.addParameter("path",RunConsole.project + "/" + eachCase + "/" + dir.list()[0]);
		}
	
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();
	
		
		try {
			Merger.main(new String[] {"-i=" + reportDir,"-o=" + RunConsole.project + "/cucumber_report/" + now + ".xml", "-s=Smoke"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
