package com.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import console.Execute;
import console.RunConsole;

public class StringConstants {
	
	public static String ROOT_DIR = System.getProperty("user.dir")+"/";
	public static String PROJECT_DIR = RunConsole.project;
	public static String REPORT_DIR = "";
	public static String FEATURES_FOLDER = "temp/features";
	public static String FEATURES_SOURCE = PROJECT_DIR.concat("/Include/features/");
	public static String ID_SEPARATOR= "/";
	public static String NEW_LINE= "\n";
	public static String SCRIPTS_SOURCE = PROJECT_DIR.concat("/Include/scripts/");
	public static String CUSTOM_ATTRIBUTES_FILE = "DS_Store";
	public static String GROOVY_EXT = ".groovy";
	public static String XML_EXT = ".xml";
	public static String DEFAULT_SCRIPTS_PACKAGE = "package com.stepdefinitions";
	public static Charset STANDARD_CHARSET = StandardCharsets.UTF_8;
	public static String STEP_DEFS_GLUE = "com.stepdefinitions";
	
	public static String SCRIPTS_FOLDER = "temp/scripts";
	public static String TESTCASE_SCRIPTS_SOURCE = PROJECT_DIR.concat("/Scripts/");
	public static String TESTCASE_SCRIPTS_FOLDER = "temp/Scripts/";
}
