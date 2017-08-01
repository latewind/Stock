package com.latewind.utils.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

import com.latewind.utils.enums.FileType;


public class CsvFileParseStrategy<T extends List<Object>> implements FileParseStrategy<T> {

	private EnumSet<FileType> acceptTpye = EnumSet.of(FileType.CSV);

	private BufferedReader br = null;

	private String readLine;

	private List<Object> contentList;

	public T parserInputStream(FileInputStream fis) throws ParseException {

		contentList=new LinkedList<Object>();
		br = new BufferedReader(new InputStreamReader(fis));
		try {
			while ((readLine = br.readLine()) != null) {
				splitLine(readLine);
			}
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				//  Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  (T) contentList;
	}

	
	private void splitLine(String line) {
		
		line = line.trim();
		String[] contentLine = line.split(",");
		
		List<String> lineList=Arrays.asList(contentLine);
		
		contentList .add(lineList);
	}
	
	
	public EnumSet<FileType> getAcceptType() {
		// Auto-generated method stub
		return acceptTpye;
	}


}
