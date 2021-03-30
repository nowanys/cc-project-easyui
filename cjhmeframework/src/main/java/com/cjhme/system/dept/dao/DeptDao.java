package com.cjhme.system.dept.dao;

import java.util.List;
import java.util.Map;

import com.cjhme.system.dept.bean.DeptBean;
import com.cjhme.system.main.bean.TreeNodeBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: DeptDao.java 
 * </p>  
 * Description: 部门
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Nov 21, 2014 8:59:06 PM
 */
public interface DeptDao {
	
	/**
	 * 查询所有部门结构 
	 * @return
	 */
	public List<TreeNodeBean> queryDeptList();
	
	/**
	 * 根据条件查询部门
	 * @param parameter
	 * @return
	 */
	public List<TreeNodeBean> queryDeptByCondition(Map<String,Object> parameter);
	
	/**
	 * 根据条件分页查询部门
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryDeptByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 判断部门是否存在
	 * @param parameter
	 * @return
	 */
	public int queryDeptIsExists(Map<String,Object> parameter);
	
	/**
	 * 添加部门
	 * @param parameter
	 * @return
	 */
	public int saveDept(Map<String,Object> parameter);
	
	/**
	 * 根据id查询部门 
	 * @param parameter
	 * @return
	 */
	public DeptBean queryDeptByDeptId(Map<String,Object> parameter);
	
	/**
	 * 根据id修改部门
	 * @param parameter
	 * @return
	 */
	public int updateDeptByDeptId(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除部门
	 * @param parameter
	 * @return
	 */
	public int deleteDeptByIds(Map<String,Object> parameter);
	
	
	/**
	 * 根据id移动部门
	 * @param parameter
	 * @return
	 */
	public int updateDeptParentDeptIdById(Map<String,Object> parameter);
	
	/**
	 * 根据组织ids删除部门用户映射
	 * @param parameter
	 * @return
	 */
	public int deleteDeptUserMappingByIds(Map<String,Object> parameter);

}
