package com.cjhme.system.main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>
 * Title: RequestParamToObjectUtil.java
 * </p>
 * Description: 流转换成对象
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Dec 2, 2013 3:18:37 PM
 */
public class RequestParamToObjectUtil {

	static Log LOGGER = LogFactory.getLog(RequestParamToObjectUtil.class);
	
	/**
	 * 流转换成JSON String
	 * 
	 * @param request
	 *            请求
	 * @param calssName
	 *            转换成的类的class对象
	 * @return
	 * @throws IOException
	 */
	public static String jsonParseToJSONString(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("utf-8");
		// 读取request 流
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder tempStr = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			tempStr.append(line);
		}
		reader.close();
		
		LOGGER.info("请求参数："+tempStr.toString());
		return tempStr.toString();
	}

	/**
	 * 将流转换成对象
	 * 
	 * @param request
	 *            请求
	 * @param calssName
	 *            转换成的类的class对象
	 * @return
	 * @throws IOException
	 */
	public static Object jsonParseToObject(HttpServletRequest request, Class<?> beanClass) throws IOException {
		request.setCharacterEncoding("utf-8");
		// 读取request
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder tempStr = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			tempStr.append(line);
		}
		String jsonStr = tempStr.toString();

		reader.close();
		
		LOGGER.info("请求参数："+jsonStr);
		
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return JSONObject.toBean(jsonObject, beanClass);
	}

	/**
	 * 将流转换成集合
	 * 
	 * @param request
	 *            请求
	 * @param calssName
	 *            转换成的类的class对象
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> jsonParseToList(HttpServletRequest request, Class<?> beanClass) throws IOException {
		request.setCharacterEncoding("utf-8");
		// 读取request
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder tempStr = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			tempStr.append(line);
		}
		String jsonStr = tempStr.toString();
		
		reader.close();
		
		LOGGER.info("请求参数："+jsonStr);
		
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		return (List<Object>) JSONArray.toCollection(jsonArray,beanClass);
	}

	/**
	 * 将请求参数转换成Map
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> requestParamParseToMap(HttpServletRequest request) throws IOException {
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 参数Map
		Map<?,?> parameterMap = request.getParameterMap();
		
		Iterator<?> entries = parameterMap.entrySet().iterator();
		Map.Entry<?, ?> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<?, ?>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		
		LOGGER.info("请求参数："+returnMap.toString());
		
		return returnMap;
	}
	
	/**
	 * 将请求参数转换成Page Map
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static DataPaging<Object> requestParamParseToPageMap(HttpServletRequest request) throws IOException {
		// 返回值Page Map
		DataPaging<Object> returnPageMap = DataPaging.pageUtil(request.getParameter("page"), request.getParameter("rows"));
		
		// 参数Map
		Map<?,?> parameterMap = request.getParameterMap();
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<?> entries = parameterMap.entrySet().iterator();
		Map.Entry<?, ?> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<?, ?>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		
		//移除分页临时参数
		returnMap.remove("page");
		returnMap.remove("rows");
		
		returnPageMap.setParameter(returnMap);
		
		LOGGER.info("请求参数："+returnPageMap.toString());
		
		return returnPageMap;
	}

}
