package com.latewind.excep;

import com.latewind.enums.StockErrorCode;

public abstract class AbstractStockException extends Exception {
	protected String errorInfo;

	public AbstractStockException(StockErrorCode errorCode) {

		this.errorInfo = errorCode.errorInfo();
	}
	
	public String getErrorInfo() {
		return errorInfo;
	}
}
