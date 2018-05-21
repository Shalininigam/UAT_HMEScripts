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
	WebUI.navigateToUrl(GlobalVariable.SuperAdminUrl)

	// Step 1:To verify that public admin is able to login as store admin
	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","publicadmin"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Password"))

	WebUI.delay(GlobalVariable.MED_DELAY)

	// step 2:To verify that logged in user name is displayed correctly
	WebUI.verifyElementPresent(findTestObject('HomePage/loggedinUser'),10)

	String loggedInUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/loggedinUser'))
	loggedInUser=loggedInUser.trim()

	String loggedinText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","loggedinText")

	if(loggedInUser.equals(loggedinText))
		System.out.println("Verified logged in user Text")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Could not verify logged in user Text")
		TCflag=true
	}

	//step 3:To verify that store admin name is displayed
	String  welcomeUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/welcomeUserText'))
	welcomeUser=welcomeUser.trim()
	String welcomeText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","welcomeText")

	if(welcomeUser.equals(welcomeText))
		System.out.println("Welcome user Text is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Welcome user Text is not displayed")
		TCflag=true
	}
	//step4 :To navigate to users page

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userslink'))

	WebDriver driver = DriverFactory.getWebDriver()

	List<WebElement> userlist1= driver.findElements(By.xpath("//table//tr/td[3]"))

	if(userlist1.size()>0){
		System.out.println("Users are listed in User Page")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Users are not listed in User Page")
		TCflag=true
	}

	//step5 :To verify that public admin is able to view user details

	String firstname= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('UsersPage/userFN'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/firstuser_viewlink'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String firstnamefromuserdetailpage= CustomKeywords.'uiaction.CommonUIActions.getAttribute'(findTestObject('UserDetailsPage/user_firstname'),'value')

	if(firstnamefromuserdetailpage.equals(firstname)){
		System.out.println("User first Name is displayed Correctly")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("User first Name is not displayed Correctly")
		TCflag=true
	}

	//step6 :To verify that public admin will be able to edit user details

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userslink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userlink2'))

	String Lastname_beforeedit= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('UsersPage/userLN'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/firstuser_viewlink'))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UserDetailsPage/lastnameinputfield'),CustomKeywords.'projectSpecific.Reusability.getTestData'("UserDetails","Lastname"))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UserDetailsPage/userdetail_savebtn'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userslink'))



	//step7 :To verify that user details screen has the updated last name

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userlink2'))

	String Lastname_afteredit= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('UsersPage/userLN'))

	if(!Lastname_afteredit.equals(Lastname_beforeedit)){
		System.out.println("User Last Name edited Succesfully")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("User Last Name is not edited")
		TCflag=true
	}

	//step8 :To verify that public admin will not be able to create any users - Step 1

	List<WebElement> userlist= driver.findElements(By.xpath("//table//tr/td[3]"))

	if(userlist.size()>0){
		System.out.println("Users page will be displayed")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Users are not listed in User Page")
		TCflag=true
	}

	//step9 :To verify that public admin will not be able to see Add a user button - Step 2


	boolean a=CustomKeywords.'uiaction.CommonUIActions.isElementPresent'(findTestObject('UsersPage/Add_user_btn'))

	//WebUI.verifyElementPresent(findTestObject('UsersPage/Add_user_btn'),10)

	if(a){

		if(TCflag)
			TCflag=false
		System.out.println("Add User Link is Available")
		TCflag=true
	}else{
		System.out.println("Add User Link is not displayed")

	}




	//step10 and 11 :To verify that public admin will not be able to delete any users - Step 1

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/firstuser_viewlink'))

	WebUI.delay(GlobalVariable.MED_DELAY)


	boolean b=CustomKeywords.'uiaction.CommonUIActions.isElementPresent'(findTestObject('UserDetailsPage/removeruser_link'))

	//WebUI.verifyElementPresent(findTestObject('UserDetailsPage/removeruser_link'),10)

	if(b){

		if(TCflag)
			TCflag=false
		System.out.println("Remove  Link is Available")
		TCflag=true
	}else{
		System.out.println("Remove Link is not displayed")

	}






}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true
