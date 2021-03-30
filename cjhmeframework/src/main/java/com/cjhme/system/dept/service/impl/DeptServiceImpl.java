package com.cjhme.system.dept.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.dept.bean.DeptBean;
import com.cjhme.system.dept.dao.DeptDao;
import com.cjhme.system.dept.service.DeptService;
import com.cjhme.system.main.bean.TreeNodeBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.CreateTreeUtil;

/**
 * 
 * <p>  
 * Title: DeptServiceImpl.java 
 * </p>  
 * Description: 部门
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Nov 21, 2014 9:00:47 PM
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	private DeptDao deptDao;
	
	/**
	 * 查询所有部门结构 
	 * @return
	 */
	public String queryDeptList()throws Exception{
		List<TreeNodeBean> list=this.deptDao.queryDeptList();
		CreateTreeUtil.setRootTreeNodeId("-1");
		return CreateTreeUtil.getTreeJson(CreateTreeUtil.createTree(list));
	}
	
	/**
	 * 根据条件查询部门
	 * @param parameter
	 * @return
	 */
	public String queryDeptByCondition(Map<String,Object> parameter)throws Exception{
		List<TreeNodeBean> list=this.deptDao.queryDeptByCondition(parameter);
		CreateTreeUtil.setRootTreeNodeId("-1");
		return CreateTreeUtil.getTreeJson(CreateTreeUtil.createTree(list));
	}
	
	/**
	 * 根据条件分页查询部门
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryDeptByConditionPaging(DataPaging<Object> pageParameter){
		return this.deptDao.queryDeptByConditionPaging(pageParameter);
	}
	
	/**
	 * 判断部门是否存在
	 * @param parameter
	 * @return
	 */
	public int queryDeptIsExists(Map<String,Object> parameter){
		return this.deptDao.queryDeptIsExists(parameter);
	}
	
	/**
	 * 添加部门
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveDept(Map<String,Object> parameter){
		return this.deptDao.saveDept(parameter);
	}
	
	/**
	 * 根据id查询部门 
	 * @param parameter
	 * @return
	 */
	public DeptBean queryDeptByDeptId(Map<String,Object> parameter){
		return this.deptDao.queryDeptByDeptId(parameter);
	}
	
	/**
	 * 根据id修改部门
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateDeptByDeptId(Map<String,Object> parameter){
		return this.deptDao.updateDeptByDeptId(parameter);
	}
	
	/**
	 * 根据ids删除部门
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteDeptByIds(Map<String,Object> parameter){
		//删除部门用户映射
		this.deptDao.deleteDeptUserMappingByIds(parameter);
		
		//删除部门
		this.deptDao.deleteDeptByIds(parameter);
		return 1;
	}
	
	
	/**
	 * 根据id移动部门
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateDeptParentDeptIdById(Map<String,Object> parameter){
		return this.deptDao.updateDeptParentDeptIdById(parameter);
	}
	
	/**
	 * 根据组织ids删除部门用户映射
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteDeptUserMappingByIds(Map<String,Object> parameter){
		return this.deptDao.deleteDeptUserMappingByIds(parameter);
	}

	public DeptDao getDeptDao() {
		return deptDao;
	}

	@Resource
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	

	
}
