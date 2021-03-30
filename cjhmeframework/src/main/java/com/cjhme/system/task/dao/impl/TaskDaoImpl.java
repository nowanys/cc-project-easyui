package com.cjhme.system.task.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.task.bean.TaskBean;
import com.cjhme.system.task.dao.TaskDao;

/**
 * 
 * <p>  
 * Title: TaskDaoImpl.java 
 * </p>  
 * Description: 待办
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Feb 26, 2015 10:01:50 AM
 */
@Repository("taskDao")
public class TaskDaoImpl extends BaseDao implements TaskDao {

	/**
	 * 根据用户id获得待办列表
	 * @return
	 */
	public List<TaskBean> queryTaskByUserId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.task.dao.TaskDao.queryTaskByUserId", parameter);
	}
	
	/**
	 * 根据用户id获得待办列表条数
	 * @param parameter
	 * @return
	 */
	public int queryTaskCountByUserId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.task.dao.TaskDao.queryTaskCountByUserId",parameter);
	}
	
	/**
	 * 根据条件分页查询待办 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object>  queryTaskByConditionPaging(DataPaging<Object> parameter){
		return this.selectPaging("com.cjhme.system.task.dao.TaskDao.queryTaskByConditionPaging", parameter);
	}
	
	/**
	 * 根据id修改待办状态
	 * @param parameter
	 * @return
	 */
	public int updateTaskById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.task.dao.TaskDao.updateTaskById",parameter);
	}
	
}
