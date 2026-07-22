package com.iasys.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private Workbook workbook;

	public ExcelUtil(String filePath) {
		try {

			workbook = new XSSFWorkbook(new FileInputStream(filePath));

		} catch (IOException e) {

			throw new RuntimeException("Unable to get excel file" + filePath, e);

		}

	}

	public int getRowCount(String sheetName) {
		return workbook.getSheet(sheetName).getPhysicalNumberOfRows();

	}

	public int getColumnCount(String SheetName) {

		return workbook.getSheet(SheetName).getRow(0).getLastCellNum();

	}

	public String getCellData(String sheetName, int rowNum, int colNum) {

		DataFormatter formatter = new DataFormatter();

		Cell cell = workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum);

		return formatter.formatCellValue(cell);

	}

	public Object[][] getSheetData(String sheetName) {

		Sheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows - 1][cols];

		DataFormatter formatter = new DataFormatter();
		for (int i = 1; i < rows; i++) {

			for (int j = 0; j < cols; j++) {

				data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));

			}

		}

		return data;

	}

	public void close() {

		try {

			workbook.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
