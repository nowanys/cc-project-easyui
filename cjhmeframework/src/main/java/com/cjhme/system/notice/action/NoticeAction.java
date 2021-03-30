package com.cjhme.system.notice.action;

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
import com.cjhme.system.notice.bean.NoticeBean;
import com.cjhme.system.notice.service.NoticeService;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.user.bean.UserBean;


/**
 * 
 * <p>
 * Title: NoticeAction.java
 * </p>
 * Description: 公告
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 28, 2014 7:29:14 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/notice")
public class NoticeAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(NoticeAction.class);

	private NoticeService noticeService;
	
	private BusniessButtonService busniessButtonService;

	/**
	 * 跳转公告管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preSysNoticeManage", method = RequestMethod.GET)
	public String preNoticeManage(HttpSession session,HttpServletRequest request)throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if(null!=userBean && !"".equals(userBean)){
			RoleBean role = userBean.getCurrentRole();
			if(null!=role && !"".equals(role)){
				// 数据字典
				this.bindDataDicParam(request,new String[]{DataDicConstants.QUERY_S_TYPE,DataDicConstants.S_TYPE});
				
				//查询按钮
				Map<String,Object> parameter=new HashMap<String,Object>();
				parameter.put("busniessMark", BusniessMarkConstants.SYS_NOTICE_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList=this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);
				LOGGER.info("跳转公告管理页面成功！");
				return "forward:/pages/manage/system/notice/sys-notice-manage.jsp";
			}else{
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转公告管理页面请求参数错误或失效！");
		}
		
	}


	/**
	 * 根据条件分页查询系统公告
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/querySysNoticeByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> querySysNoticeByConditionPaging(HttpServletRequest request) {

		Map<String, Object> map = null;
		 
		try {
			DataPaging<Object> result = this.noticeService.querySysNoticeByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询系统公告失败,错误——>"+e);
		}

		LOGGER.info("根据条件分页查询系统公告成功！");
		return map;

	}
	
	/**
	 * 跳转系统公告添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preSysNoticeAdd", method = RequestMethod.GET)
	public String preSysNoticeAdd(HttpServletRequest request) {
		//数据字典
		this.bindDataDicParam(request,new String[]{DataDicConstants.S_TYPE});
		LOGGER.info("跳转系统公告添加页面成功！");
		return "forward:/pages/manage/system/notice/sys-notice-add.jsp";
	}
	
	/**
	 * 添加系统公告
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSysNotice", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveSysNotice(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				parameter.put("noticeType", 1);
				int result= this.noticeService.saveSysNotice(parameter);
				if(result>0){
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加系统公告成功！");
					LOGGER.info("添加系统公告成功！");
				}else{
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加系统公告未成功！");
					LOGGER.info("添加系统公告未成功！");
				}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加系统公告失败！");
			e.printStackTrace();
			LOGGER.error("添加系统公告失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 跳转系统公告查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preSysNoticeView", method = RequestMethod.GET)
	public String preSysNoticeView(HttpServletRequest request)throws Exception {
		String noticeId = String.valueOf(request.getParameter("noticeId"));
		if (null != noticeId && !"".equals(noticeId) && !"null".equals(noticeId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("noticeId", noticeId);
			NoticeBean noticeBean = this.noticeService.queryNoticeById(parameter);
			request.setAttribute("noticeBean", noticeBean);
			//数据字典
			this.bindDataDicParam(request,new String[]{DataDicConstants.S_TYPE});
			LOGGER.info("跳转系统公告查看页面成功！");
			return "forward:/pages/manage/system/notice/sys-notice-view.jsp";
		}else{
			throw new Exception("跳转系统公告查看页面请求参数错误或失效！");
		}
	}
	
	/**
	 * 跳转系统公告编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preSysNoticeEdit", method = RequestMethod.GET)
	public String preSysNoticeEdit(HttpServletRequest request)throws Exception {
		String noticeId = String.valueOf(request.getParameter("noticeId"));
		if (null != noticeId && !"".equals(noticeId) && !"null".equals(noticeId)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("noticeId", noticeId);
			NoticeBean noticeBean = this.noticeService.queryNoticeById(parameter);
			request.setAttribute("noticeBean", noticeBean);
			//数据字典
			this.bindDataDicParam(request,new String[]{DataDicConstants.S_TYPE});
			LOGGER.info("跳转系统公告编辑页面成功！");
			return "forward:/pages/manage/system/notice/sys-notice-edit.jsp";
		}else{
			throw new Exception("跳转系统公告编辑页面请求参数错误或失效！");
		}
	}
	
	/**
	 * 根据id修改系统公告
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateSysNoticeById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateSysNoticeById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			    Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.noticeService.updateNoticeById(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改系统公告成功！");
					LOGGER.info("根据id修改系统公告成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改系统公告未成功！");
					LOGGER.info("根据id修改系统公告未成功！");
				}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改系统公告失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改系统公告失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 根据ids删除系统公告 
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteSysNoticeByIds", method = RequestMethod.POST)
	public void deleteSysNoticeByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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
				int result = this.noticeService.deleteSysNoticeByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除系统公告成功！");
					LOGGER.info("根据ids删除系统公告成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除系统公告 未成功！");
					LOGGER.info("根据ids删除系统公告 未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除系统公告 失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除系统公告 失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	


	public NoticeService getNoticeService() {
		return noticeService;
	}


	@Resource
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}
	
	

}
