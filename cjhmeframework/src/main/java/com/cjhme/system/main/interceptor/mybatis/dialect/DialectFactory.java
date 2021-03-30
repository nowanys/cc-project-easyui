package com.cjhme.system.main.interceptor.mybatis.dialect;

/**
 * 
 * <p>
 * Title: DialectFactory.java
 * </p>
 * Description: 方言工厂
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 * @created Sep 14, 2014 9:30:44 AM
 */
public abstract class DialectFactory {

	/**
	 * 根据不同数据库生成不能的方言对象
	 * 
	 * @param dialectType
	 * @return
	 */
	public static Dialect buildDialect(Dialect.Type dialectType) {
		switch (dialectType) {
		case MYSQL:
			return new MySQLDialect();
		case ORACLE:
			return new OracleDialect();
		default:
			throw new UnsupportedOperationException();
		}
	}
}
