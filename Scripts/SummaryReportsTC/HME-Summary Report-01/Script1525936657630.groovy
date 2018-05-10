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

	//Step 1: To verify that admin is able to navigate to Reports screen

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/SummaryReportheader'), 10)

	println "Summary Reports screen will be displayed"

	//Step 2: To verify the hierarchy structure displayed in summary report screen

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'),

	CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrpname1"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'),

	CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'),

	CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrpname2"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'),

	CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'),

	CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrp"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'),

	CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))

	String group1=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrpname1")
	String group2=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrpname2")
	String groupmain=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrp")

	WebUI.delay(GlobalVariable.MED_DELAY)

	WebDriver driver = DriverFactory.getWebDriver()

	List<WebElement> grouplist = driver.findElements(By.xpath("//ul[@class='unlinked-grouplist']/li"))
	WebUI.delay(GlobalVariable.MED_DELAY)

	for(int i=0; i<grouplist.size();i++){

		if(grouplist.get(i).getText()==group1){

			println grouplist.get(i).getText()
			println group1

			driver.findElement(By.xpath("(//ul[@class='unlinked-grouplist']/li/label)["+(i+1)+"]")).click()
			driver.findElement(By.xpath("(//ul[@class='unlinked-grouplist']/li/label)["+(i+2)+"]")).click()
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
	driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[1]")).click()

	driver.findElement(By.xpath("//span[contains(text(),'"+group1+"')]")).isDisplayed()

	driver.findElement(By.xpath("//span[contains(text(),'"+group2+"')]")).isDisplayed()

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 3: To verify the brand name displayed for each group and stores
	driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]")).isDisplayed()

	driver.findElement(By.xpath("//span[contains(text(),'"+group1+"')]")).isDisplayed()

	driver.findElement(By.xpath("//span[contains(text(),'"+group2+"')]")).isDisplayed()

	//Step 4: To verify that clicking on Select All check box selects all the groups and stores


	WebUI.delay(4)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/AllStoresCheckbox'))

	List<WebElement> storesCheckBoxList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 4)))

	List<WebElement> storesCheckBoxListChecked = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 14)))

	if(storesCheckBoxList.size()==storesCheckBoxListChecked.size())
	{
		System.out.println("All Store checkboxes are checked after selecting SelectAll button")

	}

	else{
		if(TCflag)
			TCflag=false
		System.out.println("Store checkboxes are unchecked")
	}

	WebUI.delay(4)


	//Step 5: To verify the default value displayed in Time Measure drop down box

	String defaulttimemeasureoption= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject

			('ReportsPage/defaulttimemeasujreoptiondisplayed'))

	if(defaulttimemeasureoption=="Day"){

		System.out.println("Default time measure value displayed as 'Day' ")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Default time measure value not displayed as 'Day' ")

	}


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

	//Step 7: To verify the default date displayed in From and To calendar controls

	String default_from_date= CustomKeywords.'uiaction.CommonUIActions.getAttribute'(findTestObject('ReportsPage/Fromdatedisplay'),"value")

	String default_to_date= CustomKeywords.'uiaction.CommonUIActions.getAttribute'(findTestObject('ReportsPage/reporttodatedisplayed'),"value")

	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Date date = new Date();
	String fromdate=dateFormat.format(date)



	if(default_from_date==fromdate){

		System.out.println("From date is displayed current date ")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("From date is not displayed current date")
	}

	if(default_to_date==fromdate){

		System.out.println("To date is displayed current date ")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("To date is not displayed current date")
	}


	//Step 8: To verify the options available under Advanced options link

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/Report_advancedoption'))


	boolean opencheckbox=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/Report_opencheckbox'))



	if(!opencheckbox){

		System.out.println("Open checkbox is checked bydefault")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Open checkbox is not checked bydefault")
	}


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/opencheckbox'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/Report_opentimepicker'))

	WebUI.verifyElementPresent(findTestObject('ReportsPage/advancedoption_am'), 10)

	WebUI.verifyElementPresent(findTestObject('ReportsPage/advanceoptions_pm'), 10)



	//Step 9: To verify the options available under Type


	WebUI.verifyElementPresent(findTestObject('ReportsPage/type_timeslice'), 10)

	WebUI.verifyElementPresent(findTestObject('ReportsPage/Type_Cumulative'), 10)


	boolean cumulativecheckbox=CustomKeywords.'uiaction.CommonUIActions.isEnabled'(findTestObject('ReportsPage/Type_Cumulative'))

	if(cumulativecheckbox){

		System.out.println("Cumulative checkbox is checked bydefault")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Cumulative checkbox is not checked bydefault")
	}

	//Step 10: To verify the options available under Include

	WebUI.verifyElementPresent(findTestObject('ReportsPage/Include_logesttime'), 5)

	WebUI.verifyElementPresent(findTestObject('ReportsPage/Include_systemstatestics'), 5)


	//Step 11: To verify the options available under Format


	WebUI.verifyElementPresent(findTestObject('ReportsPage/Format_secands'), 5)

	WebUI.verifyElementPresent(findTestObject('ReportsPage/Format_minute'), 5)


	//Step 12: To verify that no template names are shown under Save report template free text box

	WebUI.verifyElementPresent(findTestObject('ReportsPage/Report_savedreport'), 5)

	//Step 13: To verify that No values will be shown in Criteria section


	String fromdatecritiria= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportsPage/critiria_fromdate'))

	String[] fromdatecritiriasplit=fromdatecritiria.split(":")

	if(fromdatecritiriasplit[1]==fromdate){

		System.out.println("From date displaying current date")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("From date not displaying current date")
	}

	String todatecritiria= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('ReportsPage/critiria_todate'))

	String[] todatecritiriasplit=todatecritiria.split(":")

	if(todatecritiriasplit[1]==fromdate){

		System.out.println("To date displaying current date")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("To date not displaying current date")
	}


	//Step 14: To verify that user has an option to save the templates

	WebUI.verifyElementPresent(findTestObject('ReportsPage/report_saveastemplate'), 10)

	//Step 15: To verify that user has option to enter Template name

	WebUI.verifyElementPresent(findTestObject('ReportsPage/templatename_field'), 10)

	//Step 16: To verify that user has an option to Generate Report

	WebUI.verifyElementPresent(findTestObject('ReportsPage/report_submit'), 10)



	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/Summarygrp'))
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))

	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()

	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/Summarygrpname1'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))

	CustomKeywords.'uiaction.CommonUIActions.back'()
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.back'()

	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/Summarygrpname2'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))
}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true


