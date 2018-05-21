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
	
	//Step 1: To verify that public admin is able to login as store admin
	WebUI.navigateToUrl(GlobalVariable.SuperAdminUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.MED_DELAY)
	
	//Step 2: To verify that logged in user name is displayed correctly
	WebUI.verifyElementPresent(findTestObject('HomePage/loggedinUser'),10)
	
	String loggedInUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/loggedinUser'))
	loggedInUser=loggedInUser.trim()
	
	String loggedinText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","loggedinText")

	if(loggedInUser.equals(loggedinText))
		System.out.println("Verified logged in user Text")
	else
	{
		if(TCflag)
		TCflag=false
		System.out.println("Could not verify logged in user Text")
	}
	
	//Step 3 : To verify that store admin name is displayed
	String  welcomeUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/welcomeUserText'))
	welcomeUser=welcomeUser.trim()
	String welcomeText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","welcomeText")
	
	if(welcomeUser.equals(welcomeText))
	System.out.println("Welcome user Text is displayed")
else
{
	if(TCflag)
	TCflag=false
	System.out.println("Welcome user Text is not displayed")
}

//Step 4: To navigate to stores page
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

WebUI.verifyElementPresent(findTestObject('StorePage/StoreListHeading'), 5)

//Step 5: To verify that public admin is able to view store details

WebUI.verifyElementPresent(findTestObject('StorePage/storeList'),5)

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/adminView_Details'))

WebUI.verifyElementPresent(findTestObject('StorePage/storedetailslabel'), 5)

WebUI.verifyElementPresent(findTestObject('StorePage/zoomlabel'), 5)

String storeDetails = CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","StoreDetails")

String storedetail_label = CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","Store_labels")


String defaultareaselected=WebUI.getAttribute(findTestObject('StorePage/defaultstoredetsails'), "aria-selected")

if(defaultareaselected)
{
	System.out.println("Store Details is set by default")
}

else{
	if(TCflag)
		TCflag=false
	System.out.println("Store Details is not set by default")
	WebUI.takeScreenshot()

}


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

	// To verify the details in Store details tab

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
			WebUI.takeScreenshot()
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
		WebUI.takeScreenshot()

	}

	//step 6: To verify that public admin will not be able to edit store details
	/*Store Name will be editable field.*/

	boolean editfield= WebUI.clearText(findTestObject('StorePage/storeName'))

	if(editfield){

		System.out.println("The store name is editable field")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("The store name is not editable field")
		WebUI.takeScreenshot()

	}

	//To verify that user will not be able to edit other than store name field
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
		WebUI.takeScreenshot()
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
		WebUI.takeScreenshot()

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
		WebUI.takeScreenshot()

	}
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))
		
	//Step 7: To verify that public admin will be able to view only the stores based on user has access to view
	
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)
	
	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","otherUser"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","otherUserPwd"))
	
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 8: To navigate to stores page
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
	
}catch(Exception e){
e.printStackTrace()
println "Exception of element not found"
if(TCflag)
	TCflag=false
}

assert TCflag==true

