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
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;


boolean TCflag=true
try{
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)
	WebUI.delay(GlobalVariable.MED_DELAY)
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.MED_DELAY)
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

	for(int i=0; i<50;i++){


		println grouplist.get(i).getText()

		if(grouplist.get(i).getText().contains("0000")){

			driver.findElement(By.xpath("(//ul[@class='unlinked-grouplist']/li/label)["+(i+1)+"]")).click()

		}
	}

	driver.findElement(By.xpath("//button[contains(text(),'>')]")).click()

	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/secondSelectAllCB'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[2]")).click()

	List<WebElement> timemeasureoptions= driver.findElements(By.xpath("//select[@class='time-measures']/option"))

	int count=0

	for(int i=0;i<timemeasureoptions.size();i++){

		println timemeasureoptions.get(i).getText()

		if(timemeasureoptions.get(i).getText()=="Daypart"){

			timemeasureoptions[i].click()
			count++;
			break;
		}

	}


	if(count<4){
		System.out.println("Daypart option is selected")

	}else{
		if(TCflag)
			TCflag=false
		println "Daypart option is not  selected"

	}



	//Step 2: To verify that user is able to select From and To date by using calendar controls

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	String[] monthTextArray= monthText.split(" ")
	String month = monthTextArray[0]

	String monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

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

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate")

	String toDate="(//div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+endDate+"'])[3]"

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}

	//Step 4: To Generate report

	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/summaryReport'), 10)


	//Step 5: To verify the Start time and End time displayed in Report
	'verify startTime in SummaryReports page'
	String startTime =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","reportstartTime")
	WebUI.verifyElementText(findTestObject('SummarizedReportPage/report_starttime'),startTime)

	//Step 6: To verify the print time

	DateFormat dateFormat = new SimpleDateFormat("dd");
	Date date = new Date();
	String dateapp=dateFormat.format(date)
	WebUI.delay(GlobalVariable.MED_DELAY)


	String printtimefromreport=driver.findElement(By.xpath("//span[@class='report-print-time-value']")).getText()
	String[] s1=printtimefromreport.split(",")
	String[] s2=s1[0].split(" ")
	if(dateapp.contains(s2[1])){

		println "Print time is displayed"

	}else{
		if(TCflag)
			TCflag=false
		println "Print time is not  displayed"

	}


	//Step 7: To verify the column headers displayed in Average Time(min:sec) grid

	WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/summary_avaragetimemin_sec'), 10)



	//Step 8: To verify the rows displayed under Stores column header for Daypart 1

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/storeFirst'))
	WebUI.delay(GlobalVariable.LONG_DELAY)
	String daypart =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","daypart")

	String daypart_report= driver.findElement(By.xpath("//span[@class='timeSpan show']")).getText()

	if(daypart_report.equals(daypart)){

		println "Daypart is Column is displayed"
	}else{
		if(TCflag)
			TCflag=false
		println "Daypart is Column is not displayed"
	}

	//Step 9 to 11 are kept pending as it is related to email and PDF

	//Step 12: To verify that user is able to view Daypart report

	String daypartdispay=driver.findElement(By.xpath("(//table[@class='summaryreport-table']//tr[2]/th/span)[1]")).getText()


	//CustomKeywords.'projectSpecific.Reusability.getText'("SummarizedReportPage/daypart_report")

	if(daypartdispay.equals("Daypart")){

		println "Daypart Report is displayed"
	}else{
		if(TCflag)
			TCflag=false
		println "Daypart Report is not displayed"
	}

	//Step 13: To verify that user is able to view raw car data report

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/daypart_column'))
	WebUI.delay(GlobalVariable.LONG_DELAY)

	WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/rawCarDataHeading'), 10)

	//Step 14:To verify the column headers displayed in DP1 OPEN-11:59

	// Report is not generating


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


