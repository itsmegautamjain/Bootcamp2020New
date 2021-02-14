package utilities;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataLibrary {
	
	public String[][] readExcelData(String fileName, String sheetName) throws IOException{
		
		XSSFWorkbook wb = new XSSFWorkbook("E:\\Test Leaf\\WorkSpace\\BootCamp\\Test Data for Salesforce Application\\"+fileName+".xlsx");
		XSSFSheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		XSSFRow row = sh.getRow(0);
		int colCount = row.getLastCellNum();
		XSSFCell cell = row.getCell(0);
		String data[][] = new String[rowCount][colCount];
		for(int i=1;i<=rowCount;i++) {
			row = sh.getRow(i);
			for(int j=0;j<colCount;j++) {
				cell = row.getCell(j);
				cell.setCellType(CellType.STRING);
				data[i-1][j]= cell.getStringCellValue();
				
			}
		}		
		wb.close();
		return data;
		
	}

}
