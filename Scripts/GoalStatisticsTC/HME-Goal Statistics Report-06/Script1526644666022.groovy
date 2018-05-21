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

	//Step1: To verify that admin is able to select group

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/store_secondrowview1'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String storelabel= WebUI.getAttribute(findTestObject('StorePage/store_label'), "value")
	storelabel= storelabel.trim()


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomlabel'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomViewDetails'))

	String storenumber=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/storeID'))
	storenumber=storenumber.trim()


	System.out.println(" the store number is--"+storenumber.trim())


	WebDriver driver = DriverFactory.getWebDriver()

	WebUI.delay(GlobalVariable.MED_DELAY)


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	String grpName= "AutomationGrp3"

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'),grpName)

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'),
			CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))


	WebUI.delay(10)

	List<WebElement> storesLabel = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 10)))
	ArrayList<String> groupStoresList=new ArrayList<String>()

	System.out.println(storesLabel.size())
	int size=storesLabel.size()
	int index=0;
	for(int i=0;i<size;i++){
		String storeLabel= storesLabel.get(i).getText().trim()
		println storeLabel
		if(storeLabel.contains(storelabel))
		{
			index=i+1
			driver.findElement(By.xpath("(//ul[@class='unlinked-grouplist']/li/label)["+ index +"]")).click()
			driver.findElement(By.xpath("//button[contains(text(),'>')]")).click()
			break;

		}
	}
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/secondSelectAllCB'))


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()


	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/reportLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	driver.findElement(By.xpath("//li/span/span/div/span[@class='StoreTitile'][text()='"+grpName+"']/../../../../span[1]")).click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	driver.findElement(By.xpath("//li/span/span/div/span[@class='StoreTitile'][text()='"+grpName+"']/../../../../span[2]")).click()

	//Step2: To select the time measure
	List<WebElement> timemeasureoptions= driver.findElements(By.xpath("//select[@class='time-measures']/option"))

	String timemeasureoption_excel= CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","timemeasureoptions")

	String[] timemeasureoption_excelsplit=timemeasureoption_excel.split(',')

	for(int i=0;i<timemeasureoptions.size();i++){

		if(timemeasureoptions.get(i).getText()==timemeasureoption_excelsplit[i]){

			System.out.println("Options are displayed correctly ")

		}else{
			if(TCflag)
				TCflag=false
			System.out.println("Options are not matching ")

		}

	}
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportsPage/timeSelection'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportsPage/dayTimeOption'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	//Step3: To select the date

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	String[] monthTextArray= monthText.split(" ")
	String month = monthTextArray[0]

	String monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month_January")

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

	String data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","January_StartDate")
	String startDate="(//div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+data+"'])[1]"


	println startDate
	WebElement startdateEle=driver.findElement(By.xpath(startDate))
	startdateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	String monthTextValue1=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month_January")

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

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","January_EndDate2")

	String toDate="(//div[@class='rdt date-time rdtOpen']/div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+endDate+"'])[1]"

	println toDate

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate_January1")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate_January1")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}



	WebUI.delay(GlobalVariable.MED_DELAY)


	//Step 4: To include longest time and system statistics


	WebUI.verifyElementPresent(findTestObject('Object Repository/ReportsPage/longestTimeOption'), 10)

	WebUI.verifyElementPresent(findTestObject('Object Repository/ReportsPage/systemStatisticsOption'), 10)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportsPage/systemStatisticsOption'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	//Step5: To verify the report after executing above step.

	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	List<WebElement> goalStatisticsList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 17)))
	int groupSize = goalStatisticsList.size()
	List<String> actualgroupList=new ArrayList<String>();

	for(int i=0;i<groupSize;i++){

		actualgroupList.add(i,goalStatisticsList.get(i).getText())

	}


	String goalStatisticsdata=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","goalStatisticsData")


	String[] goalStatisticsdataArray=goalStatisticsdata.split(',')
	def goalStatisticsdataList=new ArrayList(Arrays.asList(goalStatisticsdataArray))
	goalStatisticsdataList.unique()
	System.out.println(goalStatisticsdataList)
	boolean matchFound=true;
	for(int j=0;j<actualgroupList.size();j++)
	{

		matchFound=	WebUI.verifyMatch(actualgroupList.get(j).toString().trim(), goalStatisticsdataList.get(j).toString().trim(), false)

		if(!matchFound)
		{
			//System.out.println("GoalStatistics Columns are not verified ");
			break;

		}
	}

	if(matchFound)
	{
		System.out.println("GoalStatistics Columns are verified")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the GoalStatistics Columns")

	}

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	WebUI.delay(GlobalVariable.MED_DELAY)


	WebUI.delay(GlobalVariable.MED_DELAY)


	String groupLink= "//span[text()='"+ grpName+ "']"

	driver.findElement(By.xpath(groupLink)).click()


	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))



}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true




