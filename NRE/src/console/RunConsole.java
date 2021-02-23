package console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.script.ScriptEngine;

import org.codehaus.groovy.control.CompilerConfiguration;

import com.constants.StringConstants;
import com.scripts.*;
import com.ucf.pcte.*;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;



@SuppressWarnings("unused")
public class RunConsole {
	
	public static String project;
	public static String testSuite;
	public static String profile;
	public static String browser;
	public static String headless;
	
	public static ArrayList<String> testCases = new ArrayList<String>();
	public static Map<String,Object> GlobalVariables;
	
	public static ClassLoader rootClassLoader = ClassLoader.getSystemClassLoader();
	

	public static void main(String[] args) {
		
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to NRE. The new runtime engine for Katalon Studio.");
		
		System.out.println("Enter desired project path:");
//		project = in.nextLine();
		project = "/Users/sebastiangilarranz/Desktop/PCTE_Similar";
		
		// Checking if project directory exists.
		File projDirectory = new File(project);
		while(projDirectory.listFiles() == null)
		{
			System.out.println("Project path does not exist, please enter new path:");
			project = in.nextLine();
			projDirectory = new File(project);
		}
		
		

		
		System.out.println("Enter desired testSuite: (do not include extension)");
//		testSuite = in.nextLine();
		testSuite = "Smoke";
		
		// Checking if suite exists.
		File suiteFile = new File(project + "/Test Suites/" + testSuite + ".ts");
		while(suiteFile.isFile() == false)
		{
			System.out.println("Test Suite path does not exist, please enter new path:");
			testSuite = in.nextLine();
			suiteFile = new File(project + "/Test\\ Suites/" + testSuite + ".ts");
		}
		
		
		System.out.println("Enter desired profile: (do not include extension)");
//		profile = in.nextLine();
		profile = "default";
		
		// Checking if profile exists.
		File profileFile = new File(project + "/Profiles/" + profile + ".glbl");
		while(profileFile.isFile() == false)
		{
			System.out.println("Profile path does not exist, please enter new path:");
			profile = in.nextLine();
			profileFile = new File(project + "/Profiles/" + profile + ".glbl");
		}
		
		
		System.out.println("Enter desired profile: (do not include extension)");
//		profile = in.nextLine();
		profile = "default";
		
		System.out.println("Enter browser: C for chrome, F for firefox");
//		browser = in.nextLine();
		browser = "C";
		
		System.out.println("Headless: Y or N");
//		headless = in.nextLine();
		headless = "N";
		
		in.close();
		
		
		
		
		
		try {
			
			FeaturesFactory.copyFeatures();
			StepDefinitionsFactory.copyScripts();
			
			StepDefinitionsFactory.copyDirectory("com/stepdefinitions", "bin/com/stepdefinitions");
			StepDefinitionsFactory.deleteDirectory("com");
			
			
			TestCaseFactory.copyTestCaseScripts();
			
			
			
			for(File file : StepDefinitionsFactory.listOfSteps) {
				    
				
					file.deleteOnExit();
	          }
			
			for(File file : FeaturesFactory.listOfNewFeatures) {
			
				file.deleteOnExit();
			}
			
			
			
						
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		TestCases.getTestCases(testSuite);
		
		GlobalVariables = GlobalVars.getGlobalVars(profile);
		GlobalVariables.forEach((key, value) -> System.out.println(key + ":" + value + "class:" + value.getClass().getName()));
		
		
		
		ImportFactory.startImports();
				
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
		
		StepDefinitionsFactory.deleteDirectory("com");
		StepDefinitionsFactory.deleteDirectory("features");
		StepDefinitionsFactory.deleteDirectory("bin/com/stepdefinitions");
		
		TestCaseFactory.deleteDirectory();
		
		for(File file : StepDefinitionsFactory.listOfSteps) {
			    
			
				file.deleteOnExit();
          }
		
		for(File file : FeaturesFactory.listOfNewFeatures) {
		
			file.deleteOnExit();
		}
		
				
		
	}
	
}
