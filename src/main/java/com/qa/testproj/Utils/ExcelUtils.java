package com.qa.testproj.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public static String Test_Data_Sheet_Path = ".//src//main//java//com//qa//testproj//TestData//TutorialsNinjaTesData.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	

	public static Object[][] getDatafromsheet(String SheetName) {
		
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(Test_Data_Sheet_Path);
			try {
				book = WorkbookFactory.create(ip);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sheet = book.getSheet(SheetName);

			 data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {

				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}

			}

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;

	}

}
