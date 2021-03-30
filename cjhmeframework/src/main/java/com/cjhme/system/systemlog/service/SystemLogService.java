package com.cjhme.system.systemlog.service;

import java.util.List;

import com.cjhme.system.systemlog.bean.SystemLogFileBean;

/**
 * 
 * <p>
 * Title: SystemLogService.java
 * </p>
 * Description: 系统日志
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Dec 15, 2013 6:57:29 PM
 */
public interface SystemLogService {

	/**
	 * 获取日志文件
	 * 
	 * @return
	 */
	public List<SystemLogFileBean> querySystemLogFile();

}
