package com.cjhme.system.main.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cjhme.system.button.bean.ButtonBean;
import com.cjhme.system.button.service.BusniessButtonService;
import com.cjhme.system.role.bean.RoleBean;

/**
 * 
 * <p>
 * Title: SysCommonAction.java
 * </p>
 * Description: 系统公共action(实现按钮获得)
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Jun 24, 2015 1:08:43 PM
 */
public class SysCommonAction extends SysBaseAction {

	private BusniessButtonService busniessButtonService;

	/**
	 * 根据业务标识与角色id获得按钮
	 * @param request httpServletRequest对象
	 * @param role 角色
	 * @param busniessMark 业务标识
	 */
	public void queryBtnByBusniessMarkAndRole(HttpServletRequest request,RoleBean role,String busniessMark) {
		// 查询按钮
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("busniessMark", busniessMark);
		parameter.put("roleId", role.getRoleId());
		List<ButtonBean> buttonList = this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
		request.setAttribute("buttonList", buttonList);
	}

	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(
			BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}

}
