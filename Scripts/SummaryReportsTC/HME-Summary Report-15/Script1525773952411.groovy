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

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	'Click on View/Edit link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewEditUsers'))

	'Click on Zoom Label'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomlabel'))

	'Click on View Details link on Zoom Tab'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomViewDetails'))

	String storeID =CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/storeID'))
	String storenumber=storeID.trim()
	println storenumber

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	WebUI.navigateToUrl("https://hme-dev-public-cloud-app.azurewebsites.net/")

	//CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	//Step 1: To verify that user is able to select Groups

	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	//Create group
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","dummyGroupName1"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))
	WebUI.delay(GlobalVariable.MED_DELAY)

	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> grouplist = driver.findElements(By.xpath("//ul[@class='unlinked-grouplist']/li"))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	for(int i=0; i<grouplist.size();i++){

		println grouplist.get(i).getText()

		if(grouplist.get(i).getText().contains(storenumber)){

			driver.findElement(By.xpath("(//ul[@class='unlinked-grouplist']/li/label)["+(i+1)+"]")).click()
			driver.findElement(By.xpath("//button[contains(text(),'>')]")).click()
			break;
		}
	}

	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/secondSelectAllCB'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	String groupmain=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","dummyGroupName1")
	driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[1]")).click()

	driver.findElement(By.xpath("//span[contains(text(),'"+storenumber.trim()+"')]")).isDisplayed()

	driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[2]")).click()

	String storesInCriteriaTextBox =CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportsPage/cretiria_store'))

	if(storesInCriteriaTextBox.contains(storenumber))
	{
		println "Store is displayed in Criteria free text box"
	}else{
		println "Store is not displayed in Criteria free text box"
	}

	//Step 2: To verify that user is able to select From and To date by using calendar controls

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	String[] monthTextArray= monthText.split(" ")
	String month = monthTextArray[0]

	String monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","JanMonth")

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}

	}

	String data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate")
	String startDate="(//td[text()='"+data+"'])[1]"
	println startDate
	WebElement startdateEle=driver.findElement(By.xpath(startDate))
	startdateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	String monthTextValue1=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","AprilMonth")

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector2'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}


	}

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate7")

	String toDate="(//td[text()='"+endDate+"'])[2]"

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDateJan")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate8")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}

	'verify include text as None'
	WebUI.verifyElementText(findTestObject('ReportsPage/includeText'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","includeText"))

	'verify format text'
	WebUI.verifyElementText(findTestObject('ReportsPage/formatText'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","formatText"))

	//Step 3: To verify the options available under Advanced options link

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/Report_advancedoption'))

	boolean opencheckbox=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/Report_opencheckbox'))

	if(!opencheckbox){

		System.out.println("Open checkbox is checked bydefault")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Open checkbox is not checked bydefault")
	}

	boolean closecheckbox=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/Report_closecheckbox'))

	if(!closecheckbox){

		System.out.println("Close checkbox is checked bydefault")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Close checkbox is not checked bydefault")
	}

	//Step 4: To verify that user is able to select from time

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/opencheckbox'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/Report_opentimepicker'))

	WebUI.verifyElementPresent(findTestObject('ReportsPage/advancedoption_am'), 10)

	WebUI.verifyElementPresent(findTestObject('ReportsPage/advanceoptions_pm'), 10)

	boolean open_am=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/advancedoption_am'))

	if(open_am){

		System.out.println("AM Drop down boxes  enabled")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("AM Drop down boxes not  enabled")
	}

	boolean open_pm=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/advanceoptions_pm'))

	if(open_pm){

		System.out.println("PM Drop down boxes  enabled")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("PM Drop down boxes not  enabled")
	}

	//Step 5: To verify that user is able to set open time

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advance_options_time'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advance_options_min'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advancedoption_am'))

	String open_textboxdata= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportsPage/advance_option_opentextbox'))

	if(open_textboxdata=="8:00 am"){

		System.out.println("8:00 am time shown ")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("8:00 am time is not showing")
	}

	//Step 6: To verify that user is able to select To time

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advanceoption_closecheckbox'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/Report_closetimepicker'))

	WebUI.verifyElementPresent(findTestObject('ReportsPage/advancedoption_am'), 10)

	WebUI.verifyElementPresent(findTestObject('ReportsPage/advanceoptions_pm'), 10)

	boolean close_am=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/advancedoption_am'))

	if(close_am){

		System.out.println("AM Drop down boxes  enabled")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("AM Drop down boxes not  enabled")
	}

	boolean close_pm=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/advanceoptions_pm'))

	if(close_pm){

		System.out.println("PM Drop down boxes  enabled")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("PM Drop down boxes not  enabled")
	}

	//Step 7: To verify that user is able to set open time

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advancedoption_closetimehour'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advance_options_min'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advanceoptions_pm'))

	String open_textboxdata1= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportsPage/advance_option_opentextbox'))

	if(open_textboxdata1=="3:00 pm"){

		System.out.println("3:00 pm time shown ")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("3:00 pm time is not showing")

	}
	//Step 8: To verify that user will not be able to generate daypart summary report for more than 1 month

	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	String errMessage = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportsPage/errorMsg'))
	String errMsgText =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","DateErrorMsg4")
	WebUI.verifyMatch(errMessage, errMsgText, false)

	//Step 9: To verify that user is able to generate daypart summary report for maximum of one month

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	String[] monthTextArray= monthText.split(" ")
	String month = monthTextArray[0]

	String monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","JanMonth")

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}

	}

	String data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate")
	String startDate="(//td[text()='"+data+"'])[1]"
	println startDate
	WebElement startdateEle=driver.findElement(By.xpath(startDate))
	startdateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	String monthTextValue1=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","AprilMonth")

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector2'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}


	}

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate4")

	String toDate="(//td[text()='"+endDate+"'])[2]"

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDateJan")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate9")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}
	
	//Post-Condition : Deleting the created group
	WebUI.navigateToUrl("https://hme-dev-public-cloud-app.azurewebsites.net/")
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/groupdummy1'))
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/secondSelectAllCB'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/moveBackToAvailableStores'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()

	WebUI.delay(GlobalVariable.MIN_DELAY)
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