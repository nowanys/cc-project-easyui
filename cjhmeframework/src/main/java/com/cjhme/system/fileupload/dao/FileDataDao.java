package com.cjhme.system.fileupload.dao;

import java.util.List;
import java.util.Map;

import com.cjhme.system.fileupload.bean.FileDataBean;

/**
 * 
 * <p>  
 * Title: FileDataDao.java 
 * </p>  
 * Description: 文件数据
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Apr 7, 2015 7:51:43 PM
 */
public interface FileDataDao {

	/**
	 * 添加文件数据
	 * @param parameter
	 * @return
	 */
	public int saveFileData(Map<String, Object> parameter);
	
	/**
	 * 根据业务标识与业务id查询文件数据
	 * @param parameter
	 * @return
	 */
	public List<FileDataBean> queryFileDataByBMarkAndBId(Map<String, Object> parameter);
	
	/**
	 * 根据id修改文件数据
	 * @param parameter
	 * @return
	 */
	public int updateFileDataByFileId(Map<String, Object> parameter);
}
