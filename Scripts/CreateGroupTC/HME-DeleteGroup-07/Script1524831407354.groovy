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
//import org.junit.After
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath

boolean TCflag=true
try {
	
WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

WebUI.delay(GlobalVariable.MIN_DELAY)


WebDriver driver = DriverFactory.getWebDriver()


//Step 1: To verify that clicking on Group name will navigate the user to Reporting Group Details screen

'Click on Add New Group button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

'Verify Reporting group details screen is displayed'
WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)


CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC7"))
CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupDescTC7"))
WebUI.delay(GlobalVariable.MIN_DELAY)
List<WebElement> StoresCBList1 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 12)))
List<WebElement> storesLabel = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 10)))
ArrayList<String> groupStoresList=new ArrayList<String>()

System.out.println(StoresCBList1.size())
int size=StoresCBList1.size()

for(int i=0;i<size;i++){
	  
		   groupStoresList[i]=storesLabel.get(i).getText()
		   StoresCBList1.get(i).click()
		   
	  }
System.out.println(groupStoresList)

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/moveToButton'))

WebUI.delay(GlobalVariable.MIN_DELAY)

List<WebElement> secondStoreListBox1 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 11)))

List<String> actualList1=new ArrayList<String>();

for(int i=0;i<secondStoreListBox1.size();i++){

	  actualList1.add(i,secondStoreListBox1.get(i).getText())

}
System.out.println(actualList1)

WebUI.delay(GlobalVariable.MED_DELAY)
boolean exists=false;
if(actualList1.containsAll(groupStoresList)){
	exists=true;
	  println "Selected stores are displayed in Group/Stores in Group listbox"

}else{

	  println "Selected stores are not displayed in Group/Stores in Group listbox"
	  if(!TCflag)
	    TCFlag =false;
		WebUI.takeScreenshot()
}
 StoresCBList1 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 12)))
 storesLabel = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 10)))
 List<String>  storesLabelList=new ArrayList<String>();
 
 for(int i=0;i<storesLabel.size();i++){
	 
		   storesLabelList.add(i,storesLabel.get(i).getText())
	 
	 }
	 
 
 println("storesLabel"+storesLabelList)

'click on save button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
WebUI.delay(GlobalVariable.MED_DELAY)
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)

List<WebElement> groupHierarchyList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))
List<String> actualgroupList=new ArrayList<String>();

for(int i=0;i<groupHierarchyList.size();i++){

	  actualgroupList.add(i,groupHierarchyList.get(i).getText())

}

String groupXpath= "//span[text()='"+CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC7")+"']"
println(groupXpath)

driver.findElement(By.xpath(groupXpath)).click();

WebUI.delay(GlobalVariable.MED_DELAY)

//Step 2: To verify the Group name and Group description pre-populated in the screen


'Verify Group Name and Group description'


String groupNameAttr =WebUI.getAttribute(findTestObject('ReportingGroupManagement/groupNameTxt'),"value")
WebUI.verifyMatch(groupNameAttr, CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC7"), false)

String groupDescAttr =WebUI.getAttribute(findTestObject('ReportingGroupManagement/groupDescTxt'),"value")
WebUI.verifyMatch(groupDescAttr, CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupDescTC7"), false)



//Step 3:To verify the Groups/Stores displayed in Available Groups/Stores list box


List<WebElement> availableStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 10)))

List<String> actualList=new ArrayList<String>();

for(int i=0;i<availableStoreList.size();i++){

	actualList.add(i,availableStoreList.get(i).getText())

}
System.out.println(actualList)
System.out.println(storesLabelList)

if(actualList.containsAll(storesLabelList)){
	println "Selected stores are displayed in Group/Stores in Group listbox"

}else{

	println "Selected stores are not displayed in Group/Stores in Group listbox"
}



//Step4: To verify that assigned groups/stores are shown properly under Group/Stores list box

secondStoreListBox1 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 11)))

 actualList2=new ArrayList<String>();

for(int i=0;i<secondStoreListBox1.size();i++){



}
System.out.println(actualList2)


