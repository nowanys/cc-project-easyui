package com.cjhme.system.access.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.access.bean.AccessUrlBean;
import com.cjhme.system.access.dao.AccessUrlDao;
import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: AccessUrlDaoImpl.java 
 * </p>  
 * Description: 访问地址
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Aug 7, 2015 5:22:51 PM
 */
@Repository("accessUrlDao")
public class AccessUrlDaoImpl extends BaseDao implements AccessUrlDao {

	/**
	 * 根据条件分页查询访问地址
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryAccessUrlByConditionPaging(DataPaging<Object> pageParameter){
		return this.selectPaging("com.cjhme.system.access.dao.AccessUrlDao.queryAccessUrlByConditionPaging", pageParameter);
	}
	
	/**
	 * 判断访问地址是否存在
	 * @param parameter
	 * @return
	 */
	public int queryAccessUrlIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.access.dao.AccessUrlDao.queryAccessUrlIsExists",parameter);
	}
	
	/**
	 * 添加访问地址
	 * @param parameter
	 * @return
	 */
	public int saveAccessUrl(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.access.dao.AccessUrlDao.saveAccessUrl",parameter);
	}
	
	/**
	 * 根据id查询访问地址
	 * @param parameter
	 * @return
	 */
	public AccessUrlBean queryAccessUrlById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.access.dao.AccessUrlDao.queryAccessUrlById",parameter);
	}
	
	/**
	 *  根据id修改访问地址
	 * @param parameter
	 * @return
	 */
	public int updateAccessUrlById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.access.dao.AccessUrlDao.updateAccessUrlById",parameter);
	}
	
	/**
	 * 根据ids删除访问地址
	 */
	public int deleteAccessUrlByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.access.dao.AccessUrlDao.deleteAccessUrlByIds",parameter);
	}
	
	/**
	 * 查询所有访问地址列表
	 * @return
	 */
	public List<AccessUrlBean> queryAllAccessUrlList(){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.access.dao.AccessUrlDao.queryAllAccessUrlList");
	}
	
	/**
	 * 根据访问地址ids删除访问地址角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteAccessUrlRoleMappingByIds(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.access.dao.AccessUrlDao.deleteAccessUrlRoleMappingByIds", parameter);
	}
}
