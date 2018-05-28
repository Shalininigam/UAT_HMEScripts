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

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath


boolean TCflag=true
try{
	
	//Step1: To navigate to the application login page
	WebUI.navigateToUrl(GlobalVariable.SuperAdminUrl)
	
	//Step2:To navigate to the application home page
	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","publicadmin"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Password"))
	WebUI.delay(GlobalVariable.MED_DELAY)
	
	
	//Step3:To navigate to users page
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/HomePage/UsersLink'))
	
	WebUI.delay(GlobalVariable.MED_DELAY)
	WebUI.verifyElementPresent(findTestObject('Object Repository/UsersPage/users'),10)
	WebUI.verifyElementPresent(findTestObject('Object Repository/UsersPage/viewEditButton'),10)
	
	WebDriver driver = DriverFactory.getWebDriver()
	
	//Step4:To verify that user names are displayed correctly
	
	List<WebElement> userList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 19)))
		
	
	
	int size1= userList.size()
	
	
	if(size1<=0)
	{
		if(TCflag)
		TCflag=false
		System.out.println("User List is not displayed")
	}
	else
	System.out.println("User List is displayed")
	
	
	//Step5:To verify that admin is able to view "View as User"  button
	
	List<WebElement> viewEditButtonList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 20)))
	int size2 =viewEditButtonList.size()
	if(size2<=0)
	{
		if(TCflag)
		TCflag=false
		System.out.println("View/Edit Button is not displayed for each user")
	}
	else
	System.out.println("View/Edit Button is displayed")
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/Admin_SignOut'))
	
}catch(Exception e){
e.printStackTrace()
println "Exception of element not found"
if(TCflag)
	TCflag=false
}

assert TCflag==true



