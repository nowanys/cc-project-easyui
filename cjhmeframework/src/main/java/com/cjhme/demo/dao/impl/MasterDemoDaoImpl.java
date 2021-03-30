package com.cjhme.demo.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.demo.bean.MasterDemoBean;
import com.cjhme.demo.dao.MasterDemoDao;
import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

@Repository("masterDemoDao")
public class MasterDemoDaoImpl extends BaseDao implements MasterDemoDao  {

	@Override
	public int saveDemo(Map<String, Object> parameter) {
		return this.getSqlSessionTemplate().insert("com.cjhme.demo.dao.MasterDemoDao.saveDemo",parameter);
	}

	@Override
	public int updateDemo(Map<String, Object> parameter) {
		return this.getSqlSessionTemplate().update("com.cjhme.demo.dao.MasterDemoDao.updateDemo",parameter);
	}

	@Override
	public int deleteDemo(String id) {
		return this.getSqlSessionTemplate().delete("com.cjhme.demo.dao.MasterDemoDao.deleteDemo", id);
	}

	@Override
	public DataPaging<Object> queryDemoPaging(DataPaging<Object> pageParameter) {
		return this.selectPaging("com.cjhme.demo.dao.MasterDemoDao.queryDemoPaging", pageParameter);
	}

	@Override
	public MasterDemoBean queryDemoById(String id) {
		return this.getSqlSessionTemplate().selectOne("com.cjhme.demo.dao.MasterDemoDao.queryDemoById",id);
	}

}
