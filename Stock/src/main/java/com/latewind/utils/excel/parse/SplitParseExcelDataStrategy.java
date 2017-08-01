package com.latewind.utils.excel.parse;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.latewind.enums.StockCode;

public class SplitParseExcelDataStrategy extends DefaultParseExcelStrategy  {
	private List stockList = new LinkedList<List>();
	
	private StockCode stockCode=StockCode.SHSZ_A;
	
	
	
	public  SplitParseExcelDataStrategy() {
		super();
	}
	
	public SplitParseExcelDataStrategy(StockCode stockCode){
		this.stockCode=stockCode;
	}

	
	@Override
	public List parseSheetData(XSSFSheet sheet) {
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			XSSFRow row = (XSSFRow) rowIterator.next();
			String stockNameAndId = row.getCell(0).getStringCellValue().trim();
			String[] stocks = stockNameAndId.split("[\\(|\\)]");
			
			if(filterStocks(stocks)){
			stockList.add(Arrays.asList(stocks));
			}
		}		
		return stockList;
	}
	
	
	private boolean filterStocks(String[] stocks){
		if(stocks.length<2){
			System.out.println(stocks[0]);
			return false;
		}
		return stockCode.match(stocks[1]);
		
	}

}
