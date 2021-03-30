package com.cjhme.system.comfunc.service;

import java.util.List;
import java.util.Map;

import com.cjhme.system.comfunc.bean.ComFuncBean;

/**
 * 
 * <p>  
 * Title: ComFuncService.java 
 * </p>  
 * Description: 数据字典
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Sep 18, 2014 2:26:12 PM
 */
public interface ComFuncService {
	
	/**
	 * 查询用户常用功能列表
	 * @param parameter
	 * @return
	 */
	public List<ComFuncBean> queryUserComFuncList(Map<String,Object> parameter);
	
	/**
	 * 保存常用功能配置
	 * @param parameter
	 * @return
	 */
	public int saveComfunc(Map<String,Object> parameter);
}
