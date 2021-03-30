package com.cjhme.system.main.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cjhme.system.datadic.bean.DataDicItemBean;
import com.cjhme.system.datadic.bean.DataDicTypeBean;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.util.DataDicLoader;
import com.cjhme.system.main.util.SpringServletContextUtil;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.user.bean.UserBean;

/**
 * 
 * <p>
 * Title: SysBaseAction.java
 * </p>
 * Description: 系统基础action(实现数据字典相关操作)
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Jun 24, 2015 1:08:43 PM
 */
public class SysBaseAction extends BaseAction {

	/**
	 * 获得所有数据字典数据
	 * 
	 * @return
	 */
	public final Map<String, Object> getAllDataDic() {
		DataDicLoader dataDicLoader=SpringServletContextUtil.getBeanByName("dataDicLoader");
		return dataDicLoader.getDataDicMap();
	}

	/**
	 * 根据typeCode获得数据字典类型与明细
	 * 
	 * @param typeCode 数据字典类型code
	 * @return
	 */
	public final DataDicTypeBean getDataDicTypeByDicTypeCode(String typeCode) {
		Map<String, Object> dataDicMap = this.getAllDataDic();
		if (null != dataDicMap && dataDicMap.size() > 0) {
			return (DataDicTypeBean) dataDicMap.get(typeCode);
		} else {
			return null;
		}
	}

	/**
	 * 根据typeCode获得数据字典明细
	 *  
	 * @param typeCode  数据字典明细code
	 * @return
	 */
	public final List<DataDicItemBean> getDataDicItemByDicTypeCode(String typeCode) {
		Map<String, Object> dataDicMap = this.getAllDataDic();
		if (null != dataDicMap && dataDicMap.size() > 0) {
			DataDicTypeBean dataDicTypeBean = (DataDicTypeBean) dataDicMap.get(typeCode);
			if (null != dataDicTypeBean) {
				return dataDicTypeBean.getDataDicItems();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 根据typeCode和角色获得数据字典明细
	 * 
	 * @param typeCode 数据字典明细code
	 * @return
	 */
	public final List<DataDicItemBean> getDataDicItemByDicTypeCodeAndRole(HttpSession session,String typeCode) {
		Map<String, Object> dataDicMap = this.getAllDataDic();
		if (null != dataDicMap && dataDicMap.size() > 0) {
			DataDicTypeBean dataDicTypeBean = (DataDicTypeBean) dataDicMap.get(typeCode);
			if (null != dataDicTypeBean) {
				List<DataDicItemBean> allDataDicItem = dataDicTypeBean.getDataDicItems();
				List<DataDicItemBean> subataDicItem = new ArrayList<DataDicItemBean>();
				for (DataDicItemBean dataDicItemBean : allDataDicItem) {
					List<RoleBean> dataDicItemRoleMapping = dataDicItemBean.getDistributeRoles();
					UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

					for (RoleBean roleBean : dataDicItemRoleMapping) {
						if (roleBean.getRoleId().equals(userBean.getCurrentRole().getRoleId())) {
							subataDicItem.add(dataDicItemBean);
							break;
						}
					}
				}
				return subataDicItem;

			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 绑定数据字典参数
	 *  
	 * @param request request对象
	 * @param dataDicTypes  数据字典明细code数组
	 */
	public final void bindDataDicParam(HttpServletRequest request, String[] dataDicTypes) {
		for (String typeCode : dataDicTypes) {
			List<DataDicItemBean> dataDicItemBean = null;
			List<DataDicItemBean> tempItems = this.getDataDicItemByDicTypeCode(typeCode);
			if (null != tempItems && tempItems.size() > 0) {
				dataDicItemBean = tempItems;
			} else {
				dataDicItemBean = new ArrayList<DataDicItemBean>();
			}
			request.setAttribute(typeCode, dataDicItemBean);
		}
	}
	
	/**
	 * 绑定角色数据字典参数
	 * 
	 * @param session  session对象
	 * @param request  request对象
	 * @param dataDicTypes  数据字典明细code数组
	 */
	public final void bindRoleDataDicParam(HttpSession session,HttpServletRequest request, String[] dataDicTypes) {
		for (String typeCode : dataDicTypes) {
			List<DataDicItemBean> dataDicItemBean = null;
			List<DataDicItemBean> tempItems = this.getDataDicItemByDicTypeCodeAndRole(session,typeCode);
			if (null != tempItems && tempItems.size() > 0) {
				dataDicItemBean = tempItems;
			} else {
				dataDicItemBean = new ArrayList<DataDicItemBean>();
			}
			request.setAttribute(typeCode, dataDicItemBean);
		}
	}
}
