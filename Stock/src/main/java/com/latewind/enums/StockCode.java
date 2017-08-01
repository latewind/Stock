package com.latewind.enums;

public enum StockCode {

	SH_A("^(600|601|602|603)\\d{3}"), SZ_A("^(000|002|300)\\d{3}"),

	SHSZ_A("^(600|601|602|603|000|002|300)\\d{3}"), SH_ZB_A("^(600|601|602|603)\\d{3}"),

	SZ_ZB_A("^(000)\\d{3}"), SZ_ZXB_A("^(002)\\d{3}"), SZ_ZYB_A("^(300)\\d{3}");

	String regex;

	StockCode(String regex) {

		this.regex = regex;

	}

	public boolean match(String stockCode) {

		return stockCode.matches(regex);
	}

}
