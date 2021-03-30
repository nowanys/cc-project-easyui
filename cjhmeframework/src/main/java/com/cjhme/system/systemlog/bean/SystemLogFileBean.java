package com.cjhme.system.systemlog.bean;

/**
 * 
 * <p>
 * Title: SystemLogFileBean.java
 * </p>
 * Description: 鏃ュ織鏂囦欢
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Dec 15, 2013 6:59:38 PM
 */
public class SystemLogFileBean {

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件路径
	 */
	private String filePath;

	/**
	 * 文件大小
	 */
	private String fileSize;

	/**
	 * 最后修改时间
	 */
	private String lastModifyDate;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

}
