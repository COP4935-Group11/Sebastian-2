package com.console;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.configuration.RunConfiguration;
import com.constants.StringConstants;

import junit.xml.parser.Merger;


@SuppressWarnings("unused")
public class Execute {
	
	public static String reportDir;
	public static boolean error = false;
	public static int currTest = 0;

	
	public static void run() throws Exception
	{
		LocalDateTime now = LocalDateTime.now();  
		
		new File(RunConfiguration.getReportDir()).mkdir();
		
		reportDir = (RunConfiguration.getReportDir()+ StringConstants.ID_SEPARATOR + now);
		
				
		File reportdir = new File(reportDir);
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		List<XmlClass> classes = new ArrayList<XmlClass>();
	
		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite");
		classes.add(new XmlClass("com.console.RunScript"));
		
		for(String eachCase : RunConsole.testCases)
		{
			eachCase = eachCase.replace("Test Cases", "Scripts");
			File dir = new File("temp" + StringConstants.ID_SEPARATOR + eachCase);
			
			XmlTest test = new XmlTest(suite);
			test.setName(eachCase.replace("Scripts"+ StringConstants.ID_SEPARATOR, ""));

			test.setXmlClasses(classes);
			test.addParameter("path","temp" + StringConstants.ID_SEPARATOR 
					+ eachCase + StringConstants.ID_SEPARATOR + dir.list()[0]);
		}
	
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();
	
		
		try {
			Merger.main(new String[] {"-i=" + reportDir,"-o=" + RunConfiguration.getReportDir() + StringConstants.ID_SEPARATOR + now + StringConstants.XML_EXT, "-s=Smoke"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
