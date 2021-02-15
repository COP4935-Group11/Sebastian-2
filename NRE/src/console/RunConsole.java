package console;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class RunConsole {
	
	public static String project;
	public static String testSuite;
	public static String profile;
	
	public static ArrayList<String> testCases = new ArrayList<String>();
	public static Map<String,Object> GlobalVariables;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to NRE. The new runtime engine for Katalon Studio.");
		
		System.out.println("Enter desired project path:");
//		project = in.nextLine();
		project = "/Users/sebastiangilarranz/Desktop/Sebastian/PCTE-Similar1";
		
		// Checking if project directory exists.
		File projDirectory = new File(project);
		while(projDirectory.listFiles() == null)
		{
			System.out.println("Project path does not exist, please enter new path:");
			project = in.nextLine();
			projDirectory = new File(project);
		}
		
		
//		System.out.println(RunConfiguration.getProjectDir());
		
		System.out.println("Enter desired testSute: (do not include extension)");
//		testSuite = in.nextLine();
		testSuite = "smoke";
		
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
		
		TestCases.getTestCases(testSuite);
		
//		GroovyClassLoader cl = new GroovyClassLoader(RunConsole.class.getClassLoader());
//		
//		try {
//            cl.loadClass("GlobalVariable");
//        } catch (ClassNotFoundException ex) {
//            try {
//                cl.parseClass(new File(RunConfiguration.getProjectDir(),"/Libs/internal/GlobalVariable.groovy"));
//            } catch (Exception e) {
//            	e.printStackTrace();
//            }
//        }
		
//		GlobalVariables = GlobalVars.getGlobalVars(profile);
//		GlobalVariables.forEach((key, value) -> System.out.println(key + ":" + value + "class:" + value.getClass().getName()));

		
		try 
		{
			Execute.run();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
