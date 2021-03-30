package com.cjhme.system.button.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.button.bean.ButtonBean;
import com.cjhme.system.button.dao.ButtonDao;
import com.cjhme.system.button.service.ButtonService;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: ButtonServiceImpl.java 
 * </p>  
 * Description: 按钮
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jan 18, 2015 10:10:34 AM
 */
@Service("buttonService")
public class ButtonServiceImpl implements ButtonService {

	private ButtonDao buttonDao;
	
	
	/**
	 * 根据条件分页查询按钮
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryButtonByConditionPaging(DataPaging<Object> pageParameter){
		return this.buttonDao.queryButtonByConditionPaging(pageParameter);
	}
	
	/**
	 * 判断按钮是否存在
	 * @param parameter
	 * @return
	 */
	public int queryButtonIsExists(Map<String,Object> parameter){
		return this.buttonDao.queryButtonIsExists(parameter);
	}
	
	/**
	 * 添加按钮
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveButton(Map<String,Object> parameter){
		return this.buttonDao.saveButton(parameter);
	}
	
	/**
	 * 根据id查询按钮
	 * @param parameter
	 * @return
	 */
	public ButtonBean queryButtonById(Map<String,Object> parameter){
		return this.buttonDao.queryButtonById(parameter);
	}
	
	/**
	 *  根据id修改按钮
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateButtonById(Map<String,Object> parameter){
		return this.buttonDao.updateButtonById(parameter);
	}
	
	/**
	 * 根据ids删除按钮
	 */
	@Transactional
	public int deleteButtonByIds(Map<String,Object> parameter){
		//删除按钮角色映射
		this.buttonDao.deleteButtonRoleMappingByIds(parameter);
		
		//删除按钮
		return this.buttonDao.deleteButtonByIds(parameter);
	}
	
	
	/**
	 * 查询所有按钮列表
	 * @return
	 */
	public List<ButtonBean> queryAllButtonList(){
		return this.buttonDao.queryAllButtonList();
	}
	

	public ButtonDao getButtonDao() {
		return buttonDao;
	}

	@Resource
	public void setButtonDao(ButtonDao buttonDao) {
		this.buttonDao = buttonDao;
	}
	
	
	
}
