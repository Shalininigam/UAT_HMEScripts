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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
//import org.junit.After
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath

boolean TCflag=true
try{
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))
	WebUI.delay(10)

	//Step 1: To navigate to Reporting group details screen

	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	'Verify Reporting group details screen is displayed'
	WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)

	//Step 2: To verify that mandatory fields are marked with asterisk symbol
	String grpName = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportingGroupManagement/groupName'))
	println "grpName"
	if(grpName.contains("*"))
	{
		System.out.println("Group Name text box is marked with asterisk symbol")
	}else{
		System.out.println("Group Name text box is not marked with asterisk symbol")
	}

	//Step 3: To verify that available groups/store are shown properly under Available Group/Stores list box

	String StoreList=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","availableStoreList")
	String[] groupList=StoreList.split(',')
	ArrayList<String> listOfStores=new ArrayList<String>(Arrays.asList(groupList))
	System.out.println(listOfStores)

	WebUI.delay(10)

	WebDriver driver = DriverFactory.getWebDriver()

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

	//Step 4:To verify that vertical scroll box is shown in Available Groups/Stores list box
	//out of scope

	//Step 5: To verify that no groups/stores are shown under Groups/Stores in Group list box
	List<WebElement> secondStoreListBox = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 11)))
	List<String> list=new ArrayList<String>();

	for(int i=0;i<secondStoreListBox.size();i++){

		list.add(i,secondStoreListBox.get(i).getText())

	}

	if(list.size()==0)
	{
		println "No Groups/Stores is displayed in Groups/Stores in Group list box"
	}else{
		println "Groups/Stores is displayed in Groups/Stores in Group list box"
	}

	//Step 6: To enter Group Name and Group description
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupName"))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))

	'Verify Group Name and Group description'
	String groupNameAttr =WebUI.getAttribute(findTestObject('ReportingGroupManagement/groupNameTxt'),"value")
	WebUI.verifyMatch(groupNameAttr, CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupName"), false)

	String groupDescAttr =WebUI.getAttribute(findTestObject('ReportingGroupManagement/groupDescTxt'),"value")
	WebUI.verifyMatch(groupDescAttr, CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"), false)

	//Step 7: To verify that clicking on Select All check box selects all the Groups/Stores in Available Groups/Stores list box

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/AvailableAllStoresCB'))

	List<WebElement> availableStoresCheckBoxList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 12)))

	int index=1
	boolean flag = true;

	for(int i=0;i<availableStoresCheckBoxList.size();i++)
	{

		WebElement element =(WebElement)availableStoresCheckBoxList[i];
		if(!element.isSelected())
		{
			flag = false;
			break;
		}

	}

	if(flag){
		System.out.println("All Available Store checkboxes are checked")

	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Available Store checkboxes are unchecked")
	}

	WebUI.delay(4)
	//Step 8: To verfiy that user is able to unselect Groups/Stores in Availlable/Stores list box

	List<WebElement> StoresCBList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 12)))
	for(WebElement checkbox : StoresCBList )
	{
		if(checkbox.isSelected())
		{
			checkbox.click()
		}
	}

	//Step 9: To move the Groups/Stores from Available Groups/Stores to Groups/Stores in Group list box

	List<WebElement> StoresCBList1 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 12)))
	System.out.println(StoresCBList1.size())
	for(int i=0;i<StoresCBList1.size()-18;i++)
	{
		StoresCBList1.get(i).click()
	}

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/moveToButton'))

	String StoreInGroupList=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","StoresInGroupList")
	String[] groupList1=StoreInGroupList.split(',')
	ArrayList<String> listOfStoresinGroupListbox=new ArrayList<String>(Arrays.asList(groupList1))
	System.out.println(listOfStoresinGroupListbox)

	WebUI.delay(10)

	List<WebElement> secondStoreListBox1 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 11)))

	List<String> actualList1=new ArrayList<String>();

	for(int i=0;i<secondStoreListBox1.size();i++){

		actualList1.add(i,secondStoreListBox1.get(i).getText())

	}
	System.out.println(actualList1)

	WebUI.delay(10)

	if(actualList1.containsAll(listOfStoresinGroupListbox)){
		println "Selected stores are displayed in Group/Stores in Group listbox"

	}else{

		println "Selected stores are not displayed in Group/Stores in Group listbox"
	}

	//Step 10 : To Save the changes

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/saveStatusMsg'), 10)
	WebUI.delay(10)

	WebUI.back()

	WebUI.delay(10)

	//Step 11: To verify the Group Hierarchy in Reporting Group Management screen
	List<WebElement> groupHierarchyList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))
	List<String> actualgroupList=new ArrayList<String>();

	for(int i=0;i<groupHierarchyList.size();i++){

		actualgroupList.add(i,groupHierarchyList.get(i).getText())

	}
	System.out.println(actualgroupList)

	String groupName=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupName")

	if(actualgroupList.contains(groupName))
	{
		println "Group 11 is available in group hierarchy"
				
	}
	else{

		println "Required group is not available in tree structure"
	}

	//Step 12: To verfiy that user is able to expand the parent group

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/expandCollapseLink'))

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

















