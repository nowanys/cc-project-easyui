package com.cjhme.system.comfunc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.comfunc.bean.ComFuncBean;
import com.cjhme.system.comfunc.dao.ComFuncDao;
import com.cjhme.system.comfunc.service.ComFuncService;

/**
 * 
 * <p>  
 * Title: ComFuncServiceImpl.java 
 * </p>  
 * Description: 数据字典
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Sep 18, 2014 2:26:12 PM
 */
@Service("comFuncService")
public class ComFuncServiceImpl implements ComFuncService {

	private ComFuncDao comFuncDao;

	/**
	 * 查询用户常用功能列表
	 * @param parameter
	 * @return
	 */
	public List<ComFuncBean> queryUserComFuncList(Map<String,Object> parameter){
		return this.comFuncDao.queryUserComFuncList(parameter);
	}
	
	/**
	 * 保存常用功能配置
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveComfunc(Map<String,Object> parameter){
		this.comFuncDao.deleteComfunc(parameter);
		List<String> menuIds = (List<String>) parameter.get("menuIds");
		for(String menuId:menuIds){
			parameter.put("menuId", menuId);
			this.comFuncDao.saveComfunc(parameter);
		}
		return 1;
	}
	
	
	public ComFuncDao getComFuncDao() {
		return comFuncDao;
	}



	@Resource
	public void setComFuncDao(ComFuncDao comFuncDao) {
		this.comFuncDao = comFuncDao;
	}
	
	
	
	
	
	
}
