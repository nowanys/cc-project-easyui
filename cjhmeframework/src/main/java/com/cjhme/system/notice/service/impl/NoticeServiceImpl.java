package com.cjhme.system.notice.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.notice.bean.NoticeBean;
import com.cjhme.system.notice.dao.NoticeDao;
import com.cjhme.system.notice.service.NoticeService;

/**
 * 
 * <p>
 * Title: NoticeServiceImpl.java
 * </p>
 * Description: 公告
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Apr 26, 2015 8:00:11 PM
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	private NoticeDao noticeDao;

	
	/**
	 * 根据条件分页查询系统公告
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> querySysNoticeByConditionPaging(DataPaging<Object> pageParameter){
		return this.noticeDao.querySysNoticeByConditionPaging(pageParameter);
	}
	
	/**
	 * 
	 * 保存系统公告
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveSysNotice(Map<String,Object> parameter){
		return this.noticeDao.saveNotice(parameter);
	}
	
	/**
	 * 
	 * 根据id查询公告 
	 * @param parameter
	 * @return
	 */
	public NoticeBean queryNoticeById(Map<String,Object> parameter){
		return this.noticeDao.queryNoticeById(parameter);
	}
	
	/**
	 * 根据id修改公告
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateNoticeById(Map<String,Object> parameter){
		return this.noticeDao.updateNoticeById(parameter);
	}
	
	/**
	 * 根据ids删除系统公告 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteSysNoticeByIds(Map<String,Object> parameter){
		return this.noticeDao.deleteNoticeByIds(parameter);
	}
	
	/**
	 * 根据用户id查看公告列表
	 * @param parameter
	 * @return
	 */
	public List<NoticeBean> queryNoticeByUserId(Map<String,Object> parameter){
		return this.noticeDao.queryNoticeByUserId(parameter);
	}
	
	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	@Resource
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

}
