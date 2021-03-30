package com.cjhme.system.button.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cjhme.system.button.bean.ButtonBean;
import com.cjhme.system.button.dao.ButtonDao;
import com.cjhme.system.button.service.BusniessButtonService;
import com.cjhme.system.main.constant.CommonConstants;

/**
 * 
 * <p>
 * Title: BusniessButtonServiceImpl.java
 * </p>
 * Description: 业务按钮
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Mar 25, 2015 2:58:37 PM
 */
@Service("busniessButtonService")
public class BusniessButtonServiceImpl implements BusniessButtonService {

	
	private ButtonDao buttonDao;
	
	/**
	 * 默认业务按钮控制
	 * 
	 * @param busniessStatus
	 *            业务状态
	 * @param createUserId
	 *            创建人id
	 * @param currentUserId
	 *            当前用户id
	 * @return
	 */
	public Map<String, String> defaultBtnControl(String busniessStatus, String createUserId, String currentUserId) {
		Map<String,String> buttons = new HashMap<String,String>();
		
		//保存状态
		if(busniessStatus.equals(CommonConstants.SAVE_STATUS)){
	    
	    //提交审批状态
		}else if(busniessStatus.equals(CommonConstants.SUBMIT_STATUS)){
			
		//审批完成状态	
		}else if(busniessStatus.equals(CommonConstants.COMPLETE_STATUS)){
			
		//驳回状态
		}else if(busniessStatus.equals(CommonConstants.REJECT_STATUS)){
			
		}
		
		return buttons;
	}
	
	/**
	 * 根据业务标识与角色id查询按钮
	 * @param parameter
	 * @return
	 */
	public List<ButtonBean> queryBtnByBusniessMarkAndRole(Map<String,Object> parameter){
		return  this.buttonDao.queryBtnByBusniessMarkAndRole(parameter);
	}

	public ButtonDao getButtonDao() {
		return buttonDao;
	}

	@Resource
	public void setButtonDao(ButtonDao buttonDao) {
		this.buttonDao = buttonDao;
	}
	
	

}
