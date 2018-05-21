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

	//Step 1: To select store

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

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))
	String grpName= "AutomationGrp2"
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
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/reportLink'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	driver.findElement(By.xpath("//li/span/span/div/span[@class='StoreTitile'][text()='"+grpName+"']/../../../../span[1]")).click()

	WebUI.delay(GlobalVariable.MED_DELAY)

	driver.findElement(By.xpath("//li/span/span/div/span[@class='StoreTitile'][text()='"+grpName+"']/../../../../span[2]")).click()

	//Step 6: To verify the options available in Time measure drop down box

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

	//Step2:To select the time measure
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportsPage/timeSelection'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportsPage/daypartTimeOption'))

	//Step 3: To select the date

	WebUI.delay(GlobalVariable.MIN_DELAY)

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
	String startDate="(//td[text()='"+data+"'])[1]"
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

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","January_EndDate")

	String toDate="(//td[text()='"+endDate+"'])[2]"

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate_January2")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate_January2")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}

	//Step 4: To selected time form Advanced options from drop down

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/Report_advancedoption'))

	boolean opencheckbox=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/Report_opencheckbox'))

	if(!opencheckbox){

		System.out.println("Open checkbox is checked by default")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Open checkbox is not checked by default")
	}

	boolean closecheckbox=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/Report_closecheckbox'))

	if(!closecheckbox){

		System.out.println("Close checkbox is checked by default")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Close checkbox is not checked by default")
	}



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


	WebUI.delay(10)


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advanced_openTimeText'))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportsPage/advanced_openTimeText'),"9:00 am")

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/tooltip'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 6: To verify that user is able to select close time

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


	WebUI.delay(10)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/advanced_openTimeText'))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportsPage/advanced_openTimeText'),"11:00 am")

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/tooltip'))

	WebUI.delay(GlobalVariable.MED_DELAY)



	//Step 5: To generate report

	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))


	WebUI.delay(GlobalVariable.LONG_DELAY)
	String confirmationText= CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","confirmationText")
	String actualconfirmationText= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/emailConfirmation'))

	boolean confirm = actualconfirmationText.trim().contains(confirmationText.trim())
	if(confirm){

		System.out.println("Email Verification received")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Email Verification not received")
	}

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String groupLink= "//span[text()='"+ grpName+ "']"

	driver.findElement(By.xpath(groupLink)).click()


	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))

	WebUI.delay(GlobalVariable.MED_DELAY)

}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true




