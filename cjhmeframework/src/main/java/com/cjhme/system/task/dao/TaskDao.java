package com.cjhme.system.task.dao;

import java.util.List;
import java.util.Map;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.task.bean.TaskBean;

/**
 * 
 * <p>  
 * Title: TaskDao.java 
 * </p>  
 * Description: 待办
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Feb 26, 2015 10:01:25 AM
 */
public interface TaskDao {

	/**
	 * 根据用户id获得待办列表
	 * @return
	 */
	public List<TaskBean> queryTaskByUserId(Map<String,Object> parameter);
	
	/**
	 * 根据用户id获得待办列表条数
	 * @param parameter
	 * @return
	 */
	public int queryTaskCountByUserId(Map<String,Object> parameter);
	
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
