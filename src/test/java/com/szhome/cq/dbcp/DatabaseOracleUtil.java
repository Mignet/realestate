package com.szhome.cq.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 连接和使用数据库资源的工具类
 * [Oracle]
 * @author Mignet
 */
@Component
@Transactional
@Scope("prototype")
public class DatabaseOracleUtil {

	/**
	 * 数据源
	 */
	@Autowired
	private DataSource dataSource;


	/**
	 * 获取数据库连接
	 * 
	 * @return conn
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取执行SQL的工具
	 * 
	 * @param conn
	 *            数据库连接
	 * @param sql
	 *            SQL语句
	 * @return prepStmt
	 */
	public static PreparedStatement getPrepStatement(Connection conn, String sql) {
		PreparedStatement prepStmt = null;
		try {
			prepStmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prepStmt;
	}

	/**
	 * 关闭数据库资源
	 * 
	 * @param stmt
	 */
	public static void closeStatement(Statement stmt) {
		if (null != stmt) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭数据库资源
	 * 
	 * @param prepStmt
	 */
	public static void closePrepStatement(PreparedStatement prepStmt) {
		if (null != prepStmt) {
			try {
				prepStmt.close();
				prepStmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭数据库资源
	 * 
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Boolean setAutoCommit(Connection conn, boolean commitStatus) {
		if (conn == null) {
			return true;
		}
		try {
			boolean commit = conn.getAutoCommit();
			conn.setAutoCommit(commitStatus);
			return commit;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return true;
		}
	}

	public static boolean rollback(Connection conn, boolean oldCommitStatus) {
		if (conn == null) {
			return true;
		}
		try {
			conn.rollback(); // 事物回滚
			conn.setAutoCommit(oldCommitStatus);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public static boolean commit(Connection conn, boolean oldCommitStatus) {
		if (conn == null) {
			return true;
		}
		try {
			conn.commit(); // 事物回滚
			conn.setAutoCommit(oldCommitStatus);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public static int getLastId(PreparedStatement ps) {
		ResultSet rs = null;
		try {
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			closeResultSet(rs);
		}
		return -1;
	}

	/**
	 * 获取表的主键 
	 */
	public String getPk(String tableName) {
		Connection conn = getConnection();
		String keySql = "select   *   from    user_cons_columns  " +
				" where    constraint_name   =    (select    constraint_name   from    user_constraints  " +
				" where    table_name   =    upper('"+tableName+"')  and    constraint_type   ='P')";
		/*//select   *   from    user_cons_columns   
		  where    constraint_name   =    (select    constraint_name   from    user_constraints   
	              where    table_name   =    upper('表名')  and    constraint_type   ='P');*/
		PreparedStatement pst = getPrepStatement(conn, keySql);
		// 获取结果集
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			while(rs.next()) {
				String name = rs.getString("Column_name").toLowerCase();
					return name;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库资源
			closeResultSet(rs);
			closePrepStatement(pst);
			closeConnection(conn);
		}
		return "";
	}
	/**
	 * 获取表的列
	 */
	public List<Map<String, Object>> getColumnList(String tableName) {
		Connection conn = getConnection();
		String colsql = "select c.*," +
				"(select comments from  user_col_comments where table_name = c.table_name" +
				" and COLUMN_NAME = c.COLUMN_NAME) comments" +
				" from user_tab_columns c " +
				"where table_name = upper('"+tableName+"')";
		PreparedStatement pst = getPrepStatement(conn, colsql);
		// 获取结果集
		ResultSet rs = null;
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			rs = pst.executeQuery();
			while(rs.next()) {
				String type = rs.getString("data_type").toLowerCase();
				String name = rs.getString("column_name").toLowerCase();
				String comments = rs.getString("comments").toLowerCase();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("table_name", tableName);
				m.put("column_name", name);
				m.put("data_type", type);
				m.put("comment", comments);
				result.add(m);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库资源
			closeResultSet(rs);
			closePrepStatement(pst);
			closeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 获取表的列-testcase
	 */
	public void getColumnsByTablename(String tableName) {
		Connection conn = getConnection();
		String colsql = "select * from user_tab_columns where table_name = upper('"+tableName+"')";
		PreparedStatement pst = getPrepStatement(conn, colsql);
		// 获取结果集
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			while(rs.next()) {
				String type = rs.getString("data_type").toLowerCase();
				String name = rs.getString("column_name").toLowerCase();
				System.out.println(name+"\t"+type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库资源
			closeResultSet(rs);
			closePrepStatement(pst);
			closeConnection(conn);
		}
	}
}
