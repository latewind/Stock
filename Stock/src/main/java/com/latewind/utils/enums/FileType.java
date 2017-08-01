package com.latewind.utils.enums;

public enum FileType {

	XLSX(".xlsx"), CSV(".csv");

	String fileType;

	FileType(String fileType) {
		this.fileType = fileType;
	}
	
    public static FileType getType(String fileType) {
        for (FileType type : FileType.values()) {
            if (type.fileType.equals(fileType)) {
                return type;
            }
        }
        return null;
    }

}
