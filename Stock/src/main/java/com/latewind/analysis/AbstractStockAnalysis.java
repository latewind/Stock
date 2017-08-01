package com.latewind.analysis;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import org.jfree.ui.RefineryUtilities;

import com.latewind.entity.stock.StockDayInfo;
import com.latewind.service.stock.StockService;
import com.latewind.window.StockWin;

public abstract class AbstractStockAnalysis {

	private List<StockDayInfo> stockDayInfos;

	private HightPriceComparator hightPriceComparator = new HightPriceComparator();

	private AdjClosePriceComparator adjClosePriceComparator = new AdjClosePriceComparator();

	private LinkedHashMap<BigDecimal, Integer> stockPriceDistribution = new LinkedHashMap<BigDecimal, Integer>();

	private LinkedHashMap<BigDecimal, Integer> finalResultMap = new LinkedHashMap<BigDecimal, Integer>();
	private StockWin window;

	public AbstractStockAnalysis(List<StockDayInfo> stockDayInfos) {

		this.setStockDayInfos(stockDayInfos);
	}


	public void showStockAnalysis() {
		 analyseStock();
		 printStockDetails();
		 showWindow();
	}

	protected  abstract void analyseStock() ;

	protected void printStockDetails() {
		
		Object[] key =  stockPriceDistribution.keySet().toArray();    
		Arrays.sort(key);    
		  
		for(int i = 0; i<key.length; i++)  
		{    
		     System.out.println(key[i]+":"+stockPriceDistribution.get(key[i]));    
		     finalResultMap.put((BigDecimal) key[i], stockPriceDistribution.get(key[i]));
		} 
	}

	protected void pushInterval(BigDecimal interval) {

		if (containThisInterval(interval)) {

			pushIntoInterval(interval);

		} else {

			addNewInterval(interval);

		}

	}

	private Boolean containThisInterval(BigDecimal interval) {

		return stockPriceDistribution.containsKey(interval);
	}

	private void pushIntoInterval(BigDecimal interval) {

		Integer newValue = stockPriceDistribution.get(interval) + 1;

		stockPriceDistribution.put(interval, newValue);
	}

	private void addNewInterval(BigDecimal interval) {
		Integer initValue = 1;
		stockPriceDistribution.put(interval, initValue);
	}

	private void showWindow() {

		window = new StockWin("Stock Analysis ", finalResultMap);

		window.pack();

		RefineryUtilities.centerFrameOnScreen(window);

		window.setVisible(true);
	}

	public List<StockDayInfo> getStockDayInfos() {
		return stockDayInfos;
	}

	public void setStockDayInfos(List<StockDayInfo> stockDayInfos) {
		this.stockDayInfos = stockDayInfos;
	}
	
	
	
	public void sortByHigtPrice() {

		Collections.sort(getStockDayInfos(), hightPriceComparator);
	}

	public void sortByAdjPrice() {

		Collections.sort(getStockDayInfos(), adjClosePriceComparator);
	}

	class HightPriceComparator implements Comparator<StockDayInfo> {

		public int compare(StockDayInfo stock1, StockDayInfo stock2) {
			BigDecimal price1 = stock1.getHighPrice();
			BigDecimal price2 = stock2.getHighPrice();

			return price1.compareTo(price2);
		}
	}

	class AdjClosePriceComparator implements Comparator<StockDayInfo> {

		public int compare(StockDayInfo o1, StockDayInfo o2) {
			BigDecimal price1 = o1.getAdjClosePrice();
			BigDecimal price2 = o2.getAdjClosePrice();
			return price1.compareTo(price2);
		}

	}

}
