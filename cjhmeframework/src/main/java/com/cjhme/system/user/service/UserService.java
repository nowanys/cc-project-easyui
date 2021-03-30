package com.cjhme.system.user.service;

import java.util.List;
import java.util.Map;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.user.bean.UserBean;
import com.cjhme.system.user.bean.UserDeptMappingBean;
import com.cjhme.system.user.bean.UserRoleMappingBean;
/**
 * 
 * <p>  
 * Title: UserService.java 
 * </p>  
 * Description: 用户
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jul 10, 2014 10:23:16 PM
 */
public interface UserService {
	
	/**
	 * 用户登录
	 * @param parameter
	 * @return
	 */
	public UserBean login(Map<String,Object> parameter);

	/**
	 * 判断用户是否存在 
	 * @param parameter
	 * @return
	 */
	public int queryUserIsExists(Map<String,Object> parameter);
	
    /**
     * 添加用户 
     * @param parameter
     * @return
     */
	public int saveUser(Map<String,Object> parameter);
	
	/**
	 * 根据条件分页查询用户
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryUserByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 根据id查询用户
	 * @param parameter
	 * @return
	 */
	public UserBean queryUserById(Map<String,Object> parameter);
	
	/**
	 * 根据id修改用户 
	 * @param parameter
	 * @return
	 */
	public int updateUserById(Map<String,Object> parameter);
	
	
	/**
	 * 根据ids重置用户密码
	 * @param parameter
	 * @return
	 */
	public int updateUserPwdByIds(Map<String,Object> parameter);
	
	/**
	 * 根据id修改密码 
	 * @param parameter
	 * @return
	 */
	public int updateUserPasswordById(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除用户
	 * @param parameter
	 * @return
	 */
	public int deleteUserByIds(Map<String,Object> parameter);
	
	/**
	 * 根据用户id查询用户角色映射
	 * @param parameter
	 * @return
	 */
	public List<UserRoleMappingBean> queryUserRoleMappingById(Map<String,Object> parameter);
	
	/**
	 * 保存用户角色映射
	 * @param parameter
	 * @return
	 */
	public int saveUserRoleMapping(Map<String,Object> parameter);
	
	/**
	 * 根据角色id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByRoleId(Map<String,Object> parameter);
	
	/**
	 * 根据用户id查询用户部门映射
	 * @param parameter
	 * @return
	 */
	public List<UserDeptMappingBean> queryUserDeptMappingById(Map<String,Object> parameter);
	
	/**
	 * 保存用户部门映射 
	 * @param parameter
	 * @return
	 */
	public int saveUserDeptMapping(Map<String,Object> parameter);
	
	/**
	 * 根据部门id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByDeptId(Map<String,Object> parameter);
	
}
