package com.latewind.enums;

public enum StockErrorCode {

	QUERY_STOCK_NULL("没有查询到相关股票信息");
	String errorInfo;
	StockErrorCode(String errorInfo){
		this.errorInfo=errorInfo;
	}
	
	public String errorInfo(){return errorInfo;}
}
