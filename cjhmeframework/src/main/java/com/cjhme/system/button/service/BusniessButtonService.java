package com.cjhme.system.button.service;

import java.util.List;
import java.util.Map;

import com.cjhme.system.button.bean.ButtonBean;
/**
 * 
 * <p>
 * Title: BusniessButtonService.java
 * </p>
 * Description: 业务按钮
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Mar 25, 2015 1:57:11 PM
 */
public interface BusniessButtonService {

	/**
	 * 默认业务按钮控制
	 * 
	 * @param busniessStatus
	 *            业务状态
	 * @param createUserId
	 *            创建人id
	 * @param currentUserId
	 *            当前用户id
	 * @return
	 */
	public Map<String, String> defaultBtnControl(String busniessStatus, String createUserId, String currentUserId);
	
	/**
	 * 根据业务标识与角色id查询按钮
	 * @param parameter
	 * @return
	 */
	public List<ButtonBean> queryBtnByBusniessMarkAndRole(Map<String,Object> parameter);
	
	
}
