package com.cjhme.system.main.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import com.cjhme.system.main.interceptor.mybatis.PaginationInterceptor;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>
 * Title: BaseDao.java
 * </p>
 * Description: 基础BaseDao，所有dao继承BaseDao
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Dec 2, 2013 3:14:50 PM
 */
public abstract class BaseDao {

	/**
	 * SqlSessionTemplate
	 */
	private SqlSessionTemplate sqlSessionTemplate;

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	/**
	 * 有参分页实现
	 * @param <E>
	 * @param statement sql语句
	 * @param parameter 参数
	 * @param offset  起始位置
	 * @param limit   结束位置 
	 * @return
	 */
	public <E> DataPaging<E> selectPaging(String statement, Object parameter, int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<E> rows = sqlSessionTemplate.selectList(statement, parameter, rowBounds);
        int total = PaginationInterceptor.getPaginationTotal();
        PaginationInterceptor.clean();
        DataPaging<E> dataPaging = new DataPaging<E>();
        dataPaging.setRows(rows);
        dataPaging.setTotal(total);
        
        return dataPaging;
    }
	
	/**
	 * 无参分页实现
	 * @param <E>
	 * @param statement sql语句
	 * @param offset 起始位置
	 * @param limit 结束位置 
	 * @return
	 */
	public <E> DataPaging<E> selectPaging(String statement, int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<E> rows = sqlSessionTemplate.selectList(statement, rowBounds);
        int total = PaginationInterceptor.getPaginationTotal();
        PaginationInterceptor.clean();
        DataPaging<E> dataPaging = new DataPaging<E>();
        dataPaging.setRows(rows);
        dataPaging.setTotal(total);
        
        return dataPaging;
    }
	

	/**
	 * 有参分页实现
	 * @param <E>
	 * @param statement sql语句
	 * @param parameter 参数
	 * @return
	 */
	public <E> DataPaging<E> selectPaging(String statement,DataPaging<E> pageParameter) {
        RowBounds rowBounds = new RowBounds(pageParameter.getOffset(), pageParameter.getLimit());
        List<E> rows=null;
        
        //无参数
        if(null==pageParameter.getParameter() || "".equals(pageParameter.getParameter())){
        	rows= sqlSessionTemplate.selectList(statement, rowBounds);
        //有参数
        }else{
        	rows = sqlSessionTemplate.selectList(statement, pageParameter.getParameter(), rowBounds);
        }
        
        int total = PaginationInterceptor.getPaginationTotal();
        PaginationInterceptor.clean();
        DataPaging<E> dataPaging = new DataPaging<E>();
        dataPaging.setRows(rows);
        dataPaging.setTotal(total);
        
        return dataPaging;
    }
	

	
	
}