if(actualList1.containsAll(actualList2)){
	println "Selected stores are displayed in Group/Stores in Group listbox"

}else{

	println "Selected stores are not displayed in Group/Stores in Group listbox"
	
	if(!TCflag)
	TCFlag =false;
	
	WebUI.takeScreenshot()
}

//step5: To verify that application displays pop up message if user is deleting a group


CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportingGroupManagement/deleteBtn'))
WebUI.delay(GlobalVariable.MED_DELAY)

String confirmToDeleteText = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportingGroupManagement/confirmToDeleteText'))
String confirmToDeleteText2= CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","confirmDeleteTxt")
WebUI.verifyMatch(confirmToDeleteText,confirmToDeleteText2,false)


WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/confirmToDelNoBtn'),5)
WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/confirmToDelBtn'),6)

//step6: To verify that clicking on cancel button will not delete the group


CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelNoBtn'))

 groupNameAttr =WebUI.getAttribute(findTestObject('ReportingGroupManagement/groupNameTxt'),"value")
WebUI.verifyMatch(groupNameAttr, CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC7"), false)


//Step7: To delete a group - Step 1

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportingGroupManagement/deleteBtn'))

WebUI.delay(GlobalVariable.MED_DELAY)
confirmToDeleteText = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportingGroupManagement/confirmToDeleteText'))
confirmToDeleteText2= CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","confirmDeleteTxt")
WebUI.verifyMatch(confirmToDeleteText,confirmToDeleteText2,false)

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))
//Step8: To delete a group - Step 2

//WebUI.verifyElementNotPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)
WebUI.back()
WebUI.delay(GlobalVariable.MED_DELAY)


//Step9:To verify the Group Hierarchy in Reporting Group Management screen


List<WebElement> groupHierarchyList2 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 13)))
List<String> actualgroupList2=new ArrayList<String>();

for(int i=0;i<groupHierarchyList2.size();i++){

	  actualgroupList2[i]=groupHierarchyList2.get(i).getText()

}
System.out.println(actualgroupList2)

String groupName=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","groupNameTC7")


if(actualgroupList2.contains(groupName))
{
	  println "Group  is available in group hierarchy"
	 
}
else{

	  println "Required group is not available in tree structure"
	  
	  if(!TCflag)
	  TCFlag =false;
	  WebUI.takeScreenshot()
				
}

//Step11:To verify that available stores are shown correctly for a new group


'Click on Add New Group button'
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

'Verify Reporting group details screen is displayed'
WebUI.verifyElementPresent(findTestObject('ReportingGroupManagement/reportingGroupDetails'), 10)

storesLabel = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 10)))
groupStoresList=new ArrayList<String>()


for(int i=0;i<storesLabel.size();i++){
	  
		   groupStoresList[i]=storesLabel.get(i).getText()
		   
		   
	  }

if(actualgroupList2.containsAll(groupStoresList))
{
println "Selected stores are displayed in Group/Stores in Group listbox"

}else{

	println "Selected stores are not displayed in Group/Stores in Group listbox"
	if(!TCflag)
	TCFlag =false;
	
	WebUI.takeScreenshot()
}

WebUI.delay(GlobalVariable.MIN_DELAY)

//Step10:To verify that store list page shows the group names correctly
//this step cannot be done right now since group page and store page is not synchronized

//Step11:To verify that available stores are shown correctly for a new group
//this step cannot be done right now since group page and store page is not synchronized


//Step12:To verify the Groups/Stores displayed in Available Groups/Stores list box
//this step cannot be done right now since group page and store page is not synchronized

//Step13:To verify that user will be able to add the stores which were removed from the group as the group was deleted - Step 1
//this step cannot be done right now since group page and store page is not synchronized

//Step14:To verify that user will be able to add the stores which were removed from the group as the group was deleted - Step 2
//this step cannot be done right now since group page and store page is not synchronized


//Step15:To verify that user will be able to add the stores which were removed from the group as the group was deleted - Step 3
//this step cannot be done right now since group page and store page is not synchronized


CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))
assert TCflag==true
} catch (Exception e) {
e.printStackTrace()
}
