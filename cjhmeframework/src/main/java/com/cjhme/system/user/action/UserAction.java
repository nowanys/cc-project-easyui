package com.cjhme.system.user.action;

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
import com.cjhme.system.dept.service.DeptService;
import com.cjhme.system.fileupload.bean.FileDataBean;
import com.cjhme.system.fileupload.service.FileDataService;
import com.cjhme.system.main.action.SysBaseAction;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.constant.DataDicConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.DigestUtil;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.role.service.RoleService;
import com.cjhme.system.user.bean.UserBean;
import com.cjhme.system.user.bean.UserRoleMappingBean;
import com.cjhme.system.user.service.UserService;

/**
 * 
 * <p>
 * Title: UserAction.java
 * </p>
 * Description: 用户
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 28, 2014 7:29:14 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(UserAction.class);

	private UserService userService;

	private RoleService roleService;
	
	private DeptService deptService;
	
	private FileDataService fileDataService;
	
	private BusniessButtonService busniessButtonService;

	/**
	 * 跳转用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preUserManage", method = RequestMethod.GET)
	public String preUserManage(HttpSession session,HttpServletRequest request) throws Exception{
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if(null!=userBean && !"".equals(userBean)){
			RoleBean role = userBean.getCurrentRole();
			if(null!=role && !"".equals(role)){
				this.bindDataDicParam(request,new String[]{DataDicConstants.QUERY_STATUS_TYPE,DataDicConstants.STATUS_TYPE});
				
				//查询按钮
				Map<String,Object> parameter=new HashMap<String,Object>();
				parameter.put("busniessMark", BusniessMarkConstants.USER_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList=this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);
				LOGGER.info("跳转用户管理页面成功！");
				return "forward:/pages/manage/system/user/user-manage.jsp";
			}else{
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转用户管理页面请求参数错误或失效！");
		}
	}

	/**
	 * 根据条件分页查询用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryUserByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryUserByConditionPaging(HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			DataPaging<Object> result = this.userService.queryUserByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询用户失败,错误——>"+e);
		}

		LOGGER.info("根据条件分页查询用户成功！");
		return map;

	}

	/**
	 * 跳转用户添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preUserAdd", method = RequestMethod.GET)
	public String preUserAdd(HttpServletRequest request) {
		//数据字典
		this.bindDataDicParam(request,new String[]{DataDicConstants.SEX_TYPE,DataDicConstants.EMAIL_SWITCH_TYPE,DataDicConstants.SMS_SWITCH_TYPE,DataDicConstants.STATUS_TYPE});
		LOGGER.info("跳转用户添加页面成功！");
		return "forward:/pages/manage/system/user/user-add.jsp";
	}

	/**
	 * 添加用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveUser(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.userService.queryUserIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或用户名已存在！");
				LOGGER.info("编号或用户名已存在！");
			}else{
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				parameter.put("password", DigestUtil.md5(CommonConstants.DEFAULT_PWD));
				int result= this.userService.saveUser(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加用户成功！");
					LOGGER.info("添加用户成功！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加用户未成功！");
					LOGGER.info("添加用户未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加用户失败！");
			e.printStackTrace();
			LOGGER.error("添加用户失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转用户查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preUserView", method = RequestMethod.GET)
	public String preUserView(HttpServletRequest request)throws Exception{
		String userId = String.valueOf(request.getParameter("userId"));
		if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("userId", userId);
			UserBean userBean = this.userService.queryUserById(parameter);
			
			//查询用户头像
			parameter.put("busniessId", userId);
			parameter.put("busniessMark", BusniessMarkConstants.USER_HEAD_IMG);
			FileDataBean fileDataBean=this.fileDataService.queryOneFileDataByBMarkAndBId(parameter);
			request.setAttribute("userBean", userBean);
			request.setAttribute("fileDataBean", fileDataBean);
			
			//数据字典
			this.bindDataDicParam(request,new String[]{DataDicConstants.SEX_TYPE,DataDicConstants.EMAIL_SWITCH_TYPE,DataDicConstants.SMS_SWITCH_TYPE,DataDicConstants.STATUS_TYPE});
			
			LOGGER.info("跳转用户查看页面成功！");
			return "forward:/pages/manage/system/user/user-view.jsp";
		} else {
			throw new Exception("跳转用户查看页面请求参数错误或失效！");
		}

	}

	/**
	 * 跳转用户编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preUserEdit", method = RequestMethod.GET)
	public String preUserEdit(HttpServletRequest request)throws Exception{
		String userId = String.valueOf(request.getParameter("userId"));
		if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("userId", userId);
			UserBean userBean = this.userService.queryUserById(parameter);
			
			//查询用户头像
			parameter.put("busniessId", userId);
			parameter.put("busniessMark", BusniessMarkConstants.USER_HEAD_IMG);
			FileDataBean fileDataBean=this.fileDataService.queryOneFileDataByBMarkAndBId(parameter);
			request.setAttribute("userBean", userBean);
			request.setAttribute("fileDataBean", fileDataBean);
			
			//数据字典
			this.bindDataDicParam(request,new String[]{DataDicConstants.SEX_TYPE,DataDicConstants.EMAIL_SWITCH_TYPE,DataDicConstants.SMS_SWITCH_TYPE,DataDicConstants.STATUS_TYPE});
			
			LOGGER.info("跳转用户编辑页面成功！");
			return "forward:/pages/manage/system/user/user-edit.jsp";
		} else {
			throw new Exception("跳转用户编辑页面请求参数错误或失效！");
		}

	}

	/**
	 * 根据id修改用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateUserById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateUserById(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.userService.queryUserIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或用户名已存在！");
				LOGGER.info("编号或用户名已存在！");
			}else{
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				LOGGER.info("根据id修改用户成功！");
				int result= this.userService.updateUserById(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改用户！");
					LOGGER.info("根据id修改用户！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改用户未成功！");
					LOGGER.info("根据id修改用户未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改用户失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改用户失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 跳转用户密码编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preUserPasswordEdit", method = RequestMethod.GET)
	public String preUserPasswordEdit(HttpServletRequest request)throws Exception{
		String userId = String.valueOf(request.getParameter("userId"));
		if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
			request.setAttribute("userId", userId);
			LOGGER.info("跳转用户密码编辑页面成功！");
			return "forward:/pages/manage/system/user/user-password-edit.jsp";
		} else {
			throw new Exception("跳转用户密码编辑页面请求参数错误或失效！");
		}

	}

	/**
	 * 根据id修改密码(-1：失败，-2：密码和确认密码为空，-3：密码或确认密码为空,-4:密码与确认密码不一致,1成功)
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateUserPasswordById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateUserPasswordById(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			if (null != parameter && !"".equals(parameter)) {
				String password = String.valueOf(parameter.get("password"));
				String confPassword = String.valueOf(parameter.get("confPassword"));

				if (null != password && !"".equals(password) && null != confPassword && !"".equals(confPassword)) {
					if (password.equals(confPassword)) {
						parameter.put("password", DigestUtil.md5(password));

						UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
						parameter.put("updateUserId", userBean.getUserId());
						parameter.put("updateUser", userBean.getRealName());

						int result= this.userService.updateUserPasswordById(parameter);
						if(result>0){
							resultData.setResultType(CommonConstants.RESULT_SUCCESS);
							resultData.setResultMsg("修改密码成功！");
							LOGGER.info("修改密码成功！");
						}else{
							resultData.setResultType(CommonConstants.RESULT_FAILURE);
							resultData.setResultMsg("修改密码未成功！");
							LOGGER.info("修改密码未成功！");
						}
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("密码与确认密码不一致！");
						LOGGER.info("密码与确认密码不一致！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("密码或确认密码为空！");
					LOGGER.info("密码或确认密码为空！");
				}

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("密码和确认密码为空！");
				LOGGER.info("密码和确认密码为空！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改密码失败！");
			e.printStackTrace();
			LOGGER.error("修改密码失败,错误——>"+e);
		}

		resultData.printJsonData(response);
	}
	
	/**
	 * 根据ids重置用户密码
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/resetUserPwdByIds", method = RequestMethod.POST)
	public void resetUserPwdByIds(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
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
				parameter.put("password", DigestUtil.md5(CommonConstants.DEFAULT_PWD));
				int result= this.userService.updateUserPwdByIds(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("重置用户密码成功！");
					LOGGER.info("根据ids重置用户密码成功！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("重置用户密码未成功！");
					LOGGER.info("根据ids重置用户密码未成功！");
				}
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("重置用户密码失败！");
			e.printStackTrace();
			LOGGER.error("根据ids重置用户密码失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除用户
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteUserByIds", method = RequestMethod.POST)
	public void deleteUserByIds(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
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
				int result= this.userService.deleteUserByIds(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除用户成功！");
					LOGGER.info("根据ids删除用户成功！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除用户未成功！");
					LOGGER.info("根据ids删除用户未成功！");
				}
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除用户失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除用户失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 跳转用户用户分配页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preUserRoleDistribute", method = RequestMethod.GET)
	public String preUserRoleDistribute(HttpServletRequest request)throws Exception{
		String userId = String.valueOf(request.getParameter("userId"));
		if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
			List<RoleBean> roleData=this.roleService.queryAllRoleList();
			
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("userId", userId);
			List<UserRoleMappingBean> userRoleData=this.userService.queryUserRoleMappingById(parameter);
			
			request.setAttribute("userId",userId );
			request.setAttribute("roleData", roleData);
			request.setAttribute("userRoleData", userRoleData);
			LOGGER.info("跳转用户用户分配页面成功！");
			return "forward:/pages/manage/system/user/user-role-distribute.jsp";
		}else{
			throw new Exception("跳转用户用户分配页面请求参数错误或失效！");
		}
	}


	/**
	 * 保存用户角色映射
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUserRoleMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveUserRoleMapping(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> roleIds = (List<String>) parameter.get("roleIds");
				String userId = String.valueOf(parameter.get("userId"));

				if (null != userId && !"".equals(userId) && null != roleIds) {
					int result= this.userService.saveUserRoleMapping(parameter);
					if(result>0){
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加用户角色成功！");
						LOGGER.info("保存用户角色映射成功！");
					}else{
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加用户角色未成功！");
						LOGGER.info("保存用户角色映射未成功！");
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
			resultData.setResultMsg("添加用户角色失败！");
			e.printStackTrace();
			LOGGER.error("添加用户角色失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 跳转用户部门分配页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preUserDeptDistribute", method = RequestMethod.GET)
	public String preUserDeptDistribute(HttpServletRequest request)throws Exception{
		String userId = String.valueOf(request.getParameter("userId"));
		if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
			JSONArray deptTreeData = JSONArray.fromObject(this.deptService.queryDeptList());
			
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("userId", userId);
			JSONArray userDeptData = JSONArray.fromObject(this.userService.queryUserDeptMappingById(parameter));
			
			request.setAttribute("userId", userId);
			request.setAttribute("deptTreeData", deptTreeData);
			request.setAttribute("userDeptData", userDeptData);
			LOGGER.info("跳转用户部门分配页面成功！");
			return "forward:/pages/manage/system/user/user-dept-distribute.jsp";
		}else{
			throw new Exception("跳转用户部门分配页面请求参数错误或失效！");
		}
	}
	
	
	/**
	 * 保存用户部门映射
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUserDeptMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveUserDeptMapping(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> deptIds = (List<String>) parameter.get("deptIds");
				String userId = String.valueOf(parameter.get("userId"));

				if (null != userId && !"".equals(userId) && null != deptIds) {
					int result= this.userService.saveUserDeptMapping(parameter);
					if(result>0){
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加用户部门成功！");
						LOGGER.info("保存用户部门映射成功！");
					}else{
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加用户部门未成功！");
						LOGGER.info("添加用户部门映射未成功！");
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
			resultData.setResultMsg("添加用户部门失败！");
			e.printStackTrace();
			LOGGER.error("保存用户部门映射失败,错误——>"+e);
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

	public RoleService getRoleService() {
		return roleService;
	}

	@Resource
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	public DeptService getDeptService() {
		return deptService;
	}

	@Resource
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public FileDataService getFileDataService() {
		return fileDataService;
	}

	@Resource
	public void setFileDataService(FileDataService fileDataService) {
		this.fileDataService = fileDataService;
	}

	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}
	
	
	

}
