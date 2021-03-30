package com.cjhme.system.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.user.bean.UserBean;
import com.cjhme.system.user.bean.UserDeptMappingBean;
import com.cjhme.system.user.bean.UserRoleMappingBean;
import com.cjhme.system.user.dao.UserDao;

/**
 * 用户
 * <p>  
 * Title: UserDaoImpl.java 
 * </p>  
 * Description: 
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jul 10, 2014 10:24:08 PM
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	/**
	 * 用户登录
	 * @param parameter
	 * @return
	 */
	public UserBean login(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.user.dao.UserDao.login", parameter);
	}
	
	/**
	 * 判断用户是否存在 
	 * @param parameter
	 * @return
	 */
	public int queryUserIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.user.dao.UserDao.queryUserIsExists",parameter);
	}
	
    /**
     * 添加用户 
     * @param parameter
     * @return
     */
	public String saveUser(Map<String,Object> parameter){
		 this.getSqlSessionTemplate().insert("com.cjhme.system.user.dao.UserDao.saveUser",parameter);
		 return (String) parameter.get("userId");
	}
	
	/**
	 * 根据条件分页查询用户
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryUserByConditionPaging(DataPaging<Object> pageParameter){
		return this.selectPaging("com.cjhme.system.user.dao.UserDao.queryUserByConditionPaging", pageParameter);
	}
	
	/**
	 * 根据id查询用户
	 * @param parameter
	 * @return
	 */
	public UserBean queryUserById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.user.dao.UserDao.queryUserById",parameter);
	}
	
	/**
	 * 根据id修改用户 
	 * @param parameter
	 * @return
	 */
	public int updateUserById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.user.dao.UserDao.updateUserById",parameter);
	}
	
	/**
	 * 根据id修改密码 
	 * @param parameter
	 * @return
	 */
	public int updateUserPasswordById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.user.dao.UserDao.updateUserPasswordById",parameter);
	}
	
	/**
	 * 根据ids重置用户密码
	 * @param parameter
	 * @return
	 */
	public int updateUserPwdByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.user.dao.UserDao.updateUserPwdByIds",parameter);
	}
	
	/**
	 * 根据ids删除用户
	 * @param parameter
	 * @return
	 */
	public int deleteUserByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.user.dao.UserDao.deleteUserByIds",parameter);
	}
	
	/**
	 * 根据用户id查询用户角色映射
	 * @param parameter
	 * @return
	 */
	public List<UserRoleMappingBean> queryUserRoleMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.user.dao.UserDao.queryUserRoleMappingById",parameter);
	}
	
	/**
	 * 根据用户id删除用户角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserRoleMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.user.dao.UserDao.deleteUserRoleMappingById",parameter);
	}
	
	/**
	 * 根据用户ids删除用户角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserRoleMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.user.dao.UserDao.deleteUserRoleMappingByIds",parameter);
	}
	
	/**
	 * 保存用户角色映射
	 * @param parameter
	 * @return
	 */
	public int saveUserRoleMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.user.dao.UserDao.saveUserRoleMapping",parameter);
	}
	
	/**
	 * 根据角色id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByRoleId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.user.dao.UserDao.queryUserByRoleId",parameter);
	}
	
	/**
	 * 根据用户id查询用户部门映射
	 * @param parameter
	 * @return
	 */
	public List<UserDeptMappingBean> queryUserDeptMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.user.dao.UserDao.queryUserDeptMappingById", parameter);
	}
	
	/**
	 * 根据用户id删除用户部门映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserDeptMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.user.dao.UserDao.deleteUserDeptMappingById",parameter);
	}
	
	/**
	 * 根据用户ids删除用户部门映射
	 * @param parameter
	 * @return
	 */
	public int deleteUserDeptMappingByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.user.dao.UserDao.deleteUserDeptMappingByIds",parameter);
	}
	
	/**
	 * 保存用户部门映射 
	 * @param parameter
	 * @return
	 */
	public int saveUserDeptMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.user.dao.UserDao.saveUserDeptMapping",parameter);
	}
	
	/**
	 * 根据部门id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByDeptId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.user.dao.UserDao.queryUserByDeptId",parameter);
	}
	
	/**
	 * 根据用户ids删除用户常用功能
	 * @param parameter
	 * @return
	 */
	public int deleteUserComFuncByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.user.dao.UserDao.deleteUserComFuncByIds",parameter);
	}
	
	
}
