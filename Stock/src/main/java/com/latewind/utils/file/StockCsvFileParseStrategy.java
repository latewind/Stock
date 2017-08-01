package com.latewind.utils.file;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.inject.New;

import com.latewind.entity.stock.StockDayInfo;

public class StockCsvFileParseStrategy<T extends List<Object>> extends CsvFileParseStrategy<T> {

	private String stockCode;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public T parserInputStream(FileInputStream fis) throws ParseException {
		T stockDayInfos = (T) new LinkedList<Object>();
		List contentList = super.parserInputStream(fis);
		int size = contentList.size();
		for (int i = 1; i < size; i++) {
			List subList = (List) contentList.get(i);
			StockDayInfo stockDayInfo = new StockDayInfo();
			convertList2StockDayInfo(subList, stockDayInfo);
			stockDayInfos.add(stockDayInfo);
		}
		return stockDayInfos;
	}

	private void convertList2StockDayInfo(List subList, StockDayInfo targetStockObj) throws ParseException {

			 String date=(String) subList.get(0);
			 
			 String openPrice=(String)subList.get(1);
			 
			 String hightPrice=(String)subList.get(2);
			 
			 String lowPrice=(String)subList.get(3);
			 
			 String closePrice=(String)subList.get(4);
			 
			 String volume=(String)subList.get(5);
			 
			 String adjClose=(String)subList.get(6);
			 
			 
//			 System.out.println(date+","+openPrice+","+hightPrice+",");
			 targetStockObj.setOpenPrice(scale(openPrice));
			 
			 targetStockObj.setHighPrice(scale(hightPrice));
			 
			 targetStockObj.setLowPrice(scale(lowPrice));
			 
			 targetStockObj.setClosePrice(scale(closePrice));
			 
			 targetStockObj.setVolume(Long.valueOf(volume));
			 
			 targetStockObj.setAdjClosePrice(scale(adjClose));
			 
			 targetStockObj.setStockCode(getStockCode());
			
			targetStockObj.setTradeTime(sdf.parse(date));
			
	
			//  Auto-generated catch block
	

	}
	
	private static BigDecimal scale(String strVale){
		
		BigDecimal bigDecimal=new BigDecimal(strVale);
		bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bigDecimal;
		
	}


	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

}
