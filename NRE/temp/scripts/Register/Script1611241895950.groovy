import com.ucf.pcte.CucumberKW
import com.ucf.pcte.gold.WebUI as WebUI
import static com.ucf.pcte.gold.WebUI.findTestObject
import cucumber.api.java.en.*
import com.kms.katalon.core.testdata.CSVData
import com.kms.katalon.core.testdata.reader.CSVSeparator
import com.ucf.pcte.RunConfiguration


CucumberKW.runFeatureFile('Include/features/Register/Register-success.feature')

