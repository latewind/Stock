package com.latewind.utils.excel.parse;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DefaultParseExcelStrategy implements ParseExcelStrategy {

	private List<List<String>> stockList = new LinkedList<List<String>>();
	
	public DefaultParseExcelStrategy(){
		
		
	}

	public List parseSheetData(XSSFSheet sheet) {

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			XSSFRow row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator=row.cellIterator();
			List<String> subList=new LinkedList<String>();
			while(cellIterator.hasNext()){
			String  cellValue=	switchCellType( cellIterator.next());
				subList.add(cellValue);
			}
			stockList.add(subList);
		}		
		return stockList;
	}
	private String switchCellType(Cell cell){
		
		String cellValue;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			
			cellValue=cell.getStringCellValue().trim();
			break;
		
		case Cell.CELL_TYPE_NUMERIC:
			cellValue =String.valueOf(cell.getNumericCellValue());
			break;
		default:
			cellValue=cellValue=cell.getStringCellValue().trim();
			break;
		}
		return cellValue;
	}
	
	public  XSSFWorkbook createWorkBookByPattern(List list){
			
		XSSFWorkbook workbook = new XSSFWorkbook();
		//TODO 覆盖了Sheet 处理
		
		XSSFSheet sheet = workbook.createSheet();
		for (int i = 0; i < list.size(); i++) {
			XSSFRow row = sheet.createRow(i);
			List<String > subList=(List<String>) list.get(i);
			for (int j = 0; j < subList.size(); j++) {
				row.createCell(j).setCellValue((String)subList.get(j));;
			}
		}
		return workbook;
		
	}
	

}
