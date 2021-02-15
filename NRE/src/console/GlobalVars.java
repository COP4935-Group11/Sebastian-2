package console;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;



import groovy.util.Node;
import groovy.util.NodeList;
import groovy.xml.XmlParser;


public class GlobalVars{
	
//	private static ScriptEngine engine;
	
	private static ScriptEngine engine;

	
	public static Map<String, Object> getGlobalVars(String profileName) {
		GroovyClassLoader classLoader = new GroovyClassLoader(GlobalVars.class.getClassLoader());
		
		try {
			engine = ScriptEngine.getDefault(classLoader);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
        
		
		try {
            Map<String, Object> selectedVariables = new HashMap<>();
            Node rootNode = new XmlParser()
                    .parse(new File(RunConsole.project, "Profiles/" + profileName + ".glbl"));
            NodeList variableNodes = (NodeList) rootNode.get("GlobalVariableEntity");
            for (int index = 0; index < variableNodes.size(); index++) {
                Node globalVariableNode = (Node) variableNodes.get(index);
                String variableName = ((Node) ((NodeList) globalVariableNode.get("name")).get(0)).text();
                String defaultValue = ((Node) ((NodeList) globalVariableNode.get("initValue")).get(0)).text();
                try {
                	selectedVariables.put(variableName, engine.runScriptWithoutLogging(defaultValue, new Binding()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return selectedVariables;
        } catch (Exception ex) {
            System.out.println("Could not create global variable.");
            return Collections.emptyMap();
        }  
    }

}

	

