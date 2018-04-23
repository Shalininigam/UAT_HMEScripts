import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver
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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By


//step 1:To verify that user is able to navigate to Stores page
/*Stores page will be displayed. The below column headers will be displayed in the grid*/

boolean TCflag=true

try{

'User login to Application'
WebUI.navigateToUrl(GlobalVariable.Superuser_url)

CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Username2"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Password"))

String storeDetails = CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","StoreDetails")

String storedetail_label = CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","Store_labels")

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/Stores'))

WebUI.verifyElementPresent(findTestObject('HomePage/Verify_store'), 5)

WebDriver driver = DriverFactory.getWebDriver()

List<WebElement> storeHeaders = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 1)))

String[] storeArraylabels=storedetail_label.split(',,')

def storeListlabels=new ArrayList(Arrays.asList(storeArraylabels))
storeListlabels.unique()
System.out.println(storeListlabels)

def int count=1
for(int i=1;i<storeHeaders.size();i++){

	boolean execFlag = WebUI.verifyMatch(storeHeaders.get(i).getText().trim(), storeListlabels[i].toString().trim(), false)
	if(execFlag)
	{
		count++;
	}
}

if(count==storeHeaders.size()){
	WebUI.takeScreenshot()

}
else{
	TCflag=false
	WebUI.takeScreenshot()
}

//step 2: To view/edit store details

/*  Store Details pop up window will be displayed.
 *  It will have Store Details and Zoom tabs.
 *  Store Details tab will be displayed by default*/

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/View_editbtn'))

WebUI.verifyElementPresent(findTestObject('StorePage/storedetailslabel'), 5)

WebUI.verifyElementPresent(findTestObject('StorePage/zoomlabel'), 5)

String defaultareaselected=WebUI.getAttribute(findTestObject('StorePage/defaultstoredetsails'), "aria-selected")

if(defaultareaselected)
{
	System.out.println("Store Details is set by default")
}

else{
	if(TCflag)
		TCflag=false
	System.out.println("Store Details is not set by default")

}

// Step 3: To verify the details in Store details tab

/*The below details will be displayed in Store details 
 tab:Brand : KFC, Store Name : Store 1, Store Number : 00101000, Group : Group A, Report Group : Group 11
 Account email : mohanrk@nousinfo.com, Address Line 1: 55, Stowe Dr, Address line 2:, Address line 3:
 Address Line 4:, City : New York, Region : NY, Zip : 10058, Country : United States, Phone : 1234512345
 Fax:*/

String[] storeArray=storeDetails.split(',')

def storeList=new ArrayList(Arrays.asList(storeArray))
storeList.unique()
System.out.println(storeList)

List<WebElement> storeElements = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 2)))

List<String> actualList=new ArrayList<String>();

for(int i=0;i<storeElements.size();i++){

	actualList.add(i,storeElements.get(i).getText())
}
System.out.println(actualList)

boolean execFlag1=true

for(int j=0;j<actualList.size();j++){

	execFlag1=	WebUI.verifyMatch(storeList.get(j).toString().trim(), actualList.get(j).toString().trim(), false)

	if(!execFlag1)
	{
		System.out.println("Store details lables are not matched ");
		break;

	}
}

if(execFlag1)
{
	System.out.println("Store details labels are verified")
}
else{
	if(TCflag)
		TCflag=false
	System.out.println("Failed to verify the Store details labels")

}

//step 4: To verify that user is able to edit Store Name
/*Store Name will be editable field.*/

boolean editfield= WebUI.clearText(findTestObject('StorePage/storeName'))

if(editfield){

	System.out.println("The store name is editable field")

}else{
	if(TCflag)
		TCflag=false
	System.out.println("The store name is not editable field")

}

// step 5:To verify that user will not be able to edit other than store name field
/*All the other fields in the tab will be non editable*/

List<WebElement> inputFields = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 3)))
List<String> inputFieldsResults =new ArrayList<String>();
int index=3 

for(int i=0;i<inputFields.size();i++){
	String xpathval="//form[@name='store_submit']/table/tbody/tr["+index+"]//input"
	
	LinkToSearch = WebUI.modifyObjectProperty(findTestObject('StorePage/storeInputValue'), 'xpath','equals',xpathval, true)
	
	String attr1=WebUI.getAttribute(LinkToSearch, "disabled")
	if(attr1)
		inputFieldsResults[i]="editable"
	else
		inputFieldsResults[i]="noneditable"
}

String s= inputFieldsResults[1]
boolean isEditable=false
for(int j=2;j<inputFieldsResults.size();j++)
{
	String s1=inputFieldsResults[j]
	if(!s1.equals(s)){
		isEditable=true
		break
	}

}
if(!isEditable){
	System.out.println("Store fields are not editable")

}else{
	if(TCflag)
		TCflag=false
	System.out.println("Store fields are editable")
}


 attrDisabled=WebUI.getAttribute(findTestObject('StorePage/storebrandName'), "disabled")

if(attrDisabled)
{
	System.out.println("Store brand is disabled")
}

else{
	if(TCflag)
		TCflag=false
	System.out.println("Store brand is not disabled")

}

attrDisabled=WebUI.getAttribute(findTestObject('StorePage/storeCountryName'), "disabled")

if(attrDisabled)
{
	System.out.println("Store countryName is disabled")
}

else{
	if(TCflag)
		TCflag=false
	System.out.println("Store countryName is not disabled")

}
}catch(Exception e){

e.printStackTrace()
println "Exception occured"
if(TCflag)
		TCflag=false

}

assert TCflag==true




