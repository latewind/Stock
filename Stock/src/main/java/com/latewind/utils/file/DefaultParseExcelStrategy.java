package com.latewind.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.latewind.utils.enums.FileType;
import com.latewind.utils.excel.parse.ParseExcelStrategy;

public class DefaultParseExcelStrategy<T extends List<List<String>>>
		implements ParseExcelStrategy, FileParseStrategy<T> {

	private List<List<String>> stockList = new LinkedList<List<String>>();

	private  EnumSet<FileType> acceptTpye=EnumSet.of(FileType.XLSX);


	public DefaultParseExcelStrategy() {
	}

	public List<List<String>> parseSheetData(XSSFSheet sheet) {

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			XSSFRow row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			List<String> subList = new LinkedList<String>();
			while (cellIterator.hasNext()) {
				String cellValue = switchCellType(cellIterator.next());
				subList.add(cellValue);
			}
			stockList.add(subList);
		}
		return stockList;
	}

	private String switchCellType(Cell cell) {

		String cellValue;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:

			cellValue = cell.getStringCellValue().trim();
			break;

		case Cell.CELL_TYPE_NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		default:
			cellValue = cellValue = cell.getStringCellValue().trim();
			break;
		}
		return cellValue;
	}

	public XSSFWorkbook createWorkBookByPattern(List list) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		// TODO 覆盖了Sheet 处理

		XSSFSheet sheet = workbook.createSheet();
		for (int i = 0; i < list.size(); i++) {
			XSSFRow row = sheet.createRow(i);
			List<String> subList = (List<String>) list.get(i);
			for (int j = 0; j < subList.size(); j++) {
				row.createCell(j).setCellValue(subList.get(j));
				;
			}
		}
		return workbook;

	}

	public T parserInputStream(FileInputStream fis) {

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);

		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		// Auto-generated method stub
		return (T) parseSheetData(workbook.getSheetAt(0));
	}

	public EnumSet<FileType> getAcceptType() {
		// Auto-generated method stub
		return acceptTpye;
	}



}
