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
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))
	WebUI.delay(5)



	//Pre-requisities
	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	'Verify Reporting group details screen is displayed'
	WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)

	//To enter Group Name and Group description
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupName"))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))

	'click on save button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(10)
	WebUI.back()
	WebUI.delay(10)

	//Step 1: To verify that clicking on Group name will navigate the user to Reporting Group Details screen

	WebDriver driver = DriverFactory.getWebDriver()

	List<WebElement> groupHierarchyList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))
	List<String> actualgroupList=new ArrayList<String>();

	for(int i=0;i<groupHierarchyList.size();i++){

		actualgroupList.add(i,groupHierarchyList.get(i).getText())

	}
	System.out.println(actualgroupList)

	WebUI.delay(10)

	String groupName=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupName")

	if(actualgroupList.contains(groupName))
	{

		CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/group11inGroupHierarchy'))
		println "Group 11 is available in group hierarchy"

	}
	else{

		println "Required group is not available in group hierarchy"
	}

	'Verify Reporting group details screen is displayed'
	WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)
	
	WebUI.delay(5)

	//Step 2: To verify the Group name and Group description pre-populated in the screen

	'Verify Group Name and Group description'
	String groupNameAttr =WebUI.getAttribute(findTestObject('ReportingGroupManagement/groupNameTxt'),"value")
	WebUI.verifyMatch(groupNameAttr, CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupName"), false)

	WebUI.delay(5)
	String groupDescAttr =WebUI.getAttribute(findTestObject('ReportingGroupManagement/groupDescTxt'),"value")
	WebUI.delay(5)
	WebUI.verifyMatch(groupDescAttr, CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"), false)

	//Step 3: To verify that available groups/stores are shown properly under Available Group/Stores in Group list box

	String StoreList=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","availableStoreList")
	String[] groupList=StoreList.split(',')
	ArrayList<String> listOfStores=new ArrayList<String>(Arrays.asList(groupList))
	System.out.println(listOfStores)

	WebUI.delay(10)


	List<WebElement> availableStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 10)))

	List<String> actualList=new ArrayList<String>();

	for(int i=0;i<availableStoreList.size();i++){

		actualList.add(i,availableStoreList.get(i).getText())

	}
	System.out.println(actualList)

	if(actualList.containsAll(listOfStores)){
		println "Actual list contains the required stores or groups"

	}else{

		println "Actual list does not contain the required stores or groups"
	}

	//Step 4,5&6: To move the Groups/Stores from Available Groups/Stores to Groups/Stores in Group list box - Step 1

	List<WebElement> StoresCBList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 12)))
	System.out.println(StoresCBList.size())
	for(int i=0;i<StoresCBList.size()-18;i++)
	{
		StoresCBList.get(i).click()
	}

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/moveToButton'))

	String StoreInGroupList=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","StoresInGroupList")
	String[] groupList1=StoreInGroupList.split(',')
	ArrayList<String> listOfStoresinGroupListbox=new ArrayList<String>(Arrays.asList(groupList1))
	System.out.println(listOfStoresinGroupListbox)

	WebUI.delay(10)

	List<WebElement> secondStoreListBox = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 11)))

	List<String> actualList1=new ArrayList<String>();

	for(int i=0;i<secondStoreListBox.size();i++){

		actualList1.add(i,secondStoreListBox.get(i).getText())

	}
	System.out.println(actualList1)

	WebUI.delay(10)

	if(actualList1.containsAll(listOfStoresinGroupListbox)){
		println "Selected stores are displayed in Group/Stores in Group listbox"

	}else{

		println "Selected stores are not displayed in Group/Stores in Group listbox"
	}

	//Step 7: To Save the changes
	'click on save button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(10)
	WebUI.back()
	WebUI.delay(10)

	//Step 8: To verify the Group Hierarchy in Reporting Group Management screen
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/expandCollapseLink'))
	WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/childItemHMEtest'),10)

	//Step 9: // Store list page doesn't link with reports

	//Step 13: To verfiy that user is able to collapse the parent group

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/expandCollapseLink'))

	//Step 14: To verify that store list page shows the group names correctly
	// Store list page doesn't link with reports
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/group11inGroupHierarchy'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))
	WebUI.delay(10)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/secondSelectAllCB'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/moveBackToAvailableStores'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(10)
	WebUI.back()

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/group11inGroupHierarchy'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))




}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true




















