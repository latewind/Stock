package com.latewind.test.stock;

import java.io.File;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.jfree.ui.RefineryUtilities;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.latewind.analysis.AbstractStockAnalysis;
import com.latewind.entity.stock.StockCodeName;
import com.latewind.entity.stock.StockDayInfo;
import com.latewind.enums.StockCode;
import com.latewind.enums.StockErrorCode;
import com.latewind.excep.AbstractStockException;
import com.latewind.excep.QueryStockException;
import com.latewind.query.StockQueryTerms;
import com.latewind.service.stock.StockService;
import com.latewind.test.base.BasicStockTest;
import com.latewind.utils.download.YaHooLoadStock;
import com.latewind.utils.excel.operation.StockDataExcel;
import com.latewind.utils.excel.parse.DefaultParseExcelStrategy;
import com.latewind.utils.file.FileAccessorSupport;
import com.latewind.utils.file.StockCsvFileParseStrategy;
import com.latewind.utils.tools.FileUtil;
import com.latewind.window.StockWin;

public class TestStockMybatis extends BasicStockTest {

	@Resource
	private StockService stockService;

	// @Test
	public void test() {
		// fail("Not yet implemented");

		try {
			System.out.println(stockService.getStockDayInfoById(1).getStockName());
		} catch (AbstractStockException e) {
			// Auto-generated catch block
			System.out.println(e.getErrorInfo());
			e.printStackTrace();

		}
		// System.out.println(StockErrorCode.QUERY_STOCK_NULL.errorInfo());

	}

	// @Test
	@Rollback(false)
	public void testInsertStockIdName() {
		StockDataExcel stockDataExcel = new StockDataExcel(new DefaultParseExcelStrategy());
		String stockId = "010001";
		System.out.println(StockCode.SHSZ_A.match(stockId));
		List<List> stockList = stockDataExcel
				.parseSheetData(stockDataExcel.readExcelSheet("D:/data/stockNameA.xlsx", 0));
		LinkedHashMap<String, String> stockCodeNameMap = new LinkedHashMap<String, String>();
		for (List<String> subList : stockList) {
			stockCodeNameMap.put(subList.get(1), subList.get(0));
		}
		System.out.println(stockCodeNameMap);
		stockService.insertStockCodeName(stockCodeNameMap);

	}

	//
//	 @Test
	public void testListAllStockCodeName() {

		List<StockCodeName> stockCodeNames = stockService.listAllCodeName();
		System.out.println(stockCodeNames);
		YaHooLoadStock stock = new YaHooLoadStock();
		for (StockCodeName stockCodeName : stockCodeNames) {
			stock.load(stockCodeName.getStockCode());

		}
	}

	/**
	 * 从雅虎财经 多线程 下载数据
	 */
//	@Test
	public void testMultThreadDownLoadStock(){
		List<StockCodeName> stockCodeNames = stockService.listAllCodeName();
		System.out.println(stockCodeNames);
		YaHooLoadStock stock = new YaHooLoadStock(stockCodeNames);
		stock.start();	
		while(!stock.isOver()){
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
		
	}
	
	// @Test
	public void checkDownloadFiles() {

		StockDataExcel stockDataExcel = new StockDataExcel(new DefaultParseExcelStrategy());
		List<List> stockList = stockDataExcel
				.parseSheetData(stockDataExcel.readExcelSheet("D:/data/stockNameA.xlsx", 0));
		LinkedHashMap<String, String> stockCodeNameMap = new LinkedHashMap<String, String>();
		for (List<String> subList : stockList) {
			stockCodeNameMap.put(subList.get(1), subList.get(0));
		}
		System.out.println(stockCodeNameMap.keySet().size());

		File file = new File("D:/data/stock");
		List<String> fileNameList = Arrays.asList(file.list());
		System.out.println(fileNameList.size());
		for (String fileName : fileNameList) {
			fileName = FileUtil.removeFileSuffix(fileName);
			stockCodeNameMap.remove(fileName);
		}

		System.out.println(stockCodeNameMap.size() + ":" + stockCodeNameMap);
		Set<String> unLoadStock = stockCodeNameMap.keySet();

		YaHooLoadStock stock = new YaHooLoadStock();
		for (String stockCodeName : unLoadStock) {
			stock.load(stockCodeName);
		}
	}
/**
 * 下载的 csv 文件 储存到MySQL
 */
//	 @Test
	@Rollback(false)
	public void testStockCsvFileStrtegy() {

		String basePath = "F:/data/stock";
		String stockCode;
		File file = new File(basePath);
		List<String> fileNameList = Arrays.asList(file.list());
		StockCsvFileParseStrategy<List<Object>> stockCsvFileParseStrategy = new StockCsvFileParseStrategy<List<Object>>();
		FileAccessorSupport<List<Object>> fileAccessorSupport = new FileAccessorSupport<List<Object>>(
				stockCsvFileParseStrategy);
		for (String fileName : fileNameList) {
			stockCode = FileUtil.removeFileSuffix(fileName);
			System.out.println(basePath + "/" + fileName);
			stockCsvFileParseStrategy.setStockCode(stockCode);
			List content = fileAccessorSupport.readFile(basePath + "/" + fileName);
			System.out.println("Test:" + stockCode + "," + content.size());
			stockService.batchInsertStockInfo(content);

		}
	}

//	 @Test
	public void testListStockByCode() {

		System.out.println(stockService.listStockDayInfoByStockCode("000001"));
	}

	
//	 @Test
	public void testListStockByQueryTerms() {
		 StockQueryTerms stockQueryTerms=new StockQueryTerms();
		 stockQueryTerms.setStockCode("000001");
		 stockQueryTerms.setStartTime("2015-11-22");
		 stockQueryTerms.setEndTime("2016-11-22");

		System.out.println(stockService.listStockDayInfoByStockQueryTerms(stockQueryTerms).size());
	}
	
//	@Test
	public void testStockAnalsis() {

		List<StockDayInfo> stockDayInfos = stockService.listStockDayInfoByStockCode("000001");


	}

//	@Test
	public void testDistrubution() {
			
		 StockQueryTerms stockQueryTerms=new StockQueryTerms();
		 stockQueryTerms.setStockCode("000001");
		 stockQueryTerms.setStartTime("2015-11-22");
		 stockQueryTerms.setEndTime("2016-11-22");
		
		 List<StockDayInfo> stockDayInfos= stockService.listStockDayInfoByStockQueryTerms(stockQueryTerms);
		

	}
	
}
