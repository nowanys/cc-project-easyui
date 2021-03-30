package com.cjhme.system.main.interceptor.mybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;

import com.cjhme.system.main.interceptor.mybatis.dialect.Dialect;
import com.cjhme.system.main.interceptor.mybatis.helper.DialectHelper;
import com.cjhme.system.main.interceptor.mybatis.helper.SqlHelper;
import com.cjhme.system.main.util.PatternMatchUtils;
import com.cjhme.system.main.util.StringUtils;



/**
 * 
 * <p>  
 * Title: PaginationInterceptor.java 
 * </p>  
 * Description: mybatis分页拦截器实现
 * <p>
 * Modify histoty:
 * @author  
 * @version 1.0
 * @created Sep 14, 2014 9:34:47 AM
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	
	//总条数
    private static final ThreadLocal<Integer> PAGINATION_TOTAL = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    //方言
    private Dialect dialect;
    
    //分页sql正则
    private String pagingSqlIdRegex;

    /**
     * 获得Pagination总数
     * @return
     */
    public static int getPaginationTotal(){
        return PAGINATION_TOTAL.get();
    }

    /**
     * 清除PAGINATION_TOTAL
     */
    public static void clean(){
        PAGINATION_TOTAL.remove();
    }

   
    /**
     * intercept
     */
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);
        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        int offset = rowBounds.getOffset();
        int limit = rowBounds.getLimit();

        boolean intercept = PatternMatchUtils.simpleMatch(pagingSqlIdRegex, mappedStatement.getId());
        if(intercept && dialect.supportsLimit() &&
                (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)){

            BoundSql boundSql = statementHandler.getBoundSql();
            Object parameterObject = boundSql.getParameterObject();
            Connection connection = (Connection) invocation.getArgs()[0];
            int count = SqlHelper.getCount(mappedStatement, connection, parameterObject, dialect);
            PAGINATION_TOTAL.set(count);

            String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
            metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, offset, limit));
            metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        }

        return invocation.proceed();
    }

    /**
     * plugin
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * setProperties
     */
    public void setProperties(Properties properties) {
        String dialectClass = properties.getProperty("dialectClass");
        if(StringUtils.isBlank(dialectClass)){
            Dialect.Type databaseType = null;
            try{
                databaseType = Dialect.Type.valueOf(properties.getProperty("dialect").toUpperCase());
            }catch (Exception e){}

            if(null == databaseType){
                throw new RuntimeException("Plug-in [PaginationInterceptor] the dialect of the attribute value is invalid! Valid values for:"
                        + getDialectTypeValidValues());
            }
            dialect = DialectHelper.getDialect(databaseType);
        }else{
            try {
                dialect = (Dialect) Class.forName(dialectClass).newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Plug-in [PaginationInterceptor] cannot create dialect instance by dialectClass: " + dialectClass);
            }
        }

        pagingSqlIdRegex = properties.getProperty("stmtIdRegex", "*.selectPaging");
    }

    private String getDialectTypeValidValues(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Dialect.Type.values().length; i++){
            sb.append(Dialect.Type.values()[i].name())
                    .append(",");
        }
        return sb.toString();
    }

}
