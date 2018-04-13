package projectSpecific

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
import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kms.katalon.core.configuration.RunConfiguration


public class Reusability{
	@Keyword
	def login(String  username,String password){
		WebUI.setText(findTestObject('LoginPage/Username'), username)
		WebUI.setText(findTestObject('LoginPage/Password'), password)
		WebUI.click(findTestObject('LoginPage/Login'))
	}	
		
	@Keyword
	public String getTestData(String sheetName,String testdataName) throws IOException{
	
		def objectDir = RunConfiguration.getProjectDir()+"/Data Files" ;
		def objectfileName= objectDir+"/TestData.xlsx";
		
			  println objectDir
			  println objectfileName
			  String testdata =null;
			  
			  try
			  {
				
			 
			  FileInputStream fis = new FileInputStream(objectfileName);
			  XSSFWorkbook workbook = new XSSFWorkbook(fis);
			  
			  // Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName)
 
			// Iterate through each row heading one by one
		 
				Row row = sheet.getRow(0);
				Row row1 = sheet.getRow(1);
				
				// For each row, iterate through all the columns until matching testdata is not found
				int noOfColumns = sheet.getRow(0).getLastCellNum();
				String cellValue=null;
				 for(int i=0;i<noOfColumns;i++)
				{
								
					Cell cell = row.getCell(i);
					if(cell!=null)
					{
					
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							int value =cell.getNumericCellValue();
							cellValue=Integer.toString(value);
							break;
						case Cell.CELL_TYPE_STRING:
							cellValue= cell.getStringCellValue();
					}
					
					if(cellValue.equals(testdataName))
					{
						
						Cell cell1 = row1.getCell(i);
						if(cell1!=null)
						{
						
						switch (cell1.getCellType())
						 {
							case Cell.CELL_TYPE_NUMERIC:
								int value =cell1.getNumericCellValue();
								cellValue=Integer.toString(value);
								break;
							case Cell.CELL_TYPE_STRING:
								cellValue= cell1.getStringCellValue();
						}
						
						
						 testdata=cellValue
						
						 break;
					}//if
					}
					}
					
					
				}//for loop
						 
			fis.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			  return testdata;
			  
}
	
}


