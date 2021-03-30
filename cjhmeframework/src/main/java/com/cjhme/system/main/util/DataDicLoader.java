package com.cjhme.system.main.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cjhme.system.datadic.service.DataDicService;

/**
 * 
 * <p>
 * Title: DataDicLoader.java
 * </p>
 * Description: 数据字典加载器
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Oct 17, 2015 2:08:52 PM
 */
public class DataDicLoader {

	Log LOGGER = LogFactory.getLog(DataDicLoader.class);

	/**
	 * 数据字典信息
	 */
	private Map<String, Object> dataDicMap = new HashMap<String, Object>();

	/**
	 * 初始化加载器
	 * 
	 * @throws Exception
	 */
	public void initLoader() throws Exception {
		LOGGER.info("初始化数据字典成功开始！");
		DataDicService dataDicService = SpringServletContextUtil.getBeanByName("dataDicService");
		dataDicMap = dataDicService.queryInitDataDicType();
		LOGGER.info("初始化数据字典结束,加载数据字典数量：" + dataDicMap.size());
	}

	/**
	 * 获取数据字典信息
	 * 
	 * @return
	 */
	public Map<String, Object> getDataDicMap() {
		return dataDicMap;
	}
	
	/**
	 * 设置数据字典信息
	 * 
	 * @return
	 */
	public void setDataDicMap(Map<String, Object> dataDicMap){
		this.dataDicMap=dataDicMap;
	}

}
