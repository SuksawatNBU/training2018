package com.cct.trn.core.config.parameter.service;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.image.BrowseUploadServiceUtil;
import util.log.LogUtil;

import com.cct.trn.core.config.parameter.domain.Database;
import com.cct.trn.core.config.parameter.domain.Parameter;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;

public class ParameterManager {

	public static final String XML_PATH = System.getProperty("user.dir") + "/ucp/WEB-INF/parameter.xml";

	private ParameterService service = null;

	public ParameterManager() {
		this.service = new ParameterService();
	}

	public void create() throws Exception {
		try {
			service.create(XML_PATH);
		} catch (Exception e) {
			throw e;
		}
	}

	public void load() throws Exception {
		try {
			load(XML_PATH);
		} catch (Exception e) {
			throw e;
		}
	}

	public void load(String xmlPath) throws Exception {

		try {
			Parameter parameter = service.load(xmlPath);
			ParameterConfig.setParameter(parameter);
			ParameterConfig.setMapContenType(BrowseUploadServiceUtil.loadMapContentType());
		} catch (Exception e) {
			throw e;
		}
	}

	public void loadBg(String xmlPath) throws Exception {

		try {
			Parameter parameter = service.load(xmlPath);
			ParameterConfig.setParameter(parameter);
		} catch (Exception e) {
			throw e;
		}
	}

	public void testDBConnection(Database[] dbConfig) {
		LogUtil.INITIAL.debug("DB Connection test...");
		for (Database database : dbConfig) {
			CCTConnection conn = null;
			try {
				conn = new CCTConnectionProvider().getConnection(conn, database.getIndex());
				LogUtil.INITIAL.debug(database.getIndex() + " > " + database.getLookup() + " > is ok.");
			} catch (Exception e) {
				LogUtil.INITIAL.error(database.getIndex() + " > " + database.getLookup() + " > is error.");
				LogUtil.INITIAL.error("", e);
			} finally {
				CCTConnectionUtil.close(conn);
			}
		}
	}

	public static void main(String[] args) {
		ParameterManager m = new ParameterManager();
		try {
			m.create();
			m.load();
			m.testDBConnection(ParameterConfig.getParameter().getDatabase());
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}
}
