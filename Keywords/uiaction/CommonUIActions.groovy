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
	 * Assignment : Get text from list of web elements
	 * Summary : The purpose of this utility is to get all Gene text from list of web elements.
	 ***************************************************************/
	@Keyword
	def	public ArrayList<String> getListWebElementsTxt(String locator) {
		
		ArrayList<String> listWebelementTxt = new ArrayList<String>();
		try {

			
				
				List<WebElement> wes = CreateThreadLocal.getDriver().findElements(By.xpath(locator));
				for(WebElement we: wes)
				{
					listWebelementTxt.add(we.getText());
				}

		
			
				return listWebelementTxt;
			
		} catch (Exception ex) {
		
			ex.printStackTrace();
			
		}
		
	}


}
