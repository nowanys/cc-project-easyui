package com.cjhme.system.role.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.role.bean.RoleAccessUrlMappingBean;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.role.bean.RoleButtonMappingBean;
import com.cjhme.system.role.bean.RoleMenuMappingBean;
import com.cjhme.system.role.dao.RoleDao;
import com.cjhme.system.role.service.RoleService;

/**
 * 
 * <p>  
 * Title: RoleService.java 
 * </p>  
 * Description: 角色
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Oct 16, 2014 9:58:33 AM
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	private RoleDao roleDao;

	
	/**
	 * 根据条件分页查询角色
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object>  queryRoleByConditionPaging(DataPaging<Object> parameter){
		return this.roleDao.queryRoleByConditionPaging(parameter);
	}
	
	/**
	 * 判断角色是否存在
	 * @param parameter
	 * @return
	 */
	public int queryRoleIsExists(Map<String,Object> parameter){
		return this.roleDao.queryRoleIsExists(parameter);
	}
	
	/**
	 * 添加角色 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveRole(Map<String,Object> parameter){
		return this.roleDao.saveRole(parameter);
	}
	
	/**
	 * 根据id查询角色
	 * @param parameter
	 * @return
	 */
	public RoleBean queryRoleById(Map<String,Object> parameter){
		return this.roleDao.queryRoleById(parameter);
	}
	
	/**
	 * 根据id修改角色
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateRoleById(Map<String,Object> parameter){
		return this.roleDao.updateRoleById(parameter);
	}
	
	/**
	 *  根据ids删除角色
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteRoleByIds(Map<String,Object> parameter){
		//删除角色菜单映射
		this.roleDao.deleteRoleMenuMappingByIds(parameter);
		
		//删除角色按钮映射
		this.roleDao.deleteRoleButtonMappingByIds(parameter);
		
		//删除角色用户映射
		this.roleDao.deleteRoleUserMappingByIds(parameter);
		
		//删除角色数据字典明细映射
		this.roleDao.deleteDataDicItemRoleMappingByIds(parameter);
		
		//删除角色访问地址映射
		this.roleDao.deleteRoleAccessUrlMappingByIds(parameter);
		
		//删除角色常用功能
		this.roleDao.deleteRoleComFuncByIds(parameter);
		
		//删除角色
		return this.roleDao.deleteRoleByIds(parameter);
	}
	
	/**
	 * 查询所有角色列表 
	 * @return
	 */
	public List<RoleBean> queryAllRoleList(){
		return this.roleDao.queryAllRoleList();
	}
	
	/**
	 * 根据角色id查询角色菜单映射
	 * @param parameter
	 * @return
	 */
	public List<RoleMenuMappingBean> queryRoleMenuMappingById(Map<String,Object> parameter){
		return this.roleDao.queryRoleMenuMappingById(parameter);
	}
	
	
	/**
	 * 保存角色菜单映射
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveRoleMenuMapping(Map<String,Object> parameter){
		this.roleDao.deleteRoleMenuMappingById(parameter);
		List<String> menuIds = (List<String>) parameter.get("menuIds");
		for(String menuId:menuIds){
			parameter.put("menuId", menuId);
			this.roleDao.saveRoleMenuMapping(parameter);
		}
		return 1;
	}
	
	/**
	 * 根据角色查询角色按钮映射
	 * @param parameter
	 * @return
	 */
	public List<RoleButtonMappingBean> queryRoleButtonMappingById(Map<String,Object> parameter){
		return this.roleDao.queryRoleButtonMappingById(parameter);
	}
	
	
	/**
	 * 保存角色按钮映射
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int  saveRoleButtonMapping(Map<String,Object> parameter){
		this.roleDao.deleteRoleButtonMappingById(parameter);
		List<String> buttonIds = (List<String>) parameter.get("buttonIds");
		for(String buttonId:buttonIds){
			parameter.put("buttonId", buttonId);
			this.roleDao.saveRoleButtonMapping(parameter);
		}
		return 1;
	}

	/**
	 * 根据角色查询角色访问地址映射
	 * @param parameter
	 * @return
	 */
	public List<RoleAccessUrlMappingBean> queryRoleAccessUrlMappingById(Map<String,Object> parameter){
		return this.roleDao.queryRoleAccessUrlMappingById(parameter);
	}
	
	/**
	 * 保存角色访问地址映射
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveRoleAccessUrlMapping(Map<String,Object> parameter){
		this.roleDao.deleteRoleAccessUrlMappingById(parameter);
		List<String> accessUrlIds = (List<String>) parameter.get("accessUrlIds");
		for(String accessUrlId:accessUrlIds){
			parameter.put("accessUrlId", accessUrlId);
			this.roleDao.saveRoleAccessUrlMapping(parameter);
		}
		return 1;
	}
	
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	

}