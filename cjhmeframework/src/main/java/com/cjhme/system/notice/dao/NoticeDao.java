package com.cjhme.system.notice.dao;

import java.util.List;
import java.util.Map;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.notice.bean.NoticeBean;

/**
 * 
 * <p>  
 * Title: NoticeDao.java 
 * </p>  
 * Description: 公告
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Apr 26, 2015 7:57:50 PM
 */
public interface NoticeDao {

	/**
	 * 根据条件分页查询系统公告
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> querySysNoticeByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 
	 * 保存公告
	 * @param parameter
	 * @return
	 */
	public int saveNotice(Map<String,Object> parameter);
	
	/**
	 * 
	 * 根据id查询公告 
	 * @param parameter
	 * @return
	 */
	public NoticeBean queryNoticeById(Map<String,Object> parameter);
	
	
	/**
	 * 根据id修改公告
	 * @param parameter
	 * @return
	 */
	public int updateNoticeById(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除公告 
	 * @param parameter
	 * @return
	 */
	public int deleteNoticeByIds(Map<String,Object> parameter);
	
	/**
	 * 根据用户id查看公告列表
	 * @param parameter
	 * @return
	 */
	public List<NoticeBean> queryNoticeByUserId(Map<String,Object> parameter);
}
