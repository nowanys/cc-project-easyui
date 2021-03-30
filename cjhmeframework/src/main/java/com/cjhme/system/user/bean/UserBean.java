package com.cjhme.system.user.bean;

import java.util.List;

import com.cjhme.system.dept.bean.DeptBean;
import com.cjhme.system.main.bean.BaseBean;
import com.cjhme.system.role.bean.RoleBean;



/**
 * 
 * <p>  
 * Title: UserBean.java 
 * </p>  
 * Description: 用户
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jul 5, 2014 3:06:04 PM
 */
public class UserBean extends BaseBean  {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 用户头像
	 */
	private String headImg;
	
	/**
	 * 用户编号
	 */
	private String userCode;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 性别：1男，2女，3保密
	 */
	private String sex;

	/**
	 * 电话
	 */
	private String telephone;
	
	/**
	 * 邮箱
	 */
	private String email;
	

	/**
	 * 描述
	 */
	private String userDescribe;
	
	/**
	 * 部门列表
	 */
	private List<DeptBean>  depts;
	
	/**
	 * 当前部门
	 */
	private DeptBean currentDept;

	/**
	 * 角色列表
	 */
	private List<RoleBean> roles;
	
	/**
	 * 当前角色
	 */
	private RoleBean currentRole;
	
	/**
	 * 短信开关(1开启，2关闭)
	 */
	private String emailSwitch;
	
	/**
	 * 邮件开关(1开启，2关闭)
	 */
	private String smsSwitch;
	
	/**
	 * 身份证
	 */
	private String idCard;
	

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getUserDescribe() {
		return userDescribe;
	}


	public void setUserDescribe(String userDescribe) {
		this.userDescribe = userDescribe;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}




	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}





	public List<DeptBean> getDepts() {
		return depts;
	}


	public void setDepts(List<DeptBean> depts) {
		this.depts = depts;
	}


	public List<RoleBean> getRoles() {
		return roles;
	}


	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}


	public String getHeadImg() {
		return headImg;
	}


	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}


	public DeptBean getCurrentDept() {
		return currentDept;
	}


	public void setCurrentDept(DeptBean currentDept) {
		this.currentDept = currentDept;
	}


	public RoleBean getCurrentRole() {
		return currentRole;
	}


	public void setCurrentRole(RoleBean currentRole) {
		this.currentRole = currentRole;
	}


	public String getEmailSwitch() {
		return emailSwitch;
	}


	public void setEmailSwitch(String emailSwitch) {
		this.emailSwitch = emailSwitch;
	}


	public String getSmsSwitch() {
		return smsSwitch;
	}


	public void setSmsSwitch(String smsSwitch) {
		this.smsSwitch = smsSwitch;
	}


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	


	
	

}


