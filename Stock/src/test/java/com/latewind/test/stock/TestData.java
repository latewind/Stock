package com.latewind.test.stock;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import com.latewind.enums.StockCode;
import com.latewind.utils.download.YaHooLoadStock;
import com.latewind.utils.excel.operation.StockDataExcel;
import com.latewind.utils.excel.parse.DefaultParseExcelStrategy;
import com.latewind.utils.excel.parse.SplitParseExcelDataStrategy;
import com.sun.javafx.collections.MappingChange.Map;

public class TestData {

//	 @Test
	public void testParseStockNameAndId() {
		// fail("Not yet implemented");

		String stockName = "精准医疗(501005)";
		Pattern pattern = Pattern.compile(stockName);
		String[] aStrings = stockName.split("[\\(|\\)]");

		StockDataExcel stockDataExcel = new StockDataExcel(new SplitParseExcelDataStrategy());

		List stockList = stockDataExcel.parseSheetData(stockDataExcel.readExcelSheet("D:/data/stockNameId.xlsx", 0));

		stockDataExcel.writeExcelData("D:/data/stockNameA.xlsx", stockList);
	}

//	 @Test
	public void testWriteStockNameAndId2Database() {

		StockDataExcel stockDataExcel = new StockDataExcel(new DefaultParseExcelStrategy());

		List stockList = stockDataExcel.parseSheetData(stockDataExcel.readExcelSheet("D:/data/stockNameA.xlsx", 0));

		System.out.println(stockList);

	}

//	@Test
	public void testLoadDataFromNet() {

	
	}

	 @Test
	public void simpleTest() {

		StockDataExcel stockDataExcel = new StockDataExcel(new DefaultParseExcelStrategy());
		String stockId = "010001";
		System.out.println(StockCode.SHSZ_A.match(stockId));

		List<List> stockList = stockDataExcel.parseSheetData(stockDataExcel.readExcelSheet("D:/data/stockNameA.xlsx", 0));
		LinkedHashMap<String, String> stockCodeNameMap=new LinkedHashMap<String,String>();
		for(List<String> subList:stockList){
			stockCodeNameMap.put(subList.get(1), subList.get(0));			
		}
		System.out.println(stockCodeNameMap);
		
	}

}
