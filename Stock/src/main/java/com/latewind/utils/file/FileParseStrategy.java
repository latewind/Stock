package com.latewind.utils.file;

import java.io.FileInputStream;
import java.text.ParseException;
import java.util.EnumSet;
import java.util.List;

import com.latewind.utils.enums.FileType;

public interface FileParseStrategy <T> {

	public T parserInputStream(FileInputStream fis) throws ParseException;
	
	
	public EnumSet<FileType> getAcceptType();
	
	
		
}
