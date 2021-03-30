package com.cjhme.system.fileupload.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cjhme.system.fileupload.bean.FileDataBean;
import com.cjhme.system.fileupload.dao.FileDataDao;
import com.cjhme.system.fileupload.service.FileDataService;

/**
 * 
 * <p>  
 * Title: FileDataServiceImpl.java 
 * </p>  
 * Description: 文件数据
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Apr 8, 2015 6:55:11 PM
 */
@Service("fileDataService")
public class FileDataServiceImpl implements FileDataService {
	
	private FileDataDao  fileDataDao;

	/**
	 * 根据业务标识与业务id查询文件数据
	 * @param parameter
	 * @return
	 */
	public List<FileDataBean> queryFileDataByBMarkAndBId(Map<String, Object> parameter){
		return this.fileDataDao.queryFileDataByBMarkAndBId(parameter);
	}
	
	/**
	 * 根据业务标识与业务id查询一条文件数据
	 * @param parameter
	 * @return
	 */
	public FileDataBean queryOneFileDataByBMarkAndBId(Map<String, Object> parameter){
		List<FileDataBean> fileDatas= this.fileDataDao.queryFileDataByBMarkAndBId(parameter);
		if(null!=fileDatas && !"".equals(fileDatas) && !"null".equals(fileDatas) && fileDatas.size()>0){
			return fileDatas.get(0);
		}else{
			return null;
		}
	}
	
	
	
	public FileDataDao getFileDataDao() {
		return fileDataDao;
	}

	@Resource
	public void setFileDataDao(FileDataDao fileDataDao) {
		this.fileDataDao = fileDataDao;
	}
	
	

}
