package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		//Taking xl file from test data folder
		String path=".\\testData\\Opencart_LoginData.xlsx";
		
		//Creating object for XLutility 
		ExcelUtility xlutil=new ExcelUtility(path);
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);		
		//Create 2D array to store row and column value
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1; i<=totalrows; i++) {
			for(int j=0; j<totalcols; j++) {
				logindata[i-1][j]=xlutil.getCellValue("Sheet1", i, j);	//here i starting from 1, no need to waste oth index of String variable
			}
		}
		return logindata;
	}
	
	//Data Provider 2
	//DataProvider 3

}
