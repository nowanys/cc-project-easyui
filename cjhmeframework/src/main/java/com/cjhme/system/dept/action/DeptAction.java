package com.cjhme.system.dept.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjhme.system.button.bean.ButtonBean;
import com.cjhme.system.button.service.BusniessButtonService;
import com.cjhme.system.dept.bean.DeptBean;
import com.cjhme.system.dept.service.DeptService;
import com.cjhme.system.main.action.SysBaseAction;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.constant.DataDicConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.user.bean.UserBean;
import com.cjhme.system.user.service.UserService;

/**
 * 
 * <p>
 * Title: DeptAction.java
 * </p>
 * Description: 部门
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Nov 21, 2014 8:58:17 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/dept")
public class DeptAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(DeptAction.class);

	private DeptService deptService;

	private UserService userService;

	private BusniessButtonService busniessButtonService;

	/**
	 * 跳转部门管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDeptManage", method = RequestMethod.GET)
	public String preDeptManage(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean)) {
			RoleBean role = userBean.getCurrentRole();
			if (null != role && !"".equals(role)) {
				// 查询按钮
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("busniessMark", BusniessMarkConstants.DEPT_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList = this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);

				LOGGER.info("跳转部门管理页面成功！");
				return "forward:/pages/manage/system/dept/dept-manage.jsp";
			} else {
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转部门管理页面请求参数错误或失效！");
		}
	}

	/**
	 * 查询所有部门结构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryDeptList", method = RequestMethod.POST)
	public void queryDeptList(HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String depts = this.deptService.queryDeptList();
			resultData.setResultType(CommonConstants.RESULT_SUCCESS);
			resultData.setJsonArrayResultData(depts);
			resultData.setResultMsg("查询所有部门结构成功");
			LOGGER.info("查询所有部门结构成功！");
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询所有部门结构失败！");
			e.printStackTrace();
			LOGGER.error("查询所有部门结构失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据条件分页查询部门
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDeptByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryDeptByConditionPaging(HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			DataPaging<Object> result = this.deptService.queryDeptByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询部门,错误——>" + e);
		}

		LOGGER.info("根据条件分页查询部门成功！");
		return map;

	}

	/**
	 * 跳转部门添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDeptAdd", method = RequestMethod.GET)
	public String preDeptAdd(HttpServletRequest request) throws Exception {
		String deptId = request.getParameter("deptId");
		if (null != deptId && !"".equals(deptId) && !"null".equals(deptId)) {
			request.setAttribute("deptId", deptId);
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.EMAIL_SWITCH_TYPE, DataDicConstants.SMS_SWITCH_TYPE });
			LOGGER.info("跳转部门添加页面成功！");
			return "forward:/pages/manage/system/dept/dept-add.jsp";
		} else {
			throw new Exception("跳转部门添加页面请求参数错误或失效！");
		}
	}

	/**
	 * 添加部门
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDept", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDept(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.deptService.queryDeptIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或部门名称已存在！");
				LOGGER.info("编号或部门名称已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				int result = this.deptService.saveDept(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加部门成功！");
					LOGGER.info("添加部门成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加部门未成功！");
					LOGGER.info("添加部门未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加部门失败！");
			e.printStackTrace();
			LOGGER.error("添加部门失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 跳转部门查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDeptView", method = RequestMethod.GET)
	public String preDeptView(HttpServletRequest request) throws Exception {
		String deptId = request.getParameter("deptId");
		if (null != deptId && !"".equals(deptId) && !"null".equals(deptId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("deptId", deptId);
			DeptBean deptBean = this.deptService.queryDeptByDeptId(parameter);
			request.setAttribute("deptBean", deptBean);
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.EMAIL_SWITCH_TYPE, DataDicConstants.SMS_SWITCH_TYPE });
			LOGGER.info("跳转部门查看页面成功！");
			return "forward:/pages/manage/system/dept/dept-view.jsp";
		} else {
			throw new Exception("跳转部门查看页面请求参数错误或失效！");
		}
	}

	/**
	 * 跳转部门编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDeptEdit", method = RequestMethod.GET)
	public String preDeptEdit(HttpServletRequest request) throws Exception {
		String deptId = request.getParameter("deptId");
		if (null != deptId && !"".equals(deptId) && !"null".equals(deptId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("deptId", deptId);
			DeptBean deptBean = this.deptService.queryDeptByDeptId(parameter);
			request.setAttribute("deptBean", deptBean);
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.EMAIL_SWITCH_TYPE, DataDicConstants.SMS_SWITCH_TYPE });
			LOGGER.info("跳转部门编辑页面成功！");
			return "forward:/pages/manage/system/dept/dept-edit.jsp";
		} else {
			throw new Exception("跳转部门编辑页面请求参数错误或失效！");
		}
	}

	/**
	 * 根据id修改部门
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateDeptByDeptId", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateDeptByDeptId(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.deptService.queryDeptIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或部门名称已存在！");
				LOGGER.info("编号或部门名称已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.deptService.updateDeptByDeptId(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改部门！");
					LOGGER.info("根据id修改部门！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改部门未成功！");
					LOGGER.info("根据id修改部门未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改部门失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改部门失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除部门
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteDeptByIds", method = RequestMethod.POST)
	public void deleteDeptByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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

				int result = this.deptService.deleteDeptByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除部门成功！");
					LOGGER.info("根据ids删除部门成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除部门未成功！");
					LOGGER.info("根据ids删除部门未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除部门失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除部门失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据id移动部门
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateDeptParentDeptIdById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateDeptParentDeptIdById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			parameter.put("updateUserId", userBean.getUserId());
			parameter.put("updateUser", userBean.getRealName());
			int result = this.deptService.updateDeptParentDeptIdById(parameter);
			if (result > 0) {
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("移动部门成功！");
				LOGGER.info("根据id移动部门成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("移动部门未成功！");
				LOGGER.info("根据id移动部门未成功！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("移动部门失败！");
			e.printStackTrace();
			LOGGER.error("根据id移动部门失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 跳转部门用户查询页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDeptUsersQuery", method = RequestMethod.GET)
	public String preDeptUsersQuery(HttpServletRequest request) throws Exception {
		String deptId = String.valueOf(request.getParameter("deptId"));
		if (null != deptId && !"".equals(deptId) && !"null".equals(deptId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("deptId", deptId);
			JSONArray data = JSONArray.fromObject(this.userService.queryUserByDeptId(parameter));
			request.setAttribute("data", data);
			LOGGER.info("跳转部门用户查询页面成功！");
			return "forward:/pages/manage/system/dept/dept-users.jsp";
		} else {
			throw new Exception("跳转部门用户查询页面请求参数错误或失效！");
		}

	}

	public DeptService getDeptService() {
		return deptService;
	}

	@Resource
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}

}
