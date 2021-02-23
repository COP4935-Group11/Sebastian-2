package com.scripts;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.constants.StringConstants;

public class FeaturesFactory {

	private static ArrayList<File> listOfFeatures = null;
	public static ArrayList<File> listOfNewFeatures = null;
	
	

	public static void copyFeatures() throws IllegalAccessException, InstantiationException, IOException {

		listOfFeatures = getFeaturesFiles();
		listOfNewFeatures = new ArrayList<>();

		for(int i = 0; i < listOfFeatures.size(); i++) {

			listOfNewFeatures.add(getFeature(listOfFeatures.get(i)));
						
		}
		
}

	private static ArrayList<File> getFeaturesFiles() {

		ArrayList<File> features = new ArrayList<>();

		String folder = StringConstants.FEATURES_SOURCE;
		File rootFolder = new File(folder);

		for(File featureFolder : rootFolder.listFiles()) {
			
			for(File feature : featureFolder.listFiles()) {
				
				features.add(feature);
				
			}
			
		}
		
	
		return features;
	}

	static File getFeature(File rootScript) throws IOException {

		String sourceCode= null;
		


		ArrayList<String> fileContent = new ArrayList<String>(Files.readAllLines(Paths.get(rootScript.toURI()), StandardCharsets.UTF_8));
		sourceCode = String.join(StringConstants.NEW_LINE, fileContent);

		new File(StringConstants.FEATURES_FOLDER).mkdirs();
		
		Path targetPath = Paths.get(new File(StringConstants.FEATURES_FOLDER+StringConstants.ID_SEPARATOR+rootScript.getName()).getAbsolutePath());
		Files.writeString(targetPath, sourceCode, StandardCharsets.UTF_8);
		
		return new File(targetPath.toAbsolutePath().toString());	

	}

}
