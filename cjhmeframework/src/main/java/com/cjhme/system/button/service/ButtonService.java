package com.cjhme.system.button.service;

import java.util.List;
import java.util.Map;

import com.cjhme.system.button.bean.ButtonBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: ButtonService.java 
 * </p>  
 * Description: 按钮
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jan 18, 2015 10:10:18 AM
 */
public interface ButtonService {

	/**
	 * 根据条件分页查询按钮
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryButtonByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 判断按钮是否存在
	 * @param parameter
	 * @return
	 */
	public int queryButtonIsExists(Map<String,Object> parameter);
	
	/**
	 * 添加按钮
	 * @param parameter
	 * @return
	 */
	public int saveButton(Map<String,Object> parameter);
	
	/**
	 * 根据id查询按钮
	 * @param parameter
	 * @return
	 */
	public ButtonBean queryButtonById(Map<String,Object> parameter);
	
	/**
	 *  根据id修改按钮
	 * @param parameter
	 * @return
	 */
	public int updateButtonById(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除按钮
	 */
	public int deleteButtonByIds(Map<String,Object> parameter);
	
	
	/**
	 * 查询所有按钮列表
	 * @return
	 */
	public List<ButtonBean> queryAllButtonList();
	
	
}
