package com.aowin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Connect to database
 * @author Peter
 *
 */
public class DBUtil {
	private DBUtil() {}
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		InitialContext ctx = null;
		Connection conn = tl.get();
		if (conn == null || conn.isClosed()) {
			try {
				ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
				conn = ds.getConnection();
				//System.out.println(conn);
				tl.set(conn);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void close(PreparedStatement pstat, ResultSet rs) {
		try {
			if (pstat != null) {
				pstat.close();
			} if (rs != null) {
				rs.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
