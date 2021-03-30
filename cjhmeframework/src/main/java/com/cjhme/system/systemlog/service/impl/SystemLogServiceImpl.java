package com.cjhme.system.systemlog.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.cjhme.system.main.util.ConfigUtil;
import com.cjhme.system.main.util.SystemLogFileDescComparator;
import com.cjhme.system.systemlog.action.SystemLogAction;
import com.cjhme.system.systemlog.bean.SystemLogFileBean;
import com.cjhme.system.systemlog.service.SystemLogService;

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
@Service("systemLogService")
public class SystemLogServiceImpl implements SystemLogService {

	Log LOGGER = LogFactory.getLog(SystemLogAction.class);

	/**
	 * 获取日志文件
	 * 
	 * @return
	 */
	public List<SystemLogFileBean> querySystemLogFile() {
		String systemLogPath = ConfigUtil.getInstance().getPopString("systemLogPath");
		if (null != systemLogPath && !"".equals(systemLogPath)) {
			File file = new File(systemLogPath);
			if (file.exists()) {
				File[] files = file.listFiles();
				if (null != files && files.length > 0) {
					return SystemLogFileDescComparator.asList(files);
				} else {
					LOGGER.info(systemLogPath + "目录中无日志文件...");
				}
			} else {
				LOGGER.info(systemLogPath + "目录不存在...");
			}
		} else {
			LOGGER.info("无法从配置文件中获取systemLogPath...");
		}

		return null;
	}

}
