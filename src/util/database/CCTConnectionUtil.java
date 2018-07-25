package util.database;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CCTConnectionUtil {

	public static void close(CCTConnection conn) {

		if (conn != null) {

			try {
				if (conn.getConn() != null) {
					conn.close();
				}
			} catch (Exception e) {
			}

			try {
				if (conn.getSchemas() != null) {
					conn.getSchemas().clear();
				}
			} catch (Exception e) {
			}
		}

	}

	public static void closeAll(ResultSet rst, Statement stmt) {
		closeResultSet(rst);
		closeStatement(stmt);
	}

	public static void closeResultSet(ResultSet rst) {
		try {
			if (rst != null) {
				rst.close();
			}
		} catch (Exception e) {
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
		}
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
		}
	}
	
	public static void closeCallableStatement(CallableStatement cstmt) {
		try {
			if (cstmt != null) {
				cstmt.close();
			}
		} catch (Exception e) {
		}
	}
}
