package com.cjhme.system.notice.service;

import java.util.List;
import java.util.Map;

import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.notice.bean.NoticeBean;

/**
 * 
 * <p>  
 * Title: NoticeService.java 
 * </p>  
 * Description: 公告
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Apr 26, 2015 7:59:56 PM
 */
public interface NoticeService {

	/**
	 * 根据条件分页查询系统公告
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> querySysNoticeByConditionPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 
	 * 保存系统公告
	 * @param parameter
	 * @return
	 */
	public int saveSysNotice(Map<String,Object> parameter);
	
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
	 * 根据ids删除系统公告 
	 * @param parameter
	 * @return
	 */
	public int deleteSysNoticeByIds(Map<String,Object> parameter);
	
	/**
	 * 根据用户id查看公告列表
	 * @param parameter
	 * @return
	 */
	public List<NoticeBean> queryNoticeByUserId(Map<String,Object> parameter);
	
}
