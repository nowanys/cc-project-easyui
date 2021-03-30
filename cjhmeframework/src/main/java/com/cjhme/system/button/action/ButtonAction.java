package com.cjhme.system.button.action;

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
import com.cjhme.system.button.service.ButtonService;
import com.cjhme.system.main.action.SysBaseAction;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.constant.DataDicConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.menu.service.MenuService;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.user.bean.UserBean;

/**
 * 
 * <p>
 * Title: ButtonAction.java
 * </p>
 * Description: 按钮
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 28, 2014 7:29:14 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/button")
public class ButtonAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(ButtonAction.class);

	private ButtonService buttonService;

	private MenuService menuService;

	private BusniessButtonService busniessButtonService;

	/**
	 * 跳转按钮管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preButtonManage", method = RequestMethod.GET)
	public String preButtonManage(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean)) {
			RoleBean role = userBean.getCurrentRole();
			if (null != role && !"".equals(role)) {
				// 查询按钮
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("busniessMark", BusniessMarkConstants.BTN_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList = this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);

				// 业务标识
				List<Map<String,Object>> busniessMarks = BusniessMarkConstants.queryBusniessMarkList();
				request.setAttribute("busniessMarks", busniessMarks);

				// 数据字典
				this.bindDataDicParam(request, new String[] { DataDicConstants.QUERY_STATUS_TYPE, DataDicConstants.STATUS_TYPE, DataDicConstants.BUTTON_CATEGORY_TYPE, DataDicConstants.BUTTON_LAYOUT_MARK_TYPE });

				LOGGER.info("跳转按钮管理页面成功！");
				return "forward:/pages/manage/system/button/button-manage.jsp";
			} else {
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转按钮管理页面请求参数错误或失效！");
		}
	}

	/**
	 * 根据条件分页查询按钮
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryButtonByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryButtonByConditionPaging(HttpServletRequest request) {

		Map<String, Object> map = null;

		try {
			DataPaging<Object> result = this.buttonService.queryButtonByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询按钮失败,错误——>" + e);
		}

		LOGGER.info("根据条件分页查询按钮成功！");
		return map;

	}

	/**
	 * 跳转按钮添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preButtonAdd", method = RequestMethod.GET)
	public String preButtonAdd(HttpServletRequest request) throws Exception {
		// 业务标识
		List<Map<String,Object>> busniessMarks = BusniessMarkConstants.queryBusniessMarkList();
		request.setAttribute("busniessMarks", busniessMarks);
		// 数据字典
		this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE, DataDicConstants.BUTTON_CATEGORY_TYPE, DataDicConstants.BUTTON_LAYOUT_MARK_TYPE });
		LOGGER.info("跳转按钮添加页面成功！");
		return "forward:/pages/manage/system/button/button-add.jsp";
	}

	/**
	 * 添加按钮(-2存在，>0成功)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveButton", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveButton(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			// 判断是否存在
			int isExists = this.buttonService.queryButtonIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("按钮已存在（菜单名称、函数名、业务标识、布局相同）！");
				LOGGER.info("按钮已存在（菜单名称、函数名、业务标识、布局相同）！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				int result = this.buttonService.saveButton(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加按钮成功！");
					LOGGER.info("添加按钮成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加按钮未成功！");
					LOGGER.info("添加按钮未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加按钮失败！");
			e.printStackTrace();
			LOGGER.error("添加按钮失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 跳转按钮查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preButtonView", method = RequestMethod.GET)
	public String preButtonView(HttpServletRequest request) throws Exception {
		String buttonId = String.valueOf(request.getParameter("buttonId"));
		if (null != buttonId && !"".equals(buttonId) && !"null".equals(buttonId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("buttonId", buttonId);
			ButtonBean buttonBean = this.buttonService.queryButtonById(parameter);
			request.setAttribute("buttonBean", buttonBean);
			// 业务标识
			List<Map<String,Object>> busniessMarks = BusniessMarkConstants.queryBusniessMarkList();
			request.setAttribute("busniessMarks", busniessMarks);
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE, DataDicConstants.BUTTON_CATEGORY_TYPE, DataDicConstants.BUTTON_LAYOUT_MARK_TYPE });

			LOGGER.info("跳转按钮查看页面成功！");
			return "forward:/pages/manage/system/button/button-view.jsp";
		} else {
			throw new Exception("跳转按钮查看页面请求参数错误或失效！");
		}
	}

	/**
	 * 跳转按钮编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preButtonEdit", method = RequestMethod.GET)
	public String preButtonEdit(HttpServletRequest request) throws Exception {
		String buttonId = String.valueOf(request.getParameter("buttonId"));
		if (null != buttonId && !"".equals(buttonId) && !"null".equals(buttonId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("buttonId", buttonId);
			ButtonBean buttonBean = this.buttonService.queryButtonById(parameter);
			request.setAttribute("buttonBean", buttonBean);
			// 业务标识
			List<Map<String,Object>> busniessMarks = BusniessMarkConstants.queryBusniessMarkList();
			request.setAttribute("busniessMarks", busniessMarks);
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE, DataDicConstants.BUTTON_CATEGORY_TYPE, DataDicConstants.BUTTON_LAYOUT_MARK_TYPE });
			LOGGER.info("跳转按钮编辑页面成功！");
			return "forward:/pages/manage/system/button/button-edit.jsp";
		} else {
			throw new Exception("跳转按钮编辑页面请求参数错误或失效！");
		}
	}

	/**
	 * 根据id修改按钮
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateButtonById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateButtonById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 判断是否存在
			int isExists = this.buttonService.queryButtonIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("按钮已存在（菜单名称、函数名、业务标识、布局相同）！");
				LOGGER.info("按钮已存在（菜单名称、函数名、业务标识、布局相同）！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.buttonService.updateButtonById(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改按钮成功！");
					LOGGER.info("根据id修改按钮成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改按钮未成功！");
					LOGGER.info("根据id修改按钮未成功！");
				}

			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改按钮失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改按钮失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除按钮
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteButtonByIds", method = RequestMethod.POST)
	public void deleteButtonByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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
				int result = this.buttonService.deleteButtonByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除按钮成功！");
					LOGGER.info("根据ids删除按钮成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除按钮未成功！");
					LOGGER.info("根据ids删除按钮未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除按钮失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除按钮失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	public ButtonService getButtonService() {
		return buttonService;
	}

	@Resource
	public void setButtonService(ButtonService buttonService) {
		this.buttonService = buttonService;
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
