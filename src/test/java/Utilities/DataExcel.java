package Utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class DataExcel {
	public static Object[][] readExcel(String path,String namesheet) throws Exception {
		FileInputStream file = new FileInputStream(new File(path));
		
		XSSFWorkbook Excel= new XSSFWorkbook(file);
		XSSFSheet Sheet=Excel.getSheet(namesheet);
		
		System.out.println(namesheet);
		
		XSSFRow row;
		int rows = Sheet.getPhysicalNumberOfRows();
		int columns= Sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object cellValue[][]=new Object[rows][columns];
		
		for (int r = 0; r < rows; r++) {
		 row = Sheet.getRow(r);
		 if (row == null){ 
			 break; 
	     }else{ 
	    	 for (int c = 0; c < columns; c++) {
	    		 DataFormatter dataFormatter = new DataFormatter();
	    		 cellValue[r][c] = dataFormatter.formatCellValue(Sheet.getRow(r).getCell(c));
	    	 } 
	     }
		 }
		
		Excel.close();
		
		return cellValue; 
	} 
}
