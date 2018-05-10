import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.stringtemplate.v4.compiler.STParser.exprTag_return

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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.common.WebUiCommonHelper

boolean TCflag=true
try {
	
WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))
WebUI.delay(GlobalVariable.MED_DELAY)
WebDriver driver = DriverFactory.getWebDriver()

//Pre-Condition : To redirect to Group hirerchy page
'Click on Stores Link'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

// To enter Group Name and Group description (to create group and delete it and later check whether will be able to create

'Click on Add New Group button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))
WebUI.delay(GlobalVariable.MED_DELAY)

String existingGroup=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","alreadyDeletedGroup")
CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), existingGroup)

CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))

WebUI.delay(GlobalVariable.MED_DELAY)
'click on save button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)

WebUI.back()

WebUI.delay(GlobalVariable.MED_DELAY)


groupHierarchyList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))
boolean exists = false;
for(int i=0;i<groupHierarchyList.size();i++){

	 if(groupHierarchyList.get(i).getText().equals(existingGroup))
	 {
		 
		 println "group exists"
		 exists=true;
		 break;
	 }
		

}


if(!exists)
  {
	  if(!TCflag)
	  {
	  TCflag = false
	  WebUI.takeScreenshot()
	  }
  }

//delete this group now itself so to verify whether we can create the group again
groupXpath= "//span[text()='"+existingGroup+"']"
println(groupXpath)

driver.findElement(By.xpath(groupXpath)).click();

WebUI.delay(GlobalVariable.MED_DELAY)

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportingGroupManagement/deleteBtn'))
WebUI.delay(GlobalVariable.MED_DELAY)

String confirmToDeleteText = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportingGroupManagement/confirmToDeleteText'))
String confirmToDeleteText2= CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","confirmDeleteTxt")
WebUI.verifyMatch(confirmToDeleteText,confirmToDeleteText2,false)

WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/confirmToDelNoBtn'),5)
WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/confirmToDelBtn'),6)


CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))

WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)



//Step 1: To navigate to Reporting group details screen

List<WebElement> groupHierarchyList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))
int groupSize = groupHierarchyList.size()
String existingGroupName =groupHierarchyList.get(groupSize-1).getText()

'Click on Add New Group button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

'Verify Reporting group details screen is displayed'
WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)

println(existingGroupName)
//Step 2:To enter Group Name and Group description


CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), existingGroupName)

//Step 3:To verify that application displays proper error message if group name is not unique

'click on save button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
WebUI.delay(GlobalVariable.MED_DELAY)

String groupalreadyExists= CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupAlreadyMessage")

String groupalreadyText = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportingGroupManagement/groupCreationErrorMessage'))

WebUI.verifyMatch(groupalreadyExists,groupalreadyText,false)

//Step 4: To remove the Group name

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/groupNameTxt'))
WebUiCommonHelper.findWebElement(findTestObject('ReportingGroupManagement/groupNameTxt'), 30).clear()

'click on save button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
WebUI.delay(GlobalVariable.MED_DELAY)

//Step 5:To verify that application displays error message is Group name is empty

String groupNameBlankMessage=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameBlankMessage")

String blankText = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportingGroupManagement/groupCreationErrorMessage'))

WebUI.verifyMatch(groupNameBlankMessage,blankText,false)

WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)

WebUI.back()

WebUI.delay(GlobalVariable.MED_DELAY)

//Step6: To verify that user will be allowed to create a group name which was created and deleted before - Step 1
//Step7: To verify that user will be allowed to create a group name which was created and deleted before - Step 2

'Click on Add New Group button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), existingGroup)

CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))


'click on save button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)
WebUI.back()

WebUI.delay(GlobalVariable.MED_DELAY)


//Step8: To verify the Group Hierarchy in Reporting Group Management screen

groupHierarchyList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))
exists=false;
for(int i=0;i<groupHierarchyList.size();i++){

	 if(groupHierarchyList.get(i).getText().equals(existingGroup))
	 {
		 
		 println "group is existing in GroupList"
		 exists=true;
		  break;
	 }
		

}

if(!exists)
{
	if(!TCflag)
	{
	TCflag = false
	WebUI.takeScreenshot()
	}
}


String groupXpath= "//span[text()='"+existingGroup+"']"
println(groupXpath)

driver.findElement(By.xpath(groupXpath)).click();

WebUI.delay(GlobalVariable.MED_DELAY)

 

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportingGroupManagement/deleteBtn'))


CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))

WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)
WebUI.back()

WebUI.delay(GlobalVariable.MED_DELAY)

//Step9:To verify that user will not be able to enter more than 50 characteres in group name


'Click on Add New Group button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

'Verify Reporting group details screen is displayed'
WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)


String groupNamewith50characters="thisgroupnamehas50characters50characters50charact"

// CustomKeywords.'uiaction.CommonUIActions.getTestData'("ReportingGroupManagementPage","groupNameWith50characters")

CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'),groupNamewith50characters )

CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))


'click on save button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)



groupHierarchyList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))
exists=false;
for(int i=0;i<groupHierarchyList.size();i++)
{

	 if(groupHierarchyList.get(i).getText().equals(groupNamewith50characters))
	 {
		 println "group with 50 characters in name exists"
		 exists=true;
		 break;
	 }


}


if(!exists)
{
	if(!TCflag)
	{
	TCflag = false
	WebUI.takeScreenshot()
	}
}
//delete of the created group
groupXpath= "//span[text()='"+groupNamewith50characters+"']"
println(groupXpath)

driver.findElement(By.xpath(groupXpath)).click();

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportingGroupManagement/deleteBtn'))


CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))

WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)
WebUI.back()

WebUI.delay(GlobalVariable.MED_DELAY)

//Step9: To verify that user will not be able to enter more than 200 characteres in group description

'Click on Add New Group button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

'Verify Reporting group details screen is displayed'
WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)

String groupName200characters= "groupNamewithmorethan200charactersgroupNamewithmorethan200charactersgroupNamewithmorethan200charactersgroupNamewithmorethan200charactersgroupNamewithmorethan200charactersgroupNamewithmorethan200charactersgroupNamewithmorethan200characters";


CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'),groupName200characters )


WebUI.delay(10)
String groupNameEntered = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportingGroupManagement/groupNameTxt'))


exists = true;

if(!groupNameEntered.equals(groupName200characters))
{
	println("Cannot create group with 200 characters")
	exists = false;
}

if(!exists)
{
		if(!TCflag)
		{
		TCflag = false
		WebUI.takeScreenshot()
		}
}

//logout of the url

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))
assert TCflag==true
}
catch(Exception e)
{
	println "exception occured"
}
