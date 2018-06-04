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
import org.stringtemplate.v4.compiler.STParser.exprTag_return
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath
import java.util.regex.Pattern;

boolean TCflag=true
try{

	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)
	WebUI.delay(GlobalVariable.MED_DELAY)
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	'Click on Reports Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	//Step1 : To verify that user is able to select Groups
	WebUI.delay(GlobalVariable.LONG_DELAY)
	WebDriver driver = DriverFactory.getWebDriver()

	List<WebElement> storeGrpList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 26)))

	int size=storeGrpList.size()
	int index=0;
	Pattern pattern = Pattern.compile("[0-9]+");
	for(int i=0; i<size;i++)
	{

		String txt=storeGrpList.get(i).getText()
		index=i+1
		boolean matched=pattern.matcher(txt).matches()
		if(matched){
			driver.findElement(By.xpath("//ul[@role='tree-node']/li["+index+"]/span[2]")).click()
			break;
		}
	}

	WebUI.delay(GlobalVariable.MIN_DELAY)


	'Select time'
	WebUI.click(findTestObject('ReportsPage/timeSelection'))

	'Click on week option'
	WebUI.click(findTestObject('ReportsPage/dayTimeOption'))

	'verify day is displayed in TimeSelection dropdown'

	WebUI.verifyElementText(findTestObject('ReportsPage/dayTimeOption'),
			CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage",	"dayTimeSelection"))



	//Step2: To verify that user is able to select From and To date by using calendar controls
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

	WebUI.delay(GlobalVariable.MED_DELAY)

	WebUI.click(findTestObject('ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	String monthTextValue1=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

	if(!monthTextValue1.equals(month))
	{
		while(!monthTextValue1.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector2'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}


	}

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate")

	String toDate="(//div[@class='rdt date-time rdtOpen']/div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+endDate+"'])[1]"

	println toDate

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate_march")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage",
			"fromDate_march")

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

	//Step3: To Generate report
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	WebUI.delay(GlobalVariable.LONG_DELAY)

	//Step 4 : To verify the Start time and End time displayed in Report

	'verify startTime in SummaryReports page'
	String startTime =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","reportstartTime1")
	String startTimeText = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/startTimeText'))
	startTime=startTime.trim()
	startTimeText=startTimeText.trim()
	WebUI.verifyMatch(startTimeText,startTime,false)

	'verify endTime in SummaryReports endtime'
	String endTime =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","reportendTime1")
	String endTimeText= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/stopTimeText'))
	endTime=endTime.trim()
	endTimeText= endTimeText.trim()
	
	WebUI.verifyMatch(endTimeText,endTime,false)

	//Step 5 : To verify the print time
	'verify printdate SummaryReports date'
	WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/printDateHeading'), 20)

	//Step 9: To verify the first week date range
	def today = new Date()

	String todayDate = today.format("MMMMM dd yyyy")

	String date1 = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/printDateText'))

	if(date1.contains(todayDate))
		println "matched"
		
	//Step6 : To verify the column headers displayed in DP1 OPEN-11:59	
	String reportHeaders =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","summaryReportHeader")

	WebUI.delay(GlobalVariable.MED_DELAY)
	
	String[] reportsArray=reportHeaders.split(',')
	
		def reportsHeaderList=new ArrayList(Arrays.asList(reportsArray))
		reportsHeaderList.unique()
		System.out.println(reportsHeaderList)
	
		List<WebElement> columnHeaderList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 5)))
	
		List<String> actualList=new ArrayList<String>();
	
		for(int i=0;i<columnHeaderList.size();i++){
	
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
			System.out.println("Failed to verify  Reports Column headers")
	
		}
		
	//Step 7: To verify that user is able to Email CSV version	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/SummarizedReportPage/downloadBtn'))
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/SummarizedReportPage/csvOption'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	String confirmationText= CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","confirmationText1")
	String actualconfirmationText= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/emailConfirmation'))
	
	//Step 8: To verify the CSV file
	boolean confirm = WebUI.verifyMatch(confirmationText,actualconfirmationText,false)
	if(confirm){
	
		System.out.println("Email Verification received")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Email Verification not received")
	}
	
	WebUI.back()
	WebUI.delay(GlobalVariable.MED_DELAY)
	
	
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))
}
catch(Exception e)
{

}


