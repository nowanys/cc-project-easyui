package com.cjhme.demo.action;

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

import com.cjhme.demo.service.DemoService;
import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.interceptor.springmvc.annotation.Auth;
import com.cjhme.system.main.util.RequestParamToObjectUtil;

@Controller
@Scope("prototype")
@RequestMapping("/demo")
public class DemoAction {
	
	Log LOGGER = LogFactory.getLog(DemoAction.class);
	
	private  DemoService demoService;
	
	@Resource
	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	public DemoService getDemoService() {
		return demoService;
	}

	
	/**
	 * 跳转页面
	 * 
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/preDemo", method = RequestMethod.GET)
	public String preDemo(HttpServletRequest request) {
		//数据字典
		LOGGER.info("跳转页面成功！");
		return "forward:/pages/manage/demo/demo.jsp";
	}


	/**
	 * 添加
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/saveDemo", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDemo(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
            int i=this.demoService.saveDemo(parameter);
            if(i>0){
            	resultData.setResultType(CommonConstants.RESULT_SUCCESS);
                resultData.setResultMsg("添加成功！");
            }else{
            	resultData.setResultType(CommonConstants.RESULT_FAILURE);
                resultData.setResultMsg("添加未成功！");
            }
            
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加失败！");
			e.printStackTrace();
			LOGGER.error("添加失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/updateDemo", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateDemo(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
            int i=this.demoService.updateDemo(parameter);
            if(i>0){
            	resultData.setResultType(CommonConstants.RESULT_SUCCESS);
                resultData.setResultMsg("修改成功！");
            }else{
            	resultData.setResultType(CommonConstants.RESULT_FAILURE);
                resultData.setResultMsg("修改未成功！");
            }
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改失败！");
			e.printStackTrace();
			LOGGER.error("修改失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 根据id删除
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/deleteDemo", method = RequestMethod.POST)
	public void deleteDemo(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			int i=this.demoService.deleteDemo(request.getParameter("id"));
			if(i>0){
            	resultData.setResultType(CommonConstants.RESULT_SUCCESS);
                resultData.setResultMsg("删除成功！");
            }else{
            	resultData.setResultType(CommonConstants.RESULT_FAILURE);
                resultData.setResultMsg("删除未成功！");
            }

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除失败！");
			e.printStackTrace();
			LOGGER.error("根据id删除失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	/**
	 * 根据条件分页查询
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/queryDemoPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryDemoPaging(HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			DataPaging<Object> result = this.demoService.queryDemoPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询失败,错误——>"+e);
		}

		LOGGER.info("根据条件分页查询成功！");
		return map;

	}
	
	/**
	 * 根据id查询
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyUrl=false)
	@RequestMapping(value = "/queryDemoById", method = RequestMethod.POST)
	public void queryDemoById(HttpServletRequest request,HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			LOGGER.info("根据id查询成功！");
			resultData.setResultType(CommonConstants.RESULT_SUCCESS);
            resultData.setResultMsg("根据id查询成功！");
            resultData.setResultData(this.demoService.queryDemoById(request.getParameter("id")));

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据id查询失败！");
			e.printStackTrace();
			LOGGER.error("查询失败,错误——>"+e);
		}
		resultData.printJsonData(response);
	}
	
	
}
