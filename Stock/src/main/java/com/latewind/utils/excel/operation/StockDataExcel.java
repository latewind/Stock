package com.latewind.utils.excel.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.latewind.utils.excel.parse.ParseExcelStrategy;

public class StockDataExcel {
	private FileInputStream fis = null;
	private FileOutputStream fos =null;
	private List stockList = new LinkedList<List>();
	private ParseExcelStrategy strategy;
	
	public StockDataExcel(ParseExcelStrategy strategy){
		
		this.strategy=strategy;
		
	}
	
	
	public XSSFSheet readExcelSheet(String fileName, Integer sheetIndex) {
		XSSFRow row;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(new File(fileName));
			workbook = new XSSFWorkbook(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook.getSheetAt(sheetIndex);
	}	
	
	
	
	public List parseSheetData(XSSFSheet sheet) {

		return strategy.parseSheetData(sheet);		
	}
	private void closeFileInputStream(){
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeExcelData(String fileName, List list) {
		
		//TODO 会覆盖原有的表格
		XSSFWorkbook workbook = null;
		try {
			fos= new FileOutputStream(new File(fileName));
			workbook = strategy.createWorkBookByPattern(list);	
			workbook.write(fos);
			
			
		} catch (FileNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			
			try {
				fos.close();
				workbook.close();
				
			} catch (IOException e) {
				//  Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
