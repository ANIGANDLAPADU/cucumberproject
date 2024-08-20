package com.cucumber.datadriven;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

	public static List<HashMap<String, String>> data(String filepath, String sheetName) {

		List<HashMap<String, String>> mydata = new ArrayList<>();

		try {
			FileInputStream fs = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row HeaderRow = sheet.getRow(0);
			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row currentRow = sheet.getRow(i);
				HashMap<String, String> currentHash = new HashMap<String, String>();
				for (int j = 0; j < currentRow.getLastCellNum(); j++) {
					Cell currentCell = currentRow.getCell(j);
			
						currentHash.put(HeaderRow.getCell(j).toString(), currentCell.toString());
				}
				mydata.add(currentHash);
			}
			fs.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mydata;
	}
}
