package com.latewind.utils.file;

import java.io.File;

import com.latewind.utils.tools.FileUtil;

public class FileAccessorSupport<T> extends AbstractFileAccessor<T> {

	public FileAccessorSupport(FileParseStrategy<T> parseStrategy) {
		super(parseStrategy);
	}

	
	@Override
	public Object writeFile() {
		return null;
	}


	

}
