package com.cjhme.system.task.action;

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
import com.cjhme.system.main.action.SysBaseAction;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.constant.DataDicConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.task.service.TaskService;
import com.cjhme.system.user.bean.UserBean;

/**
 * 
 * <p>
 * Title: TaskAction.java
 * </p>
 * Description: 待办
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 28, 2014 7:29:14 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/task")
public class TaskAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(TaskAction.class);

	private TaskService taskService;

	private BusniessButtonService busniessButtonService;

	/**
	 * 跳转待办管理页面
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/preTaskManage", method = RequestMethod.GET)
	public String preTaskManage(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean)) {
			RoleBean role = userBean.getCurrentRole();
			if (null != role && !"".equals(role)) {
				this.bindDataDicParam(request, new String[] {DataDicConstants.TASK_STATUS_TYPE });

				// 查询按钮
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("busniessMark", BusniessMarkConstants.TASK_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList = this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);
				LOGGER.info("跳转待办管理页面成功！");
				return "forward:/pages/manage/system/task/task-manage.jsp";
			} else {
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转待办管理页面请求参数错误或失效！");
		}
	}
	
	/**
	 * 根据条件分页查询待办 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTaskByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryTaskByConditionPaging(HttpSession session,HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			DataPaging<Object> parameter = RequestParamToObjectUtil.requestParamParseToPageMap(request);
			parameter.getParameter().put("createUserId", userBean.getUserId());
			
			DataPaging<Object> result = this.taskService.queryTaskByConditionPaging(parameter);
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询待办失败,错误——>"+e);
		}

		LOGGER.info("根据条件分页查询待办成功！");
		return map;

	}
	
	/**
	 * 根据id修改待办状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateTaskById", method = RequestMethod.POST)
	public void updateTaskById(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String taskId = request.getParameter("taskId");
			if (null != taskId && !"".equals(taskId)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("taskId", taskId);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result= this.taskService.updateTaskById(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改待办状态成功！");
					LOGGER.info("根据id修改待办状态成功！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改待办状态未成功！");
					LOGGER.info("根据id修改待办状态未成功！");
				}
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改待办状态失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改待办状态失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}

	public TaskService getTaskService() {
		return taskService;
	}

	@Resource
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}

}
