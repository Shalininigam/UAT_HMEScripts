package uiaction

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.common.WebUiCommonHelper

import internal.GlobalVariable
import org.openqa.selenium.WebElement
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class CommonUIActions {
	
	/****************************************************************
	* Assignment : click
	* Summary : The purpose of this utility is to click at a particular location of element
	*****************************************************************/
		
	@Keyword
	def click(TestObject object){
			try{
					
					WebElement element=WebUiCommonHelper.findWebElement(object, 20)
					element.click();
			}
			catch(Exception ex){
					System.out.println(ex.message)
			}
	}
	
	/***************************************************************
	 * Assignment : enter
	 * Summary : The purpose of this utility is to enter value to a text box
	 ****************************************************************/
		
	@Keyword
	def enter(TestObject object,String input){
			try{
					WebElement element=WebUiCommonHelper.findWebElement(object, 20)
					element.sendKeys(input)
			}
			catch(Exception ex){
					System.out.println(ex.message)
			}
	}
	
	/***************************************************************
	 * Assignment : getText
	 * Summary : The purpose of this utility is to getText from textbox
	 ****************************************************************/
	
	@Keyword
	def String getText(TestObject object){
			try{
					WebElement element=WebUiCommonHelper.findWebElement(object, 20)
					return element.getText()
			}
			catch(Exception ex){
				System.out.println(ex.message)
				return ex.message
					
			}
	}
	
	/***************************************************************
	 * Assignment : clickOffset
	 * Summary : The purpose of this utility is to click on a Webelement at particular offset
	 ****************************************************************/
	@Keyword
	def clickOffset(TestObject object,int x,int y){
		try{
			WebUI.clickOffset(object,x,y)

		}
		catch(Exception ex){
			println (ex.message)
		}
	}
	
	
	
	/***************************************************************
	 * Assignment :isEnabled
	 * Summary : The purpose of this utility is to check whether webelement is Enabled
	 ****************************************************************/
	@Keyword
	def boolean isEnabled(TestObject object){
		boolean isEnabled =false;
		
		try{
			WebElement element=WebUiCommonHelper.findWebElement(object, 20)
			if(element.isEnabled())
				isEnabled = true;

		}
		catch(Exception ex){
			println (ex.message)
		}
		return isEnabled;
	}
	
	
	/***************************************************************
	 * Assignment : rightClick
	 * Summary : The purpose of this utility is to rightClick at a particular location of element
	 ****************************************************************/
	@Keyword
	def rightClick(TestObject object){
		try{
			WebUI.rightClick(object)
 

		}
		catch(Exception ex){
			println (ex.message)
		}
	}
	
	
	/***************************************************************
	 * Assignment : doubleClick
	 * Summary : The purpose of this utility is to doubleclick on a Webelement
	 ****************************************************************/
	@Keyword
	def doubleClick(TestObject object){
		try{
		
			WebUI.doubleClick(object)

		}
		catch(Exception ex){
			println (ex.message)
		}
	}
	
	/***************************************************************
	 * Assignment : getListText
	 * Summary : The purpose of this utility is to get list of webelements
	 ****************************************************************/
	@Keyword
	def List<WebElement> getListText(TestObject object){
		try{
			List<WebElement> elements=WebUiCommonHelper.findWebElements(object, 20)
			return elements;
		

		}
		catch(Exception ex){
			println(ex.message);
		}
	}
	
	/***************************************************************
	 * Assignment :  verifyText
	 * Summary : The purpose of this utility is to verifyText in a textbox
	 ****************************************************************/
	@Keyword
	def boolean verifyText(TestObject object,String input){
		boolean flag;
		try{
			WebElement element=WebUiCommonHelper.findWebElement(object, 20)
		    if(element.getText().equals(input)){
			flag=true;
		    }
			
		    else{
			
			flag=false;
		    }
			
		}
			catch(Exception ex){
				println(ex.message);
			}
	
		return flag
	}
	/***************************************************************
	 * Assignment :  verifyEqual
	 * Summary : The purpose of this utility is to verify values of a textbox
	 ****************************************************************/
	@Keyword
	def boolean verifyEqual(TestObject object,String input){
		boolean flag=false;
		try{
			WebElement element=WebUiCommonHelper.findWebElement(object, 20)
			if(element.getText().equals(input)){
			flag=true;
			}
			
			
			
		}
			catch(Exception ex){
				println(ex.message);
			}
	
		return flag
	}
	
	
	/***************************************************************
	 * Assignment :   openBrowser
	 * Summary : The purpose of this utility is to openBrowser
	 ****************************************************************/
	@Keyword
	def openBrowser(){
		try{
			WebUI.openBrowser()
		}
		catch(Exception ex){
			println (ex.message)
		}
	}


/***************************************************************
 * Assignment :  closeBrowser
 * Summary : The purpose of this utility is to closeBrowser
 ****************************************************************/
@Keyword
def closeBrowser(){
	try{
		WebUI.closeBrowser()
	}
	catch(Exception ex){
		println (ex.message)
	}
}

/***************************************************************
 * Assignment : navigateToUrl
 * Summary : The purpose of this utility is to navigateToUrl
 ****************************************************************/
@Keyword
def navigateToURL(String urlName){
	try{
		WebUI.navigateToUrl(urlName)
	}
	catch(Exception ex){
		println (ex.message)
	}
}


/***************************************************************
 * Assignment : refresh
 * Summary : The purpose of this utility is to refresh browser
 ****************************************************************/

@Keyword
def refresh(){
	try{
		WebUI.refresh()
		
	}
	catch(Exception ex){
		println (ex.message)
	}
}

/***************************************************************
 * Assignment :back
 * Summary : The purpose of this utility is to go back to previous page browsed
 ****************************************************************/
@Keyword
def back(){
	try{
		WebUI.back()
		
	}
	catch(Exception ex){
		println (ex.message)
	}
}

/***************************************************************
 * Assignment :forward
 * Summary : The purpose of this utility is to forward to next page in browser
 ****************************************************************/
@Keyword
def forward(){
	try{
		WebUI.forward()
		
	}
	catch(Exception ex){
		println (ex.message)
	}
}


/***************************************************************
 * Assignment :deleteAllCookies
 * Summary : The purpose of this utility is to delete All Cookies
 ****************************************************************/
@Keyword
def deleteAllCookies(){
	try{
		WebUI.deleteAllCookies()
	}
	catch(Exception ex){
		println (ex.message)
	}
}


/***************************************************************
 * Assignment :isElementPresent
 * Summary : The purpose of this utility is to verify presence of element in a page
 ****************************************************************/
@Keyword
def boolean isElementPresent(TestObject object){
	
	boolean isPresent = false;
	try{
		isPresent = WebUI.verifyElementVisible(object,10);
		
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	return isPresent
}

/***************************************************************
 * Assignment :isElementClickable
 * Summary : The purpose of this utility is to verify is element clickable
 ****************************************************************/
@Keyword
def boolean isElementClickable(TestObject object){
	
	boolean isClickable = false;
	try{
		isClickable = WebUI.verifyElementClickable(object,10);
		
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	return isClickable
}


/***************************************************************
 * Assignment :getAttribute
 * Summary : The purpose of this utility is to get Attribute of a webelement in a page
 ****************************************************************/
@Keyword
def String  getAttribute(TestObject object,String attributeType){
	
	String attribute=null;
	try{
		attribute = WebUI.getAttribute(object,attributeType);
		
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	return  attribute
}

/***************************************************************
 * Assignment :acceptAlert
 * Summary : The purpose of this utility is to accept Alert 
 ****************************************************************/
@Keyword
def acceptAlert(){
	
	
	try{
		boolean alertPresent =WebUI.verifyAlertPresent(10);
		if(alertPresent)
			WebUI.acceptAlert()
		
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	
}

/***************************************************************
 * Assignment :dismissAlert
 * Summary : The purpose of this utility is to dismiss Alert
 ****************************************************************/
@Keyword
def dismissAlert(){
	
	
	try{
		boolean alertPresent =WebUI.verifyAlertPresent(10)
		if(alertPresent)
			WebUI.dismissAlert()
		
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	
}

/***************************************************************
 * Assignment :getAlertText
 * Summary : The purpose of this utility is to get Alert Text
 ****************************************************************/
@Keyword
def String getAlertText(){
	
	
	try{
		boolean alertPresent =WebUI.verifyAlertPresent(10)
		if(alertPresent)
		{
			alertText = WebUI.getAlertText()
			return alertText
		}
		else 
			return null
		
	}
	catch(Exception ex){
		println (ex.message)
		return null
		
	}
	
}


/***************************************************************
 * Assignment :setAlertText
 * Summary : The purpose of this utility is to set Alert Text
 ****************************************************************/
@Keyword
def  setAlertText(String alertText){
	
	
	try{
		boolean alertPresent =WebUI.verifyAlertPresent(10)
		if(alertPresent)
		{
			WebUI.setAlertText(alertText)
			
					
		}
		
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	
}


/***************************************************************
 * Assignment :scrollToElement
 * Summary : The purpose of this utility is to scroll to a element
 ****************************************************************/
@Keyword
def  scrollToElement(TestObject object)
{

	try{
		
		WebUI.scrollToElement(object)
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	
}


/***************************************************************
 * Assignment :scrollToPosition
 * Summary : The purpose of this utility is to scroll to a position
 ****************************************************************/
@Keyword
def  scrollToPosition(TestObject object,int x,int y)
{

	try{
		
		WebUI.scrollToPosition(object,x,y)
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	
}
/***************************************************************
 * Assignment :checkCheckBox
 * Summary : The purpose of this utility is to check a Checkbox
 ****************************************************************/
@Keyword
def  checkCheckBox(TestObject object){
	
	
	try{
		boolean isChecked = WebUI.verifyElementChecked(object, 10)
		if(!isChecked)
			WebUI.check(object)
		
		
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	
}

	
/***************************************************************
 * Assignment :uncheckCheckBox
 * Summary : The purpose of this utility is to uncheck a Checkbox
 ****************************************************************/
	@Keyword
	def  uncheckCheckBox(TestObject object){
		
		
		try{
			boolean isChecked = WebUI.verifyElementChecked(object, 10)
			if(isChecked)
				WebUI.uncheck(object)
		
			
		}
		catch(Exception ex){
			println (ex.message)
			
		}
		
	
}

	
	
	/***************************************************************
	 * Assignment :isChecked
	 * Summary : The purpose of this utility is to verify Checkbox is checked
	 ****************************************************************/
	@Keyword
	def  isChecked(TestObject object){
		
		boolean isChecked=false;
		try{
			 isChecked = WebUI.verifyElementChecked(object, 10)
			
			
		}
		catch(Exception ex){
			println (ex.message)
			
		}
		return isChecked
	}
	

	/***************************************************************
	 * Assignment :notChecked
	 * Summary : The purpose of this utility is to verify Checkbox is not checked
	 ****************************************************************/
	@Keyword
	def  notChecked(TestObject object){
		
		boolean isnotChecked=false;
		try{
			isnotChecked = WebUI.verifyElementNotChecked(object, 10)
			
			
		}
		catch(Exception ex){
			println (ex.message)
			
		}
		return isnotChecked
	}
	
	
	
	/***************************************************************
	 * Assignment :dragAndDropByOffset
	 * Summary : The purpose of this utility is to drag and drop an object by offset
	 ****************************************************************/
	@Keyword
	def  dragAndDropByOffset(TestObject object,int x,int y){
		
		
	try{
		
		WebUI.dragAndDropByOffset(object, x, y)
	}
	catch(Exception ex){
		println (ex.message)
		
	}
	
		
	}
	
	/***************************************************************
	 * Assignment :dragAndDropToObject
	 * Summary : The purpose of this utility is to drag and drop an object to the place of another object
	 ****************************************************************/
	@Keyword
	def  dragAndDropToObject(TestObject object1,TestObject object2){
		
		
	try{
		
		WebUI.dragAndDropToObject(object1,object2)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	
		
	}
	/***************************************************************
	 * Assignment :getElementHeight
	 * Summary : The purpose of this utility is to getElementHeight
	 ****************************************************************/
	@Keyword
	def  getElementHeight(TestObject object1){
		
		
	try{
		
		WebUI.getElementHeight(object1)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	
		
	}
	
	
	/***************************************************************
	 * Assignment :getElementWidth
	 * Summary : The purpose of this utility is to getElementWidth
	 ****************************************************************/
	@Keyword
	def  getElementWidth(TestObject object1){
		
		
	try{
		
		WebUI.getElementWidth(object1)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	
		
	}
	
	
	/***************************************************************
	 * Assignment :mouseOver
	 * Summary : The purpose of this utility is mouseOver
	 ****************************************************************/
	@Keyword
	def  mouseOver(TestObject object1){
		
		
	try{
		
		WebUI.mouseOver(object1,'font-size')
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	
	}
	/***************************************************************
	 * Assignment :verifyMatch
	 * Summary : The purpose of this to verify the  text of WebElement is as expected
	 ****************************************************************/
	@Keyword
	def boolean  verifyMatch(TestObject object1,String expectedValue)
	{
		boolean matched=false;
		
	try{
		
		String actualValue =WebUI.getAttribute(object1,"value")
		matched=WebUI.verifyMatch(actualValue,expectedValue, false)
		
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	return matched
		
	}
	
	/***************************************************************
	 * Assignment :verifyTextPresent
	 * Summary : The purpose of this to verify the text is present in Webpage
	 ****************************************************************/
	@Keyword
	def boolean  verifyTextPresent(String text){
		boolean isPresent=false;
		
	try{
		isPresent=WebUI.verifyTextPresent(text, false)
		
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	return isPresent
		
	}
	
	/***************************************************************
	 * Assignment :mouseOverOffset
	 * Summary : The purpose of this utility is mouseOver Offset 
	 ****************************************************************/
	@Keyword
	def  mouseOverOffset(TestObject object1,int x,int y){
		
		
	try{
		
		WebUI.mouseOverOffset(object1,x,y)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	
		
	}


	//combo box functions
	
	
	/***************************************************************
	 * Assignment :selectAllOptions
	 * Summary : The purpose of this utility is to select all combobox options
	 ****************************************************************/
	@Keyword
	def  selectAllOptions(TestObject object1){
		
		
	try{
		
		WebUI.selectAllOption(object1)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	}
	/***************************************************************
	 * Assignment :deselectAllOptions
	 * Summary : The purpose of this utility deselect all combobox options
	 ****************************************************************/
	@Keyword
	def  deselectAllOptions(TestObject object1){
		
		
	try{
		
		WebUI.deselectAllOption(object1)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
		
	}
	/***************************************************************
	 * Assignment :getNumberOfOptions
	 * Summary : The purpose of this utility is to get number of options
	 ****************************************************************/
	@Keyword
	def  getNumberOfOptions(TestObject object1){
		
		
	try{
		
		WebUI.getNumberOfTotalOption(object1)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
		
	}

	
	/***************************************************************
	 * Assignment :selectOrdeselectOptionByIndex
	 * Summary : The purpose of this utility is to selectOrdeselectOptionByIndex
	 ****************************************************************/
	@Keyword
	def  selectOrdeselectOptionByIndex(TestObject object1,String selectOrdeselect,String index){
		
		
	try{
		if(selectOrdeselect.equals("Select"))
		
			WebUI.selectOptionByIndex(object1, index)
		else
			WebUI.deselectOptionByIndex(object1, index)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
		
	}

	
	/***************************************************************
	 * Assignment :selectOrdeselectOptionByLabel
	 * Summary : The purpose of this utility is to selectOrdeselectOptionByLabel
	 ****************************************************************/
	@Keyword
	def selectOptionByLabel(TestObject object1,String selectOrdeselect,String label){
		
		
	try{
		if(selectOrdeselect.equals("Select"))
		
			WebUI.selectOptionByLabel(object1, label,false)
		else
			WebUI.deselectOptionByLabel(object1,label,false)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
		
	}
	
	
	/***************************************************************
	 * Assignment :selectOrdeselectOptionByValue
	 * Summary : The purpose of this utility is to selectOrdeselectOptionByValue
	 ****************************************************************/
	@Keyword
	def selectOptionByValue(TestObject object1,String selectOrdeselect,String value){
		
		
	try{
		if(selectOrdeselect.equals("Select"))
		
			WebUI.selectOptionByValue(object1, value,false)
		else
			WebUI.deselectOptionByValue(object1,value,false)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
		
	}
	
	
	/***************************************************************
	 * Assignment :switchToFrame
	 * Summary : The purpose of this utility is to switchToFrame
	 ****************************************************************/
	@Keyword
	def switchToFrame(TestObject iframeobject){
		
		
	try{
		
		WebUI.switchToFrame(iframeobject,10)
	}
	
	
	catch(Exception ex){
		println (ex.message)
	}
		
	}
	
	
	/***************************************************************
	 * Assignment :switchToDefaultContent
	 * Summary : The purpose of this utility is to switchToDefaultContent
	 ****************************************************************/
	@Keyword
	def switchToDefaultContent(TestObject iframeobject){
		
		
	try{
		
		WebUI.switchToDefaultContent()
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	
}
	/***************************************************************
	 * Assignment :waitForElementPresent
	 * Summary : The purpose of this utility is to wait until Web Element is present on page
	 ****************************************************************/
	
	@Keyword
	def waitForElementPresent(TestObject Obj,int timeout){
			
	try{
		
		WebUI.waitForElementPresent(Obj,timeout)
	}
	
	
	catch(Exception ex){
		println (ex.message)
		
	}
	
		
	}	
	
}


