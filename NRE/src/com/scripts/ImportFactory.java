package com.scripts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportFactory {
	

	public static void startImports(){
        
        List<File> files;
        
        files = listf("temp");

        for(File file: files)
        {
            System.out.println(file.getName());
        }

        ImportFactory fr = new ImportFactory();
        fr.removeAndAdd(files);
		
    }

    public void removeAndAdd(List<File> files)
    {
        for(File file: files)
        {
            List<String> lines = new ArrayList<String>();
            String line = null;
            try {
                File f1 = file;
                FileReader fr = new FileReader(f1);
                BufferedReader br = new BufferedReader(fr);

                line = br.readLine();
                if (line.contains("package"))
                {
                    lines.add(line);
                    lines.add("\n");
                    line = br.readLine();
                }

                try{
                    lines.add(getImports());
                }catch(IOException e)
                {
                    System.out.println("Could not find imports.txt");
                }
                while ((line = br.readLine()) != null) {
                    if (line.contains("import"))
                    {
                        continue;
                    }
                        
                    lines.add(line);
                    lines.add("\n");
                }

                fr.close();
                br.close();
    
                FileWriter fw = new FileWriter(f1);
                BufferedWriter out = new BufferedWriter(fw);
                for(String s : lines)
                     out.write(s);
                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public String getImports() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("imports.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
    
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }   
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.getName().endsWith((".groovy")))
            {
                resultList.add(file);
            }
            else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        return resultList;
    } 
    
    
    
    public static void removeAndAdd(File file)
    {
       
            List<String> lines = new ArrayList<String>();
            String line = null;
            try {
                File f1 = file;
                FileReader fr = new FileReader(f1);
                BufferedReader br = new BufferedReader(fr);

                line = br.readLine();
                if (line.contains("package"))
                {
                    lines.add(line);
                    lines.add("\n");
                    line = br.readLine();
                }

                try{
                    lines.add(getImports1());
                }catch(IOException e)
                {
                    System.out.println("Could not find imports.txt");
                }
                while ((line = br.readLine()) != null) {
                    if (line.contains("import"))
                    {
                        continue;
                    }
                        
                    lines.add(line);
                    lines.add("\n");
                }

                fr.close();
                br.close();
    
                FileWriter fw = new FileWriter(f1);
                BufferedWriter out = new BufferedWriter(fw);
                for(String s : lines)
                     out.write(s);
                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    
    public static String getImports1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("imports.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
    
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }   
            return sb.toString();
        } finally {
            br.close();
        }
    }
    
}
