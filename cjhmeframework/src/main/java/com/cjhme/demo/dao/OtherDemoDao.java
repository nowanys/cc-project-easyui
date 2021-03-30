package com.cjhme.demo.dao;

import java.util.Map;

import com.cjhme.demo.bean.OtherDemoBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

public interface OtherDemoDao {

public int saveDemo(Map<String,Object> parameter);
	
	public int updateDemo(Map<String,Object> parameter);
	
	public int deleteDemo(String id);
	
	public DataPaging<Object> queryDemoPaging(DataPaging<Object> pageParameter);
	
	public OtherDemoBean queryDemoById(String id);
}
