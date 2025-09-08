package com.E_Commerce_Web_App.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
    public static Object[][] getdata(String excelPath, String sheetName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        File file = new File(excelPath);
        FileInputStream fs = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount]; // skipping header row
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }

        workbook.close();
        fs.close();
        return data;
    }
}
