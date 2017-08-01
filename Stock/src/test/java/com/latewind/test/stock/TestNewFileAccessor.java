package com.latewind.test.stock;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.latewind.utils.enums.FileType;
import com.latewind.utils.file.CsvFileParseStrategy;
import com.latewind.utils.file.DefaultParseExcelStrategy;
import com.latewind.utils.file.FileAccessorSupport;
import com.latewind.utils.file.StockCsvFileParseStrategy;
import com.latewind.utils.tools.FileUtil;



public class TestNewFileAccessor {

//	 @Test
	public void testWriteStockNameAndId2Database() {

		FileAccessorSupport<List<List<String>>> stockDataExcel = new FileAccessorSupport<List<List<String>>>(new DefaultParseExcelStrategy<List<List<String>>>());

		List<List<String>> fileContent=	stockDataExcel.readFile("D:/data/stockNameA.xlsx");

		System.out.println(fileContent);

	}
	 
//	 @Test
	 public void testEnumFileType(){
		 
		 String fileType=".xlsx";
		 System.out.println(FileType.getType(fileType));
		 fileType = FileUtil.getFileSuffix("D:/data/stockNameA.xlsx");
		 System.out.println(fileType);
		 
		 
	 }
	 
//	 @Test
	 public void TestCsvFileStrtegy(){
		 
		 FileAccessorSupport<List<Object>> fileAccessorSupport=new FileAccessorSupport<List<Object>>(new StockCsvFileParseStrategy<List<Object>>());
		List content= fileAccessorSupport.readFile("D:/data/stock/000001.csv");
		System.out.println(content); 
		content= fileAccessorSupport.readFile("D:/data/stock/000002.csv");
		System.out.println(content); 		
	
		content= fileAccessorSupport.readFile("D:/data/stock/000005.csv");
		System.out.println(content); 
	 }
	 
	 
	 @Test
	 public void TestStockCsvFileStrtegy(){
		 
		 
		 	String basePath="D:/data/stock";
		 	String stockCode;
			File file=new File(basePath);
			List<String> fileNameList=Arrays.asList(file.list());
//			System.out.println(fileNameList.size());
			StockCsvFileParseStrategy<List<Object>> stockCsvFileParseStrategy=new StockCsvFileParseStrategy<List<Object>>();
			FileAccessorSupport<List<Object>> fileAccessorSupport=new FileAccessorSupport<List<Object>>(stockCsvFileParseStrategy);
			for(String fileName:fileNameList){
				stockCode=FileUtil.removeFileSuffix(fileName);
				System.out.println(basePath+"/"+fileName);
				stockCsvFileParseStrategy.setStockCode(stockCode);
				
				List content= fileAccessorSupport.readFile(basePath+"/"+fileName);
				
				System.out.println("Test:"+stockCode+","+content.size()); 
				
			}
		 
		 
		
	 }
	 

}
