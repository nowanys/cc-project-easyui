package com.cjhme.system.button.bean;

import com.cjhme.system.main.bean.BaseBean;

/**
 * 
 * <p>
 * Title: ButtonBean.java
 * </p>
 * Description: 按钮
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 8, 2014 9:03:59 AM
 */
public class ButtonBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String buttonId;

	/**
	 * 按钮名称
	 */
	private String buttonName;

	/**
	 * 按钮分类
	 */
	private String buttonType;

	/**
	 * 函数名
	 */
	private String functionName;

	/**
	 * 菜单id
	 */
	private String menuId;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 业务标识
	 */
	private String busniessMark;

	/**
	 * 布局标识
	 */
	private String layoutMark;

	/**
	 * 样式
	 */
	private String iconCls;

	/**
	 * 按钮描述
	 */
	private String buttonDescribe;
	
	/**
	 * 排序
	 */
	private int sortNum;

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getBusniessMark() {
		return busniessMark;
	}

	public void setBusniessMark(String busniessMark) {
		this.busniessMark = busniessMark;
	}

	public String getLayoutMark() {
		return layoutMark;
	}

	public void setLayoutMark(String layoutMark) {
		this.layoutMark = layoutMark;
	}

	public String getButtonDescribe() {
		return buttonDescribe;
	}

	public void setButtonDescribe(String buttonDescribe) {
		this.buttonDescribe = buttonDescribe;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	

}
