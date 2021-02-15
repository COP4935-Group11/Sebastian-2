import com.kms.katalon.core.configuration.RunConfiguration
import com.ucf.pcte.Webui as WebUI
import com.ucf.pcte.CucumberKW

CucumberKW.runFeatureFile('Include/features/Add_Contact/AddContact.feature')
CucumberKW.runFeatureFile('Include/features/Add_Contact/DeleteContact.feature')
