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
 * ���Ӻ�ʹ�����ݿ���Դ�Ĺ�����
 * [Oracle]
 * @author Mignet
 */
@Component
@Transactional
@Scope("prototype")
public class DatabaseOracleUtil {

	/**
	 * ����Դ
	 */
	@Autowired
	private DataSource dataSource;


	/**
	 * ��ȡ���ݿ�����
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
	 * �ر����ݿ�����
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
	 * ��ȡִ��SQL�Ĺ���
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param sql
	 *            SQL���
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
	 * �ر����ݿ���Դ
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
	 * �ر����ݿ���Դ
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
	 * �ر����ݿ���Դ
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
			conn.rollback(); // ����ع�
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
			conn.commit(); // ����ع�
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
	 * ��ȡ������� 
	 */
	public String getPk(String tableName) {
		Connection conn = getConnection();
		String keySql = "select   *   from    user_cons_columns  " +
				" where    constraint_name   =    (select    constraint_name   from    user_constraints  " +
				" where    table_name   =    upper('"+tableName+"')  and    constraint_type   ='P')";
		/*//select   *   from    user_cons_columns   
		  where    constraint_name   =    (select    constraint_name   from    user_constraints   
	              where    table_name   =    upper('����')  and    constraint_type   ='P');*/
		PreparedStatement pst = getPrepStatement(conn, keySql);
		// ��ȡ�����
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
			// �ر����ݿ���Դ
			closeResultSet(rs);
			closePrepStatement(pst);
			closeConnection(conn);
		}
		return "";
	}
	/**
	 * ��ȡ�����
	 */
	public List<Map<String, Object>> getColumnList(String tableName) {
		Connection conn = getConnection();
		String colsql = "select c.*," +
				"(select comments from  user_col_comments where table_name = c.table_name" +
				" and COLUMN_NAME = c.COLUMN_NAME) comments" +
				" from user_tab_columns c " +
				"where table_name = upper('"+tableName+"')";
		PreparedStatement pst = getPrepStatement(conn, colsql);
		// ��ȡ�����
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
			// �ر����ݿ���Դ
			closeResultSet(rs);
			closePrepStatement(pst);
			closeConnection(conn);
		}
		return result;
	}
	
	/**
	 * ��ȡ�����-testcase
	 */
	public void getColumnsByTablename(String tableName) {
		Connection conn = getConnection();
		String colsql = "select * from user_tab_columns where table_name = upper('"+tableName+"')";
		PreparedStatement pst = getPrepStatement(conn, colsql);
		// ��ȡ�����
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
			// �ر����ݿ���Դ
			closeResultSet(rs);
			closePrepStatement(pst);
			closeConnection(conn);
		}
	}
}
