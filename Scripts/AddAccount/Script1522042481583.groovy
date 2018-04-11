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

'User login to Application'
WebUI.navigateToUrl(GlobalVariable.AppURL)

CustomKeywords.'projectSpecific.Reusability.login'(findTestData('AdminCredentials').getValue('Username', 1),findTestData('AdminCredentials').getValue('Password', 1))



'Click on Accounts'
//WebUI.click(findTestObject('HomePage/Accounts'))
CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/Accounts'))

'Click on Add an Account'
WebUI.click(findTestObject('Accounts/AddanAccount'))

'Enter First Name'
WebUI.setText(findTestObject('Accounts/FirstName'),findTestData('AdminCredentials').getValue('FirstName', 1))

'Enter Last Name'
WebUI.setText(findTestObject('Accounts/LastName'),findTestData('AdminCredentials').getValue('LastName', 1))

'Enter Avante Number'
WebUI.setText(findTestObject('Accounts/AvanteNumber'),findTestData('AdminCredentials').getValue('AvanteNumber', 1))

'Enter Company name'
WebUI.setText(findTestObject('Accounts/CompanyName'),findTestData('AdminCredentials').getValue('CompanyName', 1))

'Select Company Country'
WebUI.click(findTestObject('Accounts/companyCountry'))

'Scroll to WebElement'
WebUI.scrollToElement(findTestObject('Accounts/scrollToIndia'),10)

'Click on Country'
WebUI.click(findTestObject('Accounts/scrollToIndia'))

'Click on Grid'
WebUI.click(findTestObject('Accounts/accountsGrid'))

'Click & set text into Company Region field'
WebUI.click(findTestObject('Accounts/companyRegion'))
WebUI.setText(findTestObject('Accounts/companyRegion'),findTestData('AdminCredentials').getValue('Region', 1))

'Distributor'
WebUI.click(findTestObject('Accounts/distributor'))
WebUI.click(findTestObject('Accounts/clickDistributorDD'))

'Company Type'
WebUI.click(findTestObject('Accounts/companyType'))

'Corporate Brand'
WebUI.click(findTestObject('Accounts/corporateBrand'))
WebUI.click(findTestObject('Accounts/brandDD'))
WebUI.click(findTestObject('Accounts/accountsGrid'))

'Email address'
WebUI.click(findTestObject('Accounts/Email'))
WebUI.setText(findTestObject('Accounts/Email'),findTestData('AdminCredentials').getValue('EmailAddress', 1))

'Confirm Email address'
WebUI.click(findTestObject('Accounts/Email2'))
WebUI.setText(findTestObject('Accounts/Email2'),findTestData('AdminCredentials').getValue('ConfirmEmailAddress', 1))

'Set Start Date'
WebUI.click(findTestObject('Accounts/SubscriptionStartDate'))
WebUI.click(findTestObject('Accounts/dateNumber'))

WebUI.delay(3)

'Set End Date'
WebUI.click(findTestObject('Accounts/SubscriptionEndDate'))
WebUI.click(findTestObject('Accounts/forwardSelector'))
WebUI.click(findTestObject('Accounts/dateNumber'))

WebUI.delay(3)

'Set Subscription Type'
WebUI.click(findTestObject('Accounts/SubscriptionType'))

WebUI.delay(3)

WebUI.click(findTestObject('Accounts/createAccount'))

WebUI.delay(3)

'verify create accounts successful'
WebUI.verifyElementText(findTestObject('Verification/AccountsSuccessfulMsg'),findTestData('AdminCredentials').getValue('VerifySignupMsg', 1))
WebUI.takeScreenshot()








