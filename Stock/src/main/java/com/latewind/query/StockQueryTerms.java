package com.latewind.query;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class StockQueryTerms {
	private String stockCode;
	
	private String stockName;
	
	private String startTime;
	
	private String endTime;
	
	private boolean order;
	
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		
		this.startTime = startTime;

	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;

	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}
	
	
	
	

}
