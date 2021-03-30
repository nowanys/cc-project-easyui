package com.cjhme.system.menu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjhme.system.button.bean.ButtonBean;
import com.cjhme.system.button.service.BusniessButtonService;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.menu.bean.MenuBean;
import com.cjhme.system.menu.service.MenuService;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.user.bean.UserBean;

/**
 * 
 * <p>
 * Title: MenuAction.java
 * </p>
 * Description: 菜单
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 11, 2014 7:06:16 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/menu")
public class MenuAction {

	Log LOGGER = LogFactory.getLog(MenuAction.class);

	private MenuService menuService;

	private BusniessButtonService busniessButtonService;

	/**
	 * 跳转菜单管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preMenuManage", method = RequestMethod.GET)
	public String preMenuManage(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean)) {
			RoleBean role = userBean.getCurrentRole();
			if (null != role && !"".equals(role)) {
				// 查询按钮
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("busniessMark", BusniessMarkConstants.MENU_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList = this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);

				LOGGER.info("跳转菜单管理页面成功！");
				return "forward:/pages/manage/system/menu/menu-manage.jsp";
			} else {
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转菜单管理页面请求参数错误或失效！");
		}
	}

	/**
	 * 查询菜单列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryMenuList", method = RequestMethod.POST)
	public void queryMenuList(HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			 String menus=this.menuService.queryMenuList();
			 resultData.setResultType(CommonConstants.RESULT_SUCCESS);
			 resultData.setJsonArrayResultData(menus);
			 resultData.setResultMsg("查询菜单列表成功");
			 LOGGER.info("查询菜单列表成功！");
		}catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询菜单列表失败！");
			e.printStackTrace();
			LOGGER.error("查询菜单列表失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据条件分页查询菜单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryMenuByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryMenuByConditionPaging(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			DataPaging<Object> result = this.menuService.queryMenuByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));

			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询菜单失败,错误——>" + e);
		}

		LOGGER.info("根据条件分页查询菜单成功！");
		return map;

	}

	/**
	 * 跳转菜单添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preMenuAdd", method = RequestMethod.GET)
	public String preMenuAdd(HttpServletRequest request) throws Exception {
		String menuId = request.getParameter("menuId");
		if (null != menuId && !"".equals(menuId) && !"null".equals(menuId)) {
			request.setAttribute("menuId", menuId);
			LOGGER.info("跳转菜单添加页面成功！");
			return "forward:/pages/manage/system/menu/menu-add.jsp";
		} else {
			throw new Exception("跳转菜单添加页面请求参数错误或失效！");
		}
	}

	/**
	 * 添加菜单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveMenu(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.menuService.queryMenuIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或菜单名称已存在！");
				LOGGER.info("编号或菜单名称已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				int result = this.menuService.saveMenu(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加菜单成功！");
					LOGGER.info("添加菜单成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加菜单未成功！");
					LOGGER.info("添加菜单未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加菜单失败！");
			e.printStackTrace();
			LOGGER.error("添加菜单失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 跳转菜单查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preMenuView", method = RequestMethod.GET)
	public String preMenuView(HttpServletRequest request) throws Exception {
		String menuId = request.getParameter("menuId");
		if (null != menuId && !"".equals(menuId) && !"null".equals(menuId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("menuId", menuId);
			MenuBean menuBean = this.menuService.queryMenuByMenuId(parameter);
			request.setAttribute("menuBean", menuBean);
			LOGGER.info("跳转菜单查看页面成功！");
			return "forward:/pages/manage/system/menu/menu-view.jsp";
		} else {
			throw new Exception("跳转菜单查看页面请求参数错误或失效！");
		}
	}

	/**
	 * 跳转菜单编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preMenuEdit", method = RequestMethod.GET)
	public String preMenuEdit(HttpServletRequest request) throws Exception {
		String menuId = request.getParameter("menuId");
		if (null != menuId && !"".equals(menuId) && !"null".equals(menuId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("menuId", menuId);
			MenuBean menuBean = this.menuService.queryMenuByMenuId(parameter);
			request.setAttribute("menuBean", menuBean);
			LOGGER.info("跳转菜单编辑页面成功！");
			return "forward:/pages/manage/system/menu/menu-edit.jsp";
		} else {
			throw new Exception("跳转菜单编辑页面请求参数错误或失效！");
		}
	}

	/**
	 * 根据id修改菜单
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateMenuByMenuId", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateMenuByMenuId(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.menuService.queryMenuIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或菜单名称已存在！");
				LOGGER.info("编号或菜单名称已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.menuService.updateMenuByMenuId(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加菜单成功！");
					LOGGER.info("添加菜单成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加菜单未成功！");
					LOGGER.info("添加菜单未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加菜单失败！");
			e.printStackTrace();
			LOGGER.error("添加菜单失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除菜单
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteMenuByIds", method = RequestMethod.POST)
	public void deleteMenuByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String idsStr = request.getParameter("ids");
			if (null != idsStr && !"".equals(idsStr)) {
				String[] ids = idsStr.split(",");
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("ids", ids);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.menuService.deleteMenuByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除菜单成功！");
					LOGGER.info("根据ids删除菜单成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除菜单未成功！");
					LOGGER.info("根据ids删除菜单未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除菜单失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除菜单失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据id移动菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateMenuParentMenuIdById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateMenuParentMenuIdById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			parameter.put("updateUserId", userBean.getUserId());
			parameter.put("updateUser", userBean.getRealName());
			int result = this.menuService.updateMenuParentMenuIdById(parameter);
			if (result > 0) {
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("移动菜单成功！");
				LOGGER.info("根据id移动菜单成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("移动菜单未成功！");
				LOGGER.info("根据id移动菜单未成功！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("移动菜单失败！");
			e.printStackTrace();
			LOGGER.error("根据id移动菜单失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据id移动菜单序号(-1失败，1成功，-2已到顶、已到底)
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateMenuSortNumById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateMenuSortNumById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			parameter.put("updateUserId", userBean.getUserId());
			parameter.put("updateUser", userBean.getRealName());
			LOGGER.info(" 根据id移动菜单序号成功！");
			int result = this.menuService.updateMenuSortNumById(parameter);
			if (result == 1) {
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("移动菜单序号成功！");
				LOGGER.info("根据id移动菜单序号成功！");
			} else if (result == -2) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("移动菜单序号已到顶或已到底！");
				LOGGER.info("根据id移动菜单序号已到顶或已到底！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_ERROR);
				resultData.setResultMsg("移动菜单序号失败！");
				LOGGER.info("根据id移动菜单序号失败！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("移动菜单序号失败！");
			e.printStackTrace();
			LOGGER.error("根据id移动菜单序号失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	public MenuService getMenuService() {
		return menuService;
	}

	@Resource
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}
}
