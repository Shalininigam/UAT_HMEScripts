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
	WebUI.navigateToUrl(GlobalVariable.AppURL)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Username1"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Password"))
	WebUI.delay(5)

	String reportHeaders =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","ReportsCoulmnHeader")

	'Click on Users'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/Users'))

	'Search for Users'
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UsersPage/Userssearch'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","ReportUserName"))
		
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/User_searchimage'))
	WebUI.delay(5)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewEditUsers'))

	'Click on Reports Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	//Step1 : To verify that user is able to select Groups
	WebUI.delay(4)

	WebDriver driver = DriverFactory.getWebDriver()

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/AllStoresCheckbox'))

	List<WebElement> storesCheckBoxList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 4)))

	int index=1
	boolean flag = true;

	for(int i=0;i<storesCheckBoxList.size();i++)
	{

		WebElement element =(WebElement)storesCheckBoxList[i];
		if(!element.isSelected())
		{
			flag = false;
			break;
		}

	}

	if(flag){
		System.out.println("All Store checkboxes are checked")

	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Store checkboxes are unchecked")
	}

	WebUI.delay(4)

	//Step2 : To verify that user is able to select time measure 

	'Select time'
	WebUI.click(findTestObject('ReportsPage/timeSelection'))

	'Click on week option'
	WebUI.click(findTestObject('ReportsPage/weekTimeOption'))

	WebUI.click(findTestObject('ReportsPage/summaryReportHeading'))

	'verify week is displayed in TimeSelection dropdown'

	WebUI.verifyElementText(findTestObject('ReportsPage/weekTimeOption'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","weekTimeSelection"))
		
    'verify week is displayed in criteria week selection'
	WebUI.verifyElementText(findTestObject('ReportsPage/criteriaTimeMeasure'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","weekTimeSelection"))
	
	//Step 3: To verify that user is able to select From and To date by using calendar controls
	WebUI.delay(4)

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(4)

	String monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))

	String monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

	if(!monthTextValue.equals(monthText))
	{
		while(!monthTextValue.equals(monthText))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))

		}

	}

	String data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate")
	String startDate="//td//a[text()='"+data+"']"
	println startDate
	WebElement startdateEle=driver.findElement(By.xpath(startDate))
	startdateEle.click()

	WebUI.delay(4)

	WebUI.click(findTestObject('ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))

	monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

	if(!monthTextValue.equals(monthText))
	{
		while(!monthTextValue.equals(monthText))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))

		}


	}

	String toDate="//td//a[text()='"+CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate")+"']"
	
	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(4)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/fromDate'),"value")
	
	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/toDate'),"value")
	
	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate")
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

	//Step4 : To save this report as template
	try{
		
	String templateHeading=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","templateHeading")
	String existingTemplatexpath= "//td[text()='"+templateHeading+"']/../td[2]"
	WebElement existingTemplatedeleteLink = driver.findElement(By.xpath(existingTemplatexpath));
	if(existingTemplatedeleteLink.isDisplayed()==true)
		 driver.findElement(By.xpath(existingTemplatexpath)).click();
	}
		 catch (Exception e) {
			   e.printStackTrace()
		 }
	
	WebUI.click(findTestObject('ReportsPage/saveTemplateChkBox'))

	WebUI.verifyElementChecked(findTestObject('ReportsPage/saveTemplateChkBox'), 10)
	WebUI.click(findTestObject('ReportsPage/templateName'))

	//Step5: To verify that user is able to enter template name

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportsPage/templateName'),
			CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","templateHeading"))

	String s1=WebUI.getAttribute(findTestObject('ReportsPage/templateName'),"value")

	boolean execFlag = WebUI.verifyMatch(s1,CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","templateHeading"), false)
	if(!execFlag)
	{
		if(TCflag)
			TCflag=false
			println "template heading is not displayed"
	}
	//Step6 : To Generate report
	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(15)

	'verify  SummaryReports heading'
	String summHeading =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","summaryHeading")
	WebUI.verifyElementText(findTestObject('SummarizedReportPage/summaryReport'),summHeading)
	
	//Step 7 : To verify the Start time and End time displayed in Report

	'verify startTime in SummaryReports page'
	String startTime =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","reportstartTime")
	WebUI.verifyElementText(findTestObject('SummarizedReportPage/startDateText'),startTime)
		
	'verify endTime in SummaryReports endtime'
	String endTime =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","reportendTime")
	WebUI.verifyElementText(findTestObject('SummarizedReportPage/endDateText'),endTime)

	//Step 8 : To verify the print time
	'verify printdate SummaryReports date'
	WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/printDateText'), 20)

	//Step 9: To verify the first week date range
	def today = new Date()

	println today.format("MMMMM dd,yyyy")

	String todayDate = today.format("MMMMM dd,yyyy")
	
	String date1 = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/printDateText'))

	if(date1.contains(todayDate))
		println "matched"

	String firstDateRange =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","firstDateRange")
	WebUI.verifyElementText(findTestObject('SummarizedReportPage/firstWeekDateRange'),firstDateRange)

	//Step10 : To verify the store names displayed under Store column
	
	List<WebElement> storeNames = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 9)))
	String storeNameString ="McD";
	boolean contains = true;
	for(int i=0;i<storeNames.size();i++)
	{
		String val = storeNames[i].getText()
		if(!val.contains(storeNameString))
		{
			contains = false;
			break;
		}
	}

	if(!contains)
	{
		if(TCflag)
			TCflag=false
			println "Mcd store name is not listed"

	}

	//Step 11 'To verify the column headers displayed in the Average Time(min:sec) grid'
	/* The below column headers will be shown in the grid:
	 1. Stores
	 2. Menu
	 3. Greet
	 4. Service
	 5. Lane Queue
	 6. Lane Total
	 7. Total Cars*/

	String[] reportsArray=reportHeaders.split(',')

	def reportsHeaderList=new ArrayList(Arrays.asList(reportsArray))
	reportsHeaderList.unique()
	System.out.println(reportsHeaderList)

	List<WebElement> columnHeaderList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 5)))

	List<String> actualList=new ArrayList<String>();

	for(int i=0;i<columnHeaderList.size()-7;i++){

		actualList.add(i,columnHeaderList.get(i).getText())
	}
	System.out.println(actualList)

	boolean execFlag1=true

	for(int j=0;j<actualList.size();j++){

		execFlag1=	WebUI.verifyMatch(reportsHeaderList.get(j).toString().trim(), actualList.get(j).toString().trim(), false)

		if(!execFlag1)
		{
			System.out.println("Reports Column headers are not verified ");
			break;

		}
	}

	if(execFlag1)
	{
		System.out.println("Reports Column headers are verified")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the Store details labels")

	}
	
	//Step 12 'To verify that Total Week is shown in the report'
	//Total Week(W-Avg) will be shown as last row after displaying all the store names in the grid

	List<WebElement> reportTotalWeek = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 6)))
	int totalweekrownumber=0
	String  totalWeek=null
	for(int totalrows=2;totalrows<reportTotalWeek.size();totalrows++){
		
	if(reportTotalWeek.get(totalrows).getText().contains("Total Week")){
		
			totalWeek= driver.findElement(By.xpath("(//div[@class='clear']/table/tbody/tr/td[1])["+(totalrows+1)+"]")).getText()
			break
		}else{
		
		}
		
	}
		
	String totalWeekValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","TotalWeek")
	if(totalWeek.trim().equals(totalWeekValue))
	{
		System.out.println("Total week is displayed in last row")
	}
	else
	{
		System.out.println("Total week is not displayed in last row")
	}
	//Step 13: To verify the second week data
	WebUI.delay(5)
	String secondDateRange =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","secondDateRange")
	WebUI.verifyElementText(findTestObject('SummarizedReportPage/secondWeekDateRange'),secondDateRange)

	//Step 14 'To verify that user is able to view weekly report for a single store'

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/storeFirst'))
	
	String summheadingforSingleStore =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","summaryHeading")
	WebUI.verifyElementText(findTestObject('SummarizedReportPage/summerizedReportforSingleStore'),summheadingforSingleStore)

	// Step 15: To verify the headers in weekly report for a selected store
	//"Store: Store 1, Store Desc:,Start Time: Mar 1, 2018 OPEN,Stop Time : Mar 7, 2018 CLOSE,Print Date: Current date,Print Time: Current time"
		
	String header1 =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","WeekReportHeader1")
	String[] weekReportHeader1=header1.split(',')

	def weekReportHeaderList1=new ArrayList(Arrays.asList(weekReportHeader1))
	weekReportHeaderList1.unique()
	System.out.println(weekReportHeaderList1)

	List<WebElement> weekHeaderList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 7)))

	List<String> actualList1=new ArrayList<String>();

	for(int i=0;i<weekHeaderList.size();i++){

		actualList1.add(i,weekHeaderList.get(i).getText())
	}
	System.out.println(actualList1)

	boolean execFlag2=true

	for(int j=0;j<actualList1.size();j++){
		execFlag2=	WebUI.verifyMatch(weekReportHeaderList1.get(j).toString().trim(), actualList1.get(j).toString().trim(), false)

		if(!execFlag2)
		{
			System.out.println("Week Report headers are not verified ");
			break;

		}
	}
	if(execFlag2)
	{
		System.out.println("Week Report headers are verified")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the week report labels")

	}

}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true


