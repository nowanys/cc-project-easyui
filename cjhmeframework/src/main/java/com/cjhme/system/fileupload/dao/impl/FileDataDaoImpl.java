package com.cjhme.system.fileupload.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.fileupload.bean.FileDataBean;
import com.cjhme.system.fileupload.dao.FileDataDao;
import com.cjhme.system.main.dao.BaseDao;

/**
 * 
 * <p>  
 * Title: FileDataDaoImpl.java 
 * </p>  
 * Description: 文件数据
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Apr 7, 2015 7:52:10 PM
 */
@Repository("fileDataDao")
public class FileDataDaoImpl extends BaseDao implements FileDataDao {

	/**
	 * 添加文件数据
	 * @param parameter
	 * @return
	 */
	public  int saveFileData(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.fileupload.dao.FileDataDao.saveFileData",parameter);
	}
	
	/**
	 * 根据业务标识与业务id查询文件数据
	 * @param parameter
	 * @return
	 */
	public List<FileDataBean> queryFileDataByBMarkAndBId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.fileupload.dao.FileDataDao.queryFileDataByBMarkAndBId", parameter);
	}
	
	/**
	 * 根据id修改文件数据
	 * @param parameter
	 * @return
	 */
	public int updateFileDataByFileId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.fileupload.dao.FileDataDao.updateFileDataByFileId",parameter);
	}
	
}
