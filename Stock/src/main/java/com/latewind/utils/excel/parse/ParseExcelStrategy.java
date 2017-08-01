package com.latewind.utils.excel.parse;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ParseExcelStrategy {

	List parseSheetData(XSSFSheet sheet);

	XSSFWorkbook createWorkBookByPattern(List list);

}
