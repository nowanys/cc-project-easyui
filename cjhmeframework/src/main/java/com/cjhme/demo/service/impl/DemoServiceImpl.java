package com.cjhme.demo.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.demo.bean.MasterDemoBean;
import com.cjhme.demo.bean.OtherDemoBean;
import com.cjhme.demo.dao.MasterDemoDao;
import com.cjhme.demo.dao.OtherDemoDao;
import com.cjhme.demo.service.DemoService;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

@Service("demoService")
public class DemoServiceImpl implements DemoService {
	
	private OtherDemoDao otherDemoDao;
	private MasterDemoDao masterDemoDao;
	
	

    @Resource
	public void setOtherDemoDao(OtherDemoDao otherDemoDao) {
		this.otherDemoDao = otherDemoDao;
	}
	
    @Resource
	public void setMasterDemoDao(MasterDemoDao masterDemoDao) {
		this.masterDemoDao = masterDemoDao;
	}

	public MasterDemoDao getMasterDemoDao() {
		return masterDemoDao;
	}
	
	public OtherDemoDao getOtherDemoDao() {
		return otherDemoDao;
	}

	

	@Transactional
	public int saveDemo(Map<String, Object> parameter) {
		this.masterDemoDao.saveDemo(parameter);
		return this.otherDemoDao.saveDemo(parameter);
	}

	@Transactional
	public int updateDemo(Map<String, Object> parameter) {
		this.masterDemoDao.updateDemo(parameter);
		return this.otherDemoDao.updateDemo(parameter);
	}

	@Transactional
	public int deleteDemo(String id) {
		this.otherDemoDao.deleteDemo(id);
		return this.masterDemoDao.deleteDemo(id);
	}

	public DataPaging<Object> queryDemoPaging(DataPaging<Object> pageParameter) {
		DataPaging<Object> resultData1=this.masterDemoDao.queryDemoPaging(pageParameter);
		DataPaging<Object> resultData2=this.otherDemoDao.queryDemoPaging(pageParameter);
		resultData1.setTotal(resultData1.getTotal()+resultData2.getTotal());
		resultData1.getRows().addAll(resultData2.getRows());
		return resultData1;
	}

	@Transactional
	public MasterDemoBean queryDemoById(String id) {
		MasterDemoBean resultData1=this.masterDemoDao.queryDemoById(id);
		OtherDemoBean resultData2=this.otherDemoDao.queryDemoById(id);
		return resultData1;
	}

}
