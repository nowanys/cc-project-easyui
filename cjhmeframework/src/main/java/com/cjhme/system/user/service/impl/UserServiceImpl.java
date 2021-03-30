package com.cjhme.system.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.fileupload.dao.FileDataDao;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.user.bean.UserBean;
import com.cjhme.system.user.bean.UserDeptMappingBean;
import com.cjhme.system.user.bean.UserRoleMappingBean;
import com.cjhme.system.user.dao.UserDao;
import com.cjhme.system.user.service.UserService;

/**
 * 
 * <p>  
 * Title: UserServiceImpl.java 
 * </p>  
 * Description: 用户
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jul 10, 2014 10:23:33 PM
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	private FileDataDao fileDataDao;
	
	/**
	 * 用户登录
	 * @param parameter
	 * @return
	 */
	public UserBean login(Map<String,Object> parameter){
		return this.userDao.login(parameter);
	}
	

	/**
	 * 判断用户是否存在 
	 * @param parameter
	 * @return
	 */
	public int queryUserIsExists(Map<String,Object> parameter){
		return this.userDao.queryUserIsExists(parameter);
	}
	
    /**
     * 添加用户 
     * @param parameter
     * @return
     */
	@Transactional
	public int saveUser(Map<String,Object> parameter){
		 String userId = this.userDao.saveUser(parameter);
		 
		 //上传头像时，保存头像信息
		 String headImg = String.valueOf(parameter.get("headImg"));
		 if(null!=headImg && !"".equals(headImg) && !"null".equals(headImg) 
		 && null !=userId && !"".equals(userId) && !"null".equals(userId)){
			parameter.put("busniessMark",BusniessMarkConstants.USER_HEAD_IMG);
			parameter.put("busniessId", userId);
			parameter.put("fileUrl", headImg);
			parameter.put("fileDescript", "添加用户时的用户头像！");
			
			this.fileDataDao.saveFileData(parameter);
		 }
		 
		 return 1;
	}
	
	/**
	 * 根据条件分页查询用户
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryUserByConditionPaging(DataPaging<Object> pageParameter){
		return this.userDao.queryUserByConditionPaging(pageParameter);
	}
	
	/**
	 * 根据id查询用户
	 * @param parameter
	 * @return
	 */
	public UserBean queryUserById(Map<String,Object> parameter){
		return this.userDao.queryUserById(parameter);
	}
	
	/**
	 * 根据id修改用户 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateUserById(Map<String,Object> parameter){
		 this.userDao.updateUserById(parameter);
		
		 //上传头像时，保存头像信息
		 String headImg = String.valueOf(parameter.get("headImg"));
		 if(null!=headImg && !"".equals(headImg) && !"null".equals(headImg)){
			 parameter.put("fileUrl", headImg);
			 parameter.put("fileDescript", "根据id修改用户时的用户头像！");
			 
			 //判断是更新还是保存
			 String fileId = String.valueOf(parameter.get("fileId"));
			 if(null!=fileId && !"".equals(fileId) && !"null".equals(fileId)){
				 this.fileDataDao.updateFileDataByFileId(parameter);
			 }else{
				 parameter.put("busniessMark",BusniessMarkConstants.USER_HEAD_IMG);
				 parameter.put("busniessId", parameter.get("userId"));
				 this.fileDataDao.saveFileData(parameter);
			 }
			
			
		 }
		 return 1;
	}
	
	/**
	 * 根据id修改密码 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateUserPasswordById(Map<String,Object> parameter){
		return this.userDao.updateUserPasswordById(parameter);
	}
	
	/**
	 * 根据ids重置用户密码
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateUserPwdByIds(Map<String,Object> parameter){
		return this.userDao.updateUserPwdByIds(parameter);
	}
	
	
	/**
	 * 根据ids删除用户
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteUserByIds(Map<String,Object> parameter){
		
		//删除用户角色映射
		this.userDao.deleteUserRoleMappingByIds(parameter);
		
		//删除用户部门映射
		this.userDao.deleteUserDeptMappingByIds(parameter);
		
	    //删除用户常用功能
	    this.userDao.deleteUserComFuncByIds(parameter);
		
		//删除用户
		return this.userDao.deleteUserByIds(parameter);
		
	}
	
	/**
	 * 根据用户id查询用户角色映射
	 * @param parameter
	 * @return
	 */
	public List<UserRoleMappingBean> queryUserRoleMappingById(Map<String,Object> parameter){
		return this.userDao.queryUserRoleMappingById(parameter);
	}
	

	/**
	 * 保存用户角色映射
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveUserRoleMapping(Map<String,Object> parameter){
		//删除用户角色映射
		this.userDao.deleteUserRoleMappingById(parameter);
		
		//保存用户角色映射
		List<String> roleIds = (List<String>) parameter.get("roleIds");
		for(String roleId:roleIds){
			parameter.put("roleId", roleId);
			this.userDao.saveUserRoleMapping(parameter);
		}
		return 1;
	}
	
	/**
	 * 根据角色id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByRoleId(Map<String,Object> parameter){
		return this.userDao.queryUserByRoleId(parameter);
	}
	
	/**
	 * 根据用户id查询用户部门映射
	 * @param parameter
	 * @return
	 */
	public List<UserDeptMappingBean> queryUserDeptMappingById(Map<String,Object> parameter){
		return this.userDao.queryUserDeptMappingById(parameter);
	}
	
	/**
	 * 保存用户部门映射
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveUserDeptMapping(Map<String,Object> parameter){
		this.userDao.deleteUserDeptMappingById(parameter);
		List<String> deptIds = (List<String>) parameter.get("deptIds");
		for(String deptId:deptIds){
			parameter.put("deptId", deptId);
			this.userDao.saveUserDeptMapping(parameter);
		}
		return 1;
	}
	
	/**
	 * 根据部门id查询用户
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByDeptId(Map<String,Object> parameter){
		return this.userDao.queryUserByDeptId(parameter);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public FileDataDao getFileDataDao() {
		return fileDataDao;
	}

	@Resource
	public void setFileDataDao(FileDataDao fileDataDao) {
		this.fileDataDao = fileDataDao;
	}
	
	
	
}
