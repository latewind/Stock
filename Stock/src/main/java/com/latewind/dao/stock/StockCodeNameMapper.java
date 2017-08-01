package com.latewind.dao.stock;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.latewind.entity.stock.StockCodeName;

public interface StockCodeNameMapper {
    int deleteByPrimaryKey(String stockCode);

    int insert(StockCodeName record);

    int insertSelective(StockCodeName record);

    StockCodeName selectByPrimaryKey(String stockCode);

    int updateByPrimaryKeySelective(StockCodeName record);

    int updateByPrimaryKey(StockCodeName record);
    
//    ----------------------------------------------------------------//
    int insertStockCodeName(@Param("map") Map<String, String> map);
    
    List<StockCodeName> selectAllCodeName();

}