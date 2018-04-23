import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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

boolean TCflag=true
try{
	WebUI.navigateToUrl(GlobalVariable.AppURL)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Username1"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Password"))
	WebUI.delay(5)

	String reportHeaders =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","ReportsCoulmnHeader")

	'Click on Users'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/Users'))

	'Search for Users'
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UsersPage/Userssearch'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","ReportUserName"))
		
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/User_searchimage'))
	WebUI.delay(5)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewEditUsers'))

	'Click on Reports Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))
	
	
}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true