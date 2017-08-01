package com.latewind.enums;

public enum StockLoadUrl {

	Base_URL_YAHOO("http://table.finance.yahoo.com/table.csv?s=");

	String url;

	StockLoadUrl(String url) {

		this.url = url;

	}

	public String url() {

		return url;
	}

}
