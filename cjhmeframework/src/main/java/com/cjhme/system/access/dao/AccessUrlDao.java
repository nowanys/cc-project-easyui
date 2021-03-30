package com.cjhme.system.access.dao;

import java.util.List;
import java.util.Map;

import com.cjhme.system.access.bean.AccessUrlBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: AccessUrlDao.java 
 * </p>  
 * Description: 访问地址
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Aug 7, 2015 5:22:28 PM
 */
public interface AccessUrlDao {

	/**
	 * 根据条件分页查询访问地址
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryAccessUrlByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 判断访问地址是否存在
	 * @param parameter
	 * @return
	 */
	public int queryAccessUrlIsExists(Map<String,Object> parameter);
	
	/**
	 * 添加访问地址
	 * @param parameter
	 * @return
	 */
	public int saveAccessUrl(Map<String,Object> parameter);
	
	/**
	 * 根据id查询访问地址
	 * @param parameter
	 * @return
	 */
	public AccessUrlBean queryAccessUrlById(Map<String,Object> parameter);
	
	/**
	 *  根据id修改访问地址
	 * @param parameter
	 * @return
	 */
	public int updateAccessUrlById(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除访问地址
	 */
	public int deleteAccessUrlByIds(Map<String,Object> parameter);
	
	/**
	 * 查询所有访问地址列表
	 * @return
	 */
	public List<AccessUrlBean> queryAllAccessUrlList();
	
	/**
	 * 根据访问地址ids删除访问地址角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteAccessUrlRoleMappingByIds(Map<String, Object> parameter);
}
