package com.cjhme.system.dept.bean;

import java.util.List;

import com.cjhme.system.main.bean.BaseBean;

/**
 * 
 * <p>  
 * Title: DeptBean.java 
 * </p>  
 * Description: 部门
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Nov 18, 2014 9:26:04 PM
 */
public class DeptBean extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 部门id
	 */
	private String deptId;
	
	/**
	 * 部门编号
	 */
	private String deptCode;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 部门简称
	 */
	private String deptSortName;
	
	/**
	 * 部门级别
	 */
	private String deptLevel;
	
	/**
	 * 部门描述
	 */
	private String deptDescript;
	
	/**
	 * 上一级部门
	 */
	private String parentDeptId;
	
	
	/**
	 * 部门负责人Id
	 */
	private String deptLeaderId;
	
	/**
	 * 组织负责人
	 */
	private String deptLeader;
	
	/**
	 * 部门联系电话
	 */
	private String deptTel;
	
	/**
	 * 部门地址
	 */
	private String deptAddr;
	
	/**
	 * 下级部门
	 */
	private List<DeptBean> childDepts;
	
	/**
	 * 图标
	 */
	private String iconCls;
	
	/**
	 * 短信开关(1开启，2关闭)
	 */
	private String emailSwitch;
	
	/**
	 * 邮件开关(1开启，2关闭)
	 */
	private String smsSwitch;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptSortName() {
		return deptSortName;
	}

	public void setDeptSortName(String deptSortName) {
		this.deptSortName = deptSortName;
	}

	public String getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getDeptDescript() {
		return deptDescript;
	}

	public void setDeptDescript(String deptDescript) {
		this.deptDescript = deptDescript;
	}

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public String getDeptLeaderId() {
		return deptLeaderId;
	}

	public void setDeptLeaderId(String deptLeaderId) {
		this.deptLeaderId = deptLeaderId;
	}

	public String getDeptLeader() {
		return deptLeader;
	}

	public void setDeptLeader(String deptLeader) {
		this.deptLeader = deptLeader;
	}

	public String getDeptTel() {
		return deptTel;
	}

	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}

	public String getDeptAddr() {
		return deptAddr;
	}

	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}



	public List<DeptBean> getChildDepts() {
		return childDepts;
	}

	public void setChildDepts(List<DeptBean> childDepts) {
		this.childDepts = childDepts;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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
	
	

	

}
