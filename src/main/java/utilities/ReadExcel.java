package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	HashMap<String,List<String>> mp = new HashMap<String,List<String>>();
	public static String fileName="E:\\Test Leaf\\WorkSpace\\BootCamp\\Bootcamp Test Data.xlsx";
	static String sheetName = "Opportunity";
	
	private String key;
	private String val;
	private String result;
//	public static void main(String[] args) throws IOException {
//		 
//		ReadExcel obj = new ReadExcel();
//		obj.storeDataFromExcel();		
//	}
	
	public ReadExcel() {
		
	}
	
	public ReadExcel(String key, String val) {
		this.key = key;
		this.val = val;
	}
	
	public String getKey() {
		return key;
	}

	public String getVal() {
		return val;
	}
	
	public String storeDataFromExcel(String Key, int index) throws IOException {		
		List<ReadExcel> ls = new ArrayList<ReadExcel>();		
		XSSFWorkbook wb = new XSSFWorkbook(fileName);
		XSSFSheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		XSSFRow row = sh.getRow(0);
		int colCount = row.getLastCellNum();
		
		for(int i=0;i<colCount;i++) {
			for(int j=1;j<=rowCount;j++) {
				XSSFRow row_Val = sh.getRow(j);
				XSSFCell cell_Val = row_Val.getCell(i);
				XSSFCell cell_Key = row.getCell(i);
				ls.add(new ReadExcel(cell_Key.getStringCellValue(),cell_Val.getStringCellValue()));				
			}
		}
		
		for(ReadExcel itr:ls) {
			if(mp.containsKey(itr.getKey())) {
				mp.get(itr.getKey()).add(itr.getVal());
			}
			else {
				List<String> tempList = new ArrayList<String>();
				tempList.add(itr.getVal());
				mp.put(itr.getKey(),tempList);
			}
		}		
		  for(Map.Entry<String,List<String>> m : mp.entrySet()) {
			  if(m.getKey().toString().equals(Key)) {
				  result = m.getValue().get(index).toString();
			  }
			  System.out.println("Key is:-- "+m.getKey()+ " and value is:--- " +m.getValue());
		  }			  
		  
		  wb.close();
		return result;
	}	
}
