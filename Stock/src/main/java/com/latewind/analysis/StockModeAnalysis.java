package com.latewind.analysis;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import org.jfree.ui.RefineryUtilities;

import com.latewind.entity.stock.StockDayInfo;
import com.latewind.service.stock.StockService;
import com.latewind.window.StockWin;

public class StockModeAnalysis extends AbstractStockAnalysis{

	public StockModeAnalysis(List<StockDayInfo> stockDayInfos) {
		super(stockDayInfos);
	}

	
	protected  void analyseStock() {

		for (StockDayInfo stockDayInfo : getStockDayInfos()) {

			BigDecimal closePrice = stockDayInfo.getClosePrice();

			BigDecimal interval = closePrice.setScale(0, BigDecimal.ROUND_DOWN);

			pushInterval(interval);

		}

	}

}
