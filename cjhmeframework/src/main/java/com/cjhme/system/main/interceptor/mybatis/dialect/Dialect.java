package com.cjhme.system.main.interceptor.mybatis.dialect;

/**
 * 
 * <p>
 * Title: Dialect.java
 * </p>
 * Description: 数据库方言
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 * @created Sep 14, 2014 9:28:57 AM
 */
public abstract class Dialect {

	/**
	 * 目前支持MYSQL、ORACLE数据库（可扩展）
	 */
	public static enum Type {
		MYSQL, ORACLE
	}

	/**
	 * 数据库本身是否支持分页查询
	 * 
	 * @return {@code true} 支持分页查询
	 */
	public abstract boolean supportsLimit();

	/**
	 * 将sql包装成数据库支持的特有查询语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @param offset
	 *            开始位置
	 * @param limit
	 *            每页显示的记录数
	 * @return 数据库专属分页查询sql
	 */
	public abstract String getLimitString(String sql, int offset, int limit);

	/**
	 * 将sql包装成统计总数SQL
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 统计总数SQL
	 */
	public String getCountString(String sql) {
		return "select count(1) from (" + sql + ") tmp_count";
	}

	/**
	 * 将SQL语句变成一条语句，并且每个单词的间隔都是1个空格
	 * 
	 * @param sql
	 * @return 转换后的sql
	 */
	protected String getLineSql(String sql) {
		return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
	}
}
