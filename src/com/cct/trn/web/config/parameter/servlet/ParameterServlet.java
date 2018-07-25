package com.cct.trn.web.config.parameter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;

import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.config.parameter.service.ParameterManager;
import com.cct.trn.core.selectitem.service.SelectItemManager;

public class ParameterServlet extends HttpServlet {

	private static final long serialVersionUID = -1326931967347560900L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogUtil.INITIAL.info("");
		try {
			init();
		} catch (Exception e) {
			LogUtil.INITIAL.error("", e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogUtil.INITIAL.info("");
		try {
			init();
		} catch (Exception e) {
			LogUtil.INITIAL.error("", e);
		}
	}

	public void init() throws ServletException {

		ParameterManager parameterManager = new ParameterManager();

		try {
			String parameterFile = getServletContext().getRealPath(getInitParameter("parameterfile"));
			LogUtil.INITIAL.debug("Parameter path :- " + parameterFile);

			parameterManager.load(parameterFile);

			LogUtil.INITIAL.debug("TmpPath: " + ParameterConfig.getParameter().getAttachFile().getTmpPath());

			LogUtil.INITIAL.debug("Load parameter completed.");

//			MailConfigurationManager mailConfigManager = new MailConfigurationManager();
//			MailConfiguration mConfig = new MailConfiguration();
//			mailConfigManager.init(mConfig,ParameterConfig.getParameter().getApplication().getMailConfigPath());

		} catch (Exception e) {
			LogUtil.INITIAL.error("Can't load paramter!!!");
			LogUtil.INITIAL.error("", e);
		}

		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			LogUtil.INITIAL.debug(conn);

			// combo ต่างๆ
			LogUtil.INITIAL.debug("initial Select Item");
			SelectItemManager selectItemManager = new SelectItemManager(conn, null, null);
			selectItemManager.init();
			LogUtil.INITIAL.debug("initial Select Item completed.");
			
		} catch (Exception e) {
			LogUtil.INITIAL.error("", e);
		} finally {
			CCTConnectionUtil.close(conn);
		}

	}
}
