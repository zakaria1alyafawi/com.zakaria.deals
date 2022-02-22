package com.zakaria.deals.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
	private DbUtils() {

	}

	public static void close(Connection connection) {
		closeBase(connection);
	}

	public static void close(CallableStatement callable) {
		closeBase(callable);
	}

	public static void close(ResultSet rs) {
		closeBase(rs);
	}

	public static void close(PreparedStatement preState) {
		closeBase(preState);
	}

	public static void close(Statement statement) {
		closeBase(statement);
	}

	private static void closeBase(AutoCloseable obj) {
		if (obj == null) {
			return;
		}

		try {
			obj.close();
		} catch (Exception e) {
			// log
		}
	}

	public static boolean isClosed(Connection connection) {
		if (connection == null) {
			return true;
		}

		try {
			return connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public static boolean isClosed(CallableStatement callable) {
		if (callable == null) {
			return true;
		}

		try {
			return callable.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public static boolean isClosed(ResultSet resultSet) {
		if (resultSet == null) {
			return true;
		}

		try {
			return resultSet.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public static boolean isClosed(PreparedStatement prepareStatement) {
		if (prepareStatement == null) {
			return true;
		}

		try {
			return prepareStatement.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public static boolean isClosed(Statement statement) {
		if (statement == null) {
			return true;
		}

		try {
			return statement.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public static class Sql {
		public static final String SELECT = " SELECT ";

		public static final String FROM = " FROM ";

		public static final String WHERE = " WHERE ";
		public static final String ID_EQUAL = " ID=? ";

		public static final String AND = " AND ";
		public static final String OR = " OR ";

		public static final String ORDER_BY = " ORDER by ";

		public static final String BIL_CYCLE_ID_EQUAL = " BIL_CYCLE_ID=? ";
		public static final String BIL_CYCLE_RUN_ID_EQUAL = " BIL_CYCLE_RUN_ID=? ";
		public static final String BIL_CYCLE_STEP_ID_EQUAL = " BIL_CYCLE_STEP_ID=? ";

		public static String stepStatusIn(int countParams) {
			return " STEP_STATUS IN ( " + buildQuestions(countParams) + " ) ";
		}

		public static String buildQuestions(int num) {
			String result = null;

			StringBuilder sqlBuild = new StringBuilder();

			for (int i = 0; i < num; i++) {
				sqlBuild.append("?");

				if (i + 1 < num) {
					sqlBuild.append(", ");
				}
			}

			result = sqlBuild.toString();
			sqlBuild.delete(0, sqlBuild.length());
			return result;
		}
	}

	public static boolean isCurrentNull(ResultSet rs, String column) {
		try {
			return rs.wasNull();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isColumnExist(ResultSet rs, String column) {
		try {
			rs.findColumn(column);
			return true;
		} catch (SQLException sqlex) {
//	        logger.debug("column doesn't exist {}", column);
		}

		return false;
	}
}
