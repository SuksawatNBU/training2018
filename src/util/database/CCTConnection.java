package util.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import util.type.DatabaseType.DbType;

public class CCTConnection implements Serializable {

	private static final long serialVersionUID = 5243194996575243650L;

	private Connection conn;
	private DbType dbType;
	private Map<String, String> schemas = new HashMap<String, String>();

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Map<String, String> getSchemas() {
		return schemas;
	}

	public void setSchemas(Map<String, String> schemas) {
		this.schemas = schemas;
	}

	public Statement createStatement() throws SQLException {
		return conn.createStatement();
	}

	/**
	 * เพื่อใช้ในการ insert ข้อมูลง table และรับ pk ของ table นั้นๆ กลับมา
	 * @param sql
	 * @return PK ของ table นั้นๆ
	 * @throws Exception
	 */
	public int executeInsertStatement(String sql) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet keys = null;
		int id = 0;
		try {
			pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();

			keys = pstmt.getGeneratedKeys();
			if (keys.next()) {
				id = keys.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeResultSet(keys);
			CCTConnectionUtil.closePreparedStatement(pstmt);
		}
		return id;
	}

	public void commit() throws SQLException {
		conn.commit();
	}

	public void rollback() throws SQLException {
		conn.rollback();
	}

	public boolean getAutoCommit() throws SQLException {
		return conn.getAutoCommit();
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		conn.setAutoCommit(autoCommit);
	}

	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public DbType getDbType() {
		return dbType;
	}

	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}
}
