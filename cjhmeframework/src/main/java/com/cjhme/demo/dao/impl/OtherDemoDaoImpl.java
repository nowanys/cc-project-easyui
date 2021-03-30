package com.cjhme.demo.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.demo.bean.OtherDemoBean;
import com.cjhme.demo.dao.OtherDemoDao;
import com.cjhme.system.main.dao.OtherBaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

@Repository("otherDemoDao")
public class OtherDemoDaoImpl extends OtherBaseDao implements OtherDemoDao {

	@Override
	public int saveDemo(Map<String, Object> parameter) {
		return this.getOtherSqlSessionTemplate().insert("com.cjhme.demo.dao.OtherDemoDao.saveDemo",parameter);
	}

	@Override
	public int updateDemo(Map<String, Object> parameter) {
		return this.getOtherSqlSessionTemplate().update("com.cjhme.demo.dao.OtherDemoDao.updateDemo",parameter);
	}

	@Override
	public int deleteDemo(String id) {
		return this.getOtherSqlSessionTemplate().delete("com.cjhme.demo.dao.OtherDemoDao.deleteDemo", id);
	}

	@Override
	public DataPaging<Object> queryDemoPaging(DataPaging<Object> pageParameter) {
		return this.selectPaging("com.cjhme.demo.dao.OtherDemoDao.queryDemoPaging", pageParameter);
	}

	@Override
	public OtherDemoBean queryDemoById(String id) {
		return this.getOtherSqlSessionTemplate().selectOne("com.cjhme.demo.dao.OtherDemoDao.queryDemoById",id);
	}

	
	
	
}
