package com.cjhme.system.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.task.bean.TaskBean;
import com.cjhme.system.task.dao.TaskDao;
import com.cjhme.system.task.service.TaskService;

/**
 * 
 * <p>  
 * Title: TaskServiceImpl.java 
 * </p>  
 * Description: 待办
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Feb 26, 2015 10:06:13 AM
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

	private TaskDao taskDao;
	
	/**
	 * 根据用户id获得待办列表
	 * @return
	 */
	public Map<String,Object>  queryTaskByUserId(Map<String,Object> parameter){
		List<TaskBean> tasks= this.taskDao.queryTaskByUserId(parameter);
		int count = this.taskDao.queryTaskCountByUserId(parameter);
	    Map<String,Object> result = new HashMap<String,Object>();
	    result.put("tasks", tasks);
	    result.put("count", count);
	    return result;
	}
	
	/**
	 * 根据条件分页查询待办 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object>  queryTaskByConditionPaging(DataPaging<Object> parameter){
		return this.taskDao.queryTaskByConditionPaging(parameter);
	}
	
	/**
	 * 根据id修改待办状态
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateTaskById(Map<String,Object> parameter){
		return this.taskDao.updateTaskById(parameter);
	}

	public TaskDao getTaskDao() {
		return taskDao;
	}

	@Resource
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	

}
