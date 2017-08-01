/**
 * 
 */
package com.latewind.utils.tools;

/**
 * @author Administrator
 *
 */
public class FileUtil {

	
	public static String getFileSuffix(String filename) {
		int pIndex = 0;
		pIndex = filename.lastIndexOf(".");
		String ext =  filename.substring(pIndex).trim();
		return ext.toLowerCase();
	}
	
	
	public static String removeFileSuffix(String filename){
		
		return filename.substring(0, filename.lastIndexOf("."));
	}

	
	public static String getNameExcpExt(String filename) {
		int pIndex = filename.lastIndexOf(".");
		String name = filename.substring(0, pIndex);
		System.out.println("" + name);
		return name;

	}

	public static String getWEBINFPath() {
		String path = FileUtil.class.getResource("/").getPath();
		String path2 = Thread.currentThread().getContextClassLoader().getResource("").toString();
		System.out.println(path);
		path = path.replace("/classes", "");
		System.out.println(path);
		return path;
	}

	public static void main(String agrs[]) {

		getNameExcpExt("1234.test");
		getWEBINFPath();
		getFileSuffix("23232.1212");

	}

}
