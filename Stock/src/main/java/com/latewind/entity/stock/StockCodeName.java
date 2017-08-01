package com.latewind.entity.stock;

public class StockCodeName {
    private String stockCode;

    private String stockName;
    

    @Override
	public String toString() {
		return "StockCodeName [stockCode=" + stockCode + ", stockName=" + stockName + "]";
	}

	public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }
}