package com.cjhme.system.access.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.access.bean.AccessUrlBean;
import com.cjhme.system.access.dao.AccessUrlDao;
import com.cjhme.system.access.service.AccessUrlService;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: AccessUrlServiceImpl.java 
 * </p>  
 * Description: 访问地址
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Aug 7, 2015 5:23:28 PM
 */
@Service("accessUrlService")
public class AccessUrlServiceImpl implements AccessUrlService {

	private AccessUrlDao accessUrlDao;

	
	/**
	 * 根据条件分页查询访问地址
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryAccessUrlByConditionPaging(DataPaging<Object> pageParameter){
		return this.accessUrlDao.queryAccessUrlByConditionPaging(pageParameter);
	}
	
	/**
	 * 判断访问地址是否存在
	 * @param parameter
	 * @return
	 */
	public int queryAccessUrlIsExists(Map<String,Object> parameter){
		return this.accessUrlDao.queryAccessUrlIsExists(parameter);
	}
	
	/**
	 * 添加访问地址
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveAccessUrl(Map<String,Object> parameter){
		return this.accessUrlDao.saveAccessUrl(parameter);
	}
	
	/**
	 * 根据id查询访问地址
	 * @param parameter
	 * @return
	 */
	public AccessUrlBean queryAccessUrlById(Map<String,Object> parameter){
		return this.accessUrlDao.queryAccessUrlById(parameter);
	}
	
	/**
	 *  根据id修改访问地址
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateAccessUrlById(Map<String,Object> parameter){
		return this.accessUrlDao.updateAccessUrlById(parameter);
	}
	
	/**
	 * 根据ids删除访问地址
	 */
	@Transactional
	public int deleteAccessUrlByIds(Map<String,Object> parameter){
		//删除访问地址角色映射
		this.accessUrlDao.deleteAccessUrlRoleMappingByIds(parameter);
		
		//删除访问地址
		return this.accessUrlDao.deleteAccessUrlByIds(parameter);
	}
	
	/**
	 * 查询所有访问地址列表
	 * @return
	 */
	public List<AccessUrlBean> queryAllAccessUrlList(){
		return this.accessUrlDao.queryAllAccessUrlList();
	}
	
	
	public AccessUrlDao getAccessUrlDao() {
		return accessUrlDao;
	}

	@Resource
	public void setAccessUrlDao(AccessUrlDao accessUrlDao) {
		this.accessUrlDao = accessUrlDao;
	}
	
	
	
}
