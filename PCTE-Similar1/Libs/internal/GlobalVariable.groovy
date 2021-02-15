package internal
import com.kms.katalon.core.configuration.RunConfiguration
import com.ucf.pcte.Webui as WebUI
import com.ucf.pcte.CucumberKW


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object url
     

    static {
        try {
//            def selectedVariables = TestCaseMain.getGlobalVariables("default")
//			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
//            selectedVariables += RunConfiguration.getOverridingParameters()
    
            url = "https://google.com"
            
        } catch (Exception e) {
            e.printStackTrace
        }
    }
}
