package com.configuration;

import java.util.Map;

import com.console.RunConsole;

//import console.RunConsole;

public class RunConfiguration {

	public enum OSType{
	
		WINDOWS,
		LINUX,
			
	}	
	
	static OSType platform;
	static String projectDir;
	static String testSuite;
	static String profile;
	static String browser;
	static String reportDir;
	
	
	
	public static OSType getPlatform() {
		return platform;
	}
	public static void setPlatform(String platform) {
		
		switch(platform.toLowerCase()) {
					
		case "linux": RunConfiguration.platform = OSType.LINUX;
			break;
		case "windows": RunConfiguration.platform = OSType.WINDOWS;
			break;
		default: RunConfiguration.platform = OSType.LINUX;
					
		}
		
	}	
	
	public static String getProjectDir() {
		return projectDir;
	}
	public static void setProjectDir(String project) {
		RunConfiguration.projectDir = project;
	}
	public static String getTestSuite() {
		return testSuite;
	}
	public static void setTestSuite(String testSuite) {
		RunConfiguration.testSuite = testSuite;
	}
	public static String getProfile() {
		return profile;
	}
	public static void setProfile(String profile) {
		RunConfiguration.profile = profile;
	}
	public static String getBrowser() {
		return browser;
	}
	public static void setBrowser(String browser) {
		RunConfiguration.browser = browser;
	}
	public static String getReportDir() {
		return reportDir;
	}
	public static void setReportDir(String reportDir) {
		RunConfiguration.reportDir = reportDir;
	}

	

}
