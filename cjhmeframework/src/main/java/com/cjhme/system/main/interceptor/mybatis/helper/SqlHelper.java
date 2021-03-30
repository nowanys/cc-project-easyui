package com.cjhme.system.main.interceptor.mybatis.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import com.cjhme.system.main.interceptor.mybatis.dialect.Dialect;
/**
 * 
 * <p>
 * Title: SqlHelper.java
 * </p>
 * Description: sql帮助
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 * @created Sep 14, 2014 9:38:23 AM
 */
public abstract class SqlHelper {

	/**
	 * 获得总条数
	 * @param ms
	 * @param connection
	 * @param parameterObject
	 * @param dialect
	 * @return
	 * @throws SQLException
	 */
	public static int getCount(final MappedStatement ms, final Connection connection, final Object parameterObject, Dialect dialect) throws SQLException {
		BoundSql boundSql = ms.getBoundSql(parameterObject);
		String countSql = dialect.getCountString(boundSql.getSql());

		PreparedStatement stmt = null;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(countSql);
			DefaultParameterHandler handler = new DefaultParameterHandler(ms, parameterObject, boundSql);
			handler.setParameters(stmt);
			rs = stmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			return count;
		} finally {
			closeStatement(stmt);
		}
	}

	/**
	 * 关闭closeStatement
	 * @param statement
	 */
	private static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}