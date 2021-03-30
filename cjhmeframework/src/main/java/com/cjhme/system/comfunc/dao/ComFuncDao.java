package com.cjhme.system.comfunc.dao;

import java.util.List;
import java.util.Map;

import com.cjhme.system.comfunc.bean.ComFuncBean;


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
public interface ComFuncDao {
	
	
	/**
	 * 查询用户常用功能列表
	 * @param parameter
	 * @return
	 */
	public List<ComFuncBean> queryUserComFuncList(Map<String,Object> parameter);
	
	/**
	 * 删除常用功能 
	 * @param parameter
	 * @return
	 */
    public int deleteComfunc(Map<String,Object> parameter);
    
    /**
	 * 添加常用功能
	 * @param parameter
	 * @return
	 */
    public int saveComfunc(Map<String,Object> parameter);
}
