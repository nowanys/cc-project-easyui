package com.cjhme.system.role.action;

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

import com.cjhme.system.access.bean.AccessUrlBean;
import com.cjhme.system.access.service.AccessUrlService;
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
import com.cjhme.system.role.bean.RoleAccessUrlMappingBean;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.role.bean.RoleButtonMappingBean;
import com.cjhme.system.role.service.RoleService;
import com.cjhme.system.user.bean.UserBean;
import com.cjhme.system.user.service.UserService;

/**
 * 
 * <p>
 * Title: RoleAction.java
 * </p>
 * Description: 角色
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Oct 16, 2014 1:42:25 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/role")
public class RoleAction extends SysBaseAction{

	Log LOGGER = LogFactory.getLog(RoleAction.class);

	private RoleService roleService;
	
	private UserService userService;
	
	private MenuService menuService;
	
	private ButtonService buttonservice;
	
	private BusniessButtonService busniessButtonService;
	
	private AccessUrlService accessUrlService;

	/**
	 * 跳转角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preRoleManage", method = RequestMethod.GET)
	public String preRoleManage(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean)) {
			RoleBean role = userBean.getCurrentRole();
			if (null != role && !"".equals(role)) {
				//查询按钮
				Map<String,Object> parameter=new HashMap<String,Object>();
				parameter.put("busniessMark", BusniessMarkConstants.ROLE_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList=this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);
				
				//数据字典
				this.bindDataDicParam(request,new String[]{DataDicConstants.QUERY_STATUS_TYPE,DataDicConstants.QUERY_IS_SUPER_TYPE,DataDicConstants.STATUS_TYPE,DataDicConstants.IS_SUPER_TYPE});
				LOGGER.info("跳转角色管理页面成功！");
				return "forward:/pages/manage/system/role/role-manage.jsp";
			} else {
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转角色管理页面请求参数错误或失效！");
		}
				
	}

	/**
	 * 根据条件分页查询角色
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryRoleByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryRoleByConditionPaging(HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			DataPaging<Object> result = this.roleService.queryRoleByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询角色失败,错误——>"+e);
		}

		LOGGER.info("根据条件分页查询角色成功！");
		return map;

	}

	/**
	 * 跳转角色添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preRoleAdd", method = RequestMethod.GET)
	public String preRoleAdd(HttpServletRequest request) {
		//数据字典
		this.bindDataDicParam(request,new String[]{DataDicConstants.IS_SUPER_TYPE,DataDicConstants.EMAIL_SWITCH_TYPE,DataDicConstants.SMS_SWITCH_TYPE,DataDicConstants.STATUS_TYPE});
		LOGGER.info("跳转角色添加页面成功！");
		return "forward:/pages/manage/system/role/role-add.jsp";
	}

	/**
	 * 添加角色
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveRole(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.roleService.queryRoleIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或角色名称已存在！");
				LOGGER.info("编号或角色名称已存在！");
			}else{
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				int result = this.roleService.saveRole(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加角色成功！");
					LOGGER.info("添加角色成功！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加角色未成功！");
					LOGGER.info("添加角色未成功！");
				}
			}

			
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加角色失败！");
			e.printStackTrace();
			LOGGER.error("添加角色失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转角色查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preRoleView", method = RequestMethod.GET)
	public String preRoleView(HttpServletRequest request)throws Exception {
		String roleId = String.valueOf(request.getParameter("roleId"));
		if (null != roleId && !"".equals(roleId) && !"null".equals(roleId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("roleId", roleId);
			RoleBean roleBean = this.roleService.queryRoleById(parameter);
			request.setAttribute("roleBean", roleBean);
			//数据字典
			this.bindDataDicParam(request,new String[]{DataDicConstants.IS_SUPER_TYPE,DataDicConstants.EMAIL_SWITCH_TYPE,DataDicConstants.SMS_SWITCH_TYPE,DataDicConstants.STATUS_TYPE});
			LOGGER.info("跳转角色查看页面成功！");
			return "forward:/pages/manage/system/role/role-view.jsp";
		}else{
			throw new Exception("跳转角色查看页面请求参数错误或失效！");
		}
	}

	/**
	 * 跳转角色编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preRoleEdit", method = RequestMethod.GET)
	public String preRoleEdit(HttpServletRequest request)throws Exception {
		String roleId = String.valueOf(request.getParameter("roleId"));
		if (null != roleId && !"".equals(roleId) && !"null".equals(roleId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("roleId", roleId);
			RoleBean roleBean = this.roleService.queryRoleById(parameter);
			request.setAttribute("roleBean", roleBean);
			//数据字典
			this.bindDataDicParam(request,new String[]{DataDicConstants.IS_SUPER_TYPE,DataDicConstants.EMAIL_SWITCH_TYPE,DataDicConstants.SMS_SWITCH_TYPE,DataDicConstants.STATUS_TYPE});
			LOGGER.info("跳转角色编辑页面成功！");
			return "forward:/pages/manage/system/role/role-edit.jsp";
		}else{
			throw new Exception("跳转角色编辑页面请求参数错误或失效！");
		}
	}

	/**
	 * 根据id修改角色
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateRoleById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateRoleById(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.roleService.queryRoleIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或角色名称已存在！");
				LOGGER.info("编号或角色名称已存在！");
			}else{
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.roleService.updateRoleById(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改角色！");
					LOGGER.info("根据id修改角色！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改角色未成功！");
					LOGGER.info("根据id修改角色未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改角色失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改角色失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除角色
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteRoleByIds", method = RequestMethod.POST)
	public void deleteRoleByIds(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
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
				int result= this.roleService.deleteRoleByIds(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除角色成功！");
					LOGGER.info("根据ids删除角色成功！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除角色未成功！");
					LOGGER.info("根据ids删除角色未成功！");
				}
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除角色失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除角色失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转角色用户查询页面
	 * @return
	 */
	@RequestMapping(value = "/preRoleUsersQuery", method = RequestMethod.GET)
	public String preRoleUsersQuery(HttpServletRequest request)throws Exception{
		String roleId = String.valueOf(request.getParameter("roleId"));
		if(null!=roleId && !"".equals(roleId) && !"null".equals(roleId)){
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("roleId", roleId);
			JSONArray data = JSONArray.fromObject(this.userService.queryUserByRoleId(parameter));
			request.setAttribute("data", data);
			LOGGER.info("跳转角色用户查询页面成功！");
			return "forward:/pages/manage/system/role/role-users.jsp";
		}else{
			throw new Exception("跳转角色用户查询页面请求参数错误或失效！");
		}
		
	}
	
	/**
	 * 跳转角色菜单分配页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preRoleMenuDistribute", method = RequestMethod.GET)
	public String preRoleMenuDistribute(HttpServletRequest request)throws Exception {
		String roleId = String.valueOf(request.getParameter("roleId"));
		if (null != roleId && !"".equals(roleId) && !"null".equals(roleId)) {
			JSONArray menuTreeData = JSONArray.fromObject(this.menuService.queryMenuList());
			
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("roleId", roleId);
			JSONArray roleMenuData = JSONArray.fromObject(this.roleService.queryRoleMenuMappingById(parameter));
			
			request.setAttribute("roleId", roleId);
			request.setAttribute("menuTreeData", menuTreeData);
			request.setAttribute("roleMenuData", roleMenuData);
			LOGGER.info("跳转角色菜单分配页面成功！");
			return "forward:/pages/manage/system/role/role-menu-distribute.jsp";
		}else{
			throw new Exception("跳转角色菜单分配页面请求参数错误或失效！");
		}
	}
	
	
	/**
	 * 添加角色菜单映射
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRoleMenuMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveRoleMenuMapping(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> menuIds = (List<String>) parameter.get("menuIds");
				String roleId = String.valueOf(parameter.get("roleId"));

				if (null != roleId && !"".equals(roleId) && null != menuIds) {
					int result= this.roleService.saveRoleMenuMapping(parameter);
					if(result>0){
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加角色菜单成功！");
						LOGGER.info("添加角色菜单映射成功！");
					}else{
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加角色菜单未成功！");
						LOGGER.info("添加角色菜单映射未成功！");
					}
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("请求参数错误！");
					LOGGER.info("请求参数错误！");
				}
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加角色菜单失败！");
			e.printStackTrace();
			LOGGER.error("添加角色菜单映射失败,错误——>"+e);
		}

		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转角色按钮分配页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preRoleButtonDistribute", method = RequestMethod.GET)
	public String preRoleButtonDistribute(HttpServletRequest request)throws Exception {
		String roleId = String.valueOf(request.getParameter("roleId"));
		if (null != roleId && !"".equals(roleId) && !"null".equals(roleId)) {
			request.setAttribute("roleId", roleId);
			JSONArray data = JSONArray.fromObject(this.buttonservice.queryAllButtonList());
			request.setAttribute("data", data);
			
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.BUTTON_CATEGORY_TYPE});
		
			LOGGER.info("跳转角色按钮分配页面成功！");
			return "forward:/pages/manage/system/role/role-button-distribute.jsp";
		}else{
			throw new Exception("跳转角色按钮分配页面请求参数错误或失效！");
		}
	}
	
	/**
	 * 根据角色id查询角色按钮映射
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryRoleButtonMappingById", method = RequestMethod.POST)
	public void queryRoleButtonMappingById(HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String roleId = String.valueOf(request.getParameter("roleId"));
			if (null != roleId && !"".equals(roleId) && !"null".equals(roleId)) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("roleId", roleId);
				List<RoleButtonMappingBean>  list= this.roleService.queryRoleButtonMappingById(parameter);
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(list);
				resultData.setResultMsg("查询角色按钮成功！");
				LOGGER.info("根据角色id查询角色按钮映射成功！");
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询角色按钮失败！");
			e.printStackTrace();
			LOGGER.error("根据角色id查询角色按钮映射失败,错误——>"+e);
		}

		resultData.printJsonData(response);
	}
	
	/**
	 * 添加角色按钮映射
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRoleButtonMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveRoleButtonMapping(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> buttonIds = (List<String>) parameter.get("buttonIds");
				String roleId = String.valueOf(parameter.get("roleId"));

				if (null != roleId && !"".equals(roleId) && null != buttonIds) {
					int result= this.roleService.saveRoleButtonMapping(parameter);
					if(result>0){
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加角色按钮成功！");
						LOGGER.info("添加角色按钮映射成功！");
					}else{
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加角色按钮未成功！");
						LOGGER.info("添加角色按钮映射未成功！");
					}
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("请求参数错误！");
					LOGGER.info("请求参数错误！");
				}
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加角色按钮失败！");
			e.printStackTrace();
			LOGGER.error("添加角色按钮映射失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转角色访问地址分配页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preRoleAccessUrlDistribute", method = RequestMethod.GET)
	public String preRoleAccessUrlDistribute(HttpServletRequest request)throws Exception {
		String roleId = String.valueOf(request.getParameter("roleId"));
		if (null != roleId && !"".equals(roleId) && !"null".equals(roleId)) {
			
			List<AccessUrlBean> accessUrlData=this.accessUrlService.queryAllAccessUrlList();

			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("roleId", roleId);
			List<RoleAccessUrlMappingBean>  roleAccessUrlData=this.roleService.queryRoleAccessUrlMappingById(parameter);
			
			request.setAttribute("roleId", roleId);
			request.setAttribute("accessUrlData", accessUrlData);
			request.setAttribute("roleAccessUrlData", roleAccessUrlData);
			
			LOGGER.info("跳转角色访问地址分配页面成功！");
			return "forward:/pages/manage/system/role/role-access-url-distribute.jsp";
		}else{
			throw new Exception("跳转角色访问地址分配页面请求参数错误或失效！");
		}
	}
	
	
	/**
	 * 添加角色访问地址映射
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRoleAccessUrlMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveRoleAccessUrlMapping(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> accessUrlIds = (List<String>) parameter.get("accessUrlIds");
				String roleId = String.valueOf(parameter.get("roleId"));

				if (null != roleId && !"".equals(roleId) && null != accessUrlIds) {
					int result= this.roleService.saveRoleAccessUrlMapping(parameter);
					if(result>0){
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加角色访问地址成功！");
						LOGGER.info("添加角色访问地址映射成功！");
					}else{
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加角色访问地址未成功！");
						LOGGER.info("添加角色访问地址映射未成功！");
					}
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("请求参数错误！");
					LOGGER.info("请求参数错误！");
				}
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加角色访问地址失败！");
			e.printStackTrace();
			LOGGER.error("添加角色访问地址映射失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}



	public RoleService getRoleService() {
		return roleService;
	}

	@Resource
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	@Resource
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public ButtonService getButtonservice() {
		return buttonservice;
	}

	@Resource
	public void setButtonservice(ButtonService buttonservice) {
		this.buttonservice = buttonservice;
	}
	
	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}

	public AccessUrlService getAccessUrlService() {
		return accessUrlService;
	}

	@Resource
	public void setAccessUrlService(AccessUrlService accessUrlService) {
		this.accessUrlService = accessUrlService;
	}
	
	
	

}
