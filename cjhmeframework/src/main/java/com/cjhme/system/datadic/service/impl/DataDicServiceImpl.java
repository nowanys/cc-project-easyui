package com.cjhme.system.datadic.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.datadic.bean.DataDicItemBean;
import com.cjhme.system.datadic.bean.DataDicItemRoleMappingBean;
import com.cjhme.system.datadic.bean.DataDicTypeBean;
import com.cjhme.system.datadic.dao.DataDicDao;
import com.cjhme.system.datadic.service.DataDicService;
import com.cjhme.system.main.bean.TreeNodeBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.CreateTreeUtil;

/**
 * 
 * <p>  
 * Title: DataDicServiceImpl.java 
 * </p>  
 * Description: 数据字典
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Sep 18, 2014 2:28:33 PM
 */
@Service("dataDicService")
public class DataDicServiceImpl implements DataDicService {

	private DataDicDao dataDicDao;

	/**
	 * 初始化时查询数据字典分类列表
	 * @return
	 */
	public Map<String,Object> queryInitDataDicType(){
		return this.dataDicDao.queryInitDataDicType();
	}
	
	/**
	 * 查询数据字典分类列表
	 * @return
	 */
	public List<DataDicTypeBean> queryDataDicTypeList(Map<String, Object> parameter){
		return this.dataDicDao.queryDataDicTypeList(parameter);
	}
	
	/**
	 * 判断数据字典分类是否存在
	 * @param parameter
	 * @return
	 */
	public int queryDataDicTypeIsExists(Map<String,Object> parameter){
		return this.dataDicDao.queryDataDicTypeIsExists(parameter);
	}
	
	/**
	 * 添加数据字典分类
	 * @param dataDicTypeBean
	 * @return
	 */
	@Transactional
	public int saveDataDicType(Map<String,Object> parameter){
		return this.dataDicDao.saveDataDicType(parameter);
	}

	/**
	 * 根据id查询数据字典分类
	 * @param parameter
	 * @return
	 */
	public DataDicTypeBean queryDataDicTypeById(Map<String,Object> parameter){
		return this.dataDicDao.queryDataDicTypeById(parameter);
	}
	
	/**
	 * 根据id修改数据字典分类
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateDataDicTypeById(Map<String,Object> parameter){
		return this.dataDicDao.updateDataDicTypeById(parameter);
	}
	
	/**
	 * 根据ids删除数据字典分类
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteDataDicTypeByIds(Map<String,Object> parameter){
		//删除数据字典明细
		this.dataDicDao.deleteDataDicItemByTypeIds(parameter);
		
		//删除数据字典分类
		return this.dataDicDao.deleteDataDicTypeByIds(parameter);
	}
	
	/**
	 * 根数据字典分类id查询数据字典明细
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryDataDicItemByIdPaging(DataPaging<Object> pageParameter ){
		return this.dataDicDao.queryDataDicItemByIdPaging(pageParameter);
	}
	
	/**
	 * 判断数据字典明细是否存在
	 * @param pageParameter
	 * @return
	 */
	public int queryDataDicItemIsExists(Map<String,Object> parameter){
		return this.dataDicDao.queryDataDicItemIsExists(parameter);
	}
	
	/**
	 * 添加数据字典明细 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveDataDicItem(Map<String,Object> parameter){
		int maxSortNum = this.dataDicDao.queryDataDiaItemMaxSortNumByTypeCode(parameter);
		if(-1==maxSortNum ){
			maxSortNum=0;
		}
		parameter.put("sortNum", (maxSortNum+1));
		return this.dataDicDao.saveDataDicItem(parameter);
	}
	
	/**
	 * 根据id查询数据字典明细
	 * @param parameter
	 * @return
	 */
	public DataDicItemBean queryDataDicItemById(Map<String,Object> parameter){
		return this.dataDicDao.queryDataDicItemById(parameter);
	}
	
	/**
	 * 根据id修改数据字典明细
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateDataDicItemById(Map<String,Object> parameter){
		return this.dataDicDao.updateDataDicItemById(parameter);
	}
	
	/**
	 * 根据ids删除数据字典明细
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteDataDicItemByIds(Map<String,Object> parameter){
		//删除数据字典明细角色映射
		this.dataDicDao.deleteDataDicItemRoleMappingByIds(parameter);
		
		//删除数据字典明细
		return this.dataDicDao.deleteDataDicItemByIds(parameter);
	}
	
	/**
	 * 根据id移动数据字典序号
	 * @param parameter
	 */
	@Transactional
	public int updateDataDicItemSortNumById(Map<String,Object> parameter){
		String moveSign = String.valueOf(parameter.get("moveSign"));
		String dicItemId = String.valueOf(parameter.get("dicItemId"));
		String sortNum = String.valueOf(parameter.get("sortNum"));
		
		DataDicItemBean dataDicItemBean=null;
		
		if(null!=moveSign && !"null".equals(moveSign) && "up".equals(moveSign)){
			 dataDicItemBean =this.dataDicDao.queryPreviousDataDicItemById(parameter);
		}else if(null!=moveSign && !"null".equals(moveSign) && "down".equals(moveSign)){
			 dataDicItemBean =this.dataDicDao.queryNextDataDicItemById(parameter);
		}else{
			return -1;
		}
		
		if(null!=dataDicItemBean){
			parameter.put("dicItemId", dicItemId);
			parameter.put("sortNum", dataDicItemBean.getSortNum());
			this.dataDicDao.updateDataDicItemSortNumById(parameter);
			
			parameter.put("dicItemId", dataDicItemBean.getDicItemId());
			parameter.put("sortNum", sortNum);
			this.dataDicDao.updateDataDicItemSortNumById(parameter);
			return 1;
		}else{
			return -2;
		}
	}
	
	/**
	 * 根据数据字典明细id查询数据字典明细角色映射
	 */
	public List<DataDicItemRoleMappingBean> queryDataDicItemRoleMappingById(Map<String,Object> parameter){
		return this.dataDicDao.queryDataDicItemRoleMappingById(parameter);
	}
	
	/**
	 * 添加数据字典明细角色映射
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveDataDicItemRoleMapping(Map<String,Object> parameter){
		//删除数据字典明细角色映射
		this.dataDicDao.deleteDataDicItemRoleMappingById(parameter);
		
		//保存数据字典明细角色映射
		List<String> roleIds = (List<String>) parameter.get("roleIds");
		for(String roleId:roleIds){
		  parameter.put("roleId", roleId);
		  this.dataDicDao.saveDataDicItemRoleMapping(parameter);
		}
		return 1;
	}
	
	/**
	 * 根据条件查询菜单
	 * @param parameter
	 * @return
	 */
	public String queryDataDicItemByCondition(Map<String, Object> parameter)throws Exception{
			List<TreeNodeBean> list= this.dataDicDao.queryDataDicItemByCondition(parameter);
			CreateTreeUtil.setRootTreeNodeId("-1");
			return CreateTreeUtil.getTreeJson(CreateTreeUtil.createTree(list));
	}
	
	
	
	public DataDicDao getDataDicDao() {
		return dataDicDao;
	}

	@Resource
	public void setDataDicDao(DataDicDao dataDicDao) {
		this.dataDicDao = dataDicDao;
	}
	
	
	
	
	
	
	
}
