package com.ucf.pcte;

import java.io.FileReader;
import java.util.List;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import console.RunConsole;

public class TestData {
	
	String type = null;
	String path = null;
	String seperator = null;
	boolean isInternal = true;
	boolean contHeader = true;
	
	
	public TestData(String path, String type, Boolean contHead, Boolean isInternal, String seperator)
	{
		this.path = path;
		this.type = type;
		this.contHeader = contHead;
		this.isInternal = isInternal;
		this.seperator = seperator;
	}
	
	public Object getValue(int column, int row)
	{
		Object value = null;
		
		if(isInternal == true)
		{
			path = RunConsole.project + "/" + path;
		}
		
		switch (type)
		{
			case "CSV":
				return getCSVData(column, row);
				
			default:
				break;
		}
		
		
		return value;
		
	}
	
	public Object getValue(String column, int row)
	{
		Object value = null;
		
		
		return value;
		
	}
	
	public Object getCSVData(int column, int row)
	{
		
		 try { 
		        FileReader filereader = new FileReader(path); 
		  
		        CSVParser parser = new CSVParserBuilder().withSeparator(',').build(); 
		   
		        CSVReader csvReader = new CSVReaderBuilder(filereader) 
		                                  .withCSVParser(parser) 
		                                  .build(); 
		  
		        List<String[]> allData = csvReader.readAll();
		        
		        return allData.get(column)[row-1];
		        
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		
		return "Could not get value of Test Data";
		
	}

}
