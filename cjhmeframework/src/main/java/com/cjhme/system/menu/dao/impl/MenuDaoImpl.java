package com.cjhme.system.menu.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.main.bean.TreeNodeBean;
import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.menu.bean.MenuBean;
import com.cjhme.system.menu.dao.MenuDao;

/**
 * 
 * <p>  
 * Title: MenuDaoImpl.java 
 * </p>  
 * Description: 菜单
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Jul 1, 2014 9:19:14 PM
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDao implements MenuDao {
	
	/**
	 * 初始化用户菜单
	 * @param parameter
	 * @return
	 */
	public List<TreeNodeBean> queryUserMenuByRoleId(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.menu.dao.MenuDao.queryUserMenuByRoleId",parameter);
	}
	
	/**
	 * 初始化用户手网琴菜单
	 * @param parameter
	 * @return
	 */
	public List<MenuBean> queryUserAccordionMenuByRoleId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.menu.dao.MenuDao.queryUserAccordionMenuByRoleId",parameter);
	}
	
	/**
	 * 初始化用户手网琴菜单树
	 * @param parameter
	 * @return
	 */
	public List<TreeNodeBean> queryUserAccordionTreeMenuById(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.menu.dao.MenuDao.queryUserAccordionTreeMenuById",parameter);
	}
	
	
	/**
	 * 查询菜单列表
	 * @return
	 */
	public List<TreeNodeBean> queryMenuList(){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.menu.dao.MenuDao.queryMenuList");
	}
	
	/**
	 * 根据条件查询菜单
	 * @param parameter
	 * @return
	 */
	public List<TreeNodeBean> queryMenuByCondition(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.menu.dao.MenuDao.queryMenuByCondition", parameter);
	}
	
	
	/**
	 * 根据条件分页查询菜单
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryMenuByConditionPaging(DataPaging<Object> pageParameter){
		 return this.selectPaging("com.cjhme.system.menu.dao.MenuDao.queryMenuByConditionPaging", pageParameter);
	}
	
	/**
	 * 判断菜单是否存在
	 * 
	 * @param parameter
	 * @return
	 */
	public int queryMenuIsExists(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.menu.dao.MenuDao.queryMenuIsExists",parameter);
	}
	

	/**
	 * 根据父菜单id查询最大序号
	 * 
	 * @param parameter
	 * @return
	 */
	public int queryMenuMaxSortNumByPMId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.menu.dao.MenuDao.queryMenuMaxSortNumByPMId",parameter);
	}

	/**
	 * 添加菜单
	 * 
	 * @param parameter
	 * @return
	 */
	public int saveMenu(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.menu.dao.MenuDao.saveMenu",parameter);
	}

	/**
	 * 根据id查询菜单
	 * 
	 * @param parameter
	 * @return
	 */
	public MenuBean queryMenuByMenuId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.menu.dao.MenuDao.queryMenuByMenuId",parameter);
	}

	/**
	 * 根据id修改菜单
	 */
	public int updateMenuByMenuId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.menu.dao.MenuDao.updateMenuByMenuId",parameter);
	}

	/**
	 * 根据ids删除菜单
	 * 
	 * @param parameter
	 * @return
	 */
	public int deleteMenuByIds(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.menu.dao.MenuDao.deleteMenuByIds",parameter);
	}
	
	
	/**
	 * 根据id移动菜单
	 * @param parameter
	 * @return
	 */
	public int updateMenuParentMenuIdById(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.menu.dao.MenuDao.updateMenuParentMenuIdById",parameter);
	}
	
	/**
	 * 获得当前行的上一条菜单
	 * @param parameter
	 * @return
	 */
	public MenuBean queryPreviousMenuById(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.menu.dao.MenuDao.queryPreviousMenuById",parameter);
	}
	
	/**
	 * 获得当前行的下一条菜单
	 * @param parameter
	 * @return
	 */
	public MenuBean queryNextMenuById(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.menu.dao.MenuDao.queryNextMenuById",parameter);
	}
	
	/**
	 * 根据id移动菜单序号
	 * @param parameter
	 * @return
	 */
	public int updateMenuSortNumById(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.menu.dao.MenuDao.updateMenuSortNumById",parameter);
	}
	
	/**
	 * 根据菜单ids删除菜单角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteMenuRoleMappingByIds(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.menu.dao.MenuDao.deleteMenuRoleMappingByIds",parameter);
	}
	
	/**
	 * 根据移动菜单id查询菜单最大排序 
	 * @param parameter
	 * @return
	 */
	public String queryMaxSortNumByParentMenuId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.menu.dao.MenuDao.queryMaxSortNumByParentMenuId",parameter);
	}

}
