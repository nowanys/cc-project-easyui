package com.cjhme.system.fileupload.service;

import java.util.List;
import java.util.Map;

import com.cjhme.system.fileupload.bean.FileDataBean;

/**
 * 
 * <p>  
 * Title: FileDataService.java 
 * </p>  
 * Description: 文件数据
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Apr 8, 2015 6:55:11 PM
 */
public interface FileDataService {

	/**
	 * 根据业务标识与业务id查询文件数据
	 * @param parameter
	 * @return
	 */
	public List<FileDataBean> queryFileDataByBMarkAndBId(Map<String, Object> parameter);
	
	/**
	 * 根据业务标识与业务id查询一条文件数据
	 * @param parameter
	 * @return
	 */
	public FileDataBean queryOneFileDataByBMarkAndBId(Map<String, Object> parameter);
}
