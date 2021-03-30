package com.cjhme.system.menu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.main.bean.TreeNodeBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.CreateTreeUtil;
import com.cjhme.system.menu.bean.MenuBean;
import com.cjhme.system.menu.dao.MenuDao;
import com.cjhme.system.menu.service.MenuService;

/**
 * 
 * <p>  
 * Title: MenuServiceImpl.java 
 * </p>  
 * Description: 菜单
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jul 1, 2014 9:20:23 PM
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private MenuDao menuDao;
	
	
	/**
	 * 初始化用户菜单
	 * @param parameter
	 * @return
	 */
	public String queryUserMenuByRoleId(Map<String,Object> parameter)throws Exception{
		List<TreeNodeBean> list = this.menuDao.queryUserMenuByRoleId(parameter);
		CreateTreeUtil.setRootTreeNodeId("-1");
		return CreateTreeUtil.getTreeJson(CreateTreeUtil.createTree(list));
	}
	
	/**
	 * 初始化用户手网琴菜单
	 * @param parameter
	 * @return
	 */
	public List<MenuBean> queryUserAccordionMenuByRoleId(Map<String, Object> parameter){
		return this.menuDao.queryUserAccordionMenuByRoleId(parameter);
	}
	
	/**
	 * 初始化用户手网琴菜单树
	 * @param parameter
	 * @return
	 */
	public String queryUserAccordionTreeMenuById(Map<String, Object> parameter)throws Exception{
		List<TreeNodeBean> list= this.menuDao.queryUserAccordionTreeMenuById(parameter);
		return CreateTreeUtil.getTreeJson(list);
	}
	
	/**
	 * 查询菜单列表
	 * @return
	 */
	public String queryMenuList()throws Exception{
		List<TreeNodeBean> list= this.menuDao.queryMenuList();
		CreateTreeUtil.setRootTreeNodeId("-1");
		return CreateTreeUtil.getTreeJson(CreateTreeUtil.createTree(list));
	}
	
	/**
	 * 根据条件查询菜单
	 * @param parameter
	 * @return
	 */
	public String  queryMenuByCondition(Map<String, Object> parameter)throws Exception{
		List<TreeNodeBean> list= this.menuDao.queryMenuByCondition(parameter);
		CreateTreeUtil.setRootTreeNodeId("-1");
		return CreateTreeUtil.getTreeJson(CreateTreeUtil.createTree(list));
	}

	/**
	 * 根据条件分页查询菜单
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryMenuByConditionPaging(DataPaging<Object> pageParameter){
		return this.menuDao.queryMenuByConditionPaging(pageParameter);
	}
	
	/**
	 * 判断菜单是否存在
	 * 
	 * @param parameter
	 * @return
	 */
	public int queryMenuIsExists(Map<String, Object> parameter){
		return this.menuDao.queryMenuIsExists(parameter);
	}


	/**
	 * 添加菜单
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveMenu(Map<String, Object> parameter){
		int sortNum = this.menuDao.queryMenuMaxSortNumByPMId(parameter);
		parameter.put("sortNum", sortNum+1);
		return this.menuDao.saveMenu(parameter);
	}

	/**
	 * 根据id查询菜单
	 * 
	 * @param parameter
	 * @return
	 */
	public MenuBean queryMenuByMenuId(Map<String, Object> parameter){
		return this.menuDao.queryMenuByMenuId(parameter);
	}

	/**
	 * 根据id修改菜单
	 */
	@Transactional
	public int updateMenuByMenuId(Map<String, Object> parameter){
		return this.menuDao.updateMenuByMenuId(parameter);
	}

	/**
	 * 根据ids删除菜单
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteMenuByIds(Map<String, Object> parameter){
		//删除菜单角色映射
		this.menuDao.deleteMenuRoleMappingByIds(parameter);
		
		//删除菜单
		return this.menuDao.deleteMenuByIds(parameter);
	}
	
	
	/**
	 * 根据id移动菜单
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateMenuParentMenuIdById(Map<String, Object> parameter){
		String sortNum = this.menuDao.queryMaxSortNumByParentMenuId(parameter);
		parameter.put("sortNum", sortNum);
		return this.menuDao.updateMenuParentMenuIdById(parameter);
	}
	
	/**
	 * 根据id移动菜单序号
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateMenuSortNumById(Map<String, Object> parameter){
		String moveSign = String.valueOf(parameter.get("moveSign"));
		String menuId = String.valueOf(parameter.get("menuId"));
		String sortNum = String.valueOf(parameter.get("sortNum"));
		
		MenuBean menuBean=null;
		
		if(null!=moveSign && !"null".equals(moveSign) && "up".equals(moveSign)){
			 menuBean =this.menuDao.queryPreviousMenuById(parameter);
		}else if(null!=moveSign && !"null".equals(moveSign) && "down".equals(moveSign)){
			 menuBean =this.menuDao.queryNextMenuById(parameter);
			
		}else{
			return -1;
		}
		
		
		if(null!=menuBean){
			parameter.put("menuId", menuId);
			parameter.put("sortNum", menuBean.getSortNum());
			this.menuDao.updateMenuSortNumById(parameter);
			
			parameter.put("menuId", menuBean.getMenuId());
			parameter.put("sortNum", sortNum);
			this.menuDao.updateMenuSortNumById(parameter);
			return 1;
		}else{
			return -2;
		}
	}
	
	public MenuDao getMenuDao() {
		return menuDao;
	}

	@Resource
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	
	
}
