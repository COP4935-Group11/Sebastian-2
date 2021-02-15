package edu.ucf.irl
import com.kms.katalon.core.configuration.RunConfiguration
import com.ucf.pcte.Webui as WebUI
import com.ucf.pcte.CucumberKW
import cucumber.api.java.en.*
import static com.ucf.pcte.TestObject.findTestObject


class Register {
	@When("I register to contactology")
	def IRegister() {
		WebUI.click(findTestObject('Object Repository/Page_Contactology - login/a_Create Account'))

		WebUI.setText(findTestObject('Object Repository/Page_Contactology - register/input_First Name_firstname'), 'User004')

		WebUI.setText(findTestObject('Object Repository/Page_Contactology - register/input_Last Name_lastname'), 'User004')

		WebUI.setText(findTestObject('Object Repository/Page_Contactology - register/input_User Name_username'), 'User004')

		WebUI.setEncryptedText(findTestObject('Object Repository/Page_Contactology - register/input_Password_pass'), 'L545G4l71hM=')

		WebUI.setEncryptedText(findTestObject('Object Repository/Page_Contactology - register/input_Confirm Password_confpass'),
				'L545G4l71hM=')

		WebUI.click(findTestObject('Object Repository/Page_Contactology - register/button_Register'))
	}
}
