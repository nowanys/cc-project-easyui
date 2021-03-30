package com.cjhme.system.main.interceptor.mybatis.helper;

import java.util.HashMap;
import java.util.Map;

import com.cjhme.system.main.interceptor.mybatis.dialect.Dialect;
import com.cjhme.system.main.interceptor.mybatis.dialect.DialectFactory;

/**
 * 
 * <p>
 * Title: DialectHelper.java
 * </p>
 * Description: 方言帮助
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 * @created Sep 14, 2014 9:36:03 AM
 */
public abstract class DialectHelper {

	// 存在数据库方言
	private static Map<Dialect.Type, Dialect> MAPPERS = new HashMap<Dialect.Type, Dialect>();

	/**
	 * 获得方言
	 * 
	 * @param dialectType
	 * @return
	 */
	public static Dialect getDialect(Dialect.Type dialectType) {
		// 如果存在返回对应类型的方言对象，不存在生成并返回
		if (MAPPERS.containsKey(dialectType)) {
			return MAPPERS.get(dialectType);
		} else {
			Dialect dialect = DialectFactory.buildDialect(dialectType);
			MAPPERS.put(dialectType, dialect);
			return dialect;
		}
	}
}
