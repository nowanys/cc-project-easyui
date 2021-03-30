package com.cjhme.system.systemlog.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.cjhme.system.button.bean.ButtonBean;
import com.cjhme.system.button.service.BusniessButtonService;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.systemlog.bean.SystemLogFileBean;
import com.cjhme.system.systemlog.service.SystemLogService;
import com.cjhme.system.user.bean.UserBean;

/**
 * 
 * <p>
 * Title: SystemLogAction.java
 * </p>
 * Description: 系统日志
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Apr 23, 2015 6:36:27 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/systemLog")
public class SystemLogAction {

	Log LOGGER = LogFactory.getLog(SystemLogAction.class);

	private SystemLogService systemLogService;

	private BusniessButtonService busniessButtonService;

	/**
	 * 跳转日志管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preSystemLogManage", method = RequestMethod.GET)
	public String preSystemLogManage(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean)) {
			RoleBean role = userBean.getCurrentRole();
			if (null != role && !"".equals(role)) {
				// 查询按钮
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("busniessMark", BusniessMarkConstants.SYS_LOG_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList = this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);
				LOGGER.info("跳转日志管理页面成功！");
				List<SystemLogFileBean> systemLogFiles = this.systemLogService.querySystemLogFile();
				request.setAttribute("systemLogFiles", systemLogFiles);
				request.setAttribute("systemLogCount", systemLogFiles.size());
				return "forward:/pages/manage/system/systemlog/system-log-manage.jsp";
			} else {
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转日志管理页面请求参数错误或失效！");
		}
	}

	/**
	 * 系统日志下载
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/downloadSystemLog", method = RequestMethod.GET)
	public void downloadSystemLog(HttpServletRequest request, HttpServletResponse response) {
		try {
			String param = request.getParameter("filePath");

			// 参数不为空时
			if (null != param && !"".equals(param)) {

				String filePath = java.net.URLDecoder.decode(param, "UTF-8");

				InputStream in = new FileInputStream(filePath.trim());

				byte[] buffer = new byte[in.available()];
				in.read(buffer);
				in.close();

				String fileName = "";
				int index = 0;
				if (-1 != filePath.lastIndexOf(".")) {
					index = filePath.lastIndexOf(".");
				}
				fileName = UUID.randomUUID() + filePath.substring(index, filePath.length());

				response.reset();

				// 设置HTML头部信息
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName.trim() + "\"");
				response.addHeader("Content-Length", "" + buffer.length);
				response.setContentType("application/octet-stream;charset=UTF-8");

				OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
				outputStream.write(buffer);
				outputStream.flush();
				outputStream.close();
				LOGGER.info("系统日志下载成功！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("系统日志下载失败,错误——>" + e);
		}

	}

	/**
	 * 删除系统日志
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteSystemLog", method = RequestMethod.POST)
	public void deleteSystemLog(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String filePath = request.getParameter("filePath");
			// 参数不为空时
			if (null != filePath && !"".equals(filePath)) {
				String param = java.net.URLDecoder.decode(filePath, "UTF-8");
				File file = new File(param.trim());
				if (file.exists()) {
					boolean result = file.delete();
					if (result) {
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("删除系统日志成功！");
						LOGGER.info("删除系统日志成功！");
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("删除系统日志未成功，权限不足或文件正在使用！");
						LOGGER.info("删除系统日志未成功！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("系统日志不存在！");
					LOGGER.info("系统日志不存在！");
				}

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数有误！");
				LOGGER.info("请求参数有误！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除系统日志失败！");
			e.printStackTrace();
			LOGGER.error("删除系统日志失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	public SystemLogService getSystemLogService() {
		return systemLogService;
	}

	@Resource
	public void setSystemLogService(SystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}
}
