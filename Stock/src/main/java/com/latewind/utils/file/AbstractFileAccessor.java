package com.latewind.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumSet;

import com.latewind.utils.enums.FileType;
import com.latewind.utils.tools.FileUtil;

public abstract class AbstractFileAccessor<T> {

	private FileParseStrategy<T> parseStrategy;

	private FileInputStream fis = null;

	private String fileSuffix;
	
	private String fileName;
	
	public AbstractFileAccessor(FileParseStrategy<T> parseStrategy) {

		this.parseStrategy = parseStrategy;
	}

	public T readFile(String fileName) {
		T fileContent = null;
		if (validateFileType(fileName)) {
			try {
				getFileInputStream(fileName);
				fileContent = parseStrategy.parserInputStream(fis);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				closeInputStream();
					
			}
		}
		return fileContent;

	}
	
	private void getFileInputStream(String fileName){
		File file = new File(fileName);
		try {
			fis = new FileInputStream(file);
			setFileName(fileName);
		} catch (FileNotFoundException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void closeInputStream(){
		
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public abstract Object writeFile();

	public void setParseStrategy(FileParseStrategy<T> parseStrategy) {
		this.parseStrategy = parseStrategy;
	}

	private boolean validateFileType(String fileName) {

		fileSuffix = FileUtil.getFileSuffix(fileName);
		
		FileType fType = FileType.getType(fileSuffix);

		EnumSet<FileType> acceptType = parseStrategy.getAcceptType();

		if (acceptType.contains(fType)) {
			
			return true;
		} else {
			
			return false;
		}

	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
