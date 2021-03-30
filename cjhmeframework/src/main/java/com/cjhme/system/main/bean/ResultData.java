package com.cjhme.system.main.bean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cjhme.system.main.util.PrintData;

/**
 * 
 * <p>
 * Title: ResultData.java
 * </p>
 * Description: 返回参数
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Mar 21, 2015 12:55:29 PM
 */
public class ResultData {

	Log LOGGER = LogFactory.getLog(ResultData.class);

	/**
	 * 返回Map
	 */
	Map<String, Object> resultMap = null;

	/**
	 * 初始化
	 */
	public ResultData() {
		resultMap = new HashMap<String, Object>();
	}

	/**
	 * 返回json数据
	 * 
	 * @return
	 */
	public String toJsonData() {
		String result = JSONObject.fromObject(resultMap).toString();
		LOGGER.info("返回数据：" + result);
		return result;
	}

	/**
	 * 输出json数据
	 * 
	 * @param response
	 */
	public void printJsonData(HttpServletResponse response) {
		String result = JSONObject.fromObject(resultMap).toString();
		LOGGER.info("返回数据：" + result);
		PrintData.printData(response, result);
	}
	
	/**
	 * 输出json数据
	 * 
	 * @param response
	 */
	public void printJsonData(HttpServletResponse response,String contentType) {
		String result = JSONObject.fromObject(resultMap).toString();
		LOGGER.info("返回数据：" + result);
		PrintData.printData(response, contentType,result);
	}

	/**
	 * 设置返回类型
	 * 
	 * @param value
	 */
	public void setResultType(String value) {
		resultMap.put("resultType", value);
	}

	/**
	 * 设置返回消息
	 * 
	 * @param value
	 */
	public void setResultMsg(String value) {
		resultMap.put("resultMsg", value);
	}

	/**
	 * 设置返回数据
	 * 
	 * @param value
	 */
	public void setResultData(Object value) {
		resultMap.put("resultData", value);
	}

	/**
	 * 设置json数组返回值
	 * 
	 * @param json
	 */
	public void setJsonArrayResultData(String json) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		resultMap.put("resultData", jsonArray);
	}

	/**
	 * 设置json对象返回值
	 * 
	 * @param json
	 */
	public void setJsonObjResultData(String json) {
		JSONObject jsonObj = JSONObject.fromObject(json);
		resultMap.put("resultData", jsonObj);
	}

}
