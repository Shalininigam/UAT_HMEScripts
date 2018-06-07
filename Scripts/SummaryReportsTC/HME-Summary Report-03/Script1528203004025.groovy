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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

boolean TCflag=true
try {

     WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)
     WebUI.delay(GlobalVariable.LONG_DELAY)
     WebUI.delay(GlobalVariable.LONG_DELAY)
     CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

     WebUI.delay(GlobalVariable.LONG_DELAY)

     WebDriver driver = DriverFactory.getWebDriver()


     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))
     WebUI.delay(GlobalVariable.MED_DELAY)

     'Click on Stores Link'
     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink1'))
     WebUI.delay(GlobalVariable.LONG_DELAY)
     
     
     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

     //Step 1: To verify that user is able to select Groups
     WebUI.delay(GlobalVariable.MED_DELAY)
     'Click on Add New Group button'
     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

     //Create group
     CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","dummyGroupName1"))
     CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'), CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))
     WebUI.delay(GlobalVariable.MED_DELAY)


     List<WebElement> grouplist = driver.findElements(By.xpath("//ul[@class='unlinked-grouplist']/li"))
     WebUI.delay(GlobalVariable.MED_DELAY)

     int size=grouplist.size()
     int index=0;
     Pattern pattern = Pattern.compile("[0-9]+");
     int count=0;
     for(int i=0; i<grouplist.size();i++)
     {

           String txt=grouplist.get(i).getText()
           index=i+1
           boolean matched=pattern.matcher(txt).matches()
           if(matched){
                driver.findElement(By.xpath("(//ul[@class='unlinked-grouplist']/li/label)["+index+"]")).click()
                count++
                if(count >60)
                     break;
           }


     }
     
     
     driver.findElement(By.xpath("//button[contains(text(),'>')]")).click()
     WebUI.delay(GlobalVariable.MED_DELAY)

     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/secondSelectAllCB'))

     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
     WebUI.delay(GlobalVariable.MED_DELAY)

     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

     WebUI.delay(GlobalVariable.LONG_DELAY)
     String groupmain=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","dummyGroupName1")
     driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[1]")).click()

     driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[2]")).click()


     //Step2 : To verify that user is able to select From and To date by using calendar controls


     WebUI.delay(4)

     WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

     WebUI.delay(4)

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


     WebUI.delay(3)


     WebUI.click(findTestObject('ReportsPage/toDateSelector'))
     WebUI.delay(3)

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
     WebUI.delay(5)
     String toDate="(//div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate3")+"'])[3]"
     println toDate
     WebElement toDateEle=driver.findElement(By.xpath(toDate))
     WebUI.delay(2)
     toDateEle.click()

     WebUI.delay(4)


     String attr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")
     String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate")
     if(!attr.equals(fromDateValue))
     {
           if(TCflag)
                TCflag=false
           println "from Date is not selected properly"
           WebUI.takeScreenshot()
     }
     attr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")
     println attr
     String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate3")
     if(!attr.equals(toDateValue))
     {
           if(TCflag)
                TCflag=false
           println "to Date is not selected properly"
           WebUI.takeScreenshot()
     }

     //Step3 : To Generate report
     'click GenerateReport button'
     WebUI.click(findTestObject('ReportsPage/generateReport'))
     WebUI.delay(15)

     'verify  SummaryReports heading'
     String summHeading =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","summaryHeading");
     if (summHeading!=null)
           summHeading.trim()
     WebUI.verifyElementText(findTestObject('SummarizedReportPage/summaryReport'),summHeading)

     //Step4 :To verify the Start time and End time displayed in Report
     'verify startTime in SummaryReports page'
     String startTime =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","reportstartTime1");
     if(startTime!=null)
           startTime = startTime.trim()
     String startTimeText =WebUI.getText(findTestObject('SummarizedReportPage/startdateText1')).trim()
     WebUI.verifyMatch(startTimeText,startTime,false)
     'verify endTime in SummaryReports'
     String endTime = CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","reportendTime1")
     if(endTime!=null)
           endTime=endTime.trim()
     String endTimeText =WebUI.getText(findTestObject('SummarizedReportPage/endDateText1')).trim()
     WebUI.verifyMatch(endTimeText,endTime,false)


     //Step5: To verify the print time
     def today = new Date()
     println today.format("MMMMM dd yyyy")
     String todayDate = today.format("MMMMM dd yyyy")
     String date1 = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/printDateText'))
     if(date1.contains(todayDate))
           println "matched"

     WebUI.delay(8)

     //Step6: To verify the column headers displayed in Average Time(min:sec) grid
     String reportHeaders =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","ReportsColumnHeader")
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
           execFlag1= WebUI.verifyMatch(reportsHeaderList.get(j).toString().trim(), actualList.get(j).toString().trim(), false)
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
           System.out.println("Failed to verify the rStore details labels")
     }

     WebUI.delay(8)
     //step 7 : To verify the rows displayed under Stores column header
     String storeHeader1 = CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","storerowHeader1")
     String storeHeader1Text =WebUI.getText(findTestObject('SummarizedReportPage/storerowUnderHeader1')).trim()
     WebUI.verifyMatch(storeHeader1,storeHeader1Text,false)


     //Step 8 : To verify that user is able to view daily report for Mar 02, 2018
     WebUI.verifyElementText(findTestObject('SummarizedReportPage/weekRange1'),
                CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","firstDateRange"))

     WebUI.verifyElementText(findTestObject('SummarizedReportPage/weekRange2'),
                CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","secDateRange"))


     //Step 9 : To verify that user is able to navigate to next page
     //To verify if we can click next icon and view two days report
     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/SummarizedReportPage/nextPageIcon'))

     WebUI.delay(10)


     WebUI.verifyElementText(findTestObject('SummarizedReportPage/weekDateRange1'),
                CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","thirdDateRange"))

     WebUI.verifyElementText(findTestObject('SummarizedReportPage/weekDateRange2'),
                CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","fourthDateRange"))


     //Step 10 : To verify that user is able to view all the four days report in a single page
     //To verify if we can click Pages link and view four days report
     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/SummarizedReportPage/allReportsLink'))
     WebUI.delay(15)


     WebUI.verifyElementText(findTestObject('SummarizedReportPage/weekDateRange1'),
                CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","firstDateRange"))
     WebUI.verifyElementText(findTestObject('SummarizedReportPage/weekDateRange2'),
                CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","secDateRange"))
     WebUI.verifyElementText(findTestObject('SummarizedReportPage/weekDateRange3'),
                CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","thirdDateRange"))
     WebUI.verifyElementText(findTestObject('SummarizedReportPage/weekDateRange4'),
                CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","fourthDateRange"))


     //Step 11 : To verify that user is able to view daily report for a single store
     //to verify user can click one store link and view report for the store
     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/firstStoreLink'))
     WebUI.delay(10)

     //Step 12 : To verify the headers in daily report for a selected store
     WebUI.verifyElementVisible(findTestObject('SummarizedReportPage/summaryHeading2'))
     WebUI.verifyElementVisible(findTestObject('SummarizedReportPage/storeHeading'))
     WebUI.verifyElementVisible(findTestObject('SummarizedReportPage/storeDescHeading'))
     WebUI.verifyElementVisible(findTestObject('SummarizedReportPage/startTimeHeading'))
     WebUI.verifyElementVisible(findTestObject('SummarizedReportPage/stopTimeHeading'))
     startTimeText=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/startTimeText'))
     startTimeText=startTimeText.trim();
     String startText=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","startText")
     startText=startText.trim()
     if(startTimeText.equals(startText)){
           System.out.println("headers in daily report for a selected store displayed")
     }else{
           if(TCflag)
           TCflag=false
           System.out.println("headers in daily report for a selected store not  displayed Correctly")
           }
     endTimeText=  CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","endText")
     endTimeText=endTimeText.trim()
     String endText= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/endTimeText'))
     endText=endText.trim()
     if(endTimeText.equals(endTimeText)){

           System.out.println("endTime is displayed ")
     }else{
           if(TCflag)
           TCflag=false
           System.out.println("endTime is not  displayed Correctly ")
     }

     today = new Date()
     println today.format("MMMMM dd,yyyy")
     todayDate = today.format("MMMMM dd,yyyy")
     date1 = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/printDateHeadingText'))
     if(todayDate.contains(date1)){
           println "Print Date matched"
     }else{
           if(TCflag)
           TCflag=false
           System.out.println("Print Date is not matched ")
           }
     //Step13: To verify the column headers displayed in the Average Time(min:sec) grid

     reportHeaders =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","ReportsColumnHeader3")
     reportsArray=reportHeaders.split(',')
     reportsHeaderList=new ArrayList(Arrays.asList(reportsArray))
     reportsHeaderList.unique()
     System.out.println(reportsHeaderList)
     columnHeaderList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 15)))
     actualList=new ArrayList<String>();
     for(int i=0;i<columnHeaderList.size();i++){
           actualList.add(i,columnHeaderList.get(i).getText())
     }
     System.out.println(actualList)
     execFlag1=true
     for(int j=0;j<actualList.size();j++){
           execFlag1= WebUI.verifyMatch(reportsHeaderList.get(j).toString().trim(), actualList.get(j).toString().trim(), false)
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


     //Step 14: To verify the rows displayed under Day column
     
     String storeReportDate =CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/storeReportDate'))

     storeReportDate= storeReportDate.trim()

     String storeReportDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","storeReportDate")

     //String strDate = formatter.format(date);
     if(storeReportDate.equals(storeReportDateValue))

           System.out.println("rows displayed under Day column-passed")
     else
           if(TCflag)
           TCflag=false
           System.out.println("rows displayed under Day column-failed")

           
           
           //Step17: To verify that user is able to view Daypart report
           WebUI.back()
           WebUI.delay(10)
           WebUI.back()
           WebUI.delay(10)
          driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[2]")).click()
     'Select time'
     WebUI.click(findTestObject('ReportsPage/timeSelection'))
     'Click on week option'
     WebUI.click(findTestObject('ReportsPage/daypartTimeOption'))


     WebUI.delay(4)

     WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

     WebUI.delay(4)

     String monthText9=WebUI.getText(findTestObject('ReportsPage/monthText'))

     String[] monthTextArray9= monthText9.split(" ")
     month = monthTextArray9[0]

     monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

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



     data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate")
     startDate="(//td[text()='"+data+"'])[1]"
     println startDate
     startdateEle=driver.findElement(By.xpath(startDate))
     startdateEle.click()


     WebUI.delay(3)


     WebUI.click(findTestObject('ReportsPage/toDateSelector'))
     WebUI.delay(3)

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
     WebUI.delay(5)
     toDate="(//div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate3")+"'])[3]"
     println toDate
     toDateEle=driver.findElement(By.xpath(toDate))
     WebUI.delay(2)
     toDateEle.click()

     WebUI.delay(4)


     attr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")
     fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate")
     if(!attr.equals(fromDateValue))
     {
           if(TCflag)
                TCflag=false
           println "from Date is not selected properly"
           WebUI.takeScreenshot()
     }
     attr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")
     println attr
     toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate3")
     
     
     'click GenerateReport button'

     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/generateReport'))
     
     WebUI.delay(15)

     //Step18: To verify the column headers displayed in the Average Time(min:sec) grid
     
     WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/average_time'), 10)
     
     //Step19: To verify the rows displayed under Daypart column
     
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
     
     

     
     //Step22: To verify that user is able to view raw car data report
     
     CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/daypart_column'))
     WebUI.delay(GlobalVariable.LONG_DELAY)

     WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/rawCarDataHeading'), 10)

     //Step23: To verify the column headers displayed in DP1 OPEN-11:59

     //Step24: To verify that user is able to Email CSV version

     //Step25: To verify the CSV file

}catch(Exception e){
     e.printStackTrace()
     println "Exception of element not found"
     if(TCflag)
           TCflag=false
}

assert TCflag==true




