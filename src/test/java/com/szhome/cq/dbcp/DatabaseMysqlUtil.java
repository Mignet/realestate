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
 * [mysql]
 * @author Mignet
 */
@Component
@Transactional
@Scope("prototype")
public class DatabaseMysqlUtil {

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
		PreparedStatement pst = getPrepStatement(conn, "show index from "+tableName+" where Key_name = 'PRIMARY'");
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
		PreparedStatement pst = getPrepStatement(conn, "desc "+tableName+"");
		// 获取结果集
		ResultSet rs = null;
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			rs = pst.executeQuery();
			while(rs.next()) {
				String type = rs.getString("Type").toLowerCase();
				String name = rs.getString("Field").toLowerCase();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("table_name", tableName);
				m.put("column_name", name);
				m.put("data_type", type);
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
		PreparedStatement pst = getPrepStatement(conn, "desc "+tableName+"");
		// 获取结果集
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			while(rs.next()) {
				String type = rs.getString("Type").toLowerCase();
				String name = rs.getString("Field").toLowerCase();
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
