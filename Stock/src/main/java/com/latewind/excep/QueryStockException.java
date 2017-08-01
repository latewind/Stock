package com.latewind.excep;

import com.latewind.enums.StockErrorCode;

public class QueryStockException  extends AbstractStockException{

	public QueryStockException(StockErrorCode errorCode) {
		super(errorCode);
		// Auto-generated constructor stub
	}

}
