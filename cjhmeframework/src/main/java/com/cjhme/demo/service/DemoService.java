package com.cjhme.demo.service;

import java.util.Map;

import com.cjhme.demo.bean.MasterDemoBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

public interface DemoService {

	public int saveDemo(Map<String, Object> parameter);

	public int updateDemo(Map<String, Object> parameter);

	public int deleteDemo(String id);

	public DataPaging<Object> queryDemoPaging(DataPaging<Object> pageParameter);

	public Object queryDemoById(String id);
}
