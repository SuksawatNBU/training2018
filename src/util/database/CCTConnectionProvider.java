package util.database;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.log.LogUtil;

import com.cct.trn.core.config.parameter.domain.Database;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;

public class CCTConnectionProvider {

	private static boolean INIT = false;
	private static Map<String, DataSource> ds = new LinkedHashMap<String, DataSource>();

	public CCTConnectionProvider() throws Exception {
		init();
	}

	private void init() throws Exception {
		if (INIT == false) {
			try {
				ds.clear();
				for (int i = 0; i < ParameterConfig.getParameter().getDatabase().length; i++) {
					Database db = ParameterConfig.getParameter().getDatabase()[i];

					Context context = new InitialContext();
					if (db.isJndi()) {
						context = (Context) context.lookup("java:comp/env");
					}

					DataSource ddss = (DataSource) context.lookup(db.getLookup());

					ds.put(db.getIndex(), ddss);
				}

				INIT = true;
			} catch (Exception e) {
				throw e;
			}
		}
	}

	/**
	 * ไม่ใช้ Lookup แล้ว ******<br>
	 * 
	 * @param conn
	 * @param lookup
	 * @return
	 * @throws Exception
	 */
	public CCTConnection getConnection(CCTConnection conn, String lookup) throws Exception {

		try {
			LogUtil.INITIAL.debug(conn);
			if (conn == null) {
				conn = new CCTConnection();
			}

			if ((conn.getConn() == null) || conn.getConn().isClosed()) {
				conn.setConn(ds.get(lookup).getConnection());
				
				conn.getSchemas().clear();
				conn.getSchemas().putAll(ParameterConfig.getParameter().getDatabase()[Integer.parseInt(lookup)].getSchemasMap());

				LogUtil.INITIAL.debug(ParameterConfig.getParameter().getDatabase()[Integer.parseInt(lookup)].getDatabaseTypeEnum());
				conn.setDbType(ParameterConfig.getParameter().getDatabase()[Integer.parseInt(lookup)].getDatabaseTypeEnum());
			}
		} catch (Exception e) {
			LogUtil.INITIAL.error(e);
			throw e;
		}

		LogUtil.INITIAL.debug(conn);
		return conn;
	}
}
