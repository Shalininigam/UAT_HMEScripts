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
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

boolean TCflag=true
try{

	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Pre-Condition: fetching store name from stores list

	//Step 1: To verify that user is able to select Groups

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'),

	CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrp"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'),

	CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String groupmain=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrp")

	WebDriver driver = DriverFactory.getWebDriver()

	List<WebElement> grouplist = driver.findElements(By.xpath("//ul[@class='unlinked-grouplist']/li"))
	WebUI.delay(GlobalVariable.MED_DELAY)

	for(int i=0; i<grouplist.size();i++){
	//for(int i=0; i<100;i++){

		println grouplist.get(i).getText()

		if(grouplist.get(i).getText().contains("0000")){

			driver.findElement(By.xpath("(//ul[@class='unlinked-grouplist']/li/label)["+(i+1)+"]")).click()

		}
	}

	driver.findElement(By.xpath("//button[contains(text(),'>')]")).click()


	WebUI.delay(GlobalVariable.MED_DELAY)

	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[1]")).click()

	WebUI.delay(GlobalVariable.MIN_DELAY)
	driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[2]")).click()



	//Step 2: To verify that user is able to select From and To date by using calendar controls

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	String[] monthTextArray= monthText.split(" ")
	String month = monthTextArray[0]

	String monthTextValue_march=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month_January")

	if(!monthTextValue_march.equals(month))
	{
		while(!monthTextValue_march.equals(month))
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

	String monthTextValue_april=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month_april")

	if(!monthTextValue_april.equals(month))
	{
		while(!monthTextValue_april.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector2'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}


	}

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","enddate_april")

	WebUI.delay(GlobalVariable.MED_DELAY)

	String toDate="(//div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+endDate+"'])[3]"

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

	/*CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advance_options_time'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advance_options_min'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advancedoption_am'))

	String open_textboxdata =WebUI.getAttribute(findTestObject('ReportsPage/advance_option_opentextbox'),"value")

	if(!open_textboxdata.isEmpty()){

		System.out.println("8:00 am time shown ")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("8:00 am time is not showing")
	}

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/timemeasure_questionmark'))*/
	
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advanced_openTimeText'))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportsPage/advanced_openTimeText'),"8:00 am")

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/tooltip'))

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

	//Step 7: To verify that user is able to set close time



	/*CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advancedoption_closetimehour'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advance_options_min'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advanceoptions_pm'))

	String open_textboxdata1 =WebUI.getAttribute(findTestObject('ReportsPage/advance_option_closetextbox'),"value")

	if(!open_textboxdata1.isEmpty()){

		System.out.println("3:00 pm time shown ")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("3:00 pm time is not showing")
	}

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/timemeasure_questionmark'))*/
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advanced_openTimeText'))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportsPage/advanced_openTimeText'),"03:00 pm")

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/tooltip'))
	
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/Report_advancedoption'))

	//Step 8: To verify that user will not be able to generate day summary report for more than 1 month
	WebUI.delay(GlobalVariable.MED_DELAY)
	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	WebUI.verifyElementPresent(findTestObject('ReportsPage/report_failure_alertmsg'), 10)

	//Step 9: Pending


	// for deleting created group
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/Summarygrp'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))


}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true