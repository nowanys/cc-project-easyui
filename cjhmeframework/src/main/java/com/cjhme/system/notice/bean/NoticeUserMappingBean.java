package com.cjhme.system.notice.bean;

import com.cjhme.system.main.bean.BaseBean;

/**
 * 
 * <p>  
 * Title: NoticeUserMappingBean.java 
 * </p>  
 * Description: 公告用户映射
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Apr 17, 2015 5:06:11 PM
 */
public class NoticeUserMappingBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String numId;

	/**
	 * 公告id
	 */
	private String noticeId;
	
	/**
	 * 用户id
	 */
	private String userId;

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}
	
	
}
