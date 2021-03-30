package com.cjhme.system.publicpage.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjhme.system.datadic.service.DataDicService;
import com.cjhme.system.dept.service.DeptService;
import com.cjhme.system.main.action.SysBaseAction;
import com.cjhme.system.main.bean.IconFileBean;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.interceptor.springmvc.annotation.Auth;
import com.cjhme.system.main.util.ConfigUtil;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.menu.service.MenuService;
import com.cjhme.system.user.service.UserService;

/**
 * 
 * <p>
 * Title: PublicPageAction.java
 * </p>
 * Description: 公共页面
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 17, 2014 11:14:32 AM
 */
@Controller
@Scope("prototype")
@RequestMapping("/publicPage")
public class PublicPageAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(PublicPageAction.class);
	
	private UserService  userService;
	
	private DeptService deptService;
	
	private MenuService menuService;
	
	private DataDicService dataDicService;

	/**
	 * 跳转图标选择页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preIconSelect", method = RequestMethod.GET)
	@Auth(verifyUrl=false)
	public String preIconSelect(HttpServletRequest request) {
		// 默认回调函数处理
		this.defaultCallBackFunc(request, "iconSelectCallBackFunc");
		String systemIconPath = ConfigUtil.getInstance().getPopString("systemIconPath");
		String path = request.getSession().getServletContext().getRealPath(systemIconPath);
		File iconFilePath = new File(path);
		if (iconFilePath.exists()) {
			File[] files = iconFilePath.listFiles();
			if (null != files && files.length > 0) {
				List<IconFileBean> iconFiles = new ArrayList<IconFileBean>();
				for (File f : files) {
					String temp = f.getName();
					if (temp.endsWith(".png") || temp.endsWith(".PNG")) {
						temp = temp.substring(0, temp.indexOf("."));
						IconFileBean iconFileBean = new IconFileBean();
						iconFileBean.setIconCls("icon-" + temp);
						iconFileBean.setIconPath(systemIconPath + "/" + f.getName());
						iconFiles.add(iconFileBean);
					}
				}

				request.setAttribute("systemIcons", iconFiles);
			}
		}

		LOGGER.info("跳转图标选择页面成功！");
		return "forward:/pages/manage/system/publicpage/icon-select.jsp";
	}

	/**
	 * 跳转单个用户选择页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preSingleUserSelect", method = RequestMethod.GET)
	@Auth(verifyUrl=false)
	public String preSingleUserSelect(HttpServletRequest request) {
		// 默认回调函数处理
		this.defaultCallBackFunc(request, "userSelectCallBackFunc");
		LOGGER.info("跳转所有用户选择页面成功！");
		return "forward:/pages/manage/system/publicpage/single-user-select.jsp";
	}

	/**
	 * 根据条件查询所有用户
	 * @param page
	 * @param rows
	 * @param userCode
	 * @param userName
	 * @param realName
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/queryAllUserByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	@Auth(verifyUrl=false)
	public Map<String, Object> queryAllUserByConditionPaging(HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			DataPaging<Object> result = this.userService.queryUserByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件查询所有用户失败,错误——>"+e);
		}

		LOGGER.info("根据条件查询所有用户成功！");
		return map;

	}
	
	/**
	 * 跳转单个部门选择页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preSingleDeptSelect", method = RequestMethod.GET)
	@Auth(verifyUrl=false)
	public String preSingleDeptSelect(HttpServletRequest request) {
		// 默认回调函数处理
		this.defaultCallBackFunc(request, "deptSelectCallBackFunc");
		LOGGER.info("跳转单个部门选择页面成功！");
		return "forward:/pages/manage/system/publicpage/single-dept-select.jsp";
	}
	
	/**
	 * 根据条件查询部门
	 * @param orgCode
	 * @param orgName
	 * @param orgSortName
	 * @return
	 */
	@RequestMapping(value = "/queryDeptByCondition", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	@Auth(verifyUrl=false)
	public void queryDeptByCondition(HttpServletRequest request,HttpServletResponse response){
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if(null!=parameter && !"".equals(parameter)){
				String depts=this.deptService.queryDeptByCondition(parameter);
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setJsonArrayResultData(depts);
				resultData.setResultMsg("根据条件查询部门成功");
			    LOGGER.info("根据条件查询部门成功！");
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.error("请求参数错误！");
			}
			
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件查询部门失败！");
			e.printStackTrace();
			LOGGER.error("根据条件查询部门失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转单个菜单选择页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preSingleMenuSelect", method = RequestMethod.GET)
	@Auth(verifyUrl=false)
	public String preSingleMenuSelect(HttpServletRequest request) {
		// 默认回调函数处理
		this.defaultCallBackFunc(request, "menuSelectCallBackFunc");
		request.setAttribute("isCheckLeaf",request.getParameter("isCheckLeaf"));
		LOGGER.info("跳转单个菜单选择页面成功！");
		return "forward:/pages/manage/system/publicpage/single-menu-select.jsp";
	}

	/**
	 * 根据条件查询菜单
	 * @param orgCode
	 * @param orgName
	 * @param orgSortName
	 * @return
	 */
	@RequestMapping(value = "/queryMenuByCondition", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	@Auth(verifyUrl=false)
	public void queryMenuByCondition(HttpServletRequest request,HttpServletResponse response){
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if(null!=parameter && !"".equals(parameter)){
				String menus=this.menuService.queryMenuByCondition(parameter);
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setJsonArrayResultData(menus);
				resultData.setResultMsg("根据条件查询菜单成功");
			    LOGGER.info("根据条件查询菜单成功！");
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.error("请求参数错误！");
			}
			
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件查询菜单失败！");
			e.printStackTrace();
			LOGGER.error("根据条件查询菜单失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转单个数据字典选择页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preSingleDataDicItemSelect", method = RequestMethod.GET)
	@Auth(verifyUrl=false)
	public String preSingleDataDicItemSelect(HttpServletRequest request)throws Exception{
		// 默认回调函数处理
		this.defaultCallBackFunc(request, "dataDicItemSelectCallBackFunc");
		String dicTypeCode = String.valueOf(request.getParameter("dicTypeCode"));
		String status= String.valueOf(request.getParameter("status"));
		if (null != dicTypeCode && !"".equals(dicTypeCode) && !"null".equals(dicTypeCode) 
		&& null != status && !"".equals(status) && !"null".equals(status)) {
			request.setAttribute("isCheckLeaf",request.getParameter("isCheckLeaf"));
			request.setAttribute("dicTypeCode", dicTypeCode);
			request.setAttribute("status", status);
		    LOGGER.info("跳转单个数据字典选择页面成功！");
		    return "forward:/pages/manage/system/publicpage/single-ddi-select.jsp";
		}else {
			throw new Exception("跳转单个数据字典选择页面请求参数错误或失效！");
		}

	}
	
	/**
	 * 根据条件查询数据字典明细
	 * @param orgCode
	 * @param orgName
	 * @param orgSortName
	 * @return
	 */
	@RequestMapping(value = "/queryDataDicItemByCondition", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	@Auth(verifyUrl=false)
	public void queryDataDicItemByCondition(HttpServletRequest request,HttpServletResponse response){
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if(null!=parameter && !"".equals(parameter)){
				String dataDicItems = this.dataDicService.queryDataDicItemByCondition(parameter);
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setJsonArrayResultData(dataDicItems);
				resultData.setResultMsg("根据条件查询数据字典明细成功");
			    LOGGER.info("根据条件查询数据字典明细成功！");
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.error("请求参数错误！");
			}
			
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件查询数据字典明细失败！");
			e.printStackTrace();
			LOGGER.error("根据条件查询数据字典明细失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	
	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public DeptService getDeptService() {
		return deptService;
	}

	@Resource
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	@Resource
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@Resource
	public void setDataDicService(DataDicService dataDicService) {
		this.dataDicService = dataDicService;
	}
	
	
	
	
}
