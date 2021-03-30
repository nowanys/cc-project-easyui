package com.cjhme.system.notice.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.notice.bean.NoticeBean;
import com.cjhme.system.notice.dao.NoticeDao;

/**
 * 
 * <p>
 * Title: NoticeDaoImpl.java
 * </p>
 * Description: 公告
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Apr 26, 2015 7:58:05 PM
 */
@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDao implements NoticeDao {

	/**
	 * 根据条件分页查询系统公告
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> querySysNoticeByConditionPaging(DataPaging<Object> pageParameter){
		return this.selectPaging("com.cjhme.system.notice.dao.NoticeDao.querySysNoticeByConditionPaging", pageParameter);
	}
	
	
	/**
	 * 
	 * 保存公告
	 * @param parameter
	 * @return
	 */
	public int saveNotice(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.notice.dao.NoticeDao.saveNotice",parameter);
	}
	
	/**
	 * 
	 * 根据id查询公告 
	 * @param parameter
	 * @return
	 */
	public NoticeBean queryNoticeById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.notice.dao.NoticeDao.queryNoticeById",parameter);
	}
	
	
	/**
	 * 根据id修改公告
	 * @param parameter
	 * @return
	 */
	public int updateNoticeById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.notice.dao.NoticeDao.updateNoticeById",parameter);
	}
	
	/**
	 * 根据ids删除公告 
	 * @param parameter
	 * @return
	 */
	public int deleteNoticeByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.notice.dao.NoticeDao.deleteNoticeByIds",parameter);
	}
	
	/**
	 * 根据用户id查看公告列表
	 * @param parameter
	 * @return
	 */
	public List<NoticeBean> queryNoticeByUserId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.notice.dao.NoticeDao.queryNoticeByUserId",parameter);
	}
}
