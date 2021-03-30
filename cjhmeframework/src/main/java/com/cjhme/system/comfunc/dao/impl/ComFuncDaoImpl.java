package com.cjhme.system.comfunc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.comfunc.bean.ComFuncBean;
import com.cjhme.system.comfunc.dao.ComFuncDao;
import com.cjhme.system.main.dao.BaseDao;

/**
 * 
 * <p>  
 * Title: ComFuncDao.java 
 * </p>  
 * Description: 常用功能
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jul 10, 2014 10:23:52 PM
 */
@Repository("comFuncDao")
public class ComFuncDaoImpl extends BaseDao implements ComFuncDao {
	
	/**
	 * 查询用户常用功能列表
	 * @param parameter
	 * @return
	 */
	public List<ComFuncBean> queryUserComFuncList(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.comfunc.dao.ComFuncDao.queryUserComFuncList",parameter);
	}
	
	/**
	 * 删除常用功能 
	 * @param parameter
	 * @return
	 */
    public int deleteComfunc(Map<String,Object> parameter){
    	return this.getSqlSessionTemplate().delete("com.cjhme.system.comfunc.dao.ComFuncDao.deleteComfunc",parameter);
    }
    
    /**
	 * 添加常用功能
	 * @param parameter
	 * @return
	 */
    public int saveComfunc(Map<String,Object> parameter){
    	return this.getSqlSessionTemplate().insert("com.cjhme.system.comfunc.dao.ComFuncDao.saveComfunc",parameter);
    }
	
}
