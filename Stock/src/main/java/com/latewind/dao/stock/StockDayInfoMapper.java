package com.latewind.dao.stock;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.latewind.entity.stock.StockDayInfo;
import com.latewind.query.StockQueryTerms;

public interface StockDayInfoMapper {
    int deleteByPrimaryKey(String stockId);

    int insert(StockDayInfo record);

    int insertSelective(StockDayInfo record);

    StockDayInfo selectByPrimaryKey(Integer stockId);
    
    List<StockDayInfo> selectByStockCode(String stockCode);
    
    List<StockDayInfo> selectByStockQueryTerms(@Param("queryTerms")StockQueryTerms queryTerms);

    int updateByPrimaryKeySelective(StockDayInfo record);

    int updateByPrimaryKey(StockDayInfo record);

	int insertBatch(@Param("list")List<StockDayInfo> stockDayInfos);

    
    
    
}