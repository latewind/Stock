package com.latewind.entity.stock;

import java.math.BigDecimal;
import java.util.Date;

public class StockDayInfo  implements Nullable{
    private Integer stockId;

    private String stockCode;

    private String stockName;

    private Date tradeTime;

    private BigDecimal openPrice;

    private BigDecimal highPrice;

    private BigDecimal lowPrice;

    private BigDecimal closePrice;

    private BigDecimal adjClosePrice;

    private Long volume;
    
    @Override
	public String toString() {
		return "StockDayInfo [stockId=" + stockId + ", stockCode=" + stockCode + ", stockName=" + stockName
				+ ", tradeTime=" + tradeTime + ", openPrice=" + openPrice + ", highPrice=" + highPrice + ", lowPrice="
				+ lowPrice + ", closePrice=" + closePrice + ", adjClosePrice=" + adjClosePrice + ", volume=" + volume
				+ "]";
	}

	public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
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

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public BigDecimal getAdjClosePrice() {
        return adjClosePrice;
    }

    public void setAdjClosePrice(BigDecimal adjClosePrice) {
        this.adjClosePrice = adjClosePrice;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

	public boolean isNull() {
		//  Auto-generated method stub
		return false;
	}
}