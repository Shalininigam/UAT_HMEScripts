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

	//Step1: To verify that public admin is able to login as store admin
	WebUI.navigateToUrl(GlobalVariable.SuperAdminUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","publicadmin"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Password"))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	//Step2:To verify that logged in user name is displayed correctly

	WebUI.verifyElementPresent(findTestObject('Object Repository/HomePage/loggedinUser'),10)

	String loggedInUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('Object Repository/HomePage/loggedinUser'))
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

	//Step3: To verify that store admin name is displayed
	String  welcomeUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('Object Repository/HomePage/welcomeUserText'))
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
	//Step4: To navigate to stores page
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	WebUI.verifyElementPresent(findTestObject('StorePage/storeListHeading2'), 5)

	WebUI.verifyElementPresent(findTestObject('Object Repository/StorePage/storeList'),5)


	//Step 5: To verify that public admin is able to view store details

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/StorePage/adminView_Details'))

	WebUI.verifyElementPresent(findTestObject('StorePage/storedetailslabel'), 5)

	WebUI.verifyElementPresent(findTestObject('StorePage/zoomlabel'), 5)

	String storedetail_label = CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","admin_Store_Labels")

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

	String[] storeArraylabels=storedetail_label.split(',')
	def storeListlabels=new ArrayList(Arrays.asList(storeArraylabels))
	storeListlabels.unique()
	System.out.println(storeListlabels)

	def int count=0
	for(int i=0;i<storeHeaders.size();i++){
		String storeHeadingText = storeHeaders.get(i).getText().trim()
		storeHeadingText=storeHeadingText.replace("*","")
		boolean execFlag = WebUI.verifyMatch(storeHeadingText, storeListlabels[i].toString().trim(), false)
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

	String storeDetails = CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","admin_Store_Details")

	String[] storeArray=storeDetails.split(',')

	def storeList=new ArrayList(Arrays.asList(storeArray))
	storeList.unique()
	System.out.println(storeList)

	List<WebElement> storeElements = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 21)))

	List<String> actualList=new ArrayList<String>();

	for(int i=0;i<storeElements.size();i++){

		actualList.add(i,storeElements.get(i).getText())
	}
	System.out.println(actualList)

	boolean execFlag1=true

	for(int j=0;j<actualList.size();j++)
	{

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
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the Store details labels")
		WebUI.takeScreenshot()


	}

	//Step6: To verify that public admin will not be able to edit store details

	String attrDisabled=WebUI.getAttribute(findTestObject('StorePage/brandSelect'), "disabled")


	if(attrDisabled){

		System.out.println("Brand name is not editable field")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Brand name is editable field")
		WebUI.takeScreenshot()

	}





	//To check other fields in the tab will be non editable

	List<WebElement> inputFields = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 22)))
	List<String> inputFieldsResults =new ArrayList<String>();

	for(int i=0;i<inputFields.size();i++){
		if(i!=1)
		{
			String attr = inputFields[i].getAttribute("disabled")

			
			if(attr)

				inputFieldsResults[i]="disabled"
			else
				inputFieldsResults[i]="enabled"
		}
		else
			inputFieldsResults[i]="disabled"
		WebUI.delay(3)

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


	attrDisabled=WebUI.getAttribute(findTestObject('StorePage/store_City'), "disabled")

	if(attrDisabled)
	{
		System.out.println("Store City is disabled")
	}

	else{
		if(TCflag)
			TCflag=false
		System.out.println("Store City is not disabled")
		WebUI.takeScreenshot()

	}

	attrDisabled=WebUI.getAttribute(findTestObject('StorePage/store_Region'), "disabled")

	if(attrDisabled)
	{
		System.out.println("Store Region is disabled")
	}

	else{
		if(TCflag)
			TCflag=false
		System.out.println("Store Region is not disabled")
		WebUI.takeScreenshot()

	}

	attrDisabled=WebUI.getAttribute(findTestObject('StorePage/store_Country'), "disabled")

	if(attrDisabled)
	{
		System.out.println("Store Country is disabled")
	}

	else{
		if(TCflag)
			TCflag=false
		System.out.println("Store Country is not disabled")
		WebUI.takeScreenshot()

	}


	//timezone is enabled
	attrDisabled=WebUI.verifyElementClickable(findTestObject('StorePage/store_timeZone'))

	if(attrDisabled)
	{
		System.out.println("Store timeZone is enabled")
	}

	else{
		if(TCflag)
			TCflag=false
		System.out.println("Store timeZone is  disabled")
		WebUI.takeScreenshot()

	}

	//Step7:To verify that public admin will be able to view only the stores based on user has access to view

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/storeDialogClose'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/UsersLink'))

	String searchUserText= CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","userSearchText1")


	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UsersPage/searchTextBox'),searchUserText)


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/searchButton'))


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewUserLink1'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/userstoresLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)


	List<WebElement> userStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 23)))

	int userstoreListSize= userStoreList.size()


	//Step8: To navigate to stores page

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	List<WebElement> adminStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 23)))

	int adminstoreListSize= adminStoreList.size()


	if (userstoreListSize != adminstoreListSize||adminstoreListSize==0)

	{
		System.out.println( searchUserText+ " User can see only stores related to him or No Stores are found")
	}

	else{
		if(TCflag)
			TCflag=false
		System.out.println( searchUserText+" User can see stores which are not related to him")
		WebUI.takeScreenshot()

	}

CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/Admin_SignOut'))



}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true


