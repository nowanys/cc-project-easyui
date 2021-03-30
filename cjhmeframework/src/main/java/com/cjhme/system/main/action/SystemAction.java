package com.cjhme.system.main.action;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.cjhme.system.comfunc.bean.ComFuncBean;
import com.cjhme.system.comfunc.service.ComFuncService;
import com.cjhme.system.dept.bean.DeptBean;
import com.cjhme.system.fileupload.bean.FileDataBean;
import com.cjhme.system.fileupload.service.FileDataService;
import com.cjhme.system.loginlog.bean.LoginLogBean;
import com.cjhme.system.loginlog.service.LoginLogService;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.constant.DataDicConstants;
import com.cjhme.system.main.interceptor.springmvc.annotation.Auth;
import com.cjhme.system.main.util.DigestUtil;
import com.cjhme.system.main.util.IPUtil;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.menu.bean.MenuBean;
import com.cjhme.system.menu.service.MenuService;
import com.cjhme.system.notice.bean.NoticeBean;
import com.cjhme.system.notice.service.NoticeService;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.task.service.TaskService;
import com.cjhme.system.user.bean.UserBean;
import com.cjhme.system.user.service.UserService;

/**
 * 
 * <p>
 * Title: SystemAction.java
 * </p>
 * Description: 系统action
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Jun 28, 2014 3:37:16 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/system")
public class SystemAction extends SysBaseAction{

	Log LOGGER = LogFactory.getLog(SystemAction.class);

	private MenuService menuService;

	private UserService userService;

	private TaskService taskService;

	private LoginLogService loginLogService;

	private FileDataService fileDataService;
	
	private ComFuncService comFuncService;
	
	private NoticeService noticeService;

	
	
	/**
	 * 跳转权限未分配角色页面
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/preUndistributeRole", method = RequestMethod.GET)
	public String preUndistributeRole(HttpServletRequest request) {
		LOGGER.info("跳转权限未分配角色页面！");
		return "forward:/pages/errorPage/undistribute-role.jsp";
	}
	
	/**
	 * 跳转权限未验证通过页面
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/preNoAutority", method = RequestMethod.GET)
	public String preNoAutority(HttpServletRequest request) {
		LOGGER.info("跳转权限未验证通过页面！");
		return "forward:/pages/errorPage/no-autority.jsp";
	}
	
	/**
	 * 跳转未登录页面
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/preNotLogin", method = RequestMethod.GET)
	public String preNotLogin(HttpServletRequest request) {
		LOGGER.info("跳转未登录页面！");
		request.setAttribute("msg", "用户未登录或登录超时！");
		return "forward:/pages/manage/system/main/login.jsp";
	}
	
	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/exitLogin", method = RequestMethod.GET)
	public String exitLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 从session中清除用户
			if (session.getAttribute(CommonConstants.MANAGE_USER_SESSION) != null && !"".equals(session.getAttribute(CommonConstants.MANAGE_USER_SESSION))) {
				session.removeAttribute(CommonConstants.MANAGE_USER_SESSION);
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);
				LOGGER.info("退出登录成功！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("退出登录失败！");
		}
		return "forward:/pages/manage/system/main/login.jsp";
	}
	
	/**
	 * 跳转登录页面
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/preLogin", method = RequestMethod.GET)
	public String preLogin() {
		LOGGER.info("跳转登录页面成功！");
		return "forward:/pages/manage/system/main/login.jsp";
	}

	/**
	 * 用户登录(-1：系统错误，-2：用户名和密码为空，-3：用户名或密码为空，-4：用户名或密码有误，-5：用户被禁用，1登录成功)
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void login(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			if (null != parameter && !"".equals(parameter)) {
				String userName = String.valueOf(parameter.get("userName"));
				String password = String.valueOf(parameter.get("password"));

				if (null != userName && !"".equals(userName) && null != password && !"".equals(password)) {
					parameter.put("password", DigestUtil.md5(password));
					UserBean userBean = this.userService.login(parameter);

					if (null != userBean && !"".equals(userBean)) {
						String status = userBean.getStatus();
						if ("1".equals(status)) {

							// 用户当前部门默认设置为第一个部门
							List<DeptBean> depts = userBean.getDepts();
							if (null != depts && !"".equals(depts) && depts.size() > 0) {
								userBean.setCurrentDept(depts.get(0));
							}

							// 用户当前角色默认设置为第一个角色
							List<RoleBean> reoles = userBean.getRoles();
							if (null != reoles && !"".equals(reoles) && reoles.size() > 0) {
								userBean.setCurrentRole(reoles.get(0));
							}

							// 添加session
							session.setAttribute(CommonConstants.MANAGE_USER_SESSION, userBean);

							// 添加登录日志
							Map<String, Object> params = new HashMap<String, Object>();
							String loginIP = IPUtil.getIpAddr(request);
							params.put("createUserId", userBean.getUserId());
							params.put("createUser", userBean.getRealName());
							params.put("loginIP", loginIP);
							this.loginLogService.saveLoginLog(params);

							resultData.setResultType(CommonConstants.RESULT_SUCCESS);
							resultData.setResultMsg("登录成功！");
							LOGGER.info("登录成功！");
						} else {
							resultData.setResultType(CommonConstants.RESULT_FAILURE);
							resultData.setResultMsg("用户被禁用！");
							LOGGER.info("用户被禁用!");
						}

					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("用户名或密码有误！");
						LOGGER.info("用户名或密码有误!");
					}

				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("用户名或密码为空！");
					LOGGER.info("用户名或密码为空!");
				}

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("用户名和密码为空！");
				LOGGER.info("用户名和密码为空!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("登录失败！");
			LOGGER.error("登录失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 跳转主页面
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyUrl=false)
	@RequestMapping(value = "/preIndex", method = RequestMethod.GET)
	public String preIndex(HttpSession session, HttpServletRequest request) {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean) && !"null".equals(userBean)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("userId", userBean.getUserId());
			UserBean user = this.userService.queryUserById(parameter);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			request.setAttribute("sysCurrentUser", user.getRealName());
			request.setAttribute("sysCurrentTime", dateFormat.format(new Date()));
			request.setAttribute("currentUserDepts", userBean.getDepts());
			request.setAttribute("currentUserDept", userBean.getCurrentDept());
			request.setAttribute("currentUserRoles", userBean.getRoles());
			request.setAttribute("currentUserRole", userBean.getCurrentRole());
		}
		LOGGER.info("跳转主页面成功！");
		return "forward:/pages/manage/system/main/index.jsp";
	}

	/**
	 * 跳转主页
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@Auth(verifyUrl=false)
	@RequestMapping(value = "/preHome", method = RequestMethod.GET)
	public String preHome(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean) && !"null".equals(userBean)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			
			//待办
			parameter.put("processUserId", userBean.getUserId());
			Map<String, Object> task = this.taskService.queryTaskByUserId(parameter);

			//登录日志
			parameter.put("userId", userBean.getUserId());
			LoginLogBean userLoginLog = this.loginLogService.queryCurrentUserLoginLogInfo(parameter);
			
			//用户信息
			UserBean user = this.userService.queryUserById(parameter);

			//用户头像
			parameter.put("busniessMark", BusniessMarkConstants.USER_HEAD_IMG);
			parameter.put("busniessId", userBean.getUserId());
			FileDataBean fileDataBean = this.fileDataService.queryOneFileDataByBMarkAndBId(parameter);
			
			//常用功能
			parameter.put("roleId", userBean.getCurrentRole().getRoleId());
			List<ComFuncBean>  comFuncs = this.comFuncService.queryUserComFuncList(parameter);
			
			//公告
			List<NoticeBean> notices=this.noticeService.queryNoticeByUserId(parameter);
			
			request.setAttribute("task", task);//待办
			request.setAttribute("userLoginLog", userLoginLog);//登录日志
			request.setAttribute("user", user);//用户信息
			request.setAttribute("fileDataBean", fileDataBean);//用户头像
			request.setAttribute("comFuncs", comFuncs);//常用功能
			request.setAttribute("notices", notices);//公告

			LOGGER.info("跳转主页成功！");
			return "forward:/pages/manage/system/main/home.jsp";
		} else {
			throw new Exception("跳转主页请求参数错误或失效！");
		}
	}

	/**
	 * 初始化用户菜单
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@Auth(verifyUrl=false)
	@RequestMapping(value = "/queryUserMenuByRoleId", method = { RequestMethod.GET, RequestMethod.POST })
	public void queryUserMenuByRoleId(HttpSession session, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean) {
				RoleBean currentRole = userBean.getCurrentRole();
				if (null != currentRole && !"".equals(currentRole) && !"null".equals(currentRole)) {
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("roleId", currentRole.getRoleId());
					String menus = menuService.queryUserMenuByRoleId(parameter);
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setJsonArrayResultData(menus);
					resultData.setResultMsg("初始化用户菜单成功");
					LOGGER.info("初始化用户菜单成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("用户未分配角色！");
					LOGGER.info("用户未分配角色，初始化用户菜单失败！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("登录超时，初始化用户菜单失败！");
				LOGGER.info("登录超时，初始化用户菜单失败！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化用户菜单失败！");
			LOGGER.error("初始化用户菜单失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 初始化用户手风琴菜单
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@Auth(verifyUrl=false)
	@RequestMapping(value = "/queryUserAccordionMenuByRoleId", method = { RequestMethod.GET, RequestMethod.POST })
	public void queryUserAccordionMenuByRoleId(HttpSession session, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean) {
				RoleBean currentRole = userBean.getCurrentRole();
				if (null != currentRole && !"".equals(currentRole) && !"null".equals(currentRole)) {
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("roleId", currentRole.getRoleId());
					parameter.put("parentMenuId", "1");
					List<MenuBean> menus = menuService.queryUserAccordionMenuByRoleId(parameter);
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultData(menus);
					resultData.setResultMsg("初始化用户手网琴菜单成功");
					LOGGER.info("初始化用户手网琴菜单成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("用户未分配角色！");
					LOGGER.info("用户未分配角色，初始化用户菜单失败！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("登录超时，初始化用户菜单失败！");
				LOGGER.info("登录超时，初始化用户菜单失败！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化用户手网琴菜单失败！");
			LOGGER.error("初始化用户手网琴菜单,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 初始化用户手风琴菜单树
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@Auth(verifyUrl=false)
	@RequestMapping(value = "/queryUserAccordionTreeMenuById", method = { RequestMethod.GET, RequestMethod.POST })
	public void queryUserAccordionTreeMenuById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String parentMenuId = String.valueOf(request.getParameter("parentMenuId"));
			if (null != parentMenuId && !"".equals(parentMenuId)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				if (null != userBean) {
					RoleBean currentRole = userBean.getCurrentRole();
					if (null != currentRole && !"".equals(currentRole) && !"null".equals(currentRole)) {
						Map<String, Object> parameter = new HashMap<String, Object>();
						parameter.put("roleId", currentRole.getRoleId());
						parameter.put("parentMenuId", parentMenuId);
						String menus = menuService.queryUserAccordionTreeMenuById(parameter);
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setJsonArrayResultData(menus);
						resultData.setResultMsg("初始化用户手网琴菜单树成功");
						LOGGER.info("初始化用户手网琴菜单树成功！");
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("用户未分配角色！");
						LOGGER.info("用户未分配角色，初始化用户菜单失败！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("登录超时，初始化用户菜单失败！");
					LOGGER.info("登录超时，初始化用户菜单失败！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化用户手网琴菜单树失败！");
			LOGGER.error("初始化用户手网琴菜单树失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 跳转当前用户密码编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preCurrentUserPwdEdit", method = RequestMethod.GET)
	public String preCurrentUserPwdEdit(HttpServletRequest request, HttpSession session) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		String userId = userBean.getUserId();
		if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
			request.setAttribute("userId", userId);
			LOGGER.info("跳转当前用户密码编辑页面成功！");
			return "forward:/pages/manage/system/user/user-password-edit.jsp";
		} else {
			throw new Exception("跳转当前用户密码编辑页面请求参数错误或失效！");
		}
	}

	/**
	 * 跳转当前用户资料编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preCurrentUserInfoEdit", method = RequestMethod.GET)
	public String preCurrentUserInfoEdit(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean) && !"null".equals(userBean)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("userId", userBean.getUserId());
			UserBean user = this.userService.queryUserById(parameter);

			parameter.put("busniessId", userBean.getUserId());
			parameter.put("busniessMark", BusniessMarkConstants.USER_HEAD_IMG);
			FileDataBean fileDataBean = this.fileDataService.queryOneFileDataByBMarkAndBId(parameter);

			request.setAttribute("userBean", user);
			request.setAttribute("fileDataBean", fileDataBean);

			LOGGER.info("跳转当前用户资料编辑页面成功！");
			return "forward:/pages/manage/system/user/user-info-edit.jsp";
		} else {
			throw new Exception("跳转当前用户资料编辑页面请求参数错误或失效！");
		}

	}

	/**
	 * 切换当前用户部门
	 * 
	 * @param session
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/changeCurrentUserDept", method = RequestMethod.POST)
	public void changeCurrentUserDept(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String deptId = String.valueOf(request.getParameter("deptId"));
			if (null != deptId && !"".equals(deptId) && !"null".equals(deptId)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				if (null != userBean && !"".equals(userBean) && !"null".equals(userBean)) {
					List<DeptBean> depts = userBean.getDepts();
					if (null != depts && !"".equals(depts) && depts.size() > 0) {
						for (DeptBean dept : depts) {
							if (dept.getDeptId().equals(deptId)) {
								userBean.setCurrentDept(dept);
								resultData.setResultType(CommonConstants.RESULT_SUCCESS);
								resultData.setResultMsg("切换部门成功！");
								LOGGER.info("切换部门成功！");
								break;
							}
						}
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("用户未分配部门！");
						LOGGER.error("用户未分配部门！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("用户登录超时！");
					LOGGER.error("用户登录超时！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.error("请求参数错误！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("切换部门失败！");
			e.printStackTrace();
			LOGGER.error("切换当前用户部门失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 切换当前用户角色
	 * 
	 * @param session
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/changeCurrentUserRole", method = RequestMethod.POST)
	public void changeCurrentUserRole(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String roleId = String.valueOf(request.getParameter("roleId"));
			if (null != roleId && !"".equals(roleId) && !"null".equals(roleId)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				if (null != userBean && !"".equals(userBean) && !"null".equals(userBean)) {
					List<RoleBean> roles = userBean.getRoles();
					if (null != roles && !"".equals(roles) && roles.size() > 0) {
						for (RoleBean role : roles) {
							if (role.getRoleId().equals(roleId)) {
								userBean.setCurrentRole(role);
								resultData.setResultType(CommonConstants.RESULT_SUCCESS);
								resultData.setResultMsg("切换角色成功！");
								LOGGER.info("切换角色成功！");
								break;
							}
						}
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("用户未分配角色！");
						LOGGER.error("户未分配角色！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("用户登录超时！");
					LOGGER.error("用户登录超时！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.error("请求参数错误！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("切换角色失败！");
			e.printStackTrace();
			LOGGER.error("切换当前用户角色失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转个性化页面
	 * @return
	 */
	@Auth(verifyUrl=false)
	@RequestMapping(value = "/preIndividuation", method = RequestMethod.GET)
	public String preIndividuation(HttpServletRequest request){
		//数据字典
		this.bindDataDicParam(request,new String[]{DataDicConstants.INDIVIDUATION_MENU_TYPE,DataDicConstants.INDIVIDUATION_THEME_TYPE});
		LOGGER.info("跳转个性化页面成功！");
		return "forward:/pages/manage/system/main/individuation.jsp";
	}
	
	/**
	 * 跳转常用功能配置页面
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyUrl=false)
	@RequestMapping(value = "/preComFuncConfig", method = RequestMethod.GET)
	public String preComFuncConfig(HttpServletRequest request, HttpSession session) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		String userId = userBean.getUserId();
		if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
			RoleBean currentRole = userBean.getCurrentRole();
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("roleId", currentRole.getRoleId());
			
			String menus = menuService.queryUserMenuByRoleId(parameter);
			JSONArray menuJson = JSONArray.fromObject(menus);
			
			parameter.put("userId", userBean.getUserId());
			List<ComFuncBean> comFuncs=comFuncService.queryUserComFuncList(parameter);
			JSONArray comFuncJson = JSONArray.fromObject(comFuncs);
			
			request.setAttribute("menus", menuJson);
			request.setAttribute("comFuncs", comFuncJson);
			
			LOGGER.info("跳转常用功能配置页面成功！");
			return "forward:/pages/manage/system/comfunc/com-func-config.jsp";
		} else {
			throw new Exception("跳转常用功能配置页面请求参数错误或失效！");
		}
	}
	
	/**
	 * 添加常用功能配置
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyUrl=false)
	@RequestMapping(value = "/saveComfuncConfig", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveComfuncConfig(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> menuIds = (List<String>) parameter.get("menuIds");
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				if (null != menuIds && null!=userBean) {
					parameter.put("userId", userBean.getUserId());
					parameter.put("roleId", userBean.getCurrentRole().getRoleId());
					int result= this.comFuncService.saveComfunc(parameter);
					if(result>0){
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加常用功能成功！");
						LOGGER.info("添加常用功能配置成功！");
					}else{
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加常用功能未成功！");
						LOGGER.info("添加常用功能配置未成功！");
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
			resultData.setResultMsg("添加常用功能失败！");
			e.printStackTrace();
			LOGGER.error("添加常用功能配置失败,错误——>"+e);
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

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	@Resource
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public LoginLogService getLoginLogService() {
		return loginLogService;
	}

	@Resource
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public FileDataService getFileDataService() {
		return fileDataService;
	}

	@Resource
	public void setFileDataService(FileDataService fileDataService) {
		this.fileDataService = fileDataService;
	}

	public ComFuncService getComFuncService() {
		return comFuncService;
	}

	@Resource
	public void setComFuncService(ComFuncService comFuncService) {
		this.comFuncService = comFuncService;
	}

	public NoticeService getNoticeService() {
		return noticeService;
	}

	@Resource
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	
	
	

}
