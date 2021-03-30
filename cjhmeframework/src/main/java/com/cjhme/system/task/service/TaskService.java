package com.cjhme.system.task.service;

import java.util.Map;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;


/**
 * 
 * <p>  
 * Title: TaskService.java 
 * </p>  
 * Description: 待办
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Feb 26, 2015 10:05:14 AM
 */
public interface TaskService {

	/**
	 * 根据用户id获得待办列表
	 * @return
	 */
	public Map<String,Object>  queryTaskByUserId(Map<String,Object> parameter);
	
	/**
	 * 根据条件分页查询待办 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object>  queryTaskByConditionPaging(DataPaging<Object> parameter);
	
	/**
	 * 根据id修改待办状态
	 * @param parameter
	 * @return
	 */
	public int updateTaskById(Map<String,Object> parameter);
}
