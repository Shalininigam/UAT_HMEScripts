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

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


boolean TCflag=true

try{

	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	'User login to Application'

	WebDriver driver = DriverFactory.getWebDriver()

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	WebUI.verifyElementPresent(findTestObject('StorePage/StoreListHeading'), 5)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	WebUI.verifyElementPresent(findTestObject('StorePage/ManageLeaderGroupsButton'),5)
	'To verify that Manage report groups button is visible to the user'
	WebUI.verifyElementPresent(findTestObject('StorePage/ManageReportGroupsButtton'),5)


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))
	'To navigate to Reporting Group Management screen'
	WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/addNewGroup'),5)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/groupNameTxt'))


	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC3"))


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/groupDescTxt'))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupDescTC3"))

	'click on save button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	WebUI.back()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	WebUI.back()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	int index=0;
	String groupName=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC3")
	List<WebElement> groupHierarchyList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))

	for(int i=0;i<groupHierarchyList.size();i++){

		if(groupHierarchyList.get(i).getText().equals(groupName))
		{
			index=i;
			break;
		}

	}

	WebUI.delay(GlobalVariable.MED_DELAY)

	if(index !=0)
	{
		println "Group is available in group hierarchy"

		String xpath = "//span[text()='"+groupName+"']"

		System.out.println(xpath);
		driver.findElement(By.xpath(xpath)).click();
		WebUI.delay(GlobalVariable.MED_DELAY)
		CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))

		CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))


	}
	else{

		println "Required group is not available in group hierarchy"
		WebUI.takeScreenshot()

		if(TCflag)
			TCflag=false
	}
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))

}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true

