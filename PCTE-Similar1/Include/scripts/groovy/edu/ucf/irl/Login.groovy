package edu.ucf.irl
import com.kms.katalon.core.configuration.RunConfiguration
import cucumber.api.java.en.*
import com.ucf.pcte.Webui as WebUI
import com.ucf.pcte.CucumberKW
import static com.ucf.pcte.TestObject.findTestObject
//import internal.GlobalVariable


class Login {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("I am on contactology")
	def goToWebsite() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl("https://contactology.pythonanywhere.com")
		
	}

	@When("I login to contactology")
	def loginToWebsite() {
		WebUI.setText(findTestObject('Object Repository/Page_Contactology - login/input_User Name_username'), 'User001')

		WebUI.setEncryptedText(findTestObject('Object Repository/Page_Contactology - login/input_Password_pass'), 'wDz180nZtCQ=')

		WebUI.click(findTestObject('Object Repository/Page_Contactology - login/button_Login'))
	}

	@Then("I should have access to dashboard")
	def confirmDashboard() {
		WebUI.verifyElementText(findTestObject('Object Repository/Page_Contactology - main/h2_Contacts'),'CONTACTS')
		WebUI.closeBrowser()
	}


	@When("I logout of contactology")
	def logout(){
		WebUI.click(findTestObject('Object Repository/Page_Contactology - main/Page_Contactology - main/a_logout'))
	}

	@Then("I should no longer have access to dashboard")
	def confirmLogout() {
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_Contactology - main/h2_Contacts'),5)
		WebUI.closeBrowser()
	}
}
