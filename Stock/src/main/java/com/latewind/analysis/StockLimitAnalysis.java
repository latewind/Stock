package com.latewind.analysis;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.latewind.entity.stock.StockDayInfo;

public class StockLimitAnalysis extends AbstractStockAnalysis{

	public StockLimitAnalysis(List<StockDayInfo> stockDayInfos) {
		super(stockDayInfos);
	}
	
	
	protected void analyseStock() {
		Iterator<StockDayInfo> it = getStockDayInfos().iterator();

		StockDayInfo prevStockDayInfo = it.next();
		BigDecimal prevclosePrice = prevStockDayInfo.getClosePrice();

		while (it.hasNext()) {
			StockDayInfo currStockDayInfo = it.next();
			BigDecimal currClosePrice = currStockDayInfo.getClosePrice();
			BigDecimal stockLimt=calculateLimit(prevclosePrice, currClosePrice);
			pushInterval(stockLimt.setScale(0, BigDecimal.ROUND_HALF_UP)); 
			prevclosePrice = currClosePrice;
		}

	}
	
	private BigDecimal calculateLimit(BigDecimal prevClosePrice,BigDecimal currClosePrice){
		
		BigDecimal subResult = currClosePrice.subtract(prevClosePrice);
		BigDecimal stockLimt = subResult.divide(prevClosePrice, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
		return stockLimt.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		
	}
	

}
