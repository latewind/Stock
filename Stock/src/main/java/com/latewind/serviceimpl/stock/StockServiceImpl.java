package com.latewind.serviceimpl.stock;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.concurrent.AbortedException;
import javax.management.loading.PrivateClassLoader;

import org.springframework.stereotype.Service;

import com.latewind.dao.stock.StockCodeNameMapper;
import com.latewind.dao.stock.StockDayInfoMapper;
import com.latewind.entity.stock.StockCodeName;
import com.latewind.entity.stock.StockDayInfo;
import com.latewind.enums.StockErrorCode;
import com.latewind.excep.AbstractStockException;
import com.latewind.excep.QueryStockException;
import com.latewind.query.StockQueryTerms;
import com.latewind.service.stock.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService {

	@Resource
	private StockDayInfoMapper stockDayInfoDao;
	@Resource
	private  StockCodeNameMapper stockCodeNameDao;
	
	public StockDayInfo getStockDayInfoById(Integer id) throws  QueryStockException{
		//  Auto-generated method stub
		
		StockDayInfo stockDayInfo=stockDayInfoDao.selectByPrimaryKey(id);
		
		if (stockDayInfo==null) {
			
			throw new QueryStockException(StockErrorCode.QUERY_STOCK_NULL);
		}
		return stockDayInfo;
	}

	public int insertStockCodeName(Map<String, String> map) {
		//  Auto-generated method stub
		return stockCodeNameDao.insertStockCodeName(map);
	}
	
	public  List<StockCodeName> listAllCodeName(){
		
		return stockCodeNameDao.selectAllCodeName();
		
	}

	public int batchInsertStockInfo(List<StockDayInfo> stockDayInfos) {
		//  Auto-generated method stub
		return stockDayInfoDao.insertBatch(stockDayInfos);
	}

	public List<StockDayInfo> listStockDayInfoByStockCode(String stockCode) {
		//  Auto-generated method stub
		return stockDayInfoDao.selectByStockCode(stockCode);
	}

	@Override
	public List<StockDayInfo> listStockDayInfoByStockQueryTerms(StockQueryTerms queryTerms) {
		// TODO Auto-generated method stub
		return stockDayInfoDao.selectByStockQueryTerms(queryTerms);
	}


	
}
