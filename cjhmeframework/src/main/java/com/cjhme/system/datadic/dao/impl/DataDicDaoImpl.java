package com.cjhme.system.datadic.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.datadic.bean.DataDicItemBean;
import com.cjhme.system.datadic.bean.DataDicItemRoleMappingBean;
import com.cjhme.system.datadic.bean.DataDicTypeBean;
import com.cjhme.system.datadic.dao.DataDicDao;
import com.cjhme.system.main.bean.TreeNodeBean;
import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: DataDicDaoImpl.java 
 * </p>  
 * Description: 数据字典
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Sep 18, 2014 2:26:12 PM
 */
@Repository("dataDicDao")
public class DataDicDaoImpl extends BaseDao implements DataDicDao {

	/**
	 * 初始化时查询数据字典分类列表
	 * @return
	 */
	public Map<String,Object> queryInitDataDicType(){
	   return this.getSqlSessionTemplate().selectMap("com.cjhme.system.datadic.dao.DataDicDao.queryInitDataDicType", "dicTypeCode");
	}
	
	/**
	 * 查询数据字典分类列表
	 * @return
	 */
	public List<DataDicTypeBean> queryDataDicTypeList(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.datadic.dao.DataDicDao.queryDataDicTypeList",parameter);
	}
	

	/**
	 * 判断数据字典分类是否存在
	 * @param parameter
	 * @return
	 */
	public int queryDataDicTypeIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.datadic.dao.DataDicDao.queryDataDicTypeIsExists",parameter);
	}
	
	
	/**
	 * 添加数据字典分类
	 * @param dataDicTypeBean
	 * @return
	 */
	public int saveDataDicType(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.datadic.dao.DataDicDao.saveDataDicType",parameter);
	}
	
	/**
	 * 根据id查询数据字典分类
	 * @param parameter
	 * @return
	 */
	public DataDicTypeBean queryDataDicTypeById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.datadic.dao.DataDicDao.queryDataDicTypeById",parameter);
	}
	
	/**
	 * 根据id修改数据字典分类
	 * @param parameter
	 * @return
	 */
	public int updateDataDicTypeById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.datadic.dao.DataDicDao.updateDataDicTypeById",parameter);
	}
	
	/**
	 * 根据ids删除数据字典分类
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicTypeByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.datadic.dao.DataDicDao.deleteDataDicTypeByIds",parameter);
	}
	
	/**
	 * 根数据字典分类id查询数据字典明细
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryDataDicItemByIdPaging(DataPaging<Object> pageParameter ){
		return this.selectPaging("com.cjhme.system.datadic.dao.DataDicDao.queryDataDicItemByIdPaging", pageParameter);
	}
	
	/**
	 * 判断数据字典明细是否存在
	 * @param pageParameter
	 * @return
	 */
	public int queryDataDicItemIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.datadic.dao.DataDicDao.queryDataDicItemIsExists",parameter);
	}
	
	/**
	 * 根据分类编号查询明细最大序号
	 * @param pageParameter
	 * @return
	 */
	public int queryDataDiaItemMaxSortNumByTypeCode(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.datadic.dao.DataDicDao.queryDataDiaItemMaxSortNumByTypeCode",parameter);
	}
	
	
	/**
	 * 添加数据字典明细 
	 * @param parameter
	 * @return
	 */
	public int saveDataDicItem(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.datadic.dao.DataDicDao.saveDataDicItem",parameter);
	}
	
	/**
	 * 根据id查询数据字典明细
	 * @param parameter
	 * @return
	 */
	public DataDicItemBean queryDataDicItemById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.datadic.dao.DataDicDao.queryDataDicItemById",parameter);
	}
	
	/**
	 * 根据id修改数据字典明细
	 * @param parameter
	 * @return
	 */
	public int updateDataDicItemById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.datadic.dao.DataDicDao.updateDataDicItemById",parameter);
	}
	
	/**
	 * 根据ids删除数据字典明细
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicItemByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.datadic.dao.DataDicDao.deleteDataDicItemByIds",parameter);
	}
	
	/**
	 * 根据分类ids删除数据字典明细
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicItemByTypeIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.datadic.dao.DataDicDao.deleteDataDicItemByTypeIds",parameter);
	}
	
	/**
	 * 获得当前行的上一条明细
	 * @param parameter
	 * @return
	 */
	public DataDicItemBean queryPreviousDataDicItemById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.datadic.dao.DataDicDao.queryPreviousDataDicItemById",parameter);
	}
	
	/**
	 * 获得当前行的下一条明细
	 * @param parameter
	 * @return
	 */
	public DataDicItemBean queryNextDataDicItemById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.datadic.dao.DataDicDao.queryNextDataDicItemById",parameter);
	}
	
	/**
	 * 根据id移动数据字典序号
	 * @param parameter
	 */
	public int updateDataDicItemSortNumById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.datadic.dao.DataDicDao.updateDataDicItemSortNumById",parameter);
	}
	
	/**
	 * 根据数据字典明细id查询数据字典明细角色映射
	 */
	public List<DataDicItemRoleMappingBean> queryDataDicItemRoleMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.datadic.dao.DataDicDao.queryDataDicItemRoleMappingById",parameter);
	}
	
	/**
	 * 根据数据字典明细id删除数据字典明细角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicItemRoleMappingById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.datadic.dao.DataDicDao.deleteDataDicItemRoleMappingById",parameter);
	}
	
	/**
	 * 添加数据字典明细角色映射
	 * @param parameter
	 * @return
	 */
	public int saveDataDicItemRoleMapping(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.datadic.dao.DataDicDao.saveDataDicItemRoleMapping",parameter);
	}
	
	/**
	 * 根据数据字典明细ids删除数据字典明细角色映射
	 * @param parameter
	 * @return
	 */
    public int deleteDataDicItemRoleMappingByIds(Map<String,Object> parameter){
    	return this.getSqlSessionTemplate().delete("com.cjhme.system.datadic.dao.DataDicDao.deleteDataDicItemRoleMappingByIds",parameter);
    }
    
    /**
     * 根据条件查询数据字典明细 
     * @param parameter
     * @return
     */
	public List<TreeNodeBean> queryDataDicItemByCondition(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.datadic.dao.DataDicDao.queryDataDicItemByCondition",parameter);
	}
	
	/**
	 * 根据移动字典明细id查询明细最大排序 
	 * @param parameter
	 * @return
	 */
	public String queryMaxSortNumByParentId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.datadic.dao.DataDicDao.queryMaxSortNumByParentId",parameter);
	}
	
	
	
	
}
