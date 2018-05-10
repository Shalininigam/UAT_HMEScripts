import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver
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
	WebUI.delay(GlobalVariable.MIN_DELAY)
	
	//Pre-Condition : To redirect to Group hirerchy page
	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	//Step 1: To navigate to Reporting group details screen
	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	'Verify Reporting group details screen is displayed'
	WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)

	//Creating 2 groups
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","dummyGroupName1"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.back'()

	WebUI.delay(GlobalVariable.MIN_DELAY)
	
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","dummyGroupName2"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()

	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/groupdummy1'))


	//Step 2: To verify that available groups/stores are shown properly under Available Group/Stores list box

	String StoreList=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","dummyGroupName2")
	
	ArrayList<String> listOfStores=new ArrayList<String>(Arrays.asList(StoreList))
	System.out.println(listOfStores)

	WebUI.delay(GlobalVariable.MED_DELAY)

	WebDriver driver = DriverFactory.getWebDriver()

	List<WebElement> availableStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 10)))

	List<String> actualList=new ArrayList<String>();

	for(int i=0;i<availableStoreList.size();i++){

		actualList.add(i,availableStoreList.get(i).getText())

	}
	System.out.println(actualList)

	if(actualList.containsAll(listOfStores)){
		println "Groups & Stores are displayed in Available Groups/Stores listbox"

	}else{

		if(TCflag)
			TCflag=false

		println "Groups & Stores are not displayed in Available Groups/Stores listbox"
		WebUI.takeScreenshot()
	}

	//Deleting groups which are created as precondtion for step 2
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))
	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()

	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/groupdummy2'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))
	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	//Step 3: To verify that no groups/stores are shown under Groups/Stores in Group list box
	List<WebElement> secondStoreListBox = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 11)))
	List<String> list=new ArrayList<String>();

	for(int i=0;i<secondStoreListBox.size();i++){

		list.add(i,secondStoreListBox.get(i).getText())

	}

	if(list.size()==0)
	{
		println "No Groups/Stores is displayed in Groups/Stores in Group list box"
	}else{
		if(TCflag)
			TCflag=false
		println "Groups/Stores is displayed in Groups/Stores in Group list box"
		WebUI.takeScreenshot()
	}

	//Step 4: To enter Group Name and Group description
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC6"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupDescTC6"))

	'Verify Group Name and Group description'

	CustomKeywords.'uiaction.CommonUIActions.verifyMatch'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC6"))

	CustomKeywords.'uiaction.CommonUIActions.verifyMatch'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupDescTC6"))

	//Step 5&6: To verfiy that user is able to select Groups/Stores in Availlable/Stores list box
	WebUI.delay(GlobalVariable.MIN_DELAY)

	List<WebElement> StoresCBList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 12)))
	List<WebElement> storesLabel = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 10)))
	ArrayList<String> groupStoresList=new ArrayList<String>()
	System.out.println(StoresCBList.size())
	int  size =StoresCBList.size()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	for(int i=0;i<size;i++){

		groupStoresList[i]=storesLabel.get(i).getText()
		StoresCBList.get(i).click()


	}
	System.out.println(groupStoresList)

	WebUI.delay(GlobalVariable.MED_DELAY)

	/*String storeXpath="//li[text()='"+CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","storeName")+"']"
	println storeXpath
	groupStoresList[3]=driver.findElement(By.xpath(storeXpath)).getText()

	storeXpath=storeXpath+"/label/input"
	driver.findElement(By.xpath(storeXpath)).click()
	WebUI.delay(GlobalVariable.MIN_DELAY)*/

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/moveToButton'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	List<WebElement> secondStoreListBox1 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 11)))

	List<String> actualList1=new ArrayList<String>();

	for(int i=0;i<secondStoreListBox1.size();i++){

		actualList1.add(i,secondStoreListBox1.get(i).getText())

	}
	System.out.println(actualList1)

	WebUI.delay(GlobalVariable.MED_DELAY)

	if(actualList1.containsAll(groupStoresList)){
		println "Selected stores are displayed in Group/Stores in Group listbox"

	}else{

		if(TCflag)
			TCflag=false
		println "Selected stores are not displayed in Group/Stores in Group listbox"
		WebUI.takeScreenshot()
	}

	//Step 7: To Save the changes
	'click on save button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	//Step 8: To verify the Group Hierarchy in Reporting Group Management screen
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/expandLink'))

	//Step 9&10: To verfiy that user is able to collapse the parent group
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/expandLink'))

	//Step 12: To verify that store list page shows the group names correctly
	
	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
	
	List<WebElement> storesList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 16)))
			
	for(int i=1;i<storesList.size();i++)
	{
		String reportgroup= "(//div[@class='ctable']/table/tbody/tr[@class='tdata clear'])["+i+"]/td[7]"
		String reportgroupTxt=driver.findElement(By.xpath(reportgroup)).getText()
		if(reportgroupTxt=="AutomationGroup1")
		{
			println "Report group has right group name"
		}
		else{
			if(!TCflag)
			{
			TCflag = false
			println "Report group has not right group name"
			WebUI.takeScreenshot()
			break;
			}
		}
		
	}
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	

	WebUI.delay(GlobalVariable.MIN_DELAY)

	//Post-requisitics
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/group1inGroupHierarchy'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/secondSelectAllCB'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/moveBackToAvailableStores'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/group1inGroupHierarchy'))
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



































