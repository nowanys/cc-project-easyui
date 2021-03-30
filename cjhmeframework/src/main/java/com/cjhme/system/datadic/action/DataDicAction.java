package com.cjhme.system.datadic.action;

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
import com.cjhme.system.datadic.bean.DataDicItemBean;
import com.cjhme.system.datadic.bean.DataDicTypeBean;
import com.cjhme.system.datadic.service.DataDicService;
import com.cjhme.system.main.action.SysBaseAction;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.BusniessMarkConstants;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.constant.DataDicConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.DataDicLoader;
import com.cjhme.system.main.util.RequestParamToObjectUtil;
import com.cjhme.system.main.util.SpringServletContextUtil;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.role.service.RoleService;
import com.cjhme.system.user.bean.UserBean;

/**
 * 
 * <p>
 * Title: DataDicAction.java
 * </p>
 * Description: 数据字典
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 17, 2014 5:28:38 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/dataDic")
public class DataDicAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(DataDicAction.class);

	private DataDicService dataDicService;

	private RoleService roleService;

	private BusniessButtonService busniessButtonService;

	/**
	 * 跳转数据字典管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDataDicManage", method = RequestMethod.GET)
	public String preDataDicManage(HttpSession session, HttpServletRequest request) throws Exception {
		UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
		if (null != userBean && !"".equals(userBean)) {
			RoleBean role = userBean.getCurrentRole();
			if (null != role && !"".equals(role)) {
				// 查询按钮
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("busniessMark", BusniessMarkConstants.DATA_DIC_MANAGE);
				parameter.put("roleId", role.getRoleId());
				List<ButtonBean> buttonList = this.busniessButtonService.queryBtnByBusniessMarkAndRole(parameter);
				request.setAttribute("buttonList", buttonList);
				
				// 数据字典
				this.bindDataDicParam(request, new String[] { DataDicConstants.QUERY_STATUS_TYPE, DataDicConstants.STATUS_TYPE,DataDicConstants.DIC_CATEGORY_TYPE});

				LOGGER.info("跳转数据字典管理页面成功！");
				return "forward:/pages/manage/system/datadic/data-dic-manage.jsp";

			} else {
				throw new Exception("用户未分配角色！");
			}
		} else {
			throw new Exception("跳转数据字典管理页面请求参数错误或失效！");
		}
	}

	/**
	 * 查询数据字典分类列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryDataDicTypeList", method = RequestMethod.POST)
	@ResponseBody
	public void queryDataDicTypeList(HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String queryDicCategory=request.getParameter("queryDicCategory");
			Map<String, Object> parameter=new HashMap<String, Object>();
			parameter.put("queryDicCategory", queryDicCategory);
			
			List<DataDicTypeBean> dataDicTypes=this.dataDicService.queryDataDicTypeList(parameter);
			if(null!=dataDicTypes && dataDicTypes.size()>0){
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(dataDicTypes);
				resultData.setResultMsg("查询数据字典分类列表成功!");
			    LOGGER.info("查询数据字典分类列表成功！");
			}else{
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典分类无数据！");
				LOGGER.info("查询数据字典分类无数据！");
			}
		}catch(Exception e){
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加数据字典分类失败！");
			e.printStackTrace();
			LOGGER.error("添加数据字典分类失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 跳转数据字典分类添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDataDicTypeAdd", method = RequestMethod.GET)
	public String preDataDicTypeAdd(HttpServletRequest request)throws Exception {
		String dicCategory = request.getParameter("dicCategory");
		if (null != dicCategory && !"".equals(dicCategory) && !"null".equals(dicCategory)) {
			request.setAttribute("dicCategory", dicCategory);
			LOGGER.info("跳转数据字典分类添加页面成功！");
			return "forward:/pages/manage/system/datadic/data-dic-type-add.jsp";
		} else {
			throw new Exception("跳转部门添加页面请求参数错误或失效！");
		}
	}

	/**
	 * 添加数据字典分类(-2存在，>0成功)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDataDicType", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDataDicType(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			// 判断是否存在
			int isExists = this.dataDicService.queryDataDicTypeIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典分类已存在！");
				LOGGER.info("数据字典分类已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				int result = this.dataDicService.saveDataDicType(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加数据字典分类成功！");
					LOGGER.info("添加数据字典分类成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加数据字典分类未成功！");
					LOGGER.info("添加数据字典分类未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加数据字典分类失败！");
			e.printStackTrace();
			LOGGER.error("添加数据字典分类失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 跳转数据字典分类编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDataDicTypeEdit", method = RequestMethod.GET)
	public String preDataDicTypeEdit(HttpServletRequest request) throws Exception {
		String dicTypeId = String.valueOf(request.getParameter("dicTypeId"));
		if (null != dicTypeId && !"".equals(dicTypeId) && !"null".equals(dicTypeId)) {
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.DIC_CATEGORY_TYPE});
			
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("dicTypeId", dicTypeId);
			DataDicTypeBean dataDicTypeBean = this.dataDicService.queryDataDicTypeById(parameter);
			request.setAttribute("dataDicTypeBean", dataDicTypeBean);
			
			LOGGER.info("跳转数据字典分类编辑页面成功！");
			return "forward:/pages/manage/system/datadic/data-dic-type-edit.jsp";
		} else {
			throw new Exception("跳转数据字典分类编辑页面请求参数错误或失效！");
		}
	}

	/**
	 * 根据id修改数据字典分类(-2存在，>0成功)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateDataDicTypeById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateDataDicTypeById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			// 判断是否存在
			int isExists = this.dataDicService.queryDataDicTypeIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典分类已存在！");
				LOGGER.info("数据字典分类已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.dataDicService.updateDataDicTypeById(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改数据字典分类成功！");
					LOGGER.info("根据id修改数据字典分类成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改数据字典分类未成功！");
					LOGGER.info("根据id修改数据字典分类未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改数据字典分类失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改数据字典分类失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除数据字典分类
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteDataDicTypeByIds", method = RequestMethod.POST)
	public void deleteDataDicTypeByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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
				int result = this.dataDicService.deleteDataDicTypeByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除数据字典分类成功！");
					LOGGER.info("根据ids删除数据字典分类成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除数据字典分类未成功！");
					LOGGER.info("根据ids删除数据字典分类未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除数据字典分类失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除数据字典分类失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根数据字典分类id查询数据字典明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDataDicItemByIdPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryDataDicItemByIdPaging(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {

			DataPaging<Object> result = this.dataDicService.queryDataDicItemByIdPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根数据字典分类id查询数据字典明细失败,错误——>" + e);
		}

		LOGGER.info("根数据字典分类id查询数据字典明细成功！");
		return map;

	}

	/**
	 * 跳转数据字典明细添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDataDicItemAdd", method = RequestMethod.GET)
	public String preDataDicItemAdd(HttpServletRequest request) throws Exception {
		String dicTypeId = String.valueOf(request.getParameter("dicTypeId"));
		String dicTypeCode = String.valueOf(request.getParameter("dicTypeCode"));
		String status= String.valueOf(request.getParameter("status"));
		if (null != dicTypeId && !"".equals(dicTypeId) && !"null".equals(dicTypeId) && null != dicTypeCode && !"".equals(dicTypeCode) && !"null".equals(dicTypeCode) 
		&& null != status && !"".equals(status) && !"null".equals(status)) {
			request.setAttribute("dicTypeId", dicTypeId);
			request.setAttribute("dicTypeCode", dicTypeCode);
			request.setAttribute("status", status);
			// 数据字典
			this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE });

			return "forward:/pages/manage/system/datadic/data-dic-item-add.jsp";
		} else {
			throw new Exception("跳转数据字典明细添加页面请求参数错误或失效！");
		}

	}

	/**
	 * 添加数据字典明细(-2存在，>0成功)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDataDicItem", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDataDicItem(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			// 判断是否存在
			int isExists = this.dataDicService.queryDataDicItemIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典明细已存在！");
				LOGGER.info("数据字典明细已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("createUserId", userBean.getUserId());
				parameter.put("createUser", userBean.getRealName());
				int result = this.dataDicService.saveDataDicItem(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("添加数据字典明细成功！");
					LOGGER.info("添加数据字典明细成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("添加数据字典明细未成功！");
					LOGGER.info("添加数据字典明细未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加数据字典明细失败！");
			e.printStackTrace();
			LOGGER.error("添加数据字典明细失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 跳转数据字典明细查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDataDicItemView", method = RequestMethod.GET)
	public String preDataDicItemView(HttpServletRequest request) throws Exception {
		String dicItemId = String.valueOf(request.getParameter("dicItemId"));
		String dicTypeCode = String.valueOf(request.getParameter("dicTypeCode"));
		if (null != dicItemId && !"".equals(dicItemId) && !"null".equals(dicItemId) && null != dicTypeCode && !"".equals(dicTypeCode) && !"null".equals(dicTypeCode)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("dicItemId", dicItemId);
			DataDicItemBean dtaDicItemBean = this.dataDicService.queryDataDicItemById(parameter);
			request.setAttribute("dtaDicItemBean", dtaDicItemBean);

			if (null != dtaDicItemBean) {
				List<DataDicItemBean> dataDiaItemList = this.getDataDicItemByDicTypeCode(dtaDicItemBean.getDicTypeCode());
				request.setAttribute("dataDiaItemList", dataDiaItemList);

				// 数据字典
				this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE });
			}

			request.setAttribute("dicTypeCode", dicTypeCode);

			LOGGER.info("跳转数据字典明细查看页面成功！");
			return "forward:/pages/manage/system/datadic/data-dic-item-view.jsp";
		} else {
			throw new Exception("跳转数据字典明细查看页面请求参数错误或失效！");
		}
	}

	/**
	 * 跳转数据字典明细编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDataDicItemEdit", method = RequestMethod.GET)
	public String preDataDicItemEdit(HttpServletRequest request) throws Exception {
		String dicItemId = String.valueOf(request.getParameter("dicItemId"));
		String dicTypeCode = String.valueOf(request.getParameter("dicTypeCode"));
		if (null != dicItemId && !"".equals(dicItemId) && !"null".equals(dicItemId) && null != dicTypeCode && !"".equals(dicTypeCode) && !"null".equals(dicTypeCode)) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("dicItemId", dicItemId);
			DataDicItemBean dtaDicItemBean = this.dataDicService.queryDataDicItemById(parameter);
			request.setAttribute("dtaDicItemBean", dtaDicItemBean);

			if (null != dtaDicItemBean) {
				List<DataDicItemBean> dataDiaItemList = this.getDataDicItemByDicTypeCode(dtaDicItemBean.getDicTypeCode());
				request.setAttribute("dataDiaItemList", dataDiaItemList);

				// 数据字典
				this.bindDataDicParam(request, new String[] { DataDicConstants.STATUS_TYPE });
			}

			request.setAttribute("dicTypeCode", dicTypeCode);

			LOGGER.info("跳转数据字典明细编辑页面成功！");
			return "forward:/pages/manage/system/datadic/data-dic-item-edit.jsp";
		} else {
			throw new Exception("跳转数据字典明细编辑页面请求参数错误或失效！");
		}
	}

	/**
	 * 根据id修改数据字典明细(-2存在，>0成功)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateDataDicItemById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateDataDicItemById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			// 判断是否存在
			int isExists = this.dataDicService.queryDataDicItemIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典明细已存在！");
				LOGGER.info("数据字典明细已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("updateUser", userBean.getRealName());
				int result = this.dataDicService.updateDataDicItemById(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("修改数据字典明细成功！");
					LOGGER.info("根据id修改数据字典明细成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("修改数据字典明细未成功！");
					LOGGER.info("根据id修改数据字典明细未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改数据字典明细失败！");
			e.printStackTrace();
			LOGGER.error("根据id修改数据字典明细失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除数据字典明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteDataDicItemByIds", method = RequestMethod.POST)
	public void deleteDataDicItemByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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

				int result = this.dataDicService.deleteDataDicItemByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除数据字典明细成功！");
					LOGGER.info("根据ids删除数据字典明细成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除数据字典明细未成功！");
					LOGGER.info("根据ids删除数据字典明细未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除数据字典明细失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除数据字典明细失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 数据字典明细移动序号(-1失败，1成功，-2已到顶、已到底)
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateDataDicItemSortNumById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateDataDicItemSortNumById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			parameter.put("updateUserId", userBean.getUserId());
			parameter.put("updateUser", userBean.getRealName());
			int result = this.dataDicService.updateDataDicItemSortNumById(parameter);
			if (result == 1) {
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("数据字典明细移动序号成功！");
				LOGGER.info("数据字典明细移动序号成功！");
			} else if (result == -2) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典明细移动序号已到顶或已到底！");
				LOGGER.info("数据字典明细移动序号已到顶或已到底！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_ERROR);
				resultData.setResultMsg("数据字典明细移动序号失败！");
				LOGGER.info("数据字典明细移动序号失败！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("数据字典明细移动序号失败！");
			e.printStackTrace();
			LOGGER.error("数据字典明细移动序号失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 重新载入数据字典
	 */
	@RequestMapping(value = "/reloadDataDic", method = RequestMethod.POST)
	public void reloadDataDic(HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> dataDicMap = this.dataDicService.queryInitDataDicType();
			if (null != dataDicMap) {
				DataDicLoader dataDicLoader=SpringServletContextUtil.getBeanByName("dataDicLoader");
				dataDicLoader.setDataDicMap(dataDicMap);
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("重新载入数据字典成功！");
				LOGGER.info("重新载入数据字典成功,数据字典分类条数：" + dataDicMap.size());
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("重新载入数据字典未成功！");
				LOGGER.info("重新载入数据字典未成功！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("重新载入数据字典失败！");
			e.printStackTrace();
			LOGGER.error("重新载入数据字典失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 跳转数据字典明细角色分配页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preDataDicItemRoleDistribute", method = RequestMethod.GET)
	public String preDataDicItemRoleDistribute(HttpServletRequest request) throws Exception {
		String dicItemId = String.valueOf(request.getParameter("dicItemId"));
		if (null != dicItemId && !"".equals(dicItemId) && !"null".equals(dicItemId)) {
			JSONArray roleData = JSONArray.fromObject(this.roleService.queryAllRoleList());

			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("dicItemId", dicItemId);
			JSONArray dataDicItemRoleData = JSONArray.fromObject(this.dataDicService.queryDataDicItemRoleMappingById(parameter));

			request.setAttribute("dicItemId", dicItemId);
			request.setAttribute("roleData", roleData);
			request.setAttribute("dataDicItemRoleData", dataDicItemRoleData);
			LOGGER.info("跳转数据字典明细角色分配页面成功！");
			return "forward:/pages/manage/system/datadic/dic-item-role-distribute.jsp";
		} else {
			throw new Exception("跳转数据字典明细角色分配页面请求参数错误或失效！");
		}
	}

	/**
	 * 添加数据字典明细角色映射
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDataDicItemRoleMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDataDicItemRoleMapping(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> roleIds = (List<String>) parameter.get("roleIds");
				String dicItemId = String.valueOf(parameter.get("dicItemId"));

				if (null != dicItemId && !"".equals(dicItemId) && null != roleIds) {
					int result = this.dataDicService.saveDataDicItemRoleMapping(parameter);
					if (result > 0) {
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加数据字典明细角色映射成功！");
						LOGGER.info("添加数据字典明细角色映射成功！");
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加数据字典明细角色映射未成功！");
						LOGGER.info("添加数据字典明细角色映射未成功！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("请求参数错误！");
					LOGGER.info("请求参数错误！");
				}

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加数据字典明细角色映射失败！");
			e.printStackTrace();
			LOGGER.error("添加数据字典明细角色映射失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	

	public DataDicService getDataDicService() {
		return dataDicService;
	}

	@Resource
	public void setDataDicService(DataDicService dataDicService) {
		this.dataDicService = dataDicService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	@Resource
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public BusniessButtonService getBusniessButtonService() {
		return busniessButtonService;
	}

	@Resource
	public void setBusniessButtonService(BusniessButtonService busniessButtonService) {
		this.busniessButtonService = busniessButtonService;
	}

}
