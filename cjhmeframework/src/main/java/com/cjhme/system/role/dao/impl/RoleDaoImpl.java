package com.cjhme.system.role.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.role.bean.RoleAccessUrlMappingBean;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.role.bean.RoleButtonMappingBean;
import com.cjhme.system.role.bean.RoleMenuMappingBean;
import com.cjhme.system.role.dao.RoleDao;

/**
 * 
 * <p>  
 * Title: RoleDao.java 
 * </p>  
 * Description: 角色
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Oct 16, 2014 9:57:07 AM
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDao implements RoleDao {

	/**
	 * 根据条件分页查询角色
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object>  queryRoleByConditionPaging(DataPaging<Object> parameter){
		return this.selectPaging("com.cjhme.system.role.dao.RoleDao.queryRoleByConditionPaging", parameter);
	}
	
	/**
	 * 判断角色是否存在
	 * @param parameter
	 * @return
	 */
	public int queryRoleIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.role.dao.RoleDao.queryRoleIsExists",parameter);
	}
	
	/**
	 * 添加角色 
	 * @param parameter
	 * @return
	 */
	public int saveRole(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.role.dao.RoleDao.saveRole",parameter);
	}
	
	/**
	 * 根据id查询角色
	 * @param parameter
	 * @return
	 */
	public RoleBean queryRoleById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.role.dao.RoleDao.queryRoleById",parameter);
	}
	
	/**
	 * 根据id修改角色
	 * @param parameter
	 * @return
	 */
	public int updateRoleById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.role.dao.RoleDao.updateRoleById",parameter);
	}
	
	/**
	 *  根据ids删除角色
	 * @param parameter
	 * @return
	 */
	public int deleteRoleByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.role.dao.RoleDao.deleteRoleByIds",parameter);
	}
	
	/**
	 * 查询所有角色列表 
	 * @return
	 */
	public List<RoleBean> queryAllRoleList(){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.role.dao.RoleDao.queryAllRoleList");
	}
	
	/**
	 * 根据角色查询角色菜单映射
	 * @param parameter
	 * @return
	 */
	public List<RoleMenuMappingBean> queryRoleMenuMappingById(Map<String,Object> parameter){
		return  this.getSqlSessionTemplate().selectList("com.cjhme.system.role.dao.RoleDao.queryRoleMenuMappingById",parameter);
	}
	
	
	/**
	 * 根据角色id删除角色菜单映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleMenuMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteRoleMenuMappingById",parameter);
	}
	
	/**
	 * 根据角色ids删除角色菜单映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleMenuMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteRoleMenuMappingByIds",parameter);
	}
	
	/**
	 * 保存角色菜单映射
	 * @param parameter
	 * @return
	 */
	public int saveRoleMenuMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.role.dao.RoleDao.saveRoleMenuMapping",parameter);
	}
	
	/**
	 * 根据角色查询角色按钮映射
	 * @param parameter
	 * @return
	 */
	public List<RoleButtonMappingBean> queryRoleButtonMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.role.dao.RoleDao.queryRoleButtonMappingById",parameter);
	}
	
	/**
	 * 根据角色删除角色按钮映射 
	 * @param parameter
	 * @return
	 */
	public int deleteRoleButtonMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteRoleButtonMappingById",parameter);
	}
	
	/**
	 * 根据角色ids删除角色按钮映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleButtonMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteRoleButtonMappingByIds",parameter);
	}
	
	/**
	 * 保存角色按钮映射
	 * @param parameter
	 * @return
	 */
	public int  saveRoleButtonMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.role.dao.RoleDao.saveRoleButtonMapping",parameter);
	}
	
	/**
	 * 根据角色ids删除角色用户映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleUserMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteRoleUserMappingByIds",parameter);
	}
	
	/**
	 * 根据角色ids删除数据字典明细角色映射 
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicItemRoleMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteDataDicItemRoleMappingByIds",parameter);
	}
	
	/**
	 * 根据角色查询角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public List<RoleAccessUrlMappingBean> queryRoleAccessUrlMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.role.dao.RoleDao.queryRoleAccessUrlMappingById",parameter);
	}
	
	/**
	 * 保存角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public int saveRoleAccessUrlMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.role.dao.RoleDao.saveRoleAccessUrlMapping",parameter);
	}
	
	/**
	 * 根据角色id删除角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public int  deleteRoleAccessUrlMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteRoleAccessUrlMappingById",parameter);
	}
	
	/**
	 * 根据角色ids删除角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public int deleteRoleAccessUrlMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteRoleAccessUrlMappingByIds",parameter);
	}
	
	/**
	 * 根据角色ids删除角色常用功能
	 * @param parameter
	 * @return
	 */
	public int deleteRoleComFuncByIds(Map<String,Object> parameter){
		return  this.getSqlSessionTemplate().delete("com.cjhme.system.role.dao.RoleDao.deleteRoleComFuncByIds",parameter);
	}

}
