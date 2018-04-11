import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


'User login to Application'

WebUI.navigateToUrl(GlobalVariable.AppURL)

CustomKeywords.'projectSpecific.Reusability.login'(findTestData('AdminCredentials').getValue('Username', 1),findTestData('AdminCredentials').getValue('Password', 1))
WebUI.delay(5)

'Click on Users'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/Users'))

'Search for Users'
CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UsersPage/Userssearch'),findTestData('AdminCredentials').getValue('EmailAddress', 1) )
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/User_searchimage'))
WebUI.delay(5)

'Click on View/Edit User'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewEditUsers'))

'verify User details'
String s1=findTestData('AdminCredentials').getValue('EmailAddress', 1)
System.out.println(s1)
String s2=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('UsersPage/Verifyemail'))

boolean execFlag = WebUI.verifyMatch(s1, s2, false)
if(execFlag)
{
	System.out.println("TestCase Passed")
	WebUI.takeScreenshot()
}
else{
	System.out.println("TestCase Failed")
	WebUI.takeScreenshot()
}




