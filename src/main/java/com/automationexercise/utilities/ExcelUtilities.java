package com.automationexercise.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtilities {
	public static Object[][] getdata(String filePath, String sheetName) throws IOException {
	    FileInputStream fis = new FileInputStream(filePath);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheet(sheetName);

	    int rowCount = sheet.getPhysicalNumberOfRows();
	    int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	    Object[][] data = new Object[rowCount - 1][colCount];

	    for (int i = 1; i < rowCount; i++) {
	        Row row = sheet.getRow(i);
	        for (int j = 0; j < colCount; j++) {
	            Cell cell = row.getCell(j);
	            data[i - 1][j] = cell.getStringCellValue();
	        }
	    }

	    workbook.close();
	    return data;
	}
}
