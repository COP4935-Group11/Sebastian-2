package edu.ucf.irl
import com.kms.katalon.core.configuration.RunConfiguration
import com.ucf.pcte.Webui as WebUI
import com.ucf.pcte.CucumberKW
import cucumber.api.java.en.*
import static com.ucf.pcte.TestObject.findTestObject


class Add_Delete_Contact {
	@Given ("I add contact")
	def addContact(){
		WebUI.setText(findTestObject('Object Repository/Page_Contactology - main/input_Name_contactName'), 'Contact004')

		WebUI.setText(findTestObject('Object Repository/Page_Contactology - main/input_Phone Number_contactNumber'), '1112223333')

		WebUI.setText(findTestObject('Object Repository/Page_Contactology - main/input_Email_contactEmail'), 'Contact004@test.com')

		WebUI.setText(findTestObject('Object Repository/Page_Contactology - main/input_Address_contactAddress'), '123 Test ST Orlando FL 32826')

		WebUI.click(findTestObject('Object Repository/Page_Contactology - main/button_Create Contact'))
	}
	@Then ("I should see contact on contactlist")
	def seeContact() {

		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Contactology - main/input_Delete_name3'),5)
		WebUI.closeBrowser()
	}
	@When ("I delete contact previously created")
	def deleteContact(){
		WebUI.click(findTestObject('Object Repository/Page_Contactology - main/button_Delete'))
	}
	@Then ("I should no longer see contact")
	def verifyNoContact() {
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_Contactology - main/input_Delete_name3'),5)
		WebUI.closeBrowser()
	}
}
