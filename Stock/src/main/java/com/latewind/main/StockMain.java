package com.latewind.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.latewind.analysis.AbstractStockAnalysis;
import com.latewind.analysis.StockLimitAnalysis;
import com.latewind.analysis.StockModeAnalysis;
import com.latewind.entity.stock.StockDayInfo;
import com.latewind.query.StockQueryTerms;
import com.latewind.service.stock.StockService;

public class StockMain {

	public static void main(String[] args) {
		 ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-mybatis.xml"); 
		StockService stockService=(StockService) context.getBean("stockService");
		 StockQueryTerms stockQueryTerms=new StockQueryTerms();
		 stockQueryTerms.setStockCode("600111");
		 stockQueryTerms.setStartTime("2015-11-22");
		 stockQueryTerms.setEndTime("2016-11-22");
		
		 List<StockDayInfo> stockDayInfos= stockService.listStockDayInfoByStockQueryTerms(stockQueryTerms);
		

		AbstractStockAnalysis stockAnalysis = new StockModeAnalysis(stockDayInfos);

		stockAnalysis.showStockAnalysis();
		 

	}

}
