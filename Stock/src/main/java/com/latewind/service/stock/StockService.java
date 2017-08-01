package com.latewind.service.stock;

import java.util.List;
import java.util.Map;

import com.latewind.entity.stock.StockCodeName;
import com.latewind.entity.stock.StockDayInfo;
import com.latewind.excep.QueryStockException;
import com.latewind.query.StockQueryTerms;

public interface StockService {

	StockDayInfo getStockDayInfoById(Integer stockId) throws QueryStockException;
	
	int   insertStockCodeName(Map<String, String> map);
	
	int   batchInsertStockInfo(List<StockDayInfo> stockDayInfos);
	
	
	List<StockDayInfo> listStockDayInfoByStockCode(String stockCode);
	
	
	List<StockDayInfo> listStockDayInfoByStockQueryTerms(StockQueryTerms queryTerms);
	
	
	public  List<StockCodeName> listAllCodeName();
		
	
}
