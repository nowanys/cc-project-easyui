package com.cjhme.system.access.action;

import java.util.HashMap;
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

import com.cjhme.system.access.bean.AccessUrlBean;
import com.cjhme.system.access.service.AccessUrlService;
import com.cjhme.system.main.action.SysCommonAction;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.constant.DataDicConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.user.bean.UserBean;

/**
 * 
 * <p>  
 * Title: AccessUrlAction.java 
 * </p>  
 * Description: 访问地址
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Aug 7, 2015 5:25:57 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/accessUrl")
public class AccessUrlAction extends SysCommonAction {
	
	Log LOGGER = LogFactory.getLog(AccessUrlAction.class);

	private AccessUrlService accessUrlService;
	

	
	/**
	 * 跳转访问地址管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preAccessUrlManage", method = RequestMethod.GET)
	public String preAccessUrlManage(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean)) {
			RoleBean role = userBean.getCurrentRole();
			if (null != role && !"".equals(role)) {
				
				// 查询按钮
				this.queryBtnByBusniessMarkAndRole(request, role, BusniessMarkConstants.ACCESS_URL_MANAGE);

				// 数据字典
				this.bindDataDicParam(request, new String[] { DataDicConstants.QUERY_STATUS_TYPE, DataDicConstants.STATUS_TYPE});

				LOGGER.info("跳转访问地址管理页面成功！");
				return "forward:/pages/manage/system/access/access-url-manage.jsp";
			} else {
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转访问地址管理页面请求参数错误或失效！");
		}
	}
	

	/**
	 * 根据条件分页查询访问地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAccessUrlByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAccessUrlByConditionPaging(HttpServletRequest request) {

		Map<String, Object> map = null;

		try {
			DataPaging<Object> result = this.accessUrlService.queryAccessUrlByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询访问地址失败,错误——>" + e);
		}

		LOGGER.info("根据条件分页查询访问地址成功！");
		return map;

	}
	
	/**
	 * 跳转访问地址添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preAccessUrlAdd", method = RequestMethod.GET)
	public String preAccessUrlAdd(HttpServletRequest request) throws Exception {
		// 数据字典
		this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE});
		LOGGER.info("跳转访问地址添加页面成功！");
		return "forward:/pages/manage/system/access/access-url-add.jsp";
	}

	/**
	 * 添加访问地址(-2存在，>0成功)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveAccessUrl", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveAccessUrl(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			// 判断是否存在
			int isExists = this.accessUrlService.queryAccessUrlIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("菜单名称、访问地址已存在！");
				LOGGER.info("菜单名称、访问地址已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				int result = this.accessUrlService.saveAccessUrl(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加访问地址成功！");
					LOGGER.info("添加访问地址成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加访问地址未成功！");
					LOGGER.info("添加访问地址未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加访问地址失败！");
			e.printStackTrace();
			LOGGER.error("添加访问地址失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转访问地址查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preAccessUrlView", method = RequestMethod.GET)
	public String preAccessUrlView(HttpServletRequest request) throws Exception {
		String accessUrlId = String.valueOf(request.getParameter("accessUrlId"));
		if (null != accessUrlId && !"".equals(accessUrlId) && !"null".equals(accessUrlId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("accessUrlId", accessUrlId);
			AccessUrlBean accessUrlBean = this.accessUrlService.queryAccessUrlById(parameter);
			request.setAttribute("accessUrlBean", accessUrlBean);
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE});

			LOGGER.info("跳转访问地址查看页面成功！");
			return "forward:/pages/manage/system/access/access-url-view.jsp";
		} else {
			throw new Exception("跳转访问地址查看页面请求参数错误或失效！");
		}
	}
	
	/**
	 * 跳转访问地址编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preAccessUrlEdit", method = RequestMethod.GET)
	public String preAccessUrlEdit(HttpServletRequest request) throws Exception {
		String accessUrlId = String.valueOf(request.getParameter("accessUrlId"));
		if (null != accessUrlId && !"".equals(accessUrlId) && !"null".equals(accessUrlId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("accessUrlId", accessUrlId);
			AccessUrlBean accessUrlBean = this.accessUrlService.queryAccessUrlById(parameter);
			request.setAttribute("accessUrlBean", accessUrlBean);
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE});

			LOGGER.info("跳转访问地址编辑页面成功！");
			return "forward:/pages/manage/system/access/access-url-edit.jsp";
		} else {
			throw new Exception("跳转访问地址编辑页面请求参数错误或失效！");
		}
	}
	
	/**
	 * 根据id修改访问地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateAccessUrlById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateAccessUrlById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 判断是否存在
			int isExists = this.accessUrlService.queryAccessUrlIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("菜单名称、访问地址已存在！");
				LOGGER.info("菜单名称、访问地址已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.accessUrlService.updateAccessUrlById(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改访问地址成功！");
					LOGGER.info("根据id修改访问地址成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改访问地址未成功！");
					LOGGER.info("根据id修改访问地址未成功！");
				}

			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改访问地址失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改访问地址失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 根据ids删除访问地址
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteAccessUrlByIds", method = RequestMethod.POST)
	public void deleteAccessUrlByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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
				int result = this.accessUrlService.deleteAccessUrlByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除访问地址成功！");
					LOGGER.info("根据ids删除访问地址成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除访问地址未成功！");
					LOGGER.info("根据ids删除访问地址未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除访问地址失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除访问地址失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	

	public AccessUrlService getAccessUrlService() {
		return accessUrlService;
	}

	@Resource
	public void setAccessUrlService(AccessUrlService accessUrlService) {
		this.accessUrlService = accessUrlService;
	}

	
	
	
}
