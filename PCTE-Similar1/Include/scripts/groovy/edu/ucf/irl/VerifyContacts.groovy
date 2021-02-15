package edu.ucf.irl
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.CSVData
import com.ucf.pcte.Webui as WebUI
import com.ucf.pcte.CucumberKW
import cucumber.api.java.en.*
import static com.ucf.pcte.TestObject.findTestObject
import com.kms.katalon.core.testdata.reader.CSVSeparator;


class VerifyContacts {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Then ("I should see existing contacts")
	def verifyContacts() {
		CSVData csvData = new CSVData(RunConfiguration.getProjectDir() +'/Data Files/User001_Contacts.csv', true, CSVSeparator.COMMA)
		WebUI.verifyElementPresent(findTestObject(csvData.getValue('Name', 1)),5)
		WebUI.verifyElementPresent(findTestObject(csvData.getValue('Name', 2)),5)
		WebUI.verifyElementPresent(findTestObject(csvData.getValue('Name', 3)),5)
		WebUI.closeBrowser()
	}
}
