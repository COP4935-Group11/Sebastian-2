package com.scripts;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.constants.StringConstants;

public class TestCaseFactory {
	
	public static void copyTestCaseScripts()
	{
		String source = StringConstants.TESTCASE_SCRIPTS_SOURCE;
		File srcDir = new File(source);

		String destination = "temp/Scripts";
		File destDir = new File(destination);

		try {
		    FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void deleteDirectory() throws IOException
	{
		FileUtils.deleteDirectory(new File("temp"));
	}


}
