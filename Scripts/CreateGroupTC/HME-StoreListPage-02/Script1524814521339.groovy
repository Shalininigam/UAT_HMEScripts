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
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","otherUser"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","otherUserPwd"))
		
	WebUI.delay(GlobalVariable.MED_DELAY)
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))
	
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 1: To verify that admin is able to view Store list page
	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	WebUI.verifyElementPresent(findTestObject('StorePage/StoreListHeading'), 5)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
	//Step 2: To verify that Manage report groups button is not shown to the user
	//Manage Report Groups button will not be shown  in store list page
	
	WebUI.verifyElementNotPresent(findTestObject('StorePage/ManageLeaderGroupsButton'),5)
	'To verify that Manage report groups button is visible to the user'
	WebUI.verifyElementNotPresent(findTestObject('StorePage/ManageReportGroupsButtton'),5)
	
}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true

